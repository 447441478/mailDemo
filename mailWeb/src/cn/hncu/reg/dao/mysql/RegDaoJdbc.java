package cn.hncu.reg.dao.mysql;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import cn.hncu.domain.User;
import cn.hncu.pub.C3p0Utils;
import cn.hncu.pub.IDGenerator;
import cn.hncu.reg.dao.RegDAO;

public class RegDaoJdbc implements RegDAO{

	@Override
	public User reg(User user) throws SQLException {
		//预防空指针
		if( user == null ) return null;
		
		//组织sql语句
		String sql = "insert into user(id,name,pwd,email,acode) values(?,?,?,?,?)";
		
		//补全id 和 acode
		String id = IDGenerator.getUUID();
		String acode = IDGenerator.getUUID();
		
		//获取 QueryRunner 实例
		DataSource ds = C3p0Utils.getDataSource();
		QueryRunner qr = new QueryRunner( ds );
		
		int i = qr.update(sql,id,user.getName(),user.getPwd(),user.getEmail(),acode);
		if ( i > 0 ) {
			//能到这里说明插入成功,补全user信息
			user.setId(id);
			user.setAcode(acode);
		}
		return user;
	}
}
