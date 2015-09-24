package com.example.youngjae.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText input1,input2;
    TextView output;
    String num1,num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input1 = (EditText)findViewById(R.id.input1);
        input2 = (EditText)findViewById(R.id.input2);
        output = (TextView)findViewById(R.id.output);
    }
    public void onClick(View view){
        num1 = input1.getText().toString();
        num2 = input2.getText().toString();

        if(num1.equals("") || num2.equals("") ) {
            Toast.makeText(getApplicationContext(), "값이 입력되지않았습니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            switch (view.getId()) {
                case R.id.plus:
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    break;
                case R.id.minus:
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    break;
                case R.id.multiple:
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    break;
                case R.id.divide:
                    if(Double.parseDouble(num2)==0)
                        Toast.makeText(getApplicationContext(), "0으로 나눌수 없습니다.", Toast.LENGTH_SHORT).show();
                    else
                        result = Double.parseDouble(num1) / Double.parseDouble(num2);
                    break;
                case R.id.remainder:
                    result = Double.parseDouble(num1) % Double.parseDouble(num2);
                    break;
            }
            output.setText("계산결과: " + result);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
