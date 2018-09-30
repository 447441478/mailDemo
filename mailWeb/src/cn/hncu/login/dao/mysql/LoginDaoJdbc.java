package cn.hncu.login.dao.mysql;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.hncu.domain.User;
import cn.hncu.login.dao.LoginDAO;
import cn.hncu.pub.C3p0Utils;

public class LoginDaoJdbc implements LoginDAO {

	@Override
	public User login(User user) throws SQLException {
		//预防空指针
		if( user == null ) return null;
		//组织sql 语句
		String sql = "select * from user where name=? and acode is null";
		
		QueryRunner qr = new QueryRunner( C3p0Utils.getDataSource() );
		User res = qr.query(sql, new BeanHandler<User>(User.class),user.getName());
		if( res != null ) {
			//密码相等才返回查询结果
			if(res.getPwd() != null && res.getPwd().equals( user.getPwd()) ) {
				return res;
			}else {
				//否则返回原对象
				return user;
			}
		}
		return null;
	}

}
