package vn.softlink.countdistance;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private MyAdapter adapter;
    private ArrayList<Acceleration> data = new ArrayList<>();
    private static final String TAG_DEBUG = "check_sensor";
    String currentTime;
    private String ax;
    private String ay;
    private String az;


    @BindView(R.id.ax_txt)
    public TextView axText;
    @BindView(R.id.ay_txt)
    public TextView ayText;
    @BindView(R.id.az_txt)
    public TextView azText;
    @BindView(R.id.register_btn)
    public Button registerBtn;
    @BindView(R.id.record_btn)
    public Button recordBtn;
    @BindView(R.id.list_rcv)
    public RecyclerView listRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        ButterKnife.bind(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listRecyclerView.setLayoutManager(llm);
        adapter = new MyAdapter(data);
        listRecyclerView.setAdapter(adapter);


    }
    @OnClick(R.id.register_btn) void register(){
        registerLinearAccelerationSensorListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.record_btn) void record(){
        Acceleration a = new Acceleration();
        a.setAx(ax);
        a.setAy(ay);
        a.setAz(az);
        a.setTime(currentTime);
        data.add(a);
        adapter.notifyDataSetChanged();

    }



    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                currentTime = Calendar.getInstance().getTime().toString();
                axText.setText("ax: " + Float.toString(event.values[0]));
                ayText.setText("ay: " + Float.toString(event.values[1]));
                azText.setText("az: " + Float.toString(event.values[2]));
                ax = Float.toString(event.values[0]);
                ay = Float.toString(event.values[1]);
                az = Float.toString(event.values[2]);
                break;

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void registerLinearAccelerationSensorListener(){

        Sensor accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorManager.registerListener(this, accelerometer,
                    100000000, 1000000);
        }

    }




}