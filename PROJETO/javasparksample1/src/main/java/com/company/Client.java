package com.company;

// Java implementation for a client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class Client
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner scn = new Scanner(System.in);
            Scanner soc = new Scanner(System.in);
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");


            // establish the connection with server port 5056
            Socket s;
            System.out.println("Bem Vindo á DSCalculator!\nDigite 1 para poder acessar as seguintes funções:\n-Soma(+);\n-Subtração(-);\n-Multiplicação(*);\n-Divisão(\\);");
            System.out.println("Digite 2 para poder acessar as seguintes funções:\n-Porcentagem(%);\n-Potenciação(^);\n-Raiz Quadrada(!).\n");
            // obtaining input and out streams
            int num = soc.nextInt();
            if (num == 1) {
                // establish the connection with server port 5056
                s = new Socket(ip, 5056);
            }else {
                s = new Socket(ip, 5057);
            }


            // the following loop performs the exchange of
            // information between client and client handler
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            while (true)
            {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                if(tosend.equals("Voltar")){
                    System.out.println("DIGITE 1 PARA AS OPERAÇÕES BÁSICAS(SOMA, SUB, MULT, DIV) e 2 PARA AS OPERAÇÕES AVANÇADAS (PORCENT, RAIZ E PONT)");
                    // obtaining input and out streams
                    num = soc.nextInt();
                    if (num == 1) {
                        // establish the connection with server port 5056
                        s = new Socket(ip, 5056);
                    }else {
                        s = new Socket(ip, 5057);
                    }

                    dis = new DataInputStream(s.getInputStream());
                    dos = new DataOutputStream(s.getOutputStream());

                }else{
                    // printing date or time as requested by client
                    String received = dis.readUTF();
                    System.out.println(received);
                }


            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
