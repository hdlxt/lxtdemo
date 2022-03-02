package com.example.springbootwebsocket.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String ip_adress = "127.0.0.1";
    private final int port = 5210;

    private PrintWriter client_out;   //发送消息给服务器的输出流
    private BufferedReader client_in;  //接收服务器消息的输入流
    private Socket client_socket;   //客户端套接字

    public  Client() throws IOException {
        //1-初始化
        client_socket = new Socket(ip_adress,port); //根据ip,端口连接服务器
        client_in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        client_out = new PrintWriter(client_socket.getOutputStream(),true);

        //2-开启新线程处理监听服务器端发来的消息
        new ClientThread().start();

        //3-客户端循环发送群聊，私聊消息
        while(true){
            //获取客户端的输入
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            client_out.println(input);  //发送消息给服务器

            //4-客户端退出服务器
            if(input.equals("exit")){
                client_out.close();
                client_in.close();
                client_socket.close();
                System.exit(0);
            }
        }
    }

    //-----------------------------------------------------------------
    /**
     *          监听服务器端发来的消息
     */
    private class ClientThread extends Thread{
        public void run(){
            try {
                //接收服务器消息
                String fromServer_data;
                while((fromServer_data=client_in.readLine()) != null){
                    System.out.println(fromServer_data);
                }
            } catch (IOException e) {
            }
        }
    }


    //启动客户端
    public static void main(String[] args) throws IOException {
        new Client();
    }
}

