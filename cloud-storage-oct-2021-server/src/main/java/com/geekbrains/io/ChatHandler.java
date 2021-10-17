package com.geekbrains.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatHandler implements Runnable{

    private final Socket socket;
    private final Server server;
    private final DataInputStream dis;
    private final DataOutputStream dos;

    public ChatHandler(Socket socket, Server server) throws Exception {
        this.socket = socket;
        this.server = server;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = dis.readUTF();
                sendMessage(msg);
            }
        }catch (Exception e) {
            System.err.println("Connection was broken");
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg) throws Exception {
        dos.writeUTF(msg);
        dos.flush();
    }
}
