package com.example.com.diary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText editDiary;
    Button btn;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dp = (DatePicker)findViewById(R.id.dp);
        editDiary = (EditText)findViewById(R.id.editDiary);
        btn = (Button)findViewById(R.id.btn);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        fileName = Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(day);
        String str = readDiary(fileName);
        editDiary.setText(str);
        btn.setEnabled(true);
        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "-" + Integer.toString(monthOfYear + 1) + "-" + Integer.toString(dayOfMonth);
                String str = readDiary(fileName);
                editDiary.setText(str);
                btn.setEnabled(true);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = editDiary.getText().toString();
                    fos.write(str.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(),fileName+"이 저장됨",Toast.LENGTH_SHORT).show();
                }catch(java.io.IOException e){
                    Toast.makeText(getApplicationContext(),"저장실패",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream fis;
        try {
            fis = openFileInput(fileName);
            byte[] txt = new byte[500];
            fis.read(txt);
            fis.close();
            diaryStr = new String(txt).trim();
            btn.setText("수정하기");
        } catch (java.io.IOException e) {
            editDiary.setHint("일기 없음");
            btn.setText("새로 저장");
        }
        return diaryStr;
    }

}
