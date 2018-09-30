package cn.hncu.active.service;

import java.sql.SQLException;
import java.util.Map;

import cn.hncu.active.dao.ActiveDAO;
import cn.hncu.active.dao.mysql.ActiveDaoJdbc;

public class ActiveService implements IActiveService{
	//注入dao
	private ActiveDAO dao = new ActiveDaoJdbc();

	@Override
	public boolean active(Map<String, Object> map) throws SQLException {
		return dao.active(map);
	}

}
