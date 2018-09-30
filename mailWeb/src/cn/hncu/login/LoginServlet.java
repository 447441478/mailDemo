package cn.hncu.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.hncu.domain.User;
import cn.hncu.login.service.ILoginSerivce;
import cn.hncu.login.service.LoginService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Logger log = Logger.getLogger(LoginServlet.class);
	
	//注入service
	private ILoginSerivce service = new LoginService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//收集参数
		//按理应该进行格式校验，这里略了。
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//组织参数
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		//调用service
		try {
			user = service.login(user);
			String error = "";
			if( user == null ) {
				error = "用户名不存在";
			}else {
				if( user.getId() == null ) {
					error = "密码错误";
				}else {
					//登录成功
					request.getSession().setAttribute("user", user);
					error = null;
				}
			}
			request.setAttribute("error", error);
			request.getRequestDispatcher("/").forward(request, response);
		} catch (SQLException e) {
			//TODO: 记入日志
			log.error(e);
			e.printStackTrace();
			response.getWriter().println("服务器繁忙，请稍后再试...");
		}
	}

}
