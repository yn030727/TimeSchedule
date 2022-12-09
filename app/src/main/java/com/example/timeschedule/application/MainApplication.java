package com.example.timeschedule.application;

import static com.alibaba.android.arouter.launcher.ARouter.init;
import android.app.Application;
import android.os.Build;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.timeschedule.BuildConfig;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(isDebug()){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private boolean isDebug(){
        return BuildConfig.DEBUG;
    }

/*    @Override
    public void onTerminate() {
        super.onTerminate();
       ARouter.getInstance().destroy();//关闭ARouter
    }*/
}
