package com.example.com.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText tvName, tvEmail;
    Button btn;
    EditText dlgEditName, dlgEditEmail;
    TextView toastText;
    View dialogView, ToastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (EditText) findViewById(R.id.tvName);
        tvEmail = (EditText) findViewById(R.id.tvEmail);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.dropbox);
                dlg.setView(dialogView);
                dlgEditName = (EditText) dialogView.findViewById(R.id.dlgEditName);
                dlgEditEmail = (EditText) dialogView.findViewById(R.id.dlgEditEmail);

                dlgEditName.setText(tvName.getText().toString());
                dlgEditEmail.setText(tvEmail.getText().toString());
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tvName.setText(dlgEditName.getText().toString());
                        tvEmail.setText(dlgEditEmail.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        ToastView = View.inflate(MainActivity.this, R.layout.toast1, null);
                        toastText = (TextView) ToastView.findViewById(R.id.toastText);

                        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

                        int x = (int) (Math.random() * dm.widthPixels);
                        int y = (int) (Math.random() * dm.heightPixels);
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, x, y);
                        toast.setView(ToastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });

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
