package cn.hncu.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

public class MailDemo {
	//演示发送纯文本邮件---不带附件
	@Test
	public void demo1() throws Exception {
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", "SMTP"); //设置邮件发送协议
		p.setProperty("mail.smtp.host", "smtp.163.com"); //设置邮件服务器
		p.setProperty("mail.smtp.port", "25"); //设置发送邮件的端口号，默认 25
		p.setProperty("mail.smtp.auth", "true"); // 登录认证为true
		p.setProperty("mail.smtp.timeout","1000"); //设置超时时间
		//技术入口：
		Session mailSession = Session.getDefaultInstance(p, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String username = "xxx@163.com"; //邮箱账号 xxx@163.com
				String password = "xxx"; //密码
				return new PasswordAuthentication( username, password );
			}
		});
		mailSession.setDebug(true); //开启debug模式
		
		//////////////下面开始进行邮件信息的封装//////////////
		//1 创建邮件对象，并且设置 由 mailSession 用户进行发送邮件
		Message message = new MimeMessage(mailSession);
		//2 设置邮件发送者
		message.setFrom( new InternetAddress("xxx@163.com") );
		//3 设置邮件接收者
		message.setRecipient( RecipientType.TO, new InternetAddress("xxx@qq.com"));
		//4 设置邮件主题
		message.setSubject("测试一下");
		//5 设置邮件内容
		message.setContent("Hello world! 你好！","text/html;charset=utf-8");
		
		//发送!!!
		Transport.send(message);
	}
	//演示发带附件的邮件
	@Test
	public void demo2() throws Exception {
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", "SMTP"); //设置邮件发送协议
		p.setProperty("mail.smtp.host", "smtp.163.com"); //设置邮件服务器
		p.setProperty("mail.smtp.port", "25"); //设置发送邮件的端口号，默认 25
		p.setProperty("mail.smtp.auth", "true"); // 登录认证为true
		p.setProperty("mail.smtp.timeout","1000"); //设置超时时间
		//技术入口：
		Session mailSession = Session.getDefaultInstance(p, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String username = "xxx@163.com"; //邮箱账号 xxx@163.com
				String password = "xxx"; //密码
				return new PasswordAuthentication( username, password );
			}
		});
		mailSession.setDebug(true); //开启debug模式
		
		//////////////下面开始进行邮件信息的封装//////////////
		//1 创建邮件对象，并且设置 由 mailSession 用户进行发送邮件
		Message message = new MimeMessage(mailSession); 
		//2 设置邮件发送者
		message.setFrom( new InternetAddress("xxx@163.com") );
		//3 设置邮件接收者
		message.setRecipient( RecipientType.TO, new InternetAddress("xxx@qq.com"));
		//4 设置邮件主题
		message.setSubject("福利");
		///////////////////下面是开始变化的部分///////////////////////
//		//5 设置邮件内容
//		message.setContent("Hello world! 你好！","text/html;charset=utf-8");
		
		//第一部分
		MimeBodyPart bodyPart1 = new MimeBodyPart();
		bodyPart1.setContent("<h1>福利</h1>","text/html;charset=utf-8");
		
		//第二部分
		MimeBodyPart bodyPart2 = new MimeBodyPart();
		bodyPart2.setDataHandler( new DataHandler( new FileDataSource("./imgs/1.jpg" ) ) );
		bodyPart2.setFileName(MimeUtility.encodeText("美女1.gif"));
		
		//第三部分
		MimeBodyPart bodyPart3 = new MimeBodyPart();
		bodyPart3.setDataHandler( new DataHandler( new FileDataSource("./imgs/2.jpg" ) ) );
		bodyPart3.setFileName(MimeUtility.encodeText("美女2.gif"));
		
		//按顺序加入到  Multipart 中  
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(bodyPart1);
		mp.addBodyPart(bodyPart2);
		mp.addBodyPart(bodyPart3);
		
		//把mp设置为 message 的内容
		message.setContent(mp); //技术入口
		
		/////////////////////////////////////////////////////
		//发送!!!
		Transport.send(message);
	}
}
