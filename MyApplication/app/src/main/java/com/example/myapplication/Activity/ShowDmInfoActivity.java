package com.example.myapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Duser;
import com.example.myapplication.R;

public class ShowDmInfoActivity extends AppCompatActivity {
    private Client client;
    private Duser duser;

    private Button checkButton1;
    private Button checkButton2;

    private TextView duserName;
    private TextView duserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dm_info);
        client = Client.getInstance();

        duser=client.getDuser();
        if(duser!=null) Log.d("test","ShowDmInfoActivity: duser>> "+duser.getName()+" "+duser.returnInfo());
        else Log.d("test","ShowDmInfoActivity: duser>> null");

        duserName = (TextView) findViewById(R.id.mDuserName);
        duserInfo = (TextView) findViewById(R.id.mDuserInfo);

        setInfo();

        checkButton1 = findViewById(R.id.checkButton1);
        checkButton2 = findViewById(R.id.checkButton2);
        checkButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        checkButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ShowDmInfoActivity.this)
                        .setTitle("배달원에게 물건을 전달하셨습니까? 동의를 누르시면 배달이 시작됩니다.")
                        .setMessage("배달할 물건 전달 확인")
                        .setPositiveButton("동의", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int button) {
                                switch (button) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent =new Intent(ShowDmInfoActivity.this,DeliveryInfoActivity.class);
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
        if (duser == null) {
            duser = Duser.returnSample();
            duserName.setText(duser.getName());
            duserInfo.setText(duser.returnInfo());
            duser = null;
        } else {
            duserName.setText(duser.getName());
            duserInfo.setText(duser.returnInfo());
        }
    }
}
