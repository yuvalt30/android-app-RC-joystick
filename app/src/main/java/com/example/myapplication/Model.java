package com.example.myapplication;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {
    Socket fg;
    PrintWriter out;
    ExecutorService executor;

    public Model(String host, int port /*TODO delete parameters*/) {
        executor =  Executors.newSingleThreadExecutor();
        connectFG(host, port); // TODO delete this line and detach connection from C-tor
    }

    public void connectFG(String host, int port){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    fg = new Socket("172.19.3.158", 6400);
                    out = new PrintWriter(fg.getOutputStream(), true);
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
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String path = "set /controls/flight/"+property;
                if(property.equals("throttle"))
                    path = "set /controls/engines/current-engine/throttle";
                out.print(path+" "+val+"\r\n");
                out.flush();
            }
        });
    }
}