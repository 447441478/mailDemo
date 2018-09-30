package cn.hncu.reg.service;

import java.sql.SQLException;

import cn.hncu.domain.User;
import cn.hncu.reg.dao.RegDAO;
import cn.hncu.reg.dao.mysql.RegDaoJdbc;

public class RegService implements IRegService{

	//注入dao
	private RegDAO dao = new RegDaoJdbc();
	
	@Override
	public User reg(User user) throws SQLException {
		return dao.reg(user);
	}

}
