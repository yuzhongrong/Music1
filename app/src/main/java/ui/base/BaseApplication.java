package ui.base;

import android.app.Application;
import android.content.Context;

import com.android.lavamusic.BuildConfig;
import com.facebook.stetho.Stetho;

import ui.view.imageloader.ImageLoadProxy;
import utils.logger.LogLevel;
import utils.logger.Logger;

/**
 * Created by zhongrong.yu on 2016/6/17.
 */
public class BaseApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoadProxy.initImageLoader(this);

        if (BuildConfig.DEBUG) {
            Logger.init().hideThreadInfo().setMethodCount(1).setLogLevel(LogLevel.FULL);
        }
        //chrome debug
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());


    }


    public static Context getContext() {
        return mContext;
    }
}
