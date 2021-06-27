package com.example.myapplication;

public class ViewModel {
    Model m;

    public ViewModel(Model m) {
        this.m = m;
    }

    public void setAileron(float val) {
        m.setProperty(Float.toString(val), "aileron");
    }

    public void setElevation(float val) {
        m.setProperty(Float.toString(val), "elevation");
    }

    public void setRudder(double val) {
        m.setProperty(Double.toString(val), "rudder");
    }

    public void setThrottle(double val) {
        m.setProperty(Double.toString(val), "throttle");
    }

    public void connectFG(String host, int port) {
        m.connectFG(host, port);
    }
}
