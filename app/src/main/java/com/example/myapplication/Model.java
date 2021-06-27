package com.example.myapplication;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model implements  IModel{
    Socket fg;
    PrintWriter out;
    ExecutorService executor;
    boolean isConnected = false;

    public Model() {
        executor =  Executors.newSingleThreadExecutor();
    }

    public void connectFG(String host, int port){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    fg = new Socket(host, port);
                    out = new PrintWriter(fg.getOutputStream(), true);
                    isConnected = true;
                } catch (Exception e) {
                    System.out.println(e);
                }
                setProperty("0", "elevator");
                setProperty("0", "aileron");
                setProperty("0", "throttle");
                setProperty("0", "rudder");
            }
        });
    }

    public void setProperty(String val, String property){
        if(isConnected)
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String path = "set /controls/flight/" + property;
                    if (property.equals("throttle"))
                        path = "set /controls/engines/current-engine/throttle";
                    out.print(path + " " + val + "\r\n");
                    out.flush();
                }
            });
    }
}