package cn.hncu.login.dao;

import java.sql.SQLException;

import cn.hncu.domain.User;

public interface LoginDAO {
	User login(User user) throws SQLException;
}
