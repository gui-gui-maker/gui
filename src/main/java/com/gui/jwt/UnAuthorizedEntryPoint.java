package com.gui.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UnAuthorizedEntryPoint implements AuthenticationEntryPoint {
	

	@Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
 
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        log.info("匿名访问无权限："+request.getRequestURL());
//      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//      String reason = "统一处理，原因："+authException.getMessage();
//      response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
        response.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_NEED_AUTHORITIES,false)));
    }

}
