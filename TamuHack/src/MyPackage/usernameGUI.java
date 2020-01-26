package MyPackage;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.util.*;

public class usernameGUI implements KeyListener, ActionListener {
 
    JFrame frame;
    JTextField tf;
    JLabel lbl;
    JButton btn;
    String characters = "";
    ArrayList times = new ArrayList();
    public usernameGUI() 
    {
    	Date date = new Date();
        frame = new JFrame("Username and Password");
        lbl = new JLabel();
        tf = new JTextField(30);
        tf.addKeyListener(this);
        JTextField tf2 = new JTextField(30);
        tf2.addKeyListener(this);

        JLabel label1 = new JLabel("             Email:");
        JLabel label2 = new JLabel("   ");
        JLabel label3 = new JLabel("Password:");

        JPanel panel = new JPanel();
        panel.add(label1);
        panel.add(tf);
        panel.add(label2);
        panel.add(label3);
        panel.add(tf2);

        frame.setLayout(new BorderLayout());
        frame.add(lbl, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 100);
        frame.setVisible(true);
    }

	    @Override
	    public void keyTyped(KeyEvent ke) 
	    {
	    	char typed = ke.getKeyChar();
	    	String stringVar = Character.toString(typed);
	    	characters += typed;
	    	String[] id = characters.split("\\R");
	    	
	    	long startTime = 0;
			if(id.length >= 2)
	    		startTime = System.nanoTime();
				if(startTime != 0)
					times.add(startTime);

	    		if(stringVar.equals("\n") && id.length >= 2)
	        	{
	        		try
	        		{
	        		FileWriter fileWriter = new FileWriter("passwordData.txt");
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
	        		
	    	        for (int i = 0; i < timeDiffs.size() - 1; i+= 1)
	    	        	timingsStr += String.valueOf(timeDiffs.get(i)) + " ";
	    	        fileWriter.write(timingsStr);			
	    	        fileWriter.close();
	    	        UserNames user1 = new UserNames(id[0],id[1]);

	        		}
	        		catch (IOException e) 
	        		{
	      		      System.out.println("An error occurred.");
	      		      e.printStackTrace();
	      		}     		
	        }

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