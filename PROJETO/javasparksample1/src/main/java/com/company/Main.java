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
    }


    public static String con(int porta, String num1, String num2, String op) {
            try
            {
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

            // closing resources

            }catch(Exception e){
                String x = "Informe as Chaves e valores Corretamente para a operação(Keys:num1 e num2)";
                return x;
            }

    }
}

