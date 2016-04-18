package org.springboot.sample.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * JSON请求响应错误消息处理
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年4月17日
 */
public class JsonErrorMsgInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 因为MappingJackson2JsonView默认会把BindingResult全部过滤掉。
		// 所以我们要想将错误消息输出，要在这里自己处理好。
		Map<String, Object> modelMap = modelAndView.getModel();
		Map<String, String> errorMsg = new HashMap<>();
		modelMap.put("errorMsg", errorMsg);
		if (modelMap != null) {
			for (Entry<String, Object> entry : modelMap.entrySet()) {
				if (entry.getValue() instanceof BindingResult) {
					BindingResult bindingResult = (BindingResult) entry.getValue();
					if (bindingResult.hasErrors()) {
						for (FieldError fieldError : bindingResult.getFieldErrors()) {
							errorMsg.put(fieldError.getObjectName() + "." + fieldError.getField(),
									fieldError.getDefaultMessage());
						}
					}
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
