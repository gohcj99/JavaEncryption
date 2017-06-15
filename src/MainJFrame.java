import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainJFrame  {
	
	static JFrame frame;
	static JPanel panel;
	
	static JLabel label1;
	
	static JButton encrypt;
	static JButton decrypt;
	
	
	
	public MainJFrame(){
		
		boolean sigarcheck = true;
		
		try{
		
		System.loadLibrary("/dll/sigar-amd64-winnt");
		
		}
		
		catch (UnsatisfiedLinkError e) {
			
			frame = new JFrame("Basic Java Encyption Program");
			frame.setVisible(true);
			frame.setSize(600, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			
			
			label1 = new JLabel("                               ERROR: sigar-amd64-winnt.dll is not found in root directory");
			frame.add(label1);
			sigarcheck=false;
			
		}
		
		if (sigarcheck==true){
		
			frame = new JFrame("Basic Java Encyption Program");
			frame.setVisible(true);
			frame.setSize(600, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			GUI();
		
		}
	}
	
	public static void GUI(){
		
		
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.LIGHT_GRAY);
		
		label1 = new JLabel("Select Task");
		
		encrypt = new JButton("Encrypt");
		

		
		decrypt = new JButton("Decrypt");
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);
		
		c.gridx=0;c.gridy=0;
		
		panel.add(label1,c);

		
		c.gridx=0;c.gridy=3;
			
		panel.add(encrypt,c);
		
		c.gridx=1;c.gridy=3;
		
		panel.add(decrypt,c);
		
		encrypt.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  
			  JPanelEncyption.perform();

			    

			  
		  }
		});
		
		decrypt.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  
			  JPanelDecryption.perform();

			    

			  
		  }
		});
		
		frame.add(panel);
		
	}
	
	
	public static void main (String[] args){
		
		new MainJFrame();
		
	}
	

}
