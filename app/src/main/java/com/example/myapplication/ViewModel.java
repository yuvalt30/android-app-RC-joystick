package com.example.myapplication;

public class ViewModel implements  IViewModel{
    IModel m;

    public ViewModel() {
        this.m = new FGModel();
    }

    public void setAileron(float val) {
        m.setProperty(Float.toString(val), "aileron");
    }

    public void setElevation(float val) {
        m.setProperty(Float.toString(val), "elevator");
    }

    public void setRudder(double val) {
        m.setProperty(Double.toString(val), "rudder");
    }

    public void setThrottle(double val) {
        m.setProperty(Double.toString(val), "throttle");
    }

    public void connect(String host, int port) {
        m.connect(host, port);
    }
}
