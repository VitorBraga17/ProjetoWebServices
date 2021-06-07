package com.company;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
        staticFiles.location("/public");
        port(8081);

        get("/hello", (request, response) -> "Hello World!");

        post("/somar", (request, response) -> {
            String a = request.queryParams("num1");
            String b = request.queryParams("num2");
            String c = con(5056,a,b,"+");
            if((request.queryParams().stream().count()>2)){
                return "Quantidade de Parametros invalida";
            }
            return c;
        });

        post("/subtrair", (request, response) -> {
            String a = request.queryParams("num1");
            String b = request.queryParams("num2");
            String c = con(5056,a,b,"-");
            if((request.queryParams().stream().count()>2)){
                return "Quantidade de Parametros invalida";
            }
            return c;
        });
        post("/multiplicar", (request, response) -> {
            String a = request.queryParams("num1");
            String b = request.queryParams("num2");
            String c = con(5056,a,b,"*");
            if((request.queryParams().stream().count()>2)){
                return "Quantidade de Parametros invalida";
            }
            return c;
        });
        post("/dividir", (request, response) -> {
            String a = request.queryParams("num1");
            String b = request.queryParams("num2");
            String c = con(5056,a,b,"/");
            if((request.queryParams().stream().count()>2)){
                return "Quantidade de Parametros invalida";
            }
            return c;
        });
        post("/porcentagem", (request, response) -> {
            String a = request.queryParams("num1");
            String b = request.queryParams("num2");
            String c = con(5057,a,b,"%");
            if((request.queryParams().stream().count()>2)){
                return "Quantidade de Parametros invalida";
            }
            return c;
        });
        post("/raiz", (request, response) -> {
            String a = request.queryParams("num1");
            String c = con(5057,a,"","!");
            System.out.println((request.queryParams().stream().count()));
            if((request.queryParams().stream().count()>1)){
                return "Quantidade de Parametros invalida";
            }
            return c;
        });
        post("/potenciacao", (request, response) -> {

            String a = request.queryParams("num1");
            String b = request.queryParams("num2");
            String c = con(5057,a,b,"^");
            if((request.queryParams().stream().count()>2)){
                return "Quantidade de Parametros invalida";
            }
            return c;
        });


        /*post("/hello", (request, response) ->
                "Hello World: " + request.body()
        );

        get("/private", (request, response) -> {
            response.status(401);
            return "Go Away!!!";
        });

        get("/users/:name", (request, response) -> "Selected user: " + request.params(":name"));

        get("/news/:section", (request, response) -> {
            response.type("text/xml");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>" + request.params("section") + "</news>";
        });

        get("/protected", (request, response) -> {
            halt(403, "I don't think so!!!");
            return null;
        });

        get("/redirect", (request, response) -> {
            response.redirect("/news/world");
            return null;
        });

        get("/", (request, response) -> "root");*/
    }


    private static String renderTodos(Request req) {
        Map<String, Object> model = new HashMap<>();

        Integer a = 10;
        Integer b = 30;
        Integer resultado = a + b;

        model.put("nome", resultado);

        return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/index.vm"));
    }


    public static String con(int porta, String num1, String num2, String op) {
            try
            {
            //Scanner scn = new Scanner(System.in);
            //Scanner soc = new Scanner(System.in);
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, porta);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            String s3 = num1.concat(op);
            String s4 = s3.concat(num2);
            dos.writeUTF(s4);
            String received = dis.readUTF();
            dis.close();
            dos.close();
            return received;

            // establish the connection with server port 5056

            //System.out.println("Bem Vindo á DSCalculator!\nDigite 1 para poder acessar as seguintes funções:\n-Soma(+);\n-Subtração(-);\n-Multiplicação(*);\n-Divisão(\\);");
            //System.out.println("Digite 2 para poder acessar as seguintes funções:\n-Porcentagem(%);\n-Potenciação(^);\n-Raiz Quadrada(!).\n");
            // obtaining input and out streams
            //int num = soc.nextInt();
            /*if (num == 1) {
                // establish the connection with server port 5056
                s = new Socket(ip, 5056);
            } else {
                s = new Socket(ip, 5057);
            }*/


            // the following loop performs the exchange of
            // information between client and client handler


                //System.out.println(dis.readUTF());
                //String tosend = scn.nextLine();


                // If client sends exit,close this connection
                // and then break from the while loop
                /*if (tosend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }*/

                /*if (tosend.equals("Voltar")) {
                    System.out.println("DIGITE 1 PARA AS OPERAÇÕES BÁSICAS(SOMA, SUB, MULT, DIV) e 2 PARA AS OPERAÇÕES AVANÇADAS (PORCENT, RAIZ E PONT)");
                    // obtaining input and out streams
                    num = soc.nextInt();
                    if (num == 1) {
                        // establish the connection with server port 5056
                        s = new Socket(ip, 5056);
                    } else {
                        s = new Socket(ip, 5057);
                    }

                    dis = new DataInputStream(s.getInputStream());
                    dos = new DataOutputStream(s.getOutputStream());

                } else {
                    // printing date or time as requested by client

                    System.out.println(received);
                }*/



            // closing resources

            }catch(Exception e){
                String x = "";
                return x;
            }

    }
}

