/**
 * 
 */
import java.io.*;
import java.util.*;
import javax.swing.*;

import MyPackage.*;

/**
 * @author alexakomer
 *
 */
public class FileClass 
{

	/**
	 * 
	 */
	public FileClass() 
	{

	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// To run this code correctly, type an email into the first blank and press enter
		// Then, press on the password box text field, type your password and press enter
		// Finally, go to the next GUI and type your password again and press enter
		// If you type your password at a speed different from your original attempt, you will receive a cautionary email
		// Currently, backspacing isn't supported; making an error means you will have to rerun the program
		
		// This is a prototype for an additional layer of security for any sort of login
				
		keylistener secondGUI = new keylistener();
		usernameGUI firstGUI = new usernameGUI();
	}
}
