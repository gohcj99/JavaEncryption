import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.xml.bind.DatatypeConverter;

import org.hyperic.sigar.SigarException;

import net.codejava.crypto.CryptoException;

public class JPanelEncyption extends MainJFrame {
	
	static String TextKey="NULL"; 
	static String USBKey="NULL";
	static String CPUKey="NULL";
	static String FinalKey="NULL";
	
	static boolean USBKeyUse=false;
	static boolean CPUKeyUse=false;

	static JLabel label_USB = new JLabel("No USB Key Selected:");
	static JLabel error_check = new JLabel("No Error(s)");
	
	static File file;
	static File OutputFilePrint;
	static JButton E1;
	static JButton E2;
	static JButton E3;
	static JButton E4;
	static JButton Back;

	
	public static void perform(){
		
		panel.removeAll();
		  
	    E1 = new JButton("PlainText Key only");
	    E1.setPreferredSize(new Dimension(300,30));
	    E2 = new JButton(" PlainText Key + USB Key");
	    E2.setPreferredSize(new Dimension(300,30));
	    E3 = new JButton("PlainText Key + CPU Key.");
	    E3.setPreferredSize(new Dimension(300,30));
	    E4 = new JButton("PlainText Key + USB Key + CPU Key.");
	    E4.setPreferredSize(new Dimension(300,30));
	    
	    Back = new JButton("Back");
	    Back.setPreferredSize(new Dimension(300,30));
	    
	    
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.insets = new Insets(5,5,10,5);
	    
	    label1.setText("Select Encyption Key(s) to use.");
	    
		c.gridx=0;c.gridy=0;
		
		panel.add(label1,c);
	    
		c.gridx=0;c.gridy=1;
		
		panel.add(E1,c);
		
		c.gridx=0;c.gridy=2;
		
		panel.add(E2,c);
		
		c.gridx=0;c.gridy=3;
		
		panel.add(E3,c);
		
		c.gridx=0;c.gridy=4;
		
		panel.add(E4,c);
		
		c.gridx=0;c.gridy=5;
		
		panel.add(Back,c);
		
		JTextArea textArea = new JTextArea(" Encryption Information:\n\n"
				+ " The key(s) you selected will need to be typed/present during the decyption process using this software. \n\n"
				+ " PlainText key = A combination of text, numbers, and special characters will be used as a key.\n\n"
				+ " USB Key = Select a currently connected USB device to be used as a key.\n\n"
				+ " CPU Key = Your current machine CPU will be used as a key.\n\n"
				+ " The Encrypted file will be saved in the same directory as your selected file \n but with '-Encrypted(Key Used)' added at the end of the file name.");
		
		c.gridx=0;c.gridy=6;
		panel.add(textArea,c);
		
		panel.revalidate();
		panel.repaint();
		

		
		E1.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  USBKeyUse = false;
			  CPUKeyUse = false;
			  USBKey="NULL";
			  CPUKey="NULL";
			  
			  encrypt(1);
			  
			  System.out.println("E1");
			 

		  }
		});
		
		E2.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  USBKeyUse = true;
			  CPUKeyUse = false;
			  encrypt(2);
			  
			  System.out.println("E2");

		  }
		});
		
		E3.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  USBKeyUse = false;
			  CPUKeyUse = true;
			  
			  encrypt(3);
			  
			  System.out.println("E3");

		  }
		});
		
		E4.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  USBKeyUse = true;
			  CPUKeyUse = true;
			  encrypt(4);
			  
			  System.out.println("E4");

		  }
		});
		
		Back.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
				panel.removeAll();

				
				MainJFrame.GUI();
				
				panel.revalidate();
				panel.repaint();

		  }
		});
	    
		
	}
	
	public static void encrypt(int select){
			
			
			JDialog D1 = new JDialog(frame , "Enter Encryption Key(s)", true);  
			D1.setSize(1000,500);
			D1.setResizable(true);
			D1.setBackground(Color.gray);
			
			D1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			D1.addWindowListener(new WindowAdapter() {

		        @Override
		        public void windowClosing(WindowEvent arg0) {
		        	TextKey="NULL"; 
		        	USBKey="NULL";
		        	CPUKey="NULL";
		        	FinalKey="NULL";
		        	
		        	USBKeyUse=false;
		        	CPUKeyUse=false;
					  label_USB.setText("No USB Key Selected:");
		        }



		    });


			
			JPanel pan= new JPanel(new GridBagLayout());
	   
		    JLabel password_info = new JLabel("NOTE: Password must be 8 length long At least one Upper Case, one Lower Case, Number, and one Special Character.");
		    
			JLabel label_password = new JLabel(" Password:");
			JPasswordField password = new JPasswordField();
			password.setPreferredSize(new Dimension(300,30));
			
			JLabel label_password2 = new JLabel(" Confirm Password:");
			JPasswordField password2 = new JPasswordField();
			password2.setPreferredSize(new Dimension(300,30));
			
			JButton Confirm = new JButton("Confirm Encyption Data");
			Confirm.setPreferredSize(new Dimension(200,30));
			
			JButton SelectFile = new JButton("Select File:");
			SelectFile.setPreferredSize(new Dimension(200,30));
			
			JLabel label_file = new JLabel("No File Selected:");
			
			
			
			JButton SelectUSB = new JButton("Select USB Key:");
			SelectUSB.setPreferredSize(new Dimension(200,30));
			
			//JLabel label_USB = new JLabel("No USB Key Selected:");
			
			
			GridBagConstraints a = new GridBagConstraints();
			a.insets = new Insets(-100,10,5,5);
			a.gridx=0;a.gridy=0;
			pan.add(password_info,a);
			
			
			a.insets = new Insets(5,-320,5,5);
			
			a.gridx=0;a.gridy=1;
			pan.add(label_password,a);
			a.gridx=0;a.gridy=2;
			pan.add(password,a);
			a.gridx=0;a.gridy=3;
			pan.add(label_password2,a);
			a.gridx=0;a.gridy=4;
			pan.add(password2,a);
			a.gridx=0;a.gridy=5;
			pan.add(SelectFile,a);
			
			if (select == 2 || select == 4){
				
				a.gridx=0;a.gridy=6;
				pan.add(SelectUSB,a);
				
				
				a.gridx=1;a.gridy=6;
				pan.add(label_USB,a);
				
			}
			
			a.insets = new Insets(5,-320,5,5);
			a.gridx=0;a.gridy=7;
			pan.add(Confirm,a);
			
			a.gridx=0;a.gridy=8;
			pan.add(error_check,a);
			
			
			a.gridx=1;a.gridy=5;
			pan.add(label_file,a);
			
			a.insets = new Insets(5,-320,5,5);
			

			
			SelectFile.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e) 
			  {
				  

					  
				  file = FileBrowser.SelectFile();
				  

				  label_file.setText("["+file.toString()+"]");
				  
			  }
			});
			
			SelectUSB.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e) 
			  {
				  
				  
				  JPanelUSB.task(D1);
				  
// Starts
				  
				  
				  
				  
				  
				  
				
			  }
			});
			
			Confirm.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e) 
			  {
				  
				  boolean EncyptionCheck = true;
				  
				  if (PasswordVerify.PasswordCheck2(password.getPassword())==false){
					  
					  EncyptionCheck = false;
				  }
				  
				  
				  
				  if (PasswordVerify.PasswordCheck1(password.getPassword(),password2.getPassword())==false && EncyptionCheck == true){
					  
					  
					 
					  
					  EncyptionCheck = false;
				  }
				  
  
				  if (file==null){
					  System.out.println("No File");
					  error_check.setText("Error: No File Selected.");
					  EncyptionCheck = false;
				  }
				  
				  if (USBKeyUse==true && USBKey=="NULL"){
					  error_check.setText("Error: No USB Key Selected.");
					  EncyptionCheck = false;
				  }
				  
				  if (USBKeyUse==true && USBKey=="NULL"){
					  error_check.setText("Error: No USB Key Selected.");
					  EncyptionCheck = false;
				  }
				  
				  if (USBKeyUse==true){
					  
					  try {
						CPUKey=  ObtainCPU.data();
					} catch (SigarException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				  
				  
				 
				  
				  System.out.println(USBKeyUse);
				  System.out.println(USBKey);
				  TextKey = String.valueOf(password.getPassword());
				 
				  
				  FinalKey=TextKey+"]]"+USBKey+"]]"+CPUKey;
				  String Salt = Integer.toString(FinalKey.length());
				  FinalKey=FinalKey+Salt;
				    
				  System.out.println("Length: "+FinalKey.length());
				  
				  System.out.println("Final Key: "+ FinalKey);

				  ObtainOutputPath.Path(file,"encypt","1");
				  System.out.println(EncyptionCheck);
				  
				  if (EncyptionCheck==true){
					  
						try {
							
							MessageDigest md   = MessageDigest.getInstance("MD5"); //make sure it exists, there are other algorithms, but I prefer SHA for simple and relatively quick hashing
							String strToEncode = FinalKey;
							md.update(strToEncode.getBytes("UTF-8")); //I'd rather specify the encoding. It's platform dependent otherwise. 
							byte[] digestBuff = md.digest();
							
							String KeyUsed = "Password";
							
							if (USBKeyUse==true){
								
								KeyUsed = KeyUsed+"USB";
								
							}
							
							if (CPUKeyUse==true){
								
								KeyUsed = KeyUsed+"CPU";
								
							}
							
							
							
							String password = DatatypeConverter.printHexBinary(digestBuff);
							
							System.out.println(password);
							
							
							File Outputfile = ObtainOutputPath.Path(file,"encypt",KeyUsed);
							KeyUsed = "Password";
							
							OutputFilePrint=Outputfile;
							
							CryptoUtils.encrypt(digestBuff, file, Outputfile);
							
							System.out.println("The Encyption Process is now complete.");
							
							
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException | CryptoException  e2) {


							
							
							e2.printStackTrace();
						} 

					  FinalKey = "NULL";
					  label_USB.setText("No USB Key Selected:");
					  pan.removeAll();
					  	
					  String OutputFile = OutputFilePrint.toString();
					  
					  System.out.println(OutputFile);
					  
					  JTextArea textArea = new JTextArea("Encyption Status: Success \n\n");
					  
					  textArea.append("USB Key Use: [" +USBKeyUse+"]\n\n");
					  textArea.append("CPU Key Use: [" +CPUKeyUse+"]\n\n");
					  
					  textArea.append("Output File: "+OutputFile);
					  
					  a.insets = new Insets(5,5,5,5);
					  a.gridx=0;a.gridy=0;
					  pan.add(textArea,a);
					  
					  
						JButton Back = new JButton("Click Here to Close this message");
						
						
						Back.setPreferredSize(new Dimension(500,30));
						 a.gridx=0;a.gridy=1;
						pan.add(Back,a);
						
						
					  pan.revalidate();
					  pan.repaint();
					  D1.add(pan,BorderLayout.CENTER);
					  
					  Back.addActionListener(new ActionListener()
						{
						  public void actionPerformed(ActionEvent e) 
						  {
							  
							  
							  D1.dispose();
							  
						  }
						});
					  
					  
					  
					  
				  }
				  
			  }
			});
			
			
			
			D1.add(pan,BorderLayout.WEST);
			
		    D1.validate();
			D1.setVisible(true);
			
			//MainJFrame.frame.setEnabled(false);
		
	
		
		
		//File file = FileBrowser.SelectFile();

	}
	

}
