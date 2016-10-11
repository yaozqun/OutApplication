package com.grgbanking.baselib.util;

/**
 * Created by charry on 2016/10/8.
 */

import org.apaches.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 数据安全加密工具类
 */
public class SecurityUtils {

    private static final String DEFAULT_DES_CRYPT_KEY = "xiwei123456";
    private static final String DEFAULT_AES_CRYPT_KEY = "xiwei123456";
    private static final String AES_IV_STRING = "aabbccddeeffgghh";
    public static final String MESSAGE_AES_KEY = "1234567abcde0987";

    /**
     * 对输入的字符串进行MD5加密
     *
     * @param str 需要加密的字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(str.getBytes());
            return new String(Hex.encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 使用默认密钥进行 AES加密
     *
     * @param content 须加密内容
     * @return 字符串
     */
    public static String encryptAES(String content) {
        return encryptAES(content, DEFAULT_AES_CRYPT_KEY, AES_IV_STRING);
    }

    /**
     * AES加密
     *
     * @param content 须加密内容
     * @param key     密钥
     * @return 字符串
     */
    public static String encryptAES(String content, String key, String iv) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            byte[] ivBytes = iv.getBytes();
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
            byte[] result = cipher.doFinal(byteContent);
            return parseByte2HexStr(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 使用默认密钥进行 AES解密
     *
     * @param content
     * @return 字符串
     */
    public static String decryptAES(String content) {
        return decryptAES(content, DEFAULT_AES_CRYPT_KEY, AES_IV_STRING);
    }

    /**
     * AES解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return 字符串
     */
    public static String decryptAES(String content, String key, String iv) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            byte[] ivBytes = iv.getBytes();
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] result = cipher.doFinal(parseHexStr2Byte(content));
            return new String(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 使用默认密钥进行 AES加密
     *
     * @param content 须加密内容
     * @return 字符串
     */
    public static String encryptDES(String content) {
        return encryptDES(content, DEFAULT_DES_CRYPT_KEY);
    }

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static String encryptDES(String src, String key) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            return parseByte2HexStr(cipher.doFinal(src.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 使用默认密钥进行 DES解密
     *
     * @param content 待解密内容
     * @return 字符串
     */
    public static String decryptDES(String content) {
        return decryptDES(content, DEFAULT_DES_CRYPT_KEY);
    }

    /**
     * 解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static String decryptDES(String src, String key) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            return new String(cipher.doFinal(parseHexStr2Byte(src)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf 字节数组
     * @return 字符串
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr 16进制字符串
     * @return 字节数组
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 对字符串进行MD5编码(用户密码加密)
     *
     * @param str 待编码字符串
     * @return 字符串
     */
    public static String encodeByMD5(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(str.getBytes());
            return new String(Hex.encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return Base64Util.getEncodeByte(encrypted);
        // return new Base64().encodeToString(encrypted);//Web此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            // byte[] encrypted1 = new Base64().decode(sSrc);//Web先用base64解密
            byte[] encrypted1 = Base64Util.getDecodeStrToByte(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String aesEncrypt(String str, String key) throws Exception {

        if (str == null || key == null) return null;

        //判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        //return Base64.encodeBase64String(bytes);
        //return new BASE64Encoder().encode(bytes);
        return bytesToHexString(bytes);
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;

        //判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("GBK"), "AES"));
        byte[] bytes = hexStringToBytes(str);
        //byte[] bytes = Base64.decodeBase64(str);
        //byte[] bytes = new BASE64Decoder().decodeBuffer(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

    public static void main(String[] args) {
        try {
            //System.out.println(SecurityUtils.aesEncrypt("{\"id\":\"402847325226fef80152271f461c0001\",\"line_id\":\"402847325226fef80152271fbc1d0002\",\"status_code\":\"123456\",\"get_code_time\":\"20160910001030\"}","1234567abcde0987"));
            //System.out.println(SecurityUtils.aesDecrypt("3337635d02a165632a698cb87b796604fc04d1535597ac0c728e3764a200f267c26b64430d2a307ca91f2546046f1008f136a1998338dce0fcad3f10ccb97e11edad78e11504d20e9e03ef4fe2fff9e799023bfee60b6d1c9e101fbd37a62dc118d14cf7e6b4c96cd6132e95852a6a4bd2d9da3d9e7432064808ba90d1e7967e7e09baf12ff0a69b9d0c760fa8ea8913","1234567abcde0987"));

            System.out.println(SecurityUtils.encryptAES("ADQJXT"));
            System.out.println(SecurityUtils.decryptAES("9549B45C0C02FD52B17776F0A311D171"));
            //System.out.println(SecurityUtils.aesEncrypt("{\"taskId\":\"4028978155427f50015542845c0b0004\",\"line_number\":\"20160612-001\",\"device_number\":\"A00002\",\"userId\":0,\"arriveTime\":\"2016-06-12 17:17:48\",\"clear\":\"1\",\"repairs\":\"1\",\"beforeEquipmentStatus\":\"0\",\"actualTaskType\":\"A01;A15\",\"balanceMoney\":\"20\",\"addMoney\":\"19\",\"changeCashbox\":1,\"setAddmoneyParam\":1,\"moditySaftboxPassword\":0,\"newCashboxSeal\":1,\"oldCashboxSeal\":1,\"transferSealNo\":\"\",\"cashbox\":\"\",\"receivebox\":\"\",\"returnbox\":\"\",\"returnReceivebox\":\"\",\"cardReaderFault\":\"3\",\"tournalPrinterFault\":\"5\",\"receiptPrinterFault\":\"\",\"cashGateFault\":\"\",\"atmCoreFault\":\"\",\"otherFault\":\"\",\"processMethod\":\"复位\",\"repairAtmCoreRemark\":\"\",\"jamRemark\":\"\",\"faultHandler\":\"密码管理员\",\"processUser\":\"\",\"maintainCompanyTel\":\"\",\"maintainStartTime\":\"\",\"maintainFinishTime\":\"\",\"leaveTime\":\"2016-06-12 17:19:24\",\"taskStatus\":\"04\"}","1234567abcde0987"));
            //System.out.println(SecurityUtils.aesDecrypt("9B09CBAA930B3DE7806841B8EF76470954C97D4C6B01FC6BEB3186BA65251DAC8BCBB9380A530D06880EEC243178D5E12BE5FD01F342D6473DA611FB7606C3F383749E9738AE7BA934893FF8726441E60A05ECEF8E2D1499545123AE3BA1A80A7980BB099410F7896DC3A01A8A207DBCE05AF0C9F341114FB92216949EAFC34BD660E7E0B61D5F124E346280EF41F665704E36FA0363E9C15577544903C72912E5AEC03C8429F19ED21CE82317EBA86B2A29FE1C7738D7C8FD7D13B312E8B49145105602A8B075B4E7B7072666EC61F64A0146B9DCB375B50A8D710ED3AC29309415ED1963D2D77B541B782D674B8B40BD0315BF89EAC103C2F836739C8A65CC8D6E32343EA1F7C6E08AAAA5C822EC74F51F3703F6743A341B3D6566C8D7E3E1926E5A6CD8C5A155E031BC0F9DBC8799EFC4C2748EF0BF452D4BB23ACF4EB8C465442A5DBDE99693AD9FABC1F0473E223747AC4EFD0B1FD04C7DF2DDF282358739FED7CB3FA1E9F6D82E72A1100673A34C966AAE87C0526D80FA2CA33D239920DD9FC1072E4C950638E28808093423F66D61B3A85D1DDA980C6968403CA7D212C41A84BDEE4E5A4C67D847D0C0E91C8EBA6A2A2FAD60F37B4AFD259B71119C593D8F3A0158C7BD2AD0B79A766D15A55885F90F4F8E83462CF83FA17E1F2691C400EB2F720BC22F076DD80024ED07D1CF7DD51489A5DFF6AC68C6AD281085FC55641E0E50F12CE4D4B00684507FAA8DEB35E4A2E57B5FFFE72E23640DDD9529D8FE7540FE7A200CF5090D5AA38A43AD6A74F73C284089ECABF1A8B589D661E81026C7333BDD595DF6D20A0BD3A4FAEF815119151B3F4C34556AB465D74F5008BD752BCDF3DBEB13EF99509016437FC6ED85E75E20F4E38DF025CE554F7568DFE74BBCA064591E9D3268B5062D89E50FE9D26144A5471F43375A72B99121068BA0B4D18C10907B55CA3D3027CB60C77CC92AD4A67F158160C4E29F1DC560BFBA1B976D2DA0C292180084C8F5054EA7BFC24231FCFFDDFAB217B64A82245D194523CE521746AA092E5571014606760616B81010441E5C272C74101349B654834EEDBFCA88805CCA1F9E0AEE43DF16CFD868264BB7D4217CD3298E225ACC7A9932C4E341614EECC309DB174980D1AD2910D3","1234567abcde0987"));

            //JSONObject object = new JSONObject();
            //登录报文
            //object.put("login_name", "lyang1");
            //object.put("login_password", "654321");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
