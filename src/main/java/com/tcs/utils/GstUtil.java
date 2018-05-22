package com.tcs.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Random;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class GstUtil {
	
	private static final Logger log = LoggerFactory.getLogger(GstUtil.class);
	private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
	private static final String AES_ALGORITHM = "AES";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
	static int ENC_BITS = 256;
	static String CHARACTER_ENCODING = "UTF-8";
	public KeyGenerator KEYGEN;
	public String valueBeforeMD5 = "";
	public String valueAfterMD5 = "";
	private static Random myRand;
	private static SecureRandom mySecureRand;	
	private static String s_id;
    
	
	
	
	private static final String PUBLIC_KEY_PATH_CER = "F:/G2GData/Application/Certificates/GSTN_G2A_SANDBOX_UAT_public.cer";
	public static final String PUBLIC_KEY_PATH_PEM = "E:\\ANSHU\\gst-restservice-consume\\gst-restservice-consume\\src\\main\\resources\\"+"GSTN_G2A_SANDBOX_UAT_public.pem";
	//public static final String PUBLIC_KEY_PATH_PEM = "/opt/cybershield_programming/gst-restservice-consume/gst-restservice-consume/src/main/resources/"+"GSTN_G2A_SANDBOX_UAT_public.pem";
	public String generateSecureKey() throws Exception{
		KEYGEN = KeyGenerator.getInstance(AES_ALGORITHM);
		
		KEYGEN.init(ENC_BITS);
				SecretKey secretKey = KEYGEN.generateKey();
				return encodeBase64String(secretKey.getEncoded());
			}
	/*
     * Method to generate the random GUID
     */
    public String getRandomGuid(boolean secure) {
        MessageDigest md5 = null;
        StringBuffer sbValueBeforeMD5 = new StringBuffer();

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e);
        }

        try {
            long time = System.currentTimeMillis();
            long rand = 0;

            if (secure) {
                rand = mySecureRand.nextLong();
            } else {
                rand = myRand.nextLong();
            }

            // This StringBuffer can be a long as you need; the MD5
            // hash will always return 128 bits.  You can change
            // the seed to include anything you want here.
            // You could even stream a file through the MD5 making
            // the odds of guessing it at least as great as that
            // of guessing the contents of the file!
            sbValueBeforeMD5.append(s_id);
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(time));
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(rand));

            valueBeforeMD5 = sbValueBeforeMD5.toString();
            md5.update(valueBeforeMD5.getBytes());

            byte[] array = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < array.length; ++j) {
                int b = array[j] & 0xFF;
                if (b < 0x10) sb.append('0');
                sb.append(Integer.toHexString(b));
            }

         valueAfterMD5 = sb.toString();

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
		return valueAfterMD5;
    }     
    
	/**
	    * This method is used to encrypt base64 encoded string using an AES 256 bit key.
	    * 
	     * @param plainText
	    *            : plain text to decrypt
	    * @param secret
	    *            : key to encrypt
	    * @return : Encrypted String	    
	    * @throws NoSuchAlgorithmException
	    * @throws NoSuchPaddingException	 
	    */	
	public static String encrypt(String plainText, byte[] secret) throws NoSuchAlgorithmException, NoSuchPaddingException{
		
		Cipher ENCRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
		try{
			
			SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
			ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, sk);
			return Base64.encodeBase64String(ENCRYPT_CIPHER
                     .doFinal(plainText.getBytes()));
			
		}catch(Exception e){
			e.printStackTrace();
			return "Error in Encryption";
		}
	}
	/**
	    * This method is used to decrypt base64 encoded string using an AES 256 bit key.
	    * 
	     * @param plainText
	    *            : plain text to decrypt
	    * @param secret
	    *            : key to decrypt
	    * @return : Decrypted String
	    * @throws IOException
	    * @throws InvalidKeyException
	    * @throws BadPaddingException
	    * @throws IllegalBlockSizeException
	    */
	    public static byte[] decrypt(String plainText, byte[] secret) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException,Exception {
	    	
	    	
	    	Cipher DECRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
	    	SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
			DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, sk);
			
	        return DECRYPT_CIPHER.doFinal(decodeBase64StringTOByte(plainText));
	    }	
	
	
	/* Generate transaction number */
	/** This method is used to generate mock Transaction id
	 * 
	 * @return the string of random integer numbers
	 * length of string is 10
	 * and add prefix "GSTN"
	 */
	public String genTransaction(){
		char[] txnid = "1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
		    char c = txnid[random.nextInt(txnid.length)];
		    sb.append(c);
		}
		String transaction = sb.toString();		
		return "GSTN".concat(transaction);
	}

	
	
	/**
	 * This method is used to encrypt the string , passed to it 
	 * using a public key provided
	 * 
	 * @param planTextToEncrypt
	 *       : Text to encrypt
	 * @return
	 *       :encrypted string 
	 * @throws FileNotFoundException 
	 * @throws CertificateException 
	 * @throws UnsupportedEncodingException 
	 */
	public String encryptwithPK_CER(byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, CertificateException, UnsupportedEncodingException
	{
		String publicKeyUrl =PUBLIC_KEY_PATH_CER;
		FileInputStream fin = new FileInputStream(publicKeyUrl);
		CertificateFactory f = CertificateFactory.getInstance(RSA_ALGORITHM);
		X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);
		PublicKey pk = certificate.getPublicKey();		
		
		Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, pk);
		byte[] encryptedByte= cipher.doFinal(plaintext);
		String encodedString = new String(java.util.Base64.getEncoder().encode(encryptedByte));
		return encodedString;
	}

	public String encryptwithPK_PEM(byte[] planTextToEncrypt){
		Cipher cipher = null;
		
		try {
			Path keyPath = Paths.get(PUBLIC_KEY_PATH_PEM);
			String keyContent = new String(Files.readAllBytes(keyPath)).replace("-----BEGIN RSA PUBLIC KEY-----", "");
			String modifiedKeyContent = keyContent.replace("-----END RSA PUBLIC KEY-----", "");
			byte[] decodedKey = Base64.decodeBase64(modifiedKeyContent.getBytes(StandardCharsets.UTF_8));
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedKey);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
			PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
			
			cipher = Cipher.getInstance(RSA_TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedByte= cipher.doFinal(planTextToEncrypt);
			String encodedString = new String(java.util.Base64.getEncoder().encode(encryptedByte)); 
			return encodedString;
		} catch (Exception e) {
				e.printStackTrace();
				return null;
		}
			
	}
	
	
    public static String generateHmac(String data, byte[] ek)
    {
    	String hash = null;
    	try{ 
	    	Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secret_key = new SecretKeySpec(ek, "AES_ALGORITHM");
	        sha256_HMAC.init(secret_key);
	        hash = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes(CHARACTER_ENCODING)));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return hash;
    }
    
    @SuppressWarnings("unused")
	private static String gethashdataFile(String filepath) throws Exception {

        try {
              MessageDigest md = MessageDigest.getInstance("SHA-256");
              @SuppressWarnings("resource")
              FileInputStream fis = new FileInputStream(filepath);
              byte[] dataBytes = new byte[1024];
              int nread = -1;
              while ((nread = fis.read(dataBytes)) != -1) {
                    md.update(dataBytes, 0, nread);
              }
              byte[] mdbytes = md.digest();

              // convert the byte to hex format method 2
              StringBuilder hexString = new StringBuilder();
              for (int i = 0; i < mdbytes.length; i++) {
                    hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
              }

              return hexString.toString();
        } finally {
        	System.out.println("Error calculating Hash");

        }
  }

    public static String getHashofFile (String path) throws Exception{
		byte[] bytesArray = null;
		String calculatedHashOfFile = null;
		try
		{
			//java.nio class Files give byte array from file path
			bytesArray = Files.readAllBytes(Paths.get(path));
			
			//Using Apache commons codec jar for SHA256 hash calculation
			calculatedHashOfFile = org.apache.commons.codec.digest.DigestUtils.sha256Hex(bytesArray);
		    
		}
		catch(Exception e)
		{
			throw e;
		}
		return calculatedHashOfFile;
	}
    public static String getHashValue(String value) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(value.getBytes());
        byte[] byteData = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
              sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                          .substring(1));
        }
        return sb.toString();
  }     


    
	/**
     * This method is used to encode bytes[] to base64 string.
     * 
      * @param bytes
     *            : Bytes to encode
     * @return : Encoded Base64 String
     */
   public static String encodeBase64String(byte[] bytes) {
         return new String(java.util.Base64.getEncoder().encode(bytes));
   }
   /**
    * This method is used to decode the base64 encoded string to byte[]
    * 
     * @param stringData
    *            : String to decode
    * @return : decoded String
    * @throws UnsupportedEncodingException
    */
   public static byte[] decodeBase64StringTOByte(String stringData) throws Exception {
		return java.util.Base64.getDecoder().decode(stringData.getBytes(CHARACTER_ENCODING));
	}
 
   public void exception_handling(Exception e){
	   System.out.println("Exception Occured \n\n");
	   System.out.println(e);
   }
   
}
