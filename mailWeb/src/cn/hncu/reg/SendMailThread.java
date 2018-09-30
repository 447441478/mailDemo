package cn.hncu.reg;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import cn.hncu.domain.User;

/**
 * &emsp;&emsp;发送邮箱的线程
 * <br/><br/><b>CreateTime:</b><br/>&emsp;&emsp;&emsp; 2018年9月30日 上午12:26:08	
 * @author 宋进宇&emsp;<a href='mailto:447441478@qq.com'>447441478@qq.com</a>
 */
public class SendMailThread extends Thread{
	private User user;
	
	private Logger log = Logger.getLogger(SendMailThread.class);
	
	protected SendMailThread(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", "SMTP"); //设置邮件发送协议
		p.setProperty("mail.smtp.host", "smtp.163.com"); //设置邮件服务器
		p.setProperty("mail.smtp.port", "25"); //设置发送邮件的端口号，默认 25
		p.setProperty("mail.smtp.auth", "true"); // 登录认证为true
		p.setProperty("mail.smtp.timeout","2000"); //设置超时时间
		
		//技术入口：
		Session session = Session.getDefaultInstance(p,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xxx@163.com", "xxx");
			}
		});
		
		//设置消息内容
		Message message = new MimeMessage(session);
		try {
			//设置发送者
			message.setFrom( new InternetAddress("xxx@163.com") );
			//设置接收者
			message.setRecipient( RecipientType.TO, new InternetAddress( ""+user.getEmail() ) );
			//设置主题
			message.setSubject("HNCU,欢迎您注册");
			//设置信息内容
			String msg="点击：<a href='http://localhost:8080/mailWeb/ActiveServlet?id="+user.getId()+"&acode="+user.getAcode()+"'>激活</a><br/>";
			msg += "如果点击无效，请复制下面地址在浏览器中访问：<br/>";
			msg += "&emsp;&emsp;http://localhost:8080/mailWeb/ActiveServlet?id="+user.getId()+"&acode="+user.getAcode();
			message.setContent(msg, "text/html;charset=utf-8");
			
			//发送
			Transport.send(message);
			log.info("发送邮件成功，接收者id:"+user.getId());
		} catch ( Exception e) {
			//TODO:记录日记
			log.error(e);
			e.printStackTrace();
		}
		
	}

}
