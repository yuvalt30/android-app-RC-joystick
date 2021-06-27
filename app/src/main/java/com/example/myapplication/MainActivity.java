package com.example.myapplication;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private SeekBar throttleSeek;
    private SeekBar rudderSeek;
    private Joystick joystick;
    private IViewModel vm;
    private EditText ip_text, port_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new ViewModel();
        throttleSeek = (SeekBar) findViewById(R.id.throttle);
        rudderSeek = (SeekBar) findViewById(R.id.rudder);
        joystick = (Joystick) findViewById(R.id.joystick);
        ip_text = (EditText)findViewById(R.id.ip_input);
        port_text = (EditText)findViewById(R.id.port_input);

        joystick.setListener(new Joystick.JoystickListener() {
            @Override
            public void update(float x, float y) {
                vm.setAileron(x);
                vm.setElevation(y);
            }
        });

        throttleSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vm.setThrottle(progress/100.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {	}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {	}
        });

        rudderSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vm.setRudder((progress-100)/100.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {	}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {	}
        });
    }

    public void connect(android.view.View button){
        String sip = ip_text.getText().toString();
        int intport = 0;
        try {
            intport = Integer.parseInt(port_text.getText().toString());
        }catch(Exception e){}
        vm.connect(sip, intport);
    }
}