package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.R;

public class ShowItemInfoActivity extends AppCompatActivity {
    private Client client;
    private Item item;

    private TextView duserName;
    private TextView duserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_info);
        client = Client.getInstance();

        item=client.getItem();

        duserName = (TextView) findViewById(R.id.mItemName);
        duserInfo = (TextView) findViewById(R.id.mItemInfo);

        setInfo();

    }

    void setInfo() {
        if (item == null) {
            item=Item.returnSempleItem();
            duserName.setText(item.getName());
            duserInfo.setText(item.returnInfo());
            item=null;
        } else{
            duserName.setText(item.getName());
            duserInfo.setText(item.returnInfo());
        }
    }
}
