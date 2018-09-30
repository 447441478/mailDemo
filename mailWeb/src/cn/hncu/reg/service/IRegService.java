package cn.hncu.reg.service;

import java.sql.SQLException;

import cn.hncu.domain.User;

public interface IRegService {
	User reg(User user) throws SQLException;
}
