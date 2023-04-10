package com.example.module_enroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_enroll.okhttp.JSONEnroll;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import eventbus.EventEditSchedule_MainActivity_Back;
import eventbus.EventEnrollBackToIndividual;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Route(path = "/enroll/EnrollActivity")
public class EnrollActivity extends AppCompatActivity {

    //注意，如果账号已存在，就删除账号框里的内容让重写

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        EditText et_account = findViewById(R.id.enroll_account);
        EditText et_password = findViewById(R.id.enroll_password);
        EditText et_password2 = findViewById(R.id.enroll_password2);
        EditText et_user = findViewById(R.id.enroll_user);

        Button btn_enroll = findViewById(R.id.enroll_activity_toenroll);
        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_password.getText().toString().equals(et_password2.getText().toString())){
                    //两次密码相同，传入账号进行注册
                    String account = et_account.getText().toString();
                    String user = et_user.getText().toString();
                    String password = et_password.getText().toString();

                    //不能在主线程传  要开个子线程进行网络请求
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //在这里把账号密码传入后台进行注册
                            OkHttpClient client = new OkHttpClient();
                            FormBody formBody = new FormBody.Builder()
                                    .add("telephone", account)
                                    .add("password", password)
                                    .add("username", user)
                                    .build();
                            Request request = new Request.Builder()
                                    .url("http://120.79.186.191:8082/api/auth/register")
                                    .post(formBody)
                                    .build();
                            Response response = null;
                            JSONEnroll enroll = null;
                            try {
                                response = client.newCall(request).execute();
                                enroll = parseJSONWithGSON(response.body().string());
                                Log.d("Here", "finish call");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if(!enroll.code.equals("200")){
                                Log.d("Here", "logincode = "+enroll.code);
                                //有异常情况，切回主线程弹toast
                                JSONEnroll finalLogin = enroll;
                                Log.d("Here", "loginmsg = "+enroll.mag);
                                Log.d("Here", "finloginmsg = "+finalLogin.mag);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(EnrollActivity.this, finalLogin.mag, Toast.LENGTH_SHORT).show();
                                        if(finalLogin.mag.equals("手机号必须为十一位")){
                                            et_account.setText("");
                                        }else if(finalLogin.code.equals("密码不能少于6位")){
                                            et_password.setText("");
                                            et_password2.setText("");
                                        }else if(finalLogin.code.equals("用户已经存在")){
                                            et_account.setText("");
                                        }
                                    }
                                });
                            }else{
                                //注册成功，切回individualFragment，记得携带账号信息
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(EnrollActivity.this, "完成注册", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });

                            }

                        }
                    }).start();

                }else{
                    Toast.makeText(EnrollActivity.this,"两次输入的密码不相同",Toast.LENGTH_SHORT).show();
                    //密码不同，清空密码区并弹Toast
                    et_password.setText("");
                    et_password2.setText("");
                }
            }
        });
    }

    private JSONEnroll parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        JSONEnroll login = gson.fromJson(jsonData,JSONEnroll.class);
        return login;
    }

}