package ui.base;

import android.app.Notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.lavamusic.BuildConfig;
import com.android.lavamusic.R;
import com.android.volley.Request;

import net.RequestManager;

import utils.logger.LogLevel;
import utils.logger.Logger;

/**
 * Created by zhongrong.yu on 2016/6/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

 private Notification notification=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
        AbActivityManager.getInstance().addActivity(this);

    }


    ///////////////////////////////////////////////////////////////////////////
    // Abstract Method In Activity
    ///////////////////////////////////////////////////////////////////////////

    protected abstract void initView();

    protected abstract void initData();

    ///////////////////////////////////////////////////////////////////////////
    // Common Operation
    ///////////////////////////////////////////////////////////////////////////

    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }

//    public void executeRequest(Request<?> request) {
//        RequestManager.addRequest(request, this);
//    }


    @Override
    public void finish() {
        AbActivityManager.getInstance().addActivity(this);
        super.finish();
        overridePendingTransition(R.anim.anim_none, R.anim.trans_center_2_right);
    }



    public void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

}
