package exam.report_diary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    TextView textDate;
    Button btnSave;
    EditText editDiary;
    DatePicker datePicker;
    View datepickDialog;
    String fileName,msg;
    String sdPath;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDate = (TextView)findViewById(R.id.textDate);
        btnSave = (Button)findViewById(R.id.btnSave);
        editDiary = (EditText)findViewById(R.id.editDiary);

        /* myDiray Directroy 생성 */
        String sdCardState = Environment.getExternalStorageState();

        if(sdCardState.equals(Environment.MEDIA_MOUNTED)){
             sdPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/exam.report_diary/files";
        }else{
            sdPath = Environment.MEDIA_UNMOUNTED+"/Android/data/exam.report_diary/files";
        }
        final File myDiary = new File(sdPath);

        if( !myDiary.exists() ) {
            myDiary.mkdirs();
        }
        /* 날짜 가져오기 */
        Calendar cal = Calendar.getInstance();
        int  year = cal.get(Calendar.YEAR);
        int  month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        fileName = year+"_"+month+"_"+day+".txt";
        setTextDate(year,month,day);
        Toast.makeText(getApplicationContext(),sdPath+"/"+fileName, Toast.LENGTH_SHORT).show();
        msg = readDiary(sdPath+"/"+fileName);
        editDiary.setText(msg);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiary(sdPath+"/"+fileName);
            }
        });

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickDialog = View.inflate(MainActivity.this, R.layout.datepick_dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("날짜 선택");
                datePicker = (DatePicker)datepickDialog.findViewById(R.id.datePicker);
                dlg.setView(datepickDialog);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth()+1;
                        int day = datePicker.getDayOfMonth();
                        fileName = year+"_"+month+"_"+day+".txt";
                        setTextDate(year, month, day);
                        msg = readDiary(fileName);
                        editDiary.setText(msg);
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {

                    }
                });
                dlg.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    /* setTextDate : 날짜를 textView에 표현 */
    public void setTextDate(int year,int month,int day){
        textDate.setText(year+"년 "+month+"월 "+day+"일");
    }
    /* saveDiary : 다이어리 읽어오기 */
    public void saveDiary(String fileName){
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            String str = editDiary.getText().toString();
            fos.write(str.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_SHORT).show();
            btnSave.setText("수정하기");
        }catch(java.io.IOException e){
            Toast.makeText(getApplicationContext(),"저장실패",Toast.LENGTH_SHORT).show();
            btnSave.setText("새로 저장");
        }
    }
    /* readDiary : 다이어리 읽어오기 */
    public String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream fis;
        try {
            fis = openFileInput(fileName);
            byte[] txt = new byte[500];
            fis.read(txt);
            fis.close();
            diaryStr = new String(txt).trim();
            btnSave.setText("수정하기");
        } catch (java.io.IOException e) {
            editDiary.setHint("일기 없음");
            btnSave.setText("새로 저장");
        }
        return diaryStr;
    }
}
