import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.codeminders.hidapi.HIDDeviceInfo;
import com.codeminders.hidapi.HIDManager;

public class JPanelUSB {
	
	
	static int NumberofUSB = 0;
	static int select = 0;
	
	
	public static void task(JDialog d12){
		
	   	String[][] USBList = new String[50][13];
    	int USBListCounter = 0;
    	String USBKey=null;
    	JPanelEncyption.USBKey="NULL";
    	
    	String USB;
    	
        try {
            com.codeminders.hidapi.ClassPathLibraryLoader.loadNativeHIDLibrary();
            HIDManager hidManager = HIDManager.getInstance();
            HIDDeviceInfo[] infos = hidManager.listDevices();
            for (HIDDeviceInfo info : infos) {
            	          		
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
            	NumberofUSB++;
            	
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		
        

		JDialog D1 = new JDialog(d12 , "Select USB Key", false);  
		
		D1.setSize(700,400);
		D1.setResizable(false);
		D1.setBackground(Color.gray);
		
		JPanel pan= new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(-20,20,0,0);
		
		JTextArea textArea = new JTextArea();
		
		
		
		textArea.append(" Number of USB Devices detected: "+USBListCounter+"\n\n");
		textArea.append(" USB Device(s) information\n\n");
		textArea.append("--------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
		
		int z = 0;
		
		while (USBList[z][12]!=null){
        	
        	List<String> Information ;
        	
        	textArea.append(" [Device Number: "+z+"]\n");
        	
        	Information = Arrays.asList(USBList[z][6].split("="));
        	textArea.append(" Product Name: "+Information.get(1)+", ");
        	
        	Information = Arrays.asList(USBList[z][5].split("="));
        	textArea.append(" Manufacturer : "+Information.get(1)+", ");
        	
        	Information = Arrays.asList(USBList[z][1].split("="));
        	textArea.append(" Vendor ID: "+Information.get(1)+", ");

        	Information = Arrays.asList(USBList[z][2].split("="));
        	textArea.append(" Product ID: "+Information.get(1)+", ");
        	
        	Information = Arrays.asList(USBList[z][3].split("="));
        	System.out.print("Product Serial Number: "+Information.get(1));
        	textArea.append("\n Product Serial Number: "+Information.get(1));
        	
        	textArea.append("\n\n");
        	
        	z++;
        }
        
        
		 
			textArea.setSize(600,600);    
		
			//textArea.setPreferredSize(new Dimension(650,600));

		    textArea.setLineWrap(false);
		    textArea.setEditable(false);
		    textArea.setVisible(true);
		    textArea.setWrapStyleWord(true);
		    textArea.setCaretPosition(0); 

		    JScrollPane scroll = new JScrollPane (textArea);
		    
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    scroll.getViewport().setPreferredSize(new Dimension(600, 200));
		    
		
		    c.gridx = 0;
		    c.gridy = 0;    
		    
		pan.add(scroll,c);
		JLabel label_usb = new JLabel("Select USB Key (Device Number):");
		c.gridx = 0;
	    c.gridy = 1;  
	    c.insets = new Insets(10,-70,0,0);
		pan.add(label_usb,c);
		
		c.insets = new Insets(10,-430,0,0);
		c.gridx = 1;
	    c.gridy = 1; 
	    JPasswordField USBSelect = new JPasswordField();
		USBSelect.setPreferredSize(new Dimension(50,30));
		pan.add(USBSelect,c);
		
		c.gridx = 0;
	    c.gridy = 2;  
	    c.insets = new Insets(10,0,0,0);
		JButton ConfirmUSB = new JButton("Confirm USB Key ");
		ConfirmUSB.setPreferredSize(new Dimension(200,30));
		pan.add(ConfirmUSB,c);
		
		JLabel usb_condition = new JLabel("USB Selected: "+JPanelEncyption.USBKey);
		c.gridx = 0;
	    c.gridy = 3;  
	    c.insets = new Insets(10,0,0,0);
		pan.add(usb_condition,c);
		
		JLabel error_message = new JLabel("");
		c.gridx = 0;
	    c.gridy = 4;  
	    c.insets = new Insets(10,0,0,0);
		pan.add(error_message,c);
		

		
		D1.add(pan,BorderLayout.CENTER);
		//D1.pack();
	    D1.validate();
		D1.setVisible(true);
		
		
		ConfirmUSB.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	            	
	            	error_message.setText("");
	            	boolean USBValid = true;
	            	
	            	
	            	String USBCheck = String.valueOf(USBSelect.getPassword());
	            	
	            	try {
	            	    int intcheck = Integer.parseInt(USBCheck);
	            	} catch (NumberFormatException e2) {
	            		
	            		USBValid = false;
	            		error_message.setText("USB Selected: [ERROR: Please input a number !]");
	            	    
	            	}
	            	
	            	
	            	System.out.println(USBValid);
	            	
	            	if (USBValid = true){
	            		
	            		int intcheck = Integer.parseInt(USBCheck);
	            		
	            		
	            		
	            		int MaxUSB = NumberofUSB-1;
	            		
	            		System.out.println("MAX: "+MaxUSB);
	            		
	            		if (intcheck > MaxUSB){
	            			
	            			USBValid = false;
		            		error_message.setText("USB Selected: [ERROR: Invalid Device Number]");
	            			
	            			
	            		}
	            		
	            		else {
	            			
	            			select=intcheck;
	            		}
	            		
	            		
	            		
	            	}
	            	
	            	
	            	String k = "lol";
	                //Execute when button is pressed
	            	test("PlaceHolder");
	            	test("PlaceHolder");
	                System.out.println(NumberofUSB);
	                System.out.println(k);
	                System.out.println(USBSelect.getPassword());
	                System.out.println(USBValid);
	                
	                if (USBValid=true){
	                	
	                	
	                	
	                	JPanelEncyption.USBKey=(USBList[select][1]+"///"+USBList[select][6]+"///"+USBList[select][2]+"///"+USBList[select][3]+"///");
	                	//usb_condition.setText("USB Selected: "+JPanelEncyption.USBKey);
	                	System.out.println(JPanelEncyption.USBKey);
	                	JPanelEncyption.label_USB.setText(USBList[select][5]+", "+USBList[select][6]+", "+USBList[select][3]);
	                	for( int i = 0; i < USBList.length; i++ )
	                		   Arrays.fill( USBList[i], null );
	                	D1.dispose();
	                	D1.dispose();
	                }
	               // D1.dispose();

	            }
	        });      
	 
		
		
	}
	
	public static void test(String l){
		
		System.out.println(l);
		
	}

}
