package app;

import java.awt.*;

	import java.awt.event.*;

	import java.io.*;

	import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

	/**
06
	 * @author Tapas
07
	 * @mail tapas.friends@gmail.com
08
	 * @blog tapas4web.blogspot.com
09
	 */

	public class AppServer extends Frame implements ActionListener,Runnable

	{

	 //Declarations

	 Button b1;

	 TextField tf;

	 TextArea ta;

	 ServerSocket ss;

	 Socket s;

	 PrintWriter pw;

	 BufferedReader br;

	 Thread th;

	 private ImageIcon image;
	 private JLabel label; 
     
	 public AppServer()

	 {

	  Frame f=new Frame("SHRAA");//Frame for Server

	  f.setLayout(new FlowLayout());//set layout

	  f.setBackground(Color.orange);//set background color of the Frame
	  
	  image=new ImageIcon(getClass().getResource("/imgs/img1.JPG"));
      label=new JLabel(image);
      label.setSize(70,70);
      label.setVerticalAlignment(JLabel.TOP);
      label.setHorizontalAlignment(JLabel.LEFT);
      label.setBackground(Color.BLACK);
      
      f.add(label);

	  b1=new Button("Send");//Send Button

	  b1.setBackground(Color.pink);

	  b1.addActionListener(this);//Add action listener to send button.

	  tf=new TextField(20);

	  ta=new TextArea(18,27);

	  ta.setBackground(Color.cyan);

	  f.addWindowListener(new W1());//add Window Listener to the Frame

	  f.add(tf);//Add TextField to the frame35
	  f.add(b1);//Add send Button to the frame

	  f.add(ta);//Add TextArea to the frame

	  try{

	   ss=new ServerSocket(12000);//Socket for server

	   s=ss.accept();//accepts request from client

	   System.out.println(s);

	   //below line reads input from InputStreamReader

	   br=new BufferedReader(new InputStreamReader(s.getInputStream()));

	   //below line writes output to OutPutStream

	   pw=new PrintWriter(s.getOutputStream(),true);

	  }catch(Exception e)

	  {

	  }

	  th=new Thread(this);//start a new thread

	  th.setDaemon(true);//set the thread as demon

	  th.start();

	  setFont(new Font("Arial",Font.BOLD,20));

	  f.setSize(250,450);//set the size

	  f.setLocation(400,100);//set the location

	  f.setVisible(true);

	  f.validate();

	 }

	 //method required to close the Frame on clicking "X" icon.

	 private class W1 extends WindowAdapter

	 {

	  public void windowClosing(WindowEvent we)

	  {

	   System.exit(0);

	  }

	 }

	 //This method will called after clicking on Send button.

	 public void actionPerformed(ActionEvent ae)

	 {

	  
	  ta.append("Me: "+tf.getText()+"\n");
	  pw.println(tf.getText());//write the value of textfield into PrintWriter

	  tf.setText("");//clean the textfield

	 }

	 //Thread running as a process in background

	 public void run()

	 {

	  while(true)

	  {

	   try{

	    String s=br.readLine();//reads the input from textfield

	    ta.append("Anii: "+s+"\n");//Append to TextArea

	   }catch(Exception e)

	   {

	   }

	  }

	 }

	 //Main method

	 public static void main(String args[])

	 {

	  //Instantiate AppServer class

	  AppServer server = new AppServer();

	 }

	} 