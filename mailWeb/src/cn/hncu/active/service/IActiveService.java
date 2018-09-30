package cn.hncu.active.service;

import java.sql.SQLException;
import java.util.Map;

public interface IActiveService {
	boolean active(Map<String, Object> map) throws SQLException;
}
