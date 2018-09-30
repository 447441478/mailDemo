package cn.hncu.pub;

import java.util.UUID;

/**
 * &emsp;&emsp;生成id的工具
 * <br/><br/><b>CreateTime:</b><br/>&emsp;&emsp;&emsp; 2018年9月30日 上午12:18:19	
 * @author 宋进宇&emsp;<a href='mailto:447441478@qq.com'>447441478@qq.com</a>
 */
public class IDGenerator {
	private IDGenerator() {
	}
	/**
	 * 获取一个 UUID
	 * @return UUID字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
