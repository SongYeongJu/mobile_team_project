package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Duser;
import com.example.myapplication.R;

public class MoneyForDmActivity extends AppCompatActivity {
    private Client client;
    private Duser duser;

    private TextView moneyText;
    private EditText getMoney;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_for_dm);

        client=Client.getInstance();
        duser=Duser.returnSample();

        moneyText=(TextView)findViewById(R.id.dmMoneyTextView);
        moneyText.setText(duser.getMoney()+"원");
        getMoney=(EditText)findViewById(R.id.getDmMoney);
        button=(Button)findViewById(R.id.chargeDmButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m=Integer.parseInt(getMoney.getText().toString());
                if(duser.getMoney()-m<0) {
                    Toast.makeText(getBaseContext(),"출금하려는 금액이 잔액보다 많습닏다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                duser.setMoney(duser.getMoney()-m);
                moneyText.setText(duser.getMoney()+"원");
                getMoney.setText("");
            }
        });
    }
}
