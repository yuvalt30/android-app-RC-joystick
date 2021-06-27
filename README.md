# android-app-RC-joystick

### The purpose:
This android application makes your android device a remote controll joystick for FlightGear simulator.

### General operation:
FlightGear has to be run on host, user enters host's IP adress and port and connects in order to get control on the aircaft.
(when FlightGear starts, aircraft's engine is off, to start engine press on top of FlightGear window [aircraft's name]-> Autostart)
When FG opens user has to define "--telnet=socket,in,10,127.0.0.1,6400,tcp" in Settings-> Additional Settings
![image](https://user-images.githubusercontent.com/72381398/123542333-cefde280-d751-11eb-8803-7f9697985acf.png)



### App design pattern:
The app was designed with MVVM architecture, which mean that View knows only an abstract IViewModel (not a concrete viewmodel) and also ViewModel knows only an abstract IModel (not a concrete model) 
which enables to change these modules' implementation easily.
The app is written in JAVA.


## More documentation:
- [Controller](documentation/comments_on_AnomalyDetectorController.md)
- [UML diagram](documentation/UML.jpg)


## Link to video for demo of using:
??????????????

## Developed by:
* Yuval Tal
* David Emanuel

## Downloads:
* FlightGear
link: https://www.flightgear.org/
