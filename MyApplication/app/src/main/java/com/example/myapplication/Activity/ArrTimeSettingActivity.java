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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ArrTimeSettingActivity extends AppCompatActivity {

    private DatePicker arrDatePicker;
    private TimePicker arrTimePicker;

    private LinearLayout arrDateL;
    private LinearLayout arrTimeL;

    private Date arrTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arr_time_setting);

        arrDatePicker = (DatePicker) findViewById(R.id.ArrDatePicker);
        arrTimePicker = (TimePicker) findViewById(R.id.ArrTimePicker);

        arrDateL = (LinearLayout) findViewById(R.id.ArrDayLayout);
        arrTimeL = (LinearLayout) findViewById(R.id.ArrTimeLayout);

        GregorianCalendar oc = new GregorianCalendar();
        arrDatePicker.init(oc.get(Calendar.YEAR), oc.get(Calendar.MONTH), oc.get(Calendar.DAY_OF_MONTH), null);


        Button go_requ = findViewById(R.id.go_to_info_arr);
        go_requ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*String from = "2013-04-08 10:10:10";
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date to = transFormat.parse(from);
                */
                if (arrTimeL.getVisibility() == View.GONE) {
                    arrDateL.setVisibility(View.GONE);
                    arrTimeL.setVisibility(View.VISIBLE);
                } else {

                    String form = arrDatePicker.getYear() + "-" + arrDatePicker.getMonth() + "-" + arrDatePicker.getDayOfMonth();
                    form += " " + arrTimePicker.getHour() + ":" + arrTimePicker.getMinute() + ":00";
                    SimpleDateFormat transForm = new SimpleDateFormat(form);
                    Log.d("test", "get arr time: " + form);
                    try {
                        Date arr = transForm.parse(form);
                    } catch (ParseException e) {
                        Toast.makeText(getBaseContext(), "select day and time again", Toast.LENGTH_SHORT).show();
                        arrTimeL.setVisibility(View.GONE);
                        arrDateL.setVisibility(View.VISIBLE);
                        return;
                    }
                    if (!isValidDate(arrDatePicker.getYear(), arrDatePicker.getMonth(), arrDatePicker.getDayOfMonth()))
                        if (!isVaildTime()) return;
                    Intent intent = new Intent(ArrTimeSettingActivity.this, RequestActivity.class);
                    startActivity(intent);
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
