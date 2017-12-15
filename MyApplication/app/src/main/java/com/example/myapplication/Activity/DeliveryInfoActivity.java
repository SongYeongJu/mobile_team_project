package com.example.myapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Duser;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.R;

public class DeliveryInfoActivity extends AppCompatActivity {

    private Client client;
    private Item item;
    private Duser duser;

    private Button checkButton1;
    private Button checkButton2;
    private Button checkButton3;

    private TextView itemName;
    private TextView itemInfo;
    private TextView duserName;
    private TextView duserInfo;
    private TextView CompCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info);

        client = Client.getInstance();

        itemName = (TextView) findViewById(R.id.delItemName);
        itemInfo = (TextView) findViewById(R.id.delItemInfo);
        duserName = (TextView) findViewById(R.id.delDuserName);
        duserInfo = (TextView) findViewById(R.id.delDuserInfo);
//        CompCode = (TextView) findViewById(R.id.delCompCode);

        setInfo();

        checkButton1 = findViewById(R.id.checkButton1);
        checkButton2 = findViewById(R.id.checkButton2);
        checkButton3 = findViewById(R.id.checkButton3);
        checkButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        checkButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DeliveryInfoActivity.this)
                        .setTitle("배달원에게 물건을 전달하셨습니까? 동의를 누르시면 배달이 시작됩니다.")
                        .setMessage("배달할 물건 전달 확인")
                        .setPositiveButton("동의", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int button) {
                                switch (button) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        checkButton2.setVisibility(View.GONE);
                                        checkButton3.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
            }
        });
        checkButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DeliveryInfoActivity.this)
                        .setTitle("배달원이 물건을 전달하였습니까? 동의를 누르시면 배달이 완료되고 배달원에게 입금이 진행됩니다.")
                        .setMessage("배달 완료 확인")
                        .setPositiveButton("동의", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int button) {
                                switch (button) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent = new Intent(DeliveryInfoActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
            }
        });
    }

    void setInfo() {
        if (item != null) {
            itemName.setText(item.getName());
            itemInfo.setText(item.returnInfo());
        }
        if (duser != null) {
            duserName.setText(duser.getName());
            duserInfo.setText(duser.returnInfo());
        }
    }
}
