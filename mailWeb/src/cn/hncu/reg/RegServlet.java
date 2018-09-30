package cn.hncu.reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.hncu.domain.User;
import cn.hncu.reg.service.IRegService;
import cn.hncu.reg.service.RegService;

public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger log = Logger.getLogger(RegServlet.class);
	
	//注入service
	private IRegService service = new RegService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect( request.getHeader("Referer") );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1收集参数 id(补),name,pwd,email,acode(补)
		//按理应该校验，这里都略了
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		//2 组织参数
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setEmail(email);
		PrintWriter out = response.getWriter();
		try {
			//3 调用service层
			user = service.reg(user);
			if( user.getId() != null ) {
				//当用户信息插入成功后会补全信息，所以 id 不为null 时说明插入成功，向用户发送激活邮箱
				//因为发送邮件是比较耗时的，所以另开一个线程进行发送邮件。
				new SendMailThread(user).start();
				//4 导向结果页面
				out.println("用户注册成功，请接收激活邮箱并激活账号。如果没有收到请稍等...");
			}else {
				out.println("用户注册失败，请重新注册。");
			}
		} catch (SQLException e) {
			//TODO:记录日志
			log.error(e);
			e.printStackTrace();
			out.println("服务器繁忙，请稍后再试。");
		}
	}

}
