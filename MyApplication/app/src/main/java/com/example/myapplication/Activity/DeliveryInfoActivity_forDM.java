package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeliveryInfoActivity_forDM extends AppCompatActivity {

    private Client client;
    private Item item;

    private TextView arrTimeTextView;
    private TextView ItemNameTextView;
    private TextView ItemInfoTextView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info_for_dm);

        client = Client.getInstance();

        arrTimeTextView = (TextView) findViewById(R.id.dmArrTime);
        ItemNameTextView = (TextView) findViewById(R.id.dmItemName);
        ItemInfoTextView = (TextView) findViewById(R.id.dmItemInfo);
        progressBar = (ProgressBar) findViewById(R.id.dmProgressBar);

        setText();

        Button completeButton = findViewById(R.id.CompleteButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryInfoActivity_forDM.this, MainForDmActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    void setPB() {
        long total = item.getDt().getTime() - item.getSt().getTime();
        Date nd = new Date();
        long now = item.getDt().getTime() - nd.getTime();
        int percent = (int) ((now / total) * 100);
        progressBar.setProgress(percent);
        Log.d("test", "progress : " + percent);
    }

    void setText() {
        if (item == null) item = Item.returnSempleItem();
        else setPB();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        Date currentTime = item.getSt();

        arrTimeTextView.setText(formatter.format(currentTime));
        ItemNameTextView.setText(item.getName());
        ItemInfoTextView.setText(item.returnInfo());
        if (item.getName() == Item.returnSempleItem().getName() && item.getSta().getX() == 0)
            item = null;
    }
}
