package com.example.achuan.asynctask_easystudy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by achuan on 16-10-2.
 */
public class ImageTest extends AppCompatActivity {

    private static String URL = "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
    @BindView(R.id.download_iv)
    ImageView mDownloadIv;
    @BindView(R.id.download_pb)
    ProgressBar mDownloadPb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        ButterKnife.bind(this);//初始化加载布局控件
        //创建异步线程任务的实例
        new MyAsyncTask().execute(URL);//设置传递进去的参数

    }

    //AsyncTask<1,2,3> 3个参数的介绍：1传入给后台任务的数据的类型,2进度值类型,3返回值类型
    class MyAsyncTask  extends AsyncTask<String,Void,Bitmap> {
        //后台任务执行前的初始化操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //图片加载任务开始时显示加载进度条
            mDownloadPb.setVisibility(View.VISIBLE);
        }
        //后台子线程执行耗时操作的方法
        @Override//String... strings代表可边长数组
        protected Bitmap doInBackground(String... strings) {
            //获取传递进来的参数
            String url=strings[0];//取出数组中的第0位(即我们传入的URL)
            Bitmap bitmap=null;
            URLConnection connection=null;
            InputStream inputStream=null;
            try {
                connection=new URL(url).openConnection();//打开网络连接
                inputStream=connection.getInputStream();//获取网络连接的输入流
                //将简单的输入流包装成带缓存功能的输入流
                BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);

                //延时1秒,让加载过程可见
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*将输入流解析成bitmp类型的数据*/
                bitmap= BitmapFactory.decodeStream(bufferedInputStream);
                //最后记得关闭输入流
                inputStream.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //publishProgress();
            //将Bitmap作为返回值,返回给onPostExecute方法使用
            return bitmap;
        }
        /*//doInBackground方法中执行publishProgress()进行进度显示时将后执行该方法
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }*/
        //后台任务执行完后执行该方法,并传递执行后的结果进来
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //图片加载成功后将进度条隐藏,并显示加载好的图片
            mDownloadPb.setVisibility(View.GONE);
            mDownloadIv.setImageBitmap(bitmap);
        }
    }

}
