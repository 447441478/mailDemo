package cn.hncu.active.dao;

import java.sql.SQLException;
import java.util.Map;

public interface ActiveDAO {
	boolean active(Map<String, Object> map) throws SQLException;
}
