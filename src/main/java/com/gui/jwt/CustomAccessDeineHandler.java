package com.gui.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CustomAccessDeineHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// response.setCharacterEncoding("utf-8");
		// response.setContentType("text/javascript;charset=utf-8");
		// response.getWriter().print(JSONObject.toJSONString(RestMsg.error("没有访问权限!")));
		log.info("用户访问无权限："+request.getRequestURL());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
//	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//	        String reason = "统一处理，原因："+authException.getMessage();
//	        response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
		response.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_NO_ACCESS, false)));
	}
}
