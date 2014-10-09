package com.covisint.wechat.utils;

import java.util.Random;

/**
 * 生成微信接口配置验证TOKEN工具类
 */
public class ShortTokenUtils {
	/**
	 * 要使用的字符
	 */
	private static String[] CHARS = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * 获取微信路径验证token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public static String getToken(String accountId) {
		String token = IdentifierUtils.getId().generate().toString();
		String[] aResult = toShort(accountId, token);
		Random random = new Random();
		int j = random.nextInt(4);// 随机从4组中获取一组
		return aResult[j];
	}

	private static String[] toShort(String key, String token) {
		String hex = MD5.md5(key + token);// 进行 MD5 加密
		String[] resToken = new String[4];// 初始化数组
		for (int i = 0; i < 4; i++) {
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);
			// 这里需要使用 long 型来转换，因为 Integer.parseInt() 只能处理 31 位 , 首位为符号位 ,
			// 如果不用long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += CHARS[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resToken[i] = outChars;
		}
		return resToken;
	}
}
