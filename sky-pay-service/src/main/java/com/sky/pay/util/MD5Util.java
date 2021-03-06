/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    MD5Util.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年08月26日 17:19 
 *
 *    Revision: 
 *
 *    2018/8/26 17:19 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  * @ClassName MD5Util
 *  * @Description MD5加密
 *  * @Author Hardy
 *  * @Date 2018年08月26日 17:19
 *  * @Version 1.0.0
 *  
 **/
public class MD5Util {

    //日志
    public static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**Determine encrypt algorithm MD5*/
    private static final String ALGORITHM_MD5 = "MD5";

    /**UTF-8 Encoding*/
    private static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * MD5 16bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * */
    private String MD5_16bit(String readyEncryptStr) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(readyEncryptStr != null){
            return new MD5Util().MD5_32bit(readyEncryptStr).substring(8, 24);
        }else{
            return null;
        }
    }

    /**
     * MD5 32bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * */
    private String MD5_32bit(String readyEncryptStr) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(readyEncryptStr != null){
            //The cipher text converted to hexadecimal string
            StringBuilder su = new StringBuilder();
            //Get MD5 digest algorithm's MessageDigest's instance.
            MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
            byte [] b = md.digest(readyEncryptStr.getBytes(CHARSET_UTF_8));
            int temp = 0;
            //byte array switch hexadecimal number.
            for(int offset = 0,bLen = b.length; offset < bLen; offset++){
                temp = b[offset];
                if(temp < 0){
                    temp += 256;
                }
                int d1 = temp / 16;
                int d2 = temp % 16;
                su.append(Integer.toHexString(d1) + Integer.toHexString(d2)) ;
            }
            return su.toString();
        }else{
            return null;
        }
    }

    /**
     * MD5 16bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * */
    private String MD5_64bit(String readyEncryptStr) throws NoSuchAlgorithmException,UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte[] encryptStr = readyEncryptStr.getBytes(CHARSET_UTF_8);
        byte[] encryptByte = md.digest(encryptStr);
        return base64Encoder.encode(encryptByte);
    }

    /**
     * 功能描述:
     * 16位加密(大写)
     * @Author: Hardy
     * @Date: 2018年08月31日 15:50:28
     * @param source
     * @return: java.lang.String
     **/
    public static String encryptToUpper_16bit(String source) throws Exception{
        try {
            String str = new MD5Util().MD5_16bit(source).toUpperCase();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("MD5-16位加密异常:"+e.getMessage());
            throw new Exception("MD5-16位加密异常!");
        }
    }

    /**
     * 功能描述:
     * 16位加密(小写)
     * @Author: Hardy
     * @Date: 2018年08月31日 15:51:56
     * @param source
     * @return: java.lang.String
     **/
    public static String encryptToLower_16bit(String source) throws Exception{
        try {
            String str = new MD5Util().MD5_16bit(source).toLowerCase();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("MD5-16位加密异常:"+e.getMessage());
            throw new Exception("MD5-16位加密异常!");
        }
    }

    /**
     * 功能描述:
     * 32位加密(大写)
     * @Author: Hardy
     * @Date: 2018年08月31日 16:01:31
     * @param source
     * @return: java.lang.String
     **/
    public static String encryptToUpper_32bit(String source) throws Exception{
        try {
            String str = new MD5Util().MD5_32bit(source).toUpperCase();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("MD5-32位加密异常:"+e.getMessage());
            throw new Exception("MD5-32位加密异常!");
        }
    }

    /**
     * 功能描述:
     * 32位加密(小写)
     * @Author: Hardy
     * @Date: 2018年08月31日 16:02:04
     * @param source
     * @return: java.lang.String
     **/
    public static String encryptToLower_32bit(String source) throws Exception{
        try {
            String str = new MD5Util().MD5_32bit(source).toLowerCase();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("MD5-32位加密异常:"+e.getMessage());
            throw new Exception("MD5-32位加密异常!");
        }
    }

    /**
     * 功能描述:
     * 16位加密后然后base64编码
     * @Author: Hardy
     * @Date: 2018年08月31日 16:09:01
     * @param source
     * @return: java.lang.String
     **/
    public static String encrypt16BitToBase(String source) throws Exception{
        try {
            String encryptStr = new MD5Util().MD5_16bit(source);
            return Base64Util.encryptStr(encryptStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("MD5-16位加密异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 32位加密后然后base64编码
     * @Author: Hardy
     * @Date: 2018年08月31日 16:11:36
     * @param source
     * @return: java.lang.String
     **/
    public static String encrypt32BitToBase(String source) throws Exception{
        try {
            String encryptStr = new MD5Util().MD5_32bit(source);
            return Base64Util.encryptStr(encryptStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("MD5-16位加密异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            String str = "我们是冠军!";
            String md5lower16 = MD5Util.encryptToLower_16bit(str);
            String md5lower32 = MD5Util.encryptToLower_32bit(str);
            String md5upper32 = MD5Util.encryptToUpper_32bit(str);
            String md5upper16 = MD5Util.encryptToUpper_16bit(str);
            String md5base16 = MD5Util.encrypt16BitToBase(str);
            String md5base32 = MD5Util.encrypt32BitToBase(str);
            System.out.println("MD5-16位加密结果:  " + md5lower16);
            System.out.println("MD5-16位加密结果:  " + md5upper16);
            System.out.println("MD5-32位加密结果:  " + md5lower32);
            System.out.println("MD5-32位加密结果:  " + md5upper32);
            System.out.println("MD5-base-16位加密结果:  " + md5base16);
            System.out.println("MD5-base-32位加密结果:  " + md5base32);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
