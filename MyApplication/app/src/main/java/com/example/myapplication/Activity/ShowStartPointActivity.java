package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.R;

public class ShowStartPointActivity extends AppCompatActivity {
    private Client client;
    private Item item;
    private TextView selectedItemName;
    private TextView selectedItemInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_start_point);
        client= Client.getInstance();

        selectedItemName = (TextView) findViewById(R.id.sdItemName);
        selectedItemInfo = (TextView) findViewById(R.id.sdItemInfo);

        setItemInfo();

        Button selectButton = findViewById(R.id.StartDelButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item!=null) {
                    // new event
                    client.startDelivery();
                }
                Intent intent = new Intent(ShowStartPointActivity.this, DeliveryInfoActivity_forDM.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void setItemInfo() {
        if (item == null) {
            item = Item.returnSempleItem();
            selectedItemName.setText(item.getName());
            selectedItemInfo.setText(item.returnInfo());
            item = null;
        } else {
            selectedItemName.setText(item.getName());
            selectedItemInfo.setText(item.returnInfo());
        }
    }
}
