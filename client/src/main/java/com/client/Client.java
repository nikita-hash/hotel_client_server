package com.client;



import com.controllers.HeaderMenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable {
    private static Client singleton = new Client();
    private int port =8080;
    private Socket socket;
    public void CloseSocket() throws IOException {
        socket.close();
    }
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public ObjectOutputStream getOut(){return out;}
    public ObjectInputStream getIn(){return in;}
    public static Client getInstance()
    {
        return singleton;
    }
    private Client()  {
    }
    public void connection() throws IOException, ClassNotFoundException {
        socket=new Socket("localhost",port);
        out=new ObjectOutputStream(socket.getOutputStream());
        in =new ObjectInputStream(socket.getInputStream());
        HeaderMenu headerMenu=new HeaderMenu();
        headerMenu.Show();
    }

}
