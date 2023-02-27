package com.example.module_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_login.util.JSONLogin;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import eventbus.EventEditLogin;
import eventbus.EventEditSchedule_MainActivity_Back;
import eventbus.EventLoginInformation;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Route(path = "/login/loginActivity")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_account = findViewById(R.id.login_one_account);
        EditText et_password = findViewById(R.id.login_one_password);

        //点击登录部分
        Button btn_login = findViewById(R.id.login_activity_tologin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                Log.d("here", "onClick: account = "+account);
                Log.d("here", "onClick: passwprd = "+password);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //在这里把账号密码传入后台进行验证
                        OkHttpClient client = new OkHttpClient();
                        FormBody formBody = new FormBody.Builder()
                                .add("telephone", account)
                                .add("password", password)
                                .build();
                        Request request = new Request.Builder()
                                .url("http://8.130.23.45:8082/api/auth/login")
                                .post(formBody)
                                .build();
                        Response response = null;
                        JSONLogin login = null;
                        try {
                            response = client.newCall(request).execute();
                            login = parseJSONWithGSON(response.body().string());
                            Log.d("Here", "finish call");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(!login.code.equals("200")){
                            Log.d("Here", "logincode = "+login.code);
                            //有异常情况，切回主线程弹toast
                            JSONLogin finalLogin = login;
                            Log.d("Here", "loginmsg = "+login.msg);
                            Log.d("Here", "finloginmsg = "+finalLogin.msg);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, finalLogin.msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            //登录成功，发送eventbus通知登录然后切回individualFragment
                            JSONLogin finalLogin = login;
                            EventBus.getDefault().postSticky(new EventLoginInformation(true, finalLogin.telephone, finalLogin.username));
                            EventBus.getDefault().postSticky(new EventEditLogin(true,finalLogin.telephone,finalLogin.username));
                            Log.d("Here", "登录成功");

                            finish();
                        }

                    }
                }).start();


            }
        });

        //点击跳转注册页面
        Button btn_to_enroll = findViewById(R.id.login_activity_toenroll);
        btn_to_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/enroll/EnrollActivity").navigation();
            }
        });
    }
    private JSONLogin parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        JSONLogin login = gson.fromJson(jsonData,JSONLogin.class);
        return login;
    }

}