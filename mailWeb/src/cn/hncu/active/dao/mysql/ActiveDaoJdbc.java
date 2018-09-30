package cn.hncu.active.dao.mysql;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import cn.hncu.active.dao.ActiveDAO;
import cn.hncu.pub.C3p0Utils;

public class ActiveDaoJdbc implements ActiveDAO{

	
	@Override
	public boolean active(Map<String, Object> map) throws SQLException {
		//预防空指针
		if( map == null ) return false;
		
		//组织sql语句
		String sql = "update user set acode=null where id=? and acode=?";
		
		//获取QueryRunner 实例
		QueryRunner qr = new QueryRunner( C3p0Utils.getDataSource() );
		
		int i = qr.update( sql, map.get("id"), map.get("acode") );
		if( i > 0) {
			return true;
		}
		return false;
	}

}
