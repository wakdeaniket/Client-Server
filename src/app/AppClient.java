package app;  
    import java.awt.*;
	import java.awt.event.*;
	import java.awt.Color.*;
    import java.io.*;
    import java.net.*;
    import javax.swing.*;
    
	/**

	 * @author ANIKET WAKDE

	 * @mail wakdeaniket@gmail.com

	 * 

	 */

	public class AppClient extends JFrame implements ActionListener,Runnable

	{

	 //Declarations

	 Button b;

	 TextField tf;

	 TextArea ta;

	 Socket s;

	 PrintWriter pw;

	 BufferedReader br;

	 Thread th;
     
	 private ImageIcon image;
	 private JLabel label;
	  

	 public AppClient()

	 {

	  Frame f=new Frame("Its ANII");//Frame for Client
      
	  f.setLayout(new FlowLayout());//set layout

	  f.setBackground(Color.green);//set background color of the Frame
	  
	  image=new ImageIcon(getClass().getResource("/imgs/img2.JPG"));
      label=new JLabel(image);
      label.setSize(70,70);
      label.setVerticalAlignment(JLabel.CENTER);
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setBackground(Color.BLACK);
      f.add(label);
      
	  b=new Button("Send");//Send Button
	  b.setBackground(Color.magenta);
      
	  b.addActionListener(this);//Add action listener to send button.

	  f.addWindowListener(new W1());//add Window Listener to the Frame

	  tf=new TextField(20);

	  ta=new TextArea(18,27);

	  ta.setBackground(Color.cyan);

	  f.add(tf);//Add TextField to the frame

	  f.add(b);//Add send Button to the frame
	  f.add(ta);//Add TextArea to the frame
      
	  
	  try{

	   s=new Socket(InetAddress.getLocalHost(),12000);//Socket for client

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

	  f.setVisible(true);

	  f.setLocation(100,100);//set the location

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
		   String s;
		   s=br.readLine();

	    ta.append("pagal: "+s+"\n");//Append to TextArea

	   }catch(Exception e) {}

	  }

	 }

	 //Main method

	 public static void main(String args[])

	 {

	  //Instantiate AppClient class

	  AppClient client = new AppClient();
	  
      client.setVisible(true);
      client.pack();
	 }

	}