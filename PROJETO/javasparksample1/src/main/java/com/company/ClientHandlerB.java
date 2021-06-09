package com.company;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// ClientHandler class
class ClientHandlerB extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandlerB(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run()
    {
        String received;
        String toreturn;
        String tokens[];
        float op1,op2;
        int count, count2 = 0;


        while (count2 < 1)
        {
            try {

                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("Voltar"))
                {
                    try
                    {
                        // closing resources
                        this.dis.close();
                        this.dos.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    this.s.close();
                    break;
                }

                if(received.equals("Exit"))
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                // creating Date object
                // write on output stream based on the
                // answer from the client
                if(received.contains("!")){
                    count=0;
                    for(int i=0; i < received.length(); i++)
                    {
                        if (received.trim().charAt(i) == '!'){
                            count++;
                        }
                    }

                    if (count == 1){

                    try {
                        tokens = received.split("!");
                        tokens[0].trim();
                        op1 = Float.parseFloat(tokens[0]);
                    }catch (Exception ex)
                    {
                        dos.writeUTF("Entrada Inválida");
                        break;
                    }
                    if (tokens.length == 1){
                        toreturn = String.valueOf(Math.sqrt(op1));
                        dos.writeUTF(toreturn);
                    }else {
                        dos.writeUTF("Entrada Inválida");
                        break;
                    }
                }else {
                    dos.writeUTF("Entrada inválida");
                        break;
                }
                }else {
                    if(received.contains("%")){
                        count=0;
                        for(int i=0; i < received.length(); i++)
                        {
                            if (received.trim().charAt(i) == '%'){
                                count++;
                            }
                        }

                        if (count == 1){
                        try {
                            tokens=received.split("%");
                            tokens[0].trim();
                            tokens[1].trim();
                            op1 = Float.parseFloat(tokens[0]);
                            op2 = Float.parseFloat(tokens[1]);
                        }catch (Exception ex)
                        {
                        dos.writeUTF("Entrada invalida");
                            break;
                        }
                        if (tokens.length >2){
                            dos.writeUTF("Entrada invalida");
                            break;
                        }else {
                            toreturn = String.valueOf((op1 * op2) / 100);
                            dos.writeUTF(toreturn);
                        }
                    }else {
                        dos.writeUTF("Entrada invalida");
                        break;
                    }
                    }else{
                        if(received.contains("^")){
                            count=0;
                            for(int i=0; i < received.length(); i++)
                            {
                                if (received.trim().charAt(i) == '^'){
                                    count++;
                                }
                            }

                            if (count == 1){
                            try {
                                tokens = received.split("\\^");
                                tokens[0].trim();
                                tokens[1].trim();
                                op1 = Float.parseFloat(tokens[0]);
                                op2 = Float.parseFloat(tokens[1]);
                            } catch (Exception ex)
                            {
                                dos.writeUTF("Entrada invalida");
                                break;
                            }if (tokens.length >2){
                                dos.writeUTF("Entrada invalida");
                                    break;
                            }else {
                                toreturn = String.valueOf(Math.pow(op1, op2));
                                dos.writeUTF(toreturn);
                            }
                        }else {
                            dos.writeUTF("Entrada invalida");
                                break;
                        }
                        }else{
                            toreturn = "Entrada invalida";
                            dos.writeUTF(toreturn);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            count2++;
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
