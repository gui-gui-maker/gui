package com.gui.jwt.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.alibaba.fastjson.JSON;
import com.gui.jwt.JwtTokenUtils;
import com.gui.jwt.ResultEnum;
import com.gui.jwt.ResultVO;

public class JwtPreAuthFilter extends BasicAuthenticationFilter{
	
	public JwtPreAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
 
    /**
     * description: 从request的header部分读取Token
     *
     * @param request
     * @param response
     * @param chain
     * @return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        System.out.println("BasicAuthenticationFilters");
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        System.out.println("tokenHeader:"+tokenHeader);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
        	//System.out.println("放行了："+request.getRequestURI());
            chain.doFilter(request, response);
            return;
        }
        try {
			
        	// 如果请求头中有token，则进行解析，并且设置认证信息
        	SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        	super.doFilterInternal(request, response, chain);
		} catch (Exception e) {
			e.printStackTrace();
			//令牌超时等
			response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json; charset=utf-8");
	        response.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.LOGIN_IS_OVERDUE,e.getMessage())));
		}
    }
 
    /**
     * description: 读取Token信息，创建UsernamePasswordAuthenticationToken对象
     *
     * @param tokenHeader
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        //解析Token时将“Bearer ”前缀去掉
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        List<String> roles = JwtTokenUtils.getUserRole(token);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles!=null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;
    }


}
