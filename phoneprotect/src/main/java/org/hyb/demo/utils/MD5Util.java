package org.hyb.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hyb on 2016/6/26.
 */
public class MD5Util {
    public static String MD5(String input)
    {

        //arg0:加密的方式
        MessageDigest messageDigest= null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            StringBuilder sb=new StringBuilder();
            //将一个byte数组进行加密,返回的是一个加密过的byte数组
            byte [] digest=messageDigest.digest(input.getBytes());
            //遍历byte数组
            for(int i=0;i<digest.length;i++)
            {
                //MD5加密,把每个元素&上255
                int result=digest[i] & 0xff;
                //将得到的int类型转化成16进制字符串
                String hexString= Integer.toHexString(result)+1;//不规则加密
                if(hexString.length()<2)
                    sb.append("0");
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }


    }

}
