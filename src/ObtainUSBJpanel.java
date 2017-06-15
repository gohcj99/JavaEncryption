import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.codeminders.hidapi.HIDDeviceInfo;
import com.codeminders.hidapi.HIDManager;

public class ObtainUSBJpanel {

    public static String data() throws Exception {
    	
    	//int x= 1;
    	
    	Scanner input = new Scanner(System.in);
    	
    	String[][] USBList = new String[50][13];
    	int USBListCounter = 0;
    	String USBKey=null;
    	
    	String USB;
    	
        try {
            com.codeminders.hidapi.ClassPathLibraryLoader.loadNativeHIDLibrary();
            HIDManager hidManager = HIDManager.getInstance();
            HIDDeviceInfo[] infos = hidManager.listDevices();
            for (HIDDeviceInfo info : infos) {
            	
//                System.out.println("Product "+x +":" + info.toString());
//                x++;
            		
            	USB = info.toString();
            	List<String> USBInfoList = Arrays.asList(USB.split(", "));
            	
                int size = USBInfoList.size();
                int x =0;
                
                while (x!=size){
                	
                	//System.out.println(USBInfoList.get(x));
                	USBList[USBListCounter][x]=USBInfoList.get(x);
                	USBList[USBListCounter][12]="YES";
                	x++;
                }
            	
            	//System.out.println();
            	USBListCounter++;
            	
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Number of USB Devices detected: "+USBListCounter);
        System.out.println("USB Device(s) information");
        System.out.println();
        
        int z = 0;
        
//        HIDDeviceInfo [path=\\?\hid#vid_046d&pid_c068&mi_01&col04#9&21e3990f&0&0003#{4d1e55b2-f16f-11cf-88cb-001111000030}
//        vendor_id=1133
//        product_id=49256
//        serial_number=DE573447A60018
//        release_number=22530
//        manufacturer_string=Logitech
//        product_string=G500
//        usage_page=65280
//        usage=2
//        interface_number=1]
        
        while (USBList[z][12]!=null){
        	
        	List<String> Information ;
        	
        	System.out.println("Device Number: "+z);
        	
        	Information = Arrays.asList(USBList[z][6].split("="));
        	System.out.print("Product Name: "+Information.get(1)+", ");
        	//JPanelUSB.label_password.append("Product Name: "+Information.get(1)+", ");
        	
        	Information = Arrays.asList(USBList[z][5].split("="));
        	System.out.print("Manufacturer : "+Information.get(1)+", ");
        	
        	Information = Arrays.asList(USBList[z][1].split("="));
        	System.out.print("Vendor ID: "+Information.get(1)+", ");

        	Information = Arrays.asList(USBList[z][2].split("="));
        	System.out.print("Product ID: "+Information.get(1)+", ");
        	
        	Information = Arrays.asList(USBList[z][3].split("="));
        	System.out.print("Product Serial Number: "+Information.get(1));
        	
        	System.out.println();
        	System.out.println();
        	z++;
        }
        
        int select;
        boolean flag = true;
        
        
       System.out.println("Select the USB Device Number you would like to use as a key:");
       
       while (flag==true){
       
       while (true)
           try {
        	   select = Integer.parseInt(input.nextLine());
               break;
           } catch (NumberFormatException nfe) {
        	   System.out.print("Please input a number: ");
   			
           }
       
       if (select>(USBListCounter-1) || select<0){
    	   
    	   flag=true;
    	   System.out.println("Invalid Selection, Please select a valid USB device number: ");
    	   
       }
       
       else {
    	   
    	   // 1623
    	   //Vendor ID+ProductName+ProductID+Product Serial Number
    	   // (///) will be use as seperator
    	   
    	   flag=false;
    	   USBKey=(USBList[select][1]+"///"+USBList[select][6]+"///"+USBList[select][2]+"///"+USBList[select][3]+"///");
    	  // USBKey = USBKey.replaceAll("\\s","");
    	   System.out.println();
    	   return USBKey;
    	   
       }
       
      // return USBKey;
       
       
       }
       
       return USBKey;
        
    }
}