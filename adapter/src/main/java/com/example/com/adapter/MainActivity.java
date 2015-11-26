package com.example.com.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] posterName = {
            "한반도","노트북","수상한그녀","뱅크잡","퍼블릭에너미",
            "해바라기","렛미인","감기","짐승","스니치",
            "노트북","수상한그녀","뱅크잡","짐승","스니치",
            "해바라기","렛미인","퍼블릭에너미","한반도","짐승",
            "짐승","한반도","짐승","감기"
    };
    Integer[] posterID = {
            R.drawable.mov1,R.drawable.mov16,R.drawable.mov17,R.drawable.mov22,R.drawable.mov23,
            R.drawable.mov2,R.drawable.mov3,R.drawable.mov4,R.drawable.mov19,R.drawable.mov24,
            R.drawable.mov16,R.drawable.mov17,R.drawable.mov22,R.drawable.mov19,R.drawable.mov24,
            R.drawable.mov2,R.drawable.mov3,R.drawable.mov23,R.drawable.mov1,R.drawable.mov19,
            R.drawable.mov19,R.drawable.mov1,R.drawable.mov19,R.drawable.mov4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView = (GridView)findViewById(R.id.gridView);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gridView.setAdapter(gAdapter);
    }
    class MyGridAdapter extends BaseAdapter {
        Context context;
        public MyGridAdapter(Context c) {
            this.context = c;
        }
        @Override
        public int getCount() {
            return posterID.length;
        }
        class Holder{
            TextView tv;
            ImageView img;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.activity_main, null);
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);

            final int pos = position;
            imageView.setImageResource(posterID[position]);




            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this,R.layout.dialog,null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setIcon(R.drawable.movicon);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();

                }
            });
            return imageView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
    }
}
