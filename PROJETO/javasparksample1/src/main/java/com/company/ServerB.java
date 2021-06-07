package com.company;
// Java implementation of  Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java
// COMO LIBERAR A PORTA: netstat -nao | find "5056" -- taskkill /PID 18856 /F
import java.io.*;
import java.lang.*;
import java.math.*;
import java.net.*;
// Server class
public class ServerB
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5057);
        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket s = null;
            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected: " + s);
                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");
                // create a new thread object
                ClientHandlerB t = new ClientHandlerB(s, dis, dos);
                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}
