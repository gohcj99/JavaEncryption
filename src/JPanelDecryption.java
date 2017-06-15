import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import net.codejava.crypto.CryptoException;

public class JPanelDecryption extends MainJFrame {
	
	static File FileDecrypt;
	
	public static void perform(){
		
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		
		
		JButton SelectFile = new JButton("Select File to Decrypt:");
		SelectFile.setPreferredSize(new Dimension(200,30));
		
		JButton Decrypt = new JButton("Decrypt File ");
		Decrypt.setPreferredSize(new Dimension(200,30));
		
		JButton Back = new JButton("Back");
		Back.setPreferredSize(new Dimension(200,30));
		
		JLabel label_file = new JLabel("No File Selected:");
		
		JLabel label_encrypt = new JLabel("No File to Decrypt.");
		
		JTextArea textArea = new JTextArea(" Decryption information: \n\n"
				+ " 1) Ensure you type in the same password during the encyption process.\n\n"
				+ " 2) If a USB device (USB Key) is used during the encyption process\n ensure the same USB device is connected.\n\n"
				+ " 3) If a CPU (CPU Key) is used during the encyption process\n ensure the current machine have the same CPU as the CPU Key.\n\n"
				+ " The decypted file will be in the same directory as the file \n you selected but with '-Decypted' added at the end of the file name.");
		
		
			
		
		   GridBagConstraints c = new GridBagConstraints();
		    
		    c.insets = new Insets(5,5,10,5);
		    
		    //label_file.setText("Select Encyption Key to use.");
		    
			JLabel label_password = new JLabel("Enter Decryption Password:");
			JPasswordField password = new JPasswordField();
			password.setPreferredSize(new Dimension(300,30));
		    
			c.gridx=0;c.gridy=0;
			
			panel.add(label_password,c);
			
			c.gridx=0;c.gridy=1;
			
			panel.add(password,c);
			
			c.gridx=0;c.gridy=2;
			
			panel.add(SelectFile,c);
			
			c.gridx=0;c.gridy=3;
			
			panel.add(label_file,c);
			
			c.gridx=0;c.gridy=4;
			
			panel.add(Decrypt,c);
			
			c.gridx=0;c.gridy=5;
			
			panel.add(label_encrypt,c);
			
			c.gridx=0;c.gridy=6;
			
			panel.add(Back,c);
			
			c.gridx=0;c.gridy=7;
			
			panel.add(textArea,c);
			
			
			
			
			
			SelectFile.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e) 
			  {
				  
				  
				  FileDecrypt = FileBrowser.SelectFile();
				  String str = FileDecrypt.toString().substring(FileDecrypt.toString().length() - 50);
				  label_file.setText("Selected File: [..."+str+"]");
				  
			  }
			});
			
			Decrypt.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e) 
			  {
				  
				 char[] P1 = password.getPassword();
				 String password= String.valueOf(P1);
				 boolean DecryptStatus = false;
				 File Outputfile1 = ObtainOutputPath.Path(FileDecrypt,"decrypt","");
				 try {
					 DecryptStatus=DecryptionProcess.task(password,FileDecrypt);
				} catch (UnsupportedEncodingException | NoSuchAlgorithmException | CryptoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				if (DecryptStatus==true){
					
					label_encrypt.setText("Encyption Successfull!");
					
				}
				
				else {
					
					label_encrypt.setText("Decyption Failed ! Wrong Key(s) or file provided.");
				}
				 
			  
				  
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
					FileDecrypt=null;

			  }
			});
		    
			
		
	}

}
