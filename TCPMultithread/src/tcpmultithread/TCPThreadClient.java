/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpmultithread;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Asus
 */
public class TCPThreadClient {
    private static InetAddress host;
    private static final int PORT = 9393;
    
 public static void main(String[] args)
 {
     try
     {
       host = InetAddress.getByName("127.0.0.1");
     }
     catch(UnknownHostException uhEx)
     {
         System.out.println("\nHost ID not found!\n");
    System.exit(1);
     }
     sendMessages();
 } 
 private static void sendMessages()
 {
     Socket socket = null;

  try
  {
      socket = new Socket(host,PORT);
   Scanner networkInput =
           new Scanner(socket.getInputStream());
   PrintWriter networkOutput =
           new PrintWriter(
                   socket.getOutputStream(),true);
   
   //Set up stream for keyboard entry...
   Scanner userEntry = new Scanner(System.in); 
   String message, response;
   do
   {
       System.out.print(
               "Enter message ('QUIT' to exit): ");
     message =  userEntry.nextLine(); 
    //Send message to server on the
    //socket's output stream...
    
    //Accept response from server on the
    //socket's intput stream...
    networkOutput.println(message);
    response = networkInput.nextLine(); 
    //Display server's response to user...
    System.out.println(
            "\nSERVER> " + response);
   }while (!message.equals("QUIT"));
  }
  catch(IOException ioEx) 
{
    ioEx.printStackTrace();
} 
  finally
  {
      try
      {
          System.out.println(
                  "\nClosing connection...");
          socket.close();
      }
      catch(IOException ioEx)
      {
          System.out.println(
                  "Unable to disconnect!");
          System.exit(1);
      }
  }
 }
}