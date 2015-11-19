package com.example.com.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText input1, input2;
    RadioGroup calcGroup;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        calcGroup = (RadioGroup) findViewById(R.id.calcGroup);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input1 == null|| input2==null || input1.getText().toString().equals("") || input2.getText().toString().equals("") ) {
                    Toast.makeText(getApplicationContext(), "값이 입력되지않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(input2.getText().toString().equals("0") && calcGroup.getCheckedRadioButtonId() == R.id.radioDiv){
                    Toast.makeText(getApplicationContext(), "0으로 나눌수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    double result = 0;
                    double x = Double.parseDouble(input1.getText().toString());
                    double y = Double.parseDouble(input2.getText().toString());

                    Intent intent;
                    intent = new Intent(getApplication(), secondActivity.class);
                    intent.putExtra("input1", x);
                    intent.putExtra("input2", y);
                    intent.putExtra("calc", calcGroup.getCheckedRadioButtonId());
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            double result = data.getDoubleExtra("result", 0);
            Toast.makeText(getApplicationContext(), "결과: " + result, Toast.LENGTH_SHORT).show();
            input1.setText("");
            input2.setText("");
        }
    }
}
