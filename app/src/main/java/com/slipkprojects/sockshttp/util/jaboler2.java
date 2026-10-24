package com.slipkprojects.sockshttp.util;

import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class jaboler2 {
	private static final String TAG = "TCrypt";


    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";


    private static final String HASH_ALGORITHM = "SHA-256";



    public static boolean DEBUG_LOG_ENABLED = false;
	private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    //private static final String jaTest = "QCMkXyYtKygpLyoiITo7Pw==";

	private static final String ẳ ="4oCA4oCB4oCC4oCD4oCE4oCF4oCG4oCH4oCI4oCJ4oCK4oCL4oCM4oCN4oCO4oCP";

    private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();

        log("SHA-256 key ", key);

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }



    public static String encrypt(final String password, String message)
    throws GeneralSecurityException {

        try {
            String ja=Jakey(password);
            SecretKeySpec generateKey = generateKey(ja);
            log("message", message);
            String encodeToString = Base64.encodeToString(encrypt(generateKey, ivBytes, message.getBytes(CHARSET)), 2);
            log("Base64.NO_WRAP", encodeToString);
            return Jacodes(encodeToString);
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED) {
                Log.e(TAG, "UnsupportedEncodingException ", e);
            }
            throw new GeneralSecurityException(e);
        }
    }


    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
    throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = cipher.doFinal(message);

        log("cipherText", cipherText);

        return cipherText;
    }



    public static String decrypt(final String password, String base64EncodedCipherText)
    throws GeneralSecurityException {
        String ja1 = genString(base64EncodedCipherText);
        try {

            String ja=Jakey(password);
            final SecretKeySpec key = generateKey(ja);

            log("base64EncodedCipherText", ja1);
            byte[] decodedCipherText = Base64.decode(ja1, Base64.NO_WRAP);
            log("decodedCipherText", decodedCipherText);

            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);

            log("decryptedBytes", decryptedBytes);
            String message = new String(decryptedBytes, CHARSET);
            log("message", message);


            return message;
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED)
                Log.e(TAG, "UnsupportedEncodingException ", e);

            throw new GeneralSecurityException(e);
        }
    }


    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
    throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);

        log("decryptedBytes", decryptedBytes);

        return decryptedBytes;
    }

    public static String i(String decrypt) throws GeneralSecurityException {
        try {
            decrypt = decrypt("null", decrypt);
            return decrypt;
        }
        catch (GeneralSecurityException ex) {
            throw new GeneralSecurityException((Throwable)ex);
        }
    }
    private static void log(String what, byte[] bytes) {
        if (DEBUG_LOG_ENABLED)
            Log.d(TAG, what + "[" + bytes.length + "] [" + bytesToHex(bytes) + "]");
    }

    private static void log(String what, String value) {
        if (DEBUG_LOG_ENABLED)
            Log.d(TAG, what + "[" + value.length() + "] [" + value + "]");
    }

    public static String e(String encrypt) throws GeneralSecurityException {
        try {
            encrypt = encrypt("null", encrypt);
            return encrypt;
        }
        catch (GeneralSecurityException ex) {
            throw new GeneralSecurityException((Throwable)ex);
        }
    }


    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }



    public static String Jakey(String str) {
        Objects.requireNonNull(str);
        return JaHex(str.getBytes());
    }

    private static String JaHex(byte[] bArr) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString();
    }

    public static String Jacodes(String str) {
        Objects.requireNonNull(str);
        return jagen(str.getBytes());
    }

    private static String jagen(byte[] bArr) {
        char[] charArray = JaTest(ẳ).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString();
    }

    public static String JaTest(String str) {
        return new String(Base64.decode(str, Base64.NO_WRAP));
    }

    public static String genString(String str) {
        String var = JaTest(ẳ);
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((var.indexOf(charArray[i2]) * 16) + var.indexOf(charArray[i2 + 1])) & 255);
        }
        return new String(bArr);
    }
}


