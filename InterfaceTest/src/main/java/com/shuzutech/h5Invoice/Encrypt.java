package com.shuzutech.h5Invoice;

import com.shuzutech.config.ConfigFile;
import com.shuzutech.config.GetAccessToken;
import com.shuzutech.config.InterfaceName;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zsx on 2017/1/14.
 */
public class Encrypt {

    /*
      Encrypt the plain text using public key.

      @param text
     *          : original plain text
     * @param key
     *          :The public key
     * @return Encrypted text
     * @throws java.lang.Exception
     */
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] rsaEncrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Decrypt text using private key.
     *
     * @param text
     *          :encrypted text
     * @param key
     *          :The private key
     * @return plain text
     */
    public static String rsaDecrypt(byte[] text, PrivateKey key) {
        byte[] dectyptedText;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA");
            // decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);
            return new String(dectyptedText);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static byte[] rsaSign(String data,PrivateKey privateKey){
        Signature signature ;
        try {
            signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initSign(privateKey);
            signature.update(data.getBytes("UTF-8"));
            return signature.sign();

        } catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean rsaCheckSign(String data,byte[] signData,PublicKey publicKey){

        if(data == null){
            return false;
        }

        Signature signature;
        try {
            signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initVerify(publicKey);
            signature.update(data.getBytes("UTF-8"));
            return signature.verify(signData);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] base64Decode(String encode){

        Base64 decoder = new Base64();
        byte[] b = decoder.decode(encode.getBytes());
        return b;
    }

    public static PublicKey StringtoPublicKey(String key){
        byte[] publicBytes = Base64.decodeBase64(key.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory;
        PublicKey pubKey = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            pubKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return pubKey;
    }

    public static PrivateKey StringtoPrivateKey(String key){
        byte[] publicBytes = Base64.decodeBase64(key.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(publicBytes);
        KeyFactory keyFactory;
        PrivateKey priKey = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            priKey = keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return priKey;
    }

    private static  SecretKeySpec aesSetKey(String myKey)
    {
        byte[] key;
        SecretKeySpec secretKey;
        //MessageDigest sha;
        try {
            key = myKey.getBytes("UTF-8");
//            sha = MessageDigest.getInstance("SHA-1");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            return secretKey;
        }
//        catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *加密方法，调这个方法
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String aesEncrypt(String strToEncrypt, String secret)
    {
        try
        {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return new String(Base64.encodeBase64(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
            		//Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }


    /**
     *对文件的内容进行加密时优先使用该方法
     * @param bytes
     * @param secret
     * @return
     */
    public static byte[] aesEncryptBytes(byte[] bytes, String secret)
    {
        try
        {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return Base64.encodeBase64(cipher.doFinal(bytes));
            		//Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     *
     * @param strToDecrypt
     * @param secret
     * @return
     * @deprecated
     */
    public static String aesDecrypt(byte[] strToDecrypt, String secret)
    {
        try
        {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] tmp = cipher.doFinal(strToDecrypt);
            return new String(tmp,"utf-8");
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static byte[] aesDecryptBytes(byte[] strToDecrypt, String secret)
    {
        try
        {
            SecretKeySpec secretKey = aesSetKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(strToDecrypt);
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    /**
     * H5页面开票
     */

    @Test
    public void makeInvoice() throws Exception {
        //110101201707010057   110101201707010043
        String fpqqlsh;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHMMss");
        String date = simpleDateFormat.format(new Date());
        fpqqlsh="0101"+date;
        System.out.println("本次发票请求流水号:"+fpqqlsh);
    	String ff = "UA=PC&"+"fpqqlsh="+fpqqlsh+"&kplx=0&fplxdm=026&shnsrsbh=110101201707010057&operator=操作人";
//        String ff = "UA=PC&fpqqlsh="+fpqqlsh+"&kplx=1&fplxdm=025&shnsrsbh=110101201707010057&operator=操作人&yfpdm=150007890501&yfphm=40022137";
        String AccessToken = GetAccessToken.getToken(InterfaceName.DEV);

    	String ecoderResult=null;
        try {
            ecoderResult = java.util.Base64.getEncoder().encodeToString(new Encrypt().aesEncrypt(ff,AccessToken).getBytes("utf-8"));
            System.out.println("加密后的数据为:"+ ecoderResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String uri = "https://paymgmt.shuzutech.com/invoice/h5/invoice?appId=947647bad42f485e3f5e9605ce4cadcf&encryptMsg=";//现网环境
        String uri = "http://106.14.193.154:8081/invoice/h5/invoice?appId=f07dcd92fce254d4b344cb07dc4901e2&encryptMsg=";
        String returnUri= "&returnURL=http://www.shuzutech.com/";
        String url = uri + ecoderResult +returnUri;
        System.out.println(url);
    }

    /**
     * H5开票配置页面
     */
    @Test
    public void invoiceConfig() throws Exception {
        //110101201707010043 110101201707010057
        String  ic = "UA=PC&shnsrsbh=110101201707010057";
        String accessToken = GetAccessToken.getToken(InterfaceName.DEV);
        String ecoderResult=null;
        try {
            ecoderResult = java.util.Base64.getEncoder().encodeToString(new Encrypt().aesEncrypt(ic,accessToken).getBytes("utf-8"));
            System.out.println("加密后的数据为:"+ ecoderResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String uri = "https://paymgmt.shuzutech.com/invoice/h5/config?appId=947647bad42f485e3f5e9605ce4cadcf&encryptMsg=";//现网环境
        String uri = "http://106.14.193.154:8081/invoice/h5/config?appId=f07dcd92fce254d4b344cb07dc4901e2&encryptMsg=";//开发环境
        String returnUri= "&returnURL=http://www.shuzutech.com/";
        String url = uri + ecoderResult +returnUri;
        System.out.println(url);
    }

    @Test
    public void invoiceInto() throws Exception {
        /**
         * staffMobile 数族平台的业务员手机号
         * channelId，服务商在数族申请的平台ID，对于新商户需要提供商户所属的服务商
         * &staffMobile=13599999999
         * channelId=40
         */
        String params = "UA=Chrome&staffMobile=13599999999&companyName=江南1号店081701&contact=18672680518&mobile=18672680618&taxNo=130101201707020041&outMerchantId=201908171104&channelId=40";
        String accessToken = GetAccessToken.getToken(InterfaceName.DEV);

        String appId = "f07dcd92fce254d4b344cb07dc4901e2";
        String returnUrl = "http://www.shuzutech.com/";
//        String encryptMsg = java.util.Base64.getEncoder().encodeToString(new Encrypt().aesEncrypt(params,accessToken).getBytes("utf-8"));
        String encryptMsg = new Encrypt().aesEncrypt(params, accessToken);
        //URLEncoder.encode()不指定编码得方法已经过时，现在使用如下方法
        String encryptMsg_urlEncode = URLEncoder.encode(encryptMsg,"UTF-8");
        String url = "http://106.14.193.154:8081/third/invoice/registration?appId="+(appId)+"&encryptMsg="+(encryptMsg_urlEncode)+"&returnURL="+(returnUrl);

        System.out.println(url);
    }

    @Test
    public void makeInvoiceSelfInspection() throws Exception {
        //110101201707010043(110101201707010043~~499000152157)  110101201707010057(110101201707010057~~499000152456)  110101201707010037  554433221100001(广东)、500102010001448
        String params = "UA=Chrome&shnsrsbh=110101201707010057&jsbh=110101201707010057~~499000152456";
        String accessToken = GetAccessToken.getToken(InterfaceName.PRO);
        String appid = ConfigFile.getAppid(InterfaceName.PRO);
        String ecoderResult=null;
        try {
            ecoderResult = java.util.Base64.getEncoder().encodeToString(new Encrypt().aesEncrypt(params,accessToken).getBytes("utf-8"));
            System.out.println("加密后的数据为:"+ ecoderResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String uri = "http://106.14.193.154:8081/invoice/h5/self_test?appId="+appid+"&encryptMsg=";//开发环境
        String uri = "https://paymgmt.shuzutech.com/invoice/h5/self_test?appId="+appid+"&encryptMsg=";
        String returnUri= "&returnURL=http://www.shuzutech.com/";
        String url = uri + ecoderResult +returnUri;
        System.out.println(url);
    }
}
