package com.example.com.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                int select;
                if (switchAgree.isChecked())    select = android.view.View.VISIBLE;
                else                                    select = android.view.View.INVISIBLE;

                like.setVisibility(select);
                androidGroup.setVisibility(select);

                imgView.setVisibility(select);

                exit.setVisibility(select);
                restart.setVisibility(select);
            }
        });

        androidGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(androidGroup.getCheckedRadioButtonId()){
                    case R.id.jellybean:
                        imgView.getLayoutParams().height = jellybean.getHeight();
                        imgView.getLayoutParams().width = jellybean.getWidth();
                        imgView.setImageResource(R.drawable.jellybean);
                        break;
                    case R.id.kitkat:
                        imgView.getLayoutParams().height = kitkat.getHeight();
                        imgView.getLayoutParams().width = kitkat.getWidth();
                        imgView.setImageResource(R.drawable.kitkat);
                        break;
                    case R.id.lolipop:
                        imgView.getLayoutParams().height = lolipop.getHeight();
                        imgView.getLayoutParams().width = lolipop.getWidth();
                        imgView.setImageResource(R.drawable.lolipop);
                        break;
                }
            }
        });

    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.exit:
                finish();
                break;
            case R.id.restart:
                switchAgree.setChecked(false);
                like.setVisibility(android.view.View.INVISIBLE);
                androidGroup.setVisibility(android.view.View.INVISIBLE);
                androidGroup.clearCheck();

                imgView.setVisibility(android.view.View.INVISIBLE);

                imgView.getLayoutParams().height = 0;
                imgView.getLayoutParams().width = 0;
                imgView.setImageResource(0);

                exit.setVisibility(android.view.View.INVISIBLE);
                restart.setVisibility(android.view.View.INVISIBLE);
                break;
        }
    }

}
