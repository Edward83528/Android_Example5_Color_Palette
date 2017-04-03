package com.example.u0151051.color_palette;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

//顏色設定:在values資料夾下創一個colors.xml檔(Android Studio已有創,不用另外再創了),裡面新增顏色(#RRGGBB)
//宣告方式: <color name="red">#FF0000</color>
public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    Spinner sp;// 下拉式選單
    RadioButton rb1, rb2, rb3;//放進群組裡(RadioGroup)才是多選一
    SeekBar sb;// 拖拉工具
    int r1, g1, b1;
    int r2, g2, b2;
    int p = 0;// spinner下拉式選單所放陣列索引值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    void findview() {
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        // spinner的選項文字(在values資料夾下的strings新增string array)
        // array(1.優點是方便未來修改2.可實作多國語言)第一個spinner裝字串陣列:在xml的spinner下加android:entries="@array/陣列名稱")
        sp = (Spinner) findViewById(R.id.spinner);
        // 把監聽器設給Spinner
        sp.setOnItemSelectedListener(i);
        //radioButton放入群組中(RadioGroup)才是多選一
        //RadioGroup放的radioButton預設是垂直,加 android:orientation="horizontal" 變水平
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        // 把監聽器設給RadioButton
        rb1.setOnClickListener(c);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        // 把監聽器設給RadioButton
        rb2.setOnClickListener(c);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        // 把監聽器設給RadioButton
        rb3.setOnClickListener(c);
        sb = (SeekBar) findViewById(R.id.seekBar);
        // seekbar設監聽器
        sb.setOnSeekBarChangeListener(s);
    }

    // spinner的監聽器(在這裡我們用來選是前景色還是背景色)
    AdapterView.OnItemSelectedListener i = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //p為我們所放的字串陣列索引值
            p = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    View.OnClickListener c = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.radioButton3:
                    if (p == 0) {
                        sb.setProgress(r1);
                    } else {
                        sb.setProgress(r2);
                    }
                    // 在values資料夾的string.xml新增字串利用getResources().getString拿出
                    tv1.setText(getResources().getString(R.string.red));
                    // 在values資料夾中新增xml檔然後新增顏色,顏色的values為#RRGGBB
                    tv1.setTextColor(getResources().getColor(R.color.redc));
                    break;
                case R.id.radioButton2:
                    if (p == 0) {
                        sb.setProgress(g1);
                    } else {
                        sb.setProgress(g2);
                    }
                    tv1.setText(getResources().getString(R.string.green));
                    tv1.setTextColor(getResources().getColor(R.color.greenc));
                    break;
                case R.id.radioButton:
                    if (p == 0) {
                        sb.setProgress(b1);
                    } else {
                        sb.setProgress(b2);
                    }
                    tv1.setText(getResources().getString(R.string.blue));
                    tv1.setTextColor(getResources().getColor(R.color.bluec));
                    break;

            }
        }
    };
    SeekBar.OnSeekBarChangeListener s = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // 正在拖拉會呼叫的方法
            // 假如spinner選到位置0的字串(前景色)
            if (p == 0) {
                if (rb1.isChecked()) {
                    r1 = progress;
                    // color.rgb的color要選 import android.graphices的color
                    tv2.setTextColor(Color.rgb(r1, g1, b1));
                }
                if (rb2.isChecked()) {
                    g1 = progress;
                    tv2.setTextColor(Color.rgb(r1, g1, b1));
                }
                if (rb3.isChecked()) {
                    b1 = progress;
                    tv2.setTextColor(Color.rgb(r1, g1, b1));
                }
                // 假如spinner選到位置1的字串(背景色)
            } else {
                if (rb1.isChecked()) {
                    r2 = progress;
                    tv2.setBackgroundColor(Color.rgb(r2, g2, b2));
                }
                if (rb2.isChecked()) {
                    g2 = progress;
                    tv2.setBackgroundColor(Color.rgb(r2, g2, b2));
                }
                if (rb3.isChecked()) {
                    b2 = progress;
                    tv2.setBackgroundColor(Color.rgb(r2, g2, b2));
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
// 開始拖拉會呼叫的方法
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 停止拖拉會呼叫的方法
        }
    };

}
