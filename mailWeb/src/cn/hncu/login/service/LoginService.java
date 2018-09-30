package cn.hncu.login.service;

import java.sql.SQLException;

import cn.hncu.domain.User;
import cn.hncu.login.dao.LoginDAO;
import cn.hncu.login.dao.mysql.LoginDaoJdbc;

public class LoginService implements ILoginSerivce {
	//注入dao
	private LoginDAO dao = new LoginDaoJdbc();
	
	@Override
	public User login(User user) throws SQLException {
		return dao.login(user);
	}

}
