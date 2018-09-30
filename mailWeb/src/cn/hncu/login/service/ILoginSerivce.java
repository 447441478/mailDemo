package cn.hncu.login.service;

import java.sql.SQLException;

import cn.hncu.domain.User;

public interface ILoginSerivce {
	User login(User user) throws SQLException;
}
