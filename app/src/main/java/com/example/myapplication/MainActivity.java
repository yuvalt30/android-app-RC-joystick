package com.example.myapplication;

import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private SeekBar throttleSeek;
    private SeekBar rudderSeek;
    private TextView text;
    private ImageView joystick;
    private RelativeLayout moveLayout;
    private Model m;
    private EditText ip_text, port_text;
    private View button;

    private int xDelta;
    private int yDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m = new Model();
        throttleSeek = (SeekBar) findViewById(R.id.throttle);
        rudderSeek = (SeekBar) findViewById(R.id.rudder);
        text = (TextView) findViewById(R.id.text);
        joystick = (ImageView) findViewById(R.id.joystick);
        moveLayout = (RelativeLayout) findViewById(R.id.move);
        ip_text = (EditText)findViewById(R.id.ip_input);
        port_text = (EditText)findViewById(R.id.port_input);

        throttleSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                m.setProperty(Double.toString(progress/100.0),"throttle");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {	}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {	}
        });

        rudderSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                m.setProperty(Double.toString((progress-100)/100.0),"rudder");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {	}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {	}
        });

        joystick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();


                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                moveLayout.invalidate();
                return true;
            }
        });
    }

    public void connect(android.view.View button){
        String sip = ip_text.getText().toString();
        int intport = 0;
        try {
            intport = Integer.parseInt(port_text.getText().toString());
        }catch(Exception e){}
        m.connectFG(sip, intport);
    }
}