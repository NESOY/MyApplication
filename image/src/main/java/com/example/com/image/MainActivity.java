package com.example.com.image;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView like;
    Switch switchAgree;
    RadioGroup androidGroup;
    RadioButton jellybean, kitkat, lolipop;
    Button exit,restart;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchAgree = (Switch)findViewById(R.id.switch1);

        like = (TextView)findViewById(R.id.like);
        androidGroup = (RadioGroup)findViewById(R.id.androidGroup);

        jellybean = (RadioButton)findViewById(R.id.jellybean);
        kitkat = (RadioButton)findViewById(R.id.kitkat);
        lolipop = (RadioButton)findViewById(R.id.lolipop);

        imgView = (ImageView)findViewById(R.id.imgView);

        exit = (Button)findViewById(R.id.exit);
        restart = (Button)findViewById(R.id.restart);


        switchAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(
        ) {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchAgree.isChecked() == true) {
                    like.setVisibility(android.view.View.VISIBLE);
                    androidGroup.setVisibility(android.view.View.VISIBLE);

                    jellybean.setVisibility(android.view.View.VISIBLE);
                    kitkat.setVisibility(android.view.View.VISIBLE);
                    lolipop.setVisibility(android.view.View.VISIBLE);

                    imgView.setVisibility(android.view.View.VISIBLE);

                    exit.setVisibility(android.view.View.VISIBLE);
                    restart.setVisibility(android.view.View.VISIBLE);

                } else {
                    like.setVisibility(android.view.View.INVISIBLE);
                    androidGroup.setVisibility(android.view.View.INVISIBLE);

                    jellybean.setVisibility(android.view.View.INVISIBLE);
                    kitkat.setVisibility(android.view.View.INVISIBLE);
                    lolipop.setVisibility(android.view.View.INVISIBLE);

                    imgView.setVisibility(android.view.View.INVISIBLE);

                    exit.setVisibility(android.view.View.INVISIBLE);
                    restart.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        androidGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(androidGroup.getCheckedRadioButtonId()){
                    case R.id.jellybean:
                        imgView.setImageResource(R.drawable.jellybean);
                        break;
                    case R.id.kitkat:
                        imgView.setImageResource(R.drawable.kitkat);
                        break;
                    case R.id.lolipop:
                        imgView.setImageResource(R.drawable.lolipop);
                        break;
                }
            }
        });


       restart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switchAgree.setChecked(false);
               like.setVisibility(android.view.View.INVISIBLE);
               androidGroup.setVisibility(android.view.View.INVISIBLE);

               jellybean.setVisibility(android.view.View.INVISIBLE);
               jellybean.setChecked(false);
               kitkat.setVisibility(android.view.View.INVISIBLE);
               kitkat.setChecked(false);
               lolipop.setVisibility(android.view.View.INVISIBLE);
               lolipop.setChecked(false);

               imgView.setVisibility(android.view.View.INVISIBLE);
               imgView.setImageResource(0);

               exit.setVisibility(android.view.View.INVISIBLE);
               restart.setVisibility(android.view.View.INVISIBLE);
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
