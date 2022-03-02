package com.example.springbootwebsocket.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private int port = 5210;
    private ServerSocket server_socket;
    private Map<String,Socket> clients;   //存放所有访问服务器的客户端和其用户名

    public Server() throws Exception {
        //1-初始化
        server_socket = new ServerSocket(this.port);   //创建服务器
        clients = new HashMap<>();

        //2-每次接收一个客户端请求连接时都启用一个线程处理
        while(true) {
            //accept方法一直阻塞直到接收到一个客户端的请求 并返回该客户端的套接字socket
            Socket client_socket = server_socket.accept();
            //开启新线程处理客户端的请求
            new HandleClientThread(client_socket).start();
        }
    }

    /** ----------------------------------------------------------------------------------------------------
     *      启用一个线程处理一个客户端的请求
     */
    private class HandleClientThread extends Thread{
        private Socket client_socket;    //
        private BufferedReader server_in;
        private PrintWriter server_out;
        private String client_Name;

        public HandleClientThread(Socket client_socket) {
            try {
                //初始化
                this.client_socket = client_socket;
                server_in = new BufferedReader(new InputStreamReader(this.client_socket.getInputStream()));
                server_out = new PrintWriter(this.client_socket.getOutputStream(),true);

                //通知刚连上的客户端输入用户名
                server_out.println("您已成功连接上聊天服务器！请输入你的用户名");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //处理客户端消息
        public void run(){
            try {
                int falg = 0;     //判断该客户端是否第一次访问
                String fromClientData;

                //循环接收该客户端发送的数据
                while((fromClientData = server_in.readLine()) != null){
                    //该客户端请求关闭服务器的连接
                    if("exit".equals(fromClientData)){
                        Server.this.offline(this.client_Name);
                        break;
                    }
                    // i++   ++i
                    //判断该客户端是否第一次访问
                    if(falg++ == 0){
                        this.client_Name = fromClientData;
                        clients.put(this.client_Name,this.client_socket);
                        sendtoAllClient("欢迎："+this.client_Name+"进入聊天室");
                        Server.this.showUserList();
                        continue;
                    }

                    //处理私聊    格式  @接收客户端的名字:对其说的话
                    if(fromClientData.startsWith("@")){
                        // substring   @lxt:1111  => lxt  111
                        String receiveName = fromClientData.substring(1,fromClientData.indexOf(":"));
                        String message = fromClientData.substring(fromClientData.indexOf(":")+1,fromClientData.length());

                        sendtoUser(this.client_Name,receiveName,message);

                    }else {
                        //处理群发群发
                        sendtoAllClient(this.client_Name+"说:"+fromClientData);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //显示所有用户列表
    private synchronized  void showUserList(){
        System.out.println("\n*****用户列表****");
        System.out.println("在线人数："+clients.size());
        for (Map.Entry<String,Socket> user:clients.entrySet()){
            System.out.println("---"+user.getKey());
        }
    }

    //广播消息message给所有客户端
    private synchronized  void sendtoAllClient(String message){
        try {
            //获取所有客户端套接字socket的输出流  map
            for (Map.Entry<String,Socket> user:clients.entrySet()) {
                PrintWriter server_out = new PrintWriter(user.getValue().getOutputStream(),true);
                server_out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //转发消息给某个特定的客户端
    private synchronized void sendtoUser(String senduser,String receiveuser,String message){
        try {
            PrintWriter out = new PrintWriter( clients.get(receiveuser).getOutputStream(),true);
            out.println(senduser+"对你说："+message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //某个客户端退出服务器
    private synchronized void offline(String username){
        clients.remove(username);
        if(username !=  null)
            sendtoAllClient(username+"已下线！");
        Server.this.showUserList();
    }

    public static void main(String[] args) throws Exception {
        new Server();

    }
}

