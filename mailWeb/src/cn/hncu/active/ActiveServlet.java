package cn.hncu.active;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.hncu.active.service.ActiveService;
import cn.hncu.active.service.IActiveService;

public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private Logger log = Logger.getLogger( ActiveServlet.class );
	
	//注入service
	private IActiveService service = new ActiveService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 收集参数
		//按理应该进行格式校验，这里略了。
		String id = request.getParameter("id");
		String acode = request.getParameter("acode");
		// 组织参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("acode", acode);
		
		try {
			// 调用service
			boolean boo = service.active(map);
			
			//导向结果页面
			request.setAttribute("boo", boo);
			request.getRequestDispatcher("/jsps/result.jsp").forward(request, response);
		} catch (SQLException e) {
			//TODO: 记入日志
			log.error(e);
			e.printStackTrace();
			response.getWriter().println("系统繁忙，请稍后再试...");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
