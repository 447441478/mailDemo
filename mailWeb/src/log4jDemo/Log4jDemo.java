package log4jDemo;

import org.apache.log4j.Logger;

public class Log4jDemo {
	//添加日志对象，一般以下面方式获取
	private Logger log = Logger.getLogger(Log4jDemo.class);
	
	public static void main(String[] args) {
		new Log4jDemo().aa();
	}
	
	public void aa(){
		/*把我们原来使用System.out.println(info)方式
		 * 输出的信息根据级别选择下面的一个方法来进行输出
		 */
		//输出日志
		log.debug("这是debug信息....");
		log.info("这是info信息....");
		log.warn("这是warn信息....");
		log.error("这是error信息....");
		log.fatal("这是fatal信息....");
	}
	
}
