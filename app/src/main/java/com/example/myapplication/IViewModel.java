package com.example.myapplication;

public interface IViewModel {
    void setAileron(float val);
    void setElevation(float val);
    void setRudder(double val);
    void setThrottle(double val);
    void connect(String host, int port);
    }
