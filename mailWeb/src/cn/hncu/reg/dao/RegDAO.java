package cn.hncu.reg.dao;

import java.sql.SQLException;

import cn.hncu.domain.User;

public interface RegDAO {
	User reg(User user) throws SQLException;
}
