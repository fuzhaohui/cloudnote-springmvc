/**
 * Copyright (c) 2012-2013 Guangzhou LianYi Information Technology Co,.Ltd. All rights reserved.  
 * SHINE'S PROJECT 2013-1-9
 * 
 * 相关描述： 
 * 
 */
package utils;

import com.ces.cloud.note.base.util.Blowfish;

/**
 * 相关描述：
 *
 * 文件名：BlowfishTest.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-9 上午10:51:27 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-9 上午10:51:27 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
public class BlowfishTest {

	/**
	 * 方法描述：
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-9 上午10:51:27
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-9 上午10:51:27
	 * 维护原因: 
	 * 当前版本： v3.0 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "$ cat /proc/meminfo total: used: free: shared: buffers: cached:  Mem: 1057009664 851668992 205340672 0 67616768 367820800 "
		+ "Swap: 2146787328 164429824 1982357504 MemTotal: 1032236 kB MemFree: 200528 kB  MemShared: 0 kB "
		+"这样可以用截取字符串的方法，来得到linux内存信息."
		+"方法2在文件\"/proc/stat\"里面就包含了CPU的信息。每一个CPU的每一tick用在什么地方都在这个文件里面记着。后面的数字含义分别是： user、nice、sys、idle、iowait。有些版本的kernel没有iowait这一项。这些数值表示从开机到现在，CPU的每tick用在了哪里。例如： "
		+"cpu0 256279030 0 11832528 1637168262 "
		+"就是cpu0从开机到现在有 256279030 tick用在了user消耗，11832528用在了sys消耗。所以如果想计算单位时间（例如1s）里面CPU的负载，那只需要计算1秒前后数值的差除以每一秒的tick数量就可以了。"
		+"ok这样还剩下cpu温度，怎么做呢"
		+"发现了一个文件\"cat /proc/acpi/thermal_zone/THM/temperature\";可以返回本机的linux温度，"
		+"大概是这样的：temperature：            68C"
		+"但不是每台linux机器都有这个THM你要确定你的linux加载了这个THM才能使用这个文件，这样就用InputStreamReader(new FileInputStream（new File(\"/proc/acpi/thermal_zone/THM/temperature\")）,去读取这个文件，后面的相信大家一定会做了吧，就是把内容读出来,然后分割字符串去得到这个68。ok,系统基本信息全部完成，然后ok现在就只有一件事就是用Ajax去调用这个类来得到 基本信息，然后返回到页面上，Ajax的用法就不赘言了。"
		+"下面是系统监控的效果，大概是Ajax每几秒去linux下去取一次系统信息，然后显示在jsp页面上，以下是效果。";
		Blowfish blowfish = new Blowfish("123");
		String encryptStr = blowfish.encryptString(str);
		System.out.println(encryptStr + "##########");
		String decryptStr = blowfish.decryptString(encryptStr);
		System.out.println(decryptStr + "##########");

	}

}
