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
import android.widget.Toast;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.DataStructure.Location;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RequestActivity extends AppCompatActivity{

    private Client client;

    private Intent Sintent;
    private Intent Aintent;

    private TextView startTimeTextView;
    private TextView finishTimeTextView;

    private TextView getNameTextView;
    private TextView getSizeTextView;
    private TextView getWeigthTextView;

    private Date startD;
    private Date arrD;

    private Location startL;
    private Location arrL;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        client = Client.getInstance();

        startTimeTextView = (TextView) findViewById(R.id.StartTimeText);
        finishTimeTextView = (TextView) findViewById(R.id.ArrTimeText);

        getNameTextView = (TextView) findViewById(R.id.ItemNameText);
        getSizeTextView = (TextView) findViewById(R.id.ItemSizeText);
        getWeigthTextView = (TextView) findViewById(R.id.ItemwText);

        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sintent == null)
                    Sintent = new Intent(RequestActivity.this, StartTimeSettingActivity.class);
                startActivityForResult(Sintent, 1);
            }
        });
        finishTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aintent = new Intent(RequestActivity.this, ArrTimeSettingActivity.class);
                startActivityForResult(Aintent, 2);
            }
        });
        getSizeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test","getSizeTextView onClickListener");
                new AlertDialog.Builder(RequestActivity.this).setTitle("물건의 크기를 선택하여 주세요.\n가로,세로,높이 1m 이하 (소) 1m~2m(중) 2m~ (대)")
                        .setItems(R.array.mobilegroup, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String[] mobilegroup =
                                                getResources().getStringArray(R.array.mobilegroup);
                                        getSizeTextView.setText(mobilegroup[which]);                                    }
                                }
                        ).setNegativeButton("reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSizeTextView.setText("");
                    }
                }).setCancelable(false).show();
            }
        });

        Button requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempSendItem();
            }
        });
    }

    void attempSendItem() {

        String name = getNameTextView.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(getBaseContext(), "이름은 필수 입력 값입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        String size = getSizeTextView.getText().toString();
        if (size.equals("대") || size.equals("중") || size.equals("소")) {
        } else {
            Toast.makeText(getBaseContext(), "크기를 대/중/소 중에 선택해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        int weight;
        try {
            String w = getWeigthTextView.getText().toString();
            weight = Integer.parseInt(w);
            if (weight < 0) {
                Toast.makeText(getBaseContext(), "무게는 양수만 입력할 수 있습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "무게를 kg 단위로 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (startD == null) {
            Toast.makeText(getBaseContext(), "배송 출발 가능 시간을 설정해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (arrD == null) {
            Toast.makeText(getBaseContext(), "배송 마감 시간을 설정해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (startL == null) {
            Toast.makeText(getBaseContext(), "배송 출발지를 설정해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (arrL == null) {
            Toast.makeText(getBaseContext(), "배송 도착지를 설정해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView requT = (TextView) findViewById(R.id.RequestedText);
        String requ = requT.getText().toString();
        //public Item(String name,int weight,String size,int dx,int dy,Date dt,int sx,int sy,Date st,boolean order)
        item = new Item(name, weight, size, arrL, arrD, startL, startD, requ, false);
        client.Uitem(item);

        Intent intent = new Intent(RequestActivity.this, DeliveryInfoActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == requestCode) {
            startD = (Date) data.getExtras().getSerializable("startD");
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd HH:mm ", Locale.KOREA);
            startTimeTextView.setText("시작 시간 : " + formatter.format(startD));
        } else if (requestCode == 2 && resultCode == requestCode) {
            arrD = (Date) data.getExtras().getSerializable("arrD");
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd HH:mm ", Locale.KOREA);
            finishTimeTextView.setText("도착 시간 : " + formatter.format(arrD));
        }
    }
}
