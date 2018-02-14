package com.example.usuario.alarmtimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmTimerActivity extends AppCompatActivity {

    private static final int ALARM_TIMER = 1;
    TimePicker timePicker;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.tmpTime);
        fab = findViewById(R.id.fab);
        timePicker.setIs24HourView(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.usuario.alarmtimer");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AlarmTimerActivity.this, ALARM_TIMER, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                calendar.set(Calendar.SECOND, 0);

                //Registro una alarma teniendo en cuenta el valor
                //introducido en TimePicker
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                //Para que sea posible hay que finalizar la activity
                finish();
            }
        });

    }

}
