import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;

import com.codeminders.hidapi.HIDDeviceInfo;
import com.codeminders.hidapi.HIDManager;

import net.codejava.crypto.CryptoException;

public class DecryptionProcess {
	
	//CryptoUtils.decrypt(digestBuff, file, Outputfile2);
	
	public static boolean task(String password,File file) throws CryptoException, UnsupportedEncodingException, NoSuchAlgorithmException{
		
	   	String[][] USBList = new String[50][13];
    	int USBListCounter = 0;
    	String USBKey=null;
    	String PasswordKey = password;
    	boolean EncryptionStatus = false;
    	File Outputfile2 = ObtainOutputPath.Path(file,"decypt","");
    	
    	String Key0 = password+"]]NULL]]NULL";       
    	String Salt0 = Integer.toString(Key0.length());
		Key0 = Key0+Salt0;

		MessageDigest md2   = MessageDigest.getInstance("MD5"); //make sure it exists, there are other algorithms, but I prefer SHA for simple and relatively quick hashing
		String strToEncode2 = Key0;
		md2.update(strToEncode2.getBytes("UTF-8")); //I'd rather specify the encoding. It's platform dependent otherwise. 
		byte[] digestBuff2 = md2.digest();
    		
		EncryptionStatus=CryptoUtils.decrypt(digestBuff2, file, Outputfile2);
		
		
    	
    	String USB;
    	
    	if (EncryptionStatus==false){
    	
	        try {
	            com.codeminders.hidapi.ClassPathLibraryLoader.loadNativeHIDLibrary();
	            HIDManager hidManager = HIDManager.getInstance();
	            HIDDeviceInfo[] infos = hidManager.listDevices();
	            for (HIDDeviceInfo info : infos) {
	            	          		
	            	USB = info.toString();
	            	List<String> USBInfoList = Arrays.asList(USB.split(", "));
	            	
	                int size = USBInfoList.size();
	                int x =0;
	                
	                while (x!=size && EncryptionStatus==false){
	                	
	                	String Key1 = 
	                    (password+"]]"+USBInfoList.get(1)+"///"+USBInfoList.get(6)+"///"+USBInfoList.get(2)+"///"+USBInfoList.get(3)+"///"+"]]"+ObtainCPU.data());
	  				  	String Salt = Integer.toString(Key1.length());
	  				  	Key1 = Key1+Salt;
	  				  	
	  				  	System.out.println(Key1);
	  				  	
	  					MessageDigest md3   = MessageDigest.getInstance("MD5"); //make sure it exists, there are other algorithms, but I prefer SHA for simple and relatively quick hashing
	  					String strToEncode3 = Key1;
	  					md3.update(strToEncode3.getBytes("UTF-8")); //I'd rather specify the encoding. It's platform dependent otherwise. 
	  					byte[] digestBuff3 = md3.digest();
	  			    		    	
	  					EncryptionStatus=CryptoUtils.decrypt(digestBuff3, file, Outputfile2);
	  					
	  					if (EncryptionStatus==false){
	  					             	
		                	String Key2 = 
		                	(password+"]]"+USBInfoList.get(1)+"///"+USBInfoList.get(6)+"///"+USBInfoList.get(2)+"///"+USBInfoList.get(3)+"///"+"]]"+"NULL");
		                	String Salt2 = Integer.toString(Key2.length());
		                	Key2 = Key2+Salt2;
		                	
		            		MessageDigest md4   = MessageDigest.getInstance("MD5"); //make sure it exists, there are other algorithms, but I prefer SHA for simple and relatively quick hashing
		            		String strToEncode4 = Key2;
		            		
		            		System.out.println(Key2);
		            		
		            		md4.update(strToEncode4.getBytes("UTF-8")); //I'd rather specify the encoding. It's platform dependent otherwise. 
		            		byte[] digestBuff4 = md4.digest();
		                	
		            		EncryptionStatus=CryptoUtils.decrypt(digestBuff4, file, Outputfile2);
	  					}
		
	                	x++;
	                }
	            	
	            	//System.out.println();
	            	USBListCounter++;
	            	
	            	
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	
	
    	}
    	
    	return EncryptionStatus;
	}

}
