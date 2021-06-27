package com.example.myapplication;

public class ViewModel {
    Model m;

    public ViewModel(Model m){
        m=m;
    }

    public void setAileron(float val){
        m.setProperty(Float.toString(val), "aileron");
    }
    public void setElevation(float val){
        m.setProperty(Float.toString(val), "elevation");
    }
    public void setRudder(float val){
        m.setProperty(Float.toString(val), "rudder");
    }
    public void setThrottle(float val){
        m.setProperty(Float.toString(val), "throttle");
    }
}
