package exam.report_diary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textDate, deleteDate;
    Button btnSave;
    EditText editDiary;
    DatePicker datePicker;
    View datepickDialog, deleteDialog;
    String fileName, msg;
    String sdPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDate = (TextView) findViewById(R.id.textDate);
        btnSave = (Button) findViewById(R.id.btnSave);
        editDiary = (EditText) findViewById(R.id.editDiary);


        /* myDiray Directroy 생성 */
        String sdCardState = Environment.getExternalStorageState();

        if (sdCardState.equals(Environment.MEDIA_MOUNTED)) {    //SDCard가 Mount 된 경우
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/mydiary";
        } else {    //SDCard가 UnMount 된 경우
            sdPath = Environment.MEDIA_UNMOUNTED;
        }
        final File myDiary = new File(sdPath);

        if (!myDiary.exists()) {
            myDiary.mkdirs();
        }
        /* 날짜 가져오기 */
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        fileName = year + "_" + month + "_" + day + ".txt";
        setTextDate(year, month, day);
        msg = readDiary(sdPath + "/" + fileName);
        editDiary.setText(msg);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiary(sdPath + "/" + fileName);
            }
        });

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickDialog = View.inflate(MainActivity.this, R.layout.datepick_dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("날짜 선택");
                datePicker = (DatePicker) datepickDialog.findViewById(R.id.datePicker);
                dlg.setView(datepickDialog);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth() + 1;
                        int day = datePicker.getDayOfMonth();
                        fileName = year + "_" + month + "_" + day + ".txt";
                        setTextDate(year, month, day);
                        msg = readDiary(sdPath + "/" + fileName);
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
        switch (id) {
            case R.id.menuReread:
                msg = readDiary(sdPath + "/" + fileName);
                editDiary.setText(msg);
                Toast.makeText(getApplicationContext(), "다시읽어왔습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuDeleteDiary:
                deleteDialog = View.inflate(MainActivity.this, R.layout.delete_dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("일기 삭제");
                dlg.setView(deleteDialog);
                deleteDate = (TextView) deleteDialog.findViewById(R.id.deleteDate);
                deleteDate.setText(fileName + "을 삭제하시겠습니까?");
                deleteDate.setTextSize(15);
                deleteDate.setTextColor(Color.RED);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteDiary(sdPath + "/" + fileName);
                        Toast.makeText(getApplicationContext(),fileName+" 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                    }
                });
                dlg.show();
                break;
            case R.id.menuBigFont:
                editDiary.setTextSize(20);
                Toast.makeText(getApplicationContext(), "BIG", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuNormalFont:
                editDiary.setTextSize(15);
                break;
            case R.id.menuSmallFont:

                editDiary.setTextSize(10);
                Toast.makeText(getApplicationContext(), "SMALL", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.

                onOptionsItemSelected(item);

    }


    /* setTextDate : 날짜를 textView에 표현 */
    public void setTextDate(int year, int month, int day) {
        textDate.setText(year + "년 " + month + "월 " + day + "일");
    }

    /* saveDiary : 다이어리 읽어오기 */
    public void saveDiary(String fileName) {
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            String str = editDiary.getText().toString();
            fos.write(str.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_SHORT).show();
            btnSave.setText("수정하기");
        } catch (java.io.IOException e) {
            Toast.makeText(getApplicationContext(), "저장실패", Toast.LENGTH_SHORT).show();
            btnSave.setText("새로 저장");
        }
    }

    /* readDiary : 다이어리 읽어오기 */
    public String readDiary(String fileName) {
        String diaryStr = null;
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            Reader in = new InputStreamReader(fis);
            int size = fis.available();
            char[] buffer = new char[size];
            in.read(buffer);
            in.close();

            diaryStr = new String(buffer).trim();
            btnSave.setText("수정하기");
        } catch (java.io.IOException e) {
            editDiary.setHint("일기 없음");
            btnSave.setText("새로 저장");
        }
        return diaryStr;
    }

    /* deleteDiary : 다이어리 삭제하기 */
    public boolean deleteDiary(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            btnSave.setText("새로 저장");
            return true;
        } else
            return false;
    }
}