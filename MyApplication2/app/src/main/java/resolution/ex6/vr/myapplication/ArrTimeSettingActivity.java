package resolution.ex6.vr.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class ArrTimeSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arr_time_setting);
        Button go_requ=findViewById(R.id.go_to_info_arr);
        go_requ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ArrTimeSettingActivity.this,RequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
