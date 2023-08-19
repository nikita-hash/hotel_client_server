package com.server;

import com.controller.HeaderController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server implements Serializable {
    private int port=8080;
    private List<Socket> listSocket=new ArrayList<>();
    private ServerSocket server ;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public ObjectOutputStream getOut(){return out;}
    public ObjectInputStream getIn(){return in;}
    public InetAddress getIpClient() { return socket.getInetAddress(); }
    public Server() throws IOException {
        server=new ServerSocket(port);
    }
    public void StartServer() throws IOException {
        while(true) {
            socket = server.accept();
            listSocket.add(socket);
            for (int i=0;i<listSocket.size();i++){
                System.out.println(listSocket.get(i).getInetAddress());
            }
            out = new ObjectOutputStream(socket.getOutputStream());
            in=new ObjectInputStream(socket.getInputStream());
            System.out.println("Подкоючился : "+getIpClient().toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HeaderController headerController=new HeaderController();
                    try {
                        headerController.start(out,in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}
