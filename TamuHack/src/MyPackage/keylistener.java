package MyPackage;
import java.io.*; 
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class keylistener implements KeyListener, ActionListener {
 
    JFrame frame;
    JTextField tf;
    JLabel lbl;
    JButton btn;
    String characters = "";
    ArrayList times = new ArrayList();
    private static String email;
    
    public keylistener() throws FileNotFoundException {
    	Date date = new Date();
        frame = new JFrame("Password Rhythm");
        lbl = new JLabel();
        tf = new JTextField(15);
        tf.addKeyListener(this);
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Retype Password:");
        panel.add(label1);
        panel.add(tf);
 
        frame.setLayout(new BorderLayout());
        frame.add(lbl, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(300, 70);
        frame.setLocation(0, 120);
        frame.setVisible(true);
    }
 
    public static boolean countMistakes() throws FileNotFoundException
	{
		Scanner scanOG = new Scanner(new File("passwordData.txt"));
		Scanner scanAttempt = new Scanner(new File("attemptedPassword.txt"));
		
		email = scanOG.nextLine();
		String passOG = scanOG.nextLine();
		String passAttempt = scanAttempt.nextLine();
		
		if(!passOG.equals(passAttempt))
		{
			System.out.println("The passwords don't match!");
		}
		
		scanOG.nextLine();
		String[] strTimesOG = scanOG.nextLine().split(" ");
		String[] strTimesAttempt = scanAttempt.nextLine().split(" ");

		ArrayList<Long> timesOG = new ArrayList<Long>();
		List<Long> timesAttempt = new ArrayList<>();
		for(int i = 0; i < strTimesOG.length; i++)
		{
			timesOG.add(Long.parseLong(strTimesOG[i]));
			timesAttempt.add(Long.parseLong(strTimesAttempt[i]));
		}
		
		int mistakes = 0;
		double tol = 0.75;
		
		for(int i = 0; i < timesOG.size(); i++)
		{
			if(timesOG.get(i) * (1 - tol) > timesAttempt.get(i) || timesOG.get(i) * (1 + tol) < timesAttempt.get(i))
			{
				mistakes++;
			}
		}
		
		scanOG.close();
		scanAttempt.close();
		
		double ratio = (double)mistakes / (double)timesOG.size();
		boolean good = true;
		if(ratio > 0.30)
			good = false;
		return good;
	}
    
    @Override
    public void keyTyped(KeyEvent ke) {
        long startTime = System.nanoTime();
    	char typed = ke.getKeyChar();
    	String stringVar = Character.toString(typed);
    	if(stringVar.equals("\n"))
    	{
    		try
    		{
    		FileWriter fileWriter = new FileWriter("attemptedPassword.txt");
    		fileWriter.write(characters + "\n");	
    		String timingsStr = "";
    		
    		long original = (long)times.get(0);
    		ArrayList timeDiffs = new ArrayList();
    		for(int i = 0; i < times.size(); i++)
    		{
    			long value = (long)times.get(i);
    			long difference = value - original;
    			timeDiffs.add(difference);
    		}
	        for (int i = 0; i < timeDiffs.size(); i+= 1)
	        	timingsStr += String.valueOf(timeDiffs.get(i)) + " ";
	        
	        fileWriter.write(timingsStr);		
	        fileWriter.close();
	        boolean goodAttempt = countMistakes();
	        if(goodAttempt)
	        {
	        	System.out.println("Good job!");
	        }
	        else
	        {
	        	try {
					sendMail foo = new sendMail(email);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        System.exit(0);
	        
    		}
    		catch (IOException e) 
    		{
  		      System.out.println("An error occurred.");
  		      e.printStackTrace();
  		}
    	}

    	times.add(startTime);	
    	characters += typed;   
    }
  
    @Override
    public void keyPressed(KeyEvent ke) 
    {
      
    }
 
    @Override
    public void keyReleased(KeyEvent ke) 
    {
        
    }
 
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        
    }

	public static void keylistener() 
	{

		
	}
}