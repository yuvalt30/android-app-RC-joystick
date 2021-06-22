package com.example.myapplication;

import android.os.StrictMode;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
public class Model {
    Socket fg;
    PrintWriter out;

    public Model(String host, int port) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            fg = new Socket("192.168.43.107", 6400);
            out = new PrintWriter(fg.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println(e);
        }
        setProperty("0", "elevator");
        setProperty("0", "aileron");
        setProperty("0", "throttle");
        setProperty("0", "rudder");
    }

    public void setProperty(String val, String property){
        String path = "set /controls/flight/"+property;
        if(property.equals("throttle"))
            path = "set /controls/engines/current-engine/throttle";
        out.print(path+" "+val+"\r\n");
        out.flush();
    }
}