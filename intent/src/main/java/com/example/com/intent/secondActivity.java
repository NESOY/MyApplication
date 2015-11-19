package com.example.com.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class secondActivity extends AppCompatActivity {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                double input1 = 0;
                double input2 = 0;
                input1 = intent.getDoubleExtra("input1", 0);
                input2 = intent.getDoubleExtra("input2", 0);
                int calc = 0;
                calc = intent.getIntExtra("calc", 0);
                double result = 0;
                switch (calc) {
                    case R.id.radioPlus:
                        result = input1 + input2;
                        break;
                    case R.id.radioMinus:
                        result = input1 - input2;
                        break;
                    case R.id.radioMul:
                        result = input1 * input2;
                        break;
                    case R.id.radioDiv:
                        result = input1 / input2;
                        break;
                }
                Intent outIntent = new Intent(getApplication(),MainActivity.class);
                outIntent.putExtra("result", result);
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });


    }

}
