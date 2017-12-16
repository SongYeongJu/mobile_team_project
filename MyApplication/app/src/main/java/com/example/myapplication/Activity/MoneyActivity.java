package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.User;
import com.example.myapplication.R;

public class MoneyActivity extends AppCompatActivity {

    private Client client;
    private User user;

    private TextView moneyText;
    private EditText getMoney;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        client=Client.getInstance();
        user=client.getCustomer();

        moneyText=(TextView)findViewById(R.id.myMoneyTextView);
        moneyText.setText(user.getMoney()+"원");
        getMoney=(EditText)findViewById(R.id.getMoney);
        button=(Button)findViewById(R.id.chargeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m=Integer.parseInt(getMoney.getText().toString());
                user.setMoney(client.Deposit(m));
                moneyText.setText(user.getMoney()+"원");
                getMoney.setText("");
            }
        });
    }
}
