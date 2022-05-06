package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okhttp.databinding.ActivityMainBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements View{
    ActivityMainBinding activityMainBinding ;
    private ResultData resultData;

    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        resultData = new ResultData();
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setView(this);
        activityMainBinding.setResult(resultData);
//        getPost();
//        createPost();
    }
//    public void getPost(){
//        Request request = new Request.Builder()
//                .url("https://jsonplaceholder.typicode.com/posts/1")
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("result",e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                Log.d("result",result);
//                getResult.setText(result);
//            }
//        });
//    }
//    public void createPost(){
//        FormBody formBody = new FormBody.Builder()
//                .add("userId","1")
//                .add("id","1")
//                .add("title","OkHttp Practice")
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://jsonplaceholder.typicode.com/posts")
//                .post(formBody)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("error",e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                postResult.setText(result);
//                Log.d("postResult",result);
//            }
//        });
//    }

    @Override
    public void getData() {
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts/1")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("result",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"get",Toast.LENGTH_SHORT).show();
                        resultData.setGetResult(result);
                        activityMainBinding.setResult(resultData);
                        Log.d("result",result);
                    }
                });

            }
        });
    }

    @Override
    public void createPost() {
        FormBody formBody = new FormBody.Builder()
                .add("userId","1")
                .add("id","1")
                .add("title","OkHttp Practice")
                .build();

        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("error",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                resultData.setPostResult(result);
                activityMainBinding.setResult(resultData);
                Log.d("postResult",result);
            }
        });
    }
}