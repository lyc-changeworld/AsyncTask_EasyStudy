package com.example.achuan.asynctask_easystudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_bt)
    Button mMyBt;
    @BindView(R.id.pb_bt)
    Button mPbBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //创建自定义的异步线程任务的实例对象
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        //通过execute()方法来启动这个任务
        myAsyncTask.execute();


    }




    @OnClick({R.id.my_bt, R.id.pb_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_bt:
                Intent intent = new Intent(MainActivity.this, ImageTest.class);
                startActivity(intent);
                break;
            case R.id.pb_bt:
                Intent intent1 = new Intent(MainActivity.this, ProgressbarTest.class);
                startActivity(intent1);
                break;
        }
    }
}

