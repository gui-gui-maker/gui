package com.gui.exception;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gui.jwt.ResultEnum;
import com.gui.jwt.ResultVO;


@RestControllerAdvice
public class GlobalRestExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	public Object exception(HttpServletRequest reqest, HttpServletResponse response, HandlerMethod handlerMethod,
			Exception exception) {

		Method m = handlerMethod.getMethod();
		Class<?> clazz = handlerMethod.getBeanType();
		// 判断访问的url是否为rest接口请求
		boolean isRestReq = (m.getAnnotation(ResponseBody.class) != null
				|| clazz.getAnnotation(ResponseBody.class) != null
				|| clazz.getAnnotation(RestController.class) != null);
		// 判断访问的url是否为ajax请求
		boolean isAjax = isAjax(reqest);
		if (isAjax || isRestReq) {
			logger.error("global exception", exception);
			if (exception instanceof VeException) {
				return ResultVO.result(ResultEnum.UNKNOWN_EXCEPTION, ((VeException)exception).getMessage());
			}
			return ResultVO.result(ResultEnum.UNKNOWN_EXCEPTION, false);
		} else {
			logger.error("界面请求异常", exception);
			ModelAndView mv = new ModelAndView();
			if (exception instanceof VeException) {
				mv.addObject("errorMessage", exception.getMessage());
				mv.setViewName("error");
				return mv;
			}
			mv.addObject("errorMessage", "");
			mv.setViewName("error");
			return mv;
		}
	}

	/**
	 * 判断网络请求是否为ajax
	 *
	 * @param req
	 * @return
	 */
	private boolean isAjax(HttpServletRequest req) {
		String contentTypeHeader = req.getHeader("Content-Type");
		String acceptHeader = req.getHeader("Accept");
		String xRequestedWith = req.getHeader("X-Requested-With");
		return (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
				|| (acceptHeader != null && acceptHeader.contains("application/json"))
				|| "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
	}
}
