package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StartTimeSettingActivity extends AppCompatActivity {

    private LinearLayout startDateL;
    private LinearLayout startTimeL;

    private DatePicker startDatePicker;
    private TimePicker startTimePicker;
    private Date startTime;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time_setting);

        startDatePicker=(DatePicker)findViewById(R.id.StartDatePicker);
        startTimePicker=(TimePicker)findViewById(R.id.StartTimePicker);

        GregorianCalendar oc=new GregorianCalendar();
        startDatePicker.init(oc.get(Calendar.YEAR),oc.get(Calendar.MONTH),oc.get(Calendar.DAY_OF_MONTH),null);

        startDateL=(LinearLayout)findViewById(R.id.StartDayLayout);
        startTimeL=(LinearLayout)findViewById(R.id.StartTimeLayout);

        Button go_requ=findViewById(R.id.go_to_info_sta);
        go_requ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String from = "2013-04-08 10:10:10";
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date to = transFormat.parse(from);
                */
                Date start;
                if(startTimeL.getVisibility()==View.GONE) {
                    year=startDatePicker.getYear();
                    month=startDatePicker.getMonth();
                    day=startDatePicker.getDayOfMonth();

                    startDateL.setVisibility(View.GONE);
                    startTimeL.setVisibility(View.VISIBLE);
                } else {
                    hour=startTimePicker.getHour();
                    min=startTimePicker.getMinute();
                    try {
                        startTime=new Date(year,month,day,hour,min,0);

                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), "select day and time again", Toast.LENGTH_SHORT).show();
                        startTimeL.setVisibility(View.GONE);
                        startDateL.setVisibility(View.VISIBLE);
                        return ;
                    }
                    if (!isValidDate(startDatePicker.getYear(), startDatePicker.getMonth(), startDatePicker.getDayOfMonth()))
                        if (!isVaildTime()) {
                            Toast.makeText(getBaseContext(), "select day and time again", Toast.LENGTH_SHORT).show();
                            startTimeL.setVisibility(View.GONE);
                            startDateL.setVisibility(View.VISIBLE);
                            return;
                        }

                    Intent intent = new Intent(StartTimeSettingActivity.this,RequestActivity.class);
                    intent.putExtra("startD",startTime);
                    Log.d("test","start time in Date:"+startTime.toString());
                    setResult(1,intent);
                    finish();
                    //startActivity(intent);
                }
            }
        });

    }

    boolean isVaildTime() {
        return true;
    }

    boolean isValidDate(int year, int month, int date) {
        Calendar aDate = Calendar.getInstance(); // 비교하고자 하는 임의의 날짜
        aDate.set(year, month, date);

        Calendar bDate = Calendar.getInstance(); // 이것이 시스템의 날짜

// 여기에 시,분,초를 0으로 세팅해야 before, after를 제대로 비교함
        aDate.set(Calendar.HOUR_OF_DAY, 0);
        aDate.set(Calendar.MINUTE, 0);
        aDate.set(Calendar.SECOND, 0);
        aDate.set(Calendar.MILLISECOND, 0);


        bDate.set(Calendar.HOUR_OF_DAY, 0);
        bDate.set(Calendar.MINUTE, 0);
        bDate.set(Calendar.SECOND, 0);
        bDate.set(Calendar.MILLISECOND, 0);

        if (aDate.before(bDate)) return false; // aDate가 bDate보다 작을 경우 출력
        else return true; // 같을 경우
    }
}
