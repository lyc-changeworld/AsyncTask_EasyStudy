package com.example.achuan.asynctask_easystudy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by achuan on 16-10-2.
 *
 */
public class ProgressbarTest extends AppCompatActivity {
    @BindView(R.id.my_pb)
    ProgressBar mMyPb;
    private MyAsyncTask mAsynTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        ButterKnife.bind(this);
        mAsynTask=new MyAsyncTask();
        mAsynTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //如果当前任务不为空且为running状态,这时就应该
        if(mAsynTask!=null&&mAsynTask.getStatus()==AsyncTask.Status.RUNNING)
        {
            //cancel方法只是将相应的AsycnTask标记为cancle状态,并非取消线程的执行
            mAsynTask.cancel(true);
            //Java中不会暴力的取消掉某个线程,而是给出一个标记状态,需要程序判断来跳出任务就行
        }
    }
    //AsyncTask<1,2,3> 3个参数的介绍：1传入给后台任务的数据的类型,2进度值类型,3返回值类型
    class MyAsyncTask  extends AsyncTask<Void,Integer,Void> {
        //后台任务执行前的初始化操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        //后台子线程执行耗时操作的方法
        @Override
        protected Void doInBackground(Void... voids) {
            /***模拟进度加载的过程***/
            for (int i = 0; i <100 ; i++) {
                //如果当前任务被标记为取消状态,就应该跳出任务
                if(isCancelled())
                {
                    break;
                }
                publishProgress(i);//调用该方法来反馈当前任务执行的进度
                //添加一个延时操作,让进度加载过程可见
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        //doInBackground方法中执行publishProgress()进行进度显示时将后执行该方法,并传入进度值
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //通过进度更新值来更新进度条
            //如果当前任务被标记为取消状态,就应该跳出任务
            if(isCancelled())
            {
                return;
            }
            mMyPb.setProgress(values[0]);
        }
        //后台任务执行完后执行该方法,并传递执行后的结果进来
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
