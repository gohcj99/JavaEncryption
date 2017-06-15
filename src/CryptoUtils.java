import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import net.codejava.crypto.CryptoException;
 
/**
 * A utility class that encrypts or decrypts a file.
 * @author www.codejava.net
 *
 */
public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
 
    public static boolean encrypt(byte[] key, File inputFile, File outputFile)
            throws CryptoException {
    	
    	boolean status;
    	
        status = doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
        
        return status;
    }
 
    public static boolean decrypt(byte[] key, File inputFile, File outputFile)
            throws CryptoException {
    	
    	boolean status;
    	
    	status=doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    	
    	return status;
    }
 
    private static boolean doCrypto(int cipherMode, byte[] key, File inputFile,
    		
            File outputFile) throws CryptoException {
    	
    	boolean KeyValid = false;
    	
        try {
            Key secretKey = new SecretKeySpec(key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
             
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
             
            byte[] outputBytes = cipher.doFinal(inputBytes);
             
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
             
            inputStream.close();
            outputStream.close();
            
            System.out.println("Sucess encrypting/decrypting file");
            
            KeyValid = true;
            return KeyValid;
             
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
           // throw new CryptoException("Error encrypting/decrypting file", ex); 
        	System.out.println("Error encrypting/decrypting file");
        	
            KeyValid = false;
            return KeyValid;
        }
        
        
    }
}