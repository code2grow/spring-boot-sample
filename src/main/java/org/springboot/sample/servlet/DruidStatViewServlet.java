package org.springboot.sample.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * StatViewServlet
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年3月17日
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", 
	initParams={
			@WebInitParam(name="allow",value="192.168.16.110,127.0.0.1"),// IP白名单
			@WebInitParam(name="loginUsername",value="shanhy"),// 用户名
			@WebInitParam(name="loginPassword",value="shanhypwd")// 密码
	})
public class DruidStatViewServlet extends StatViewServlet {

	
}
