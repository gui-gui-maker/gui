package com.gui.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.gui.jwt.ResultEnum;
import com.gui.jwt.ResultVO;
@RestController
public class GlobalErrorController implements ErrorController {

	private final String PATH = "/error";
    @Autowired
    private ErrorAttributes errorAttributes;
 
    @Override
    public String getErrorPath() {
        return PATH;
    }
 
    @RequestMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> handleError(HttpServletRequest request) {
        Map<String, Object> attributesMap = getErrorAttributes(request, true);
        return ResultVO.result(ResultEnum.UNKNOWN_EXCEPTION, attributesMap.get("message").toString());
        //new Response.Builder().setStatus(500).setMessage(attributesMap.get("message").toString()).build();
    }
 
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

}
