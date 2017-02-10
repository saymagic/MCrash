package tech.saymagic.mcrash.demo;

import android.app.Application;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import tech.saymagic.mcrash.MCrash;
import tech.saymagic.mcrash.collector.ClassMemberCollector;
import tech.saymagic.mcrash.collector.ThreadInfoCollector;
import tech.saymagic.mcrash.collector.TimeInfoCollector;
import tech.saymagic.mcrash.entity.FileCrashLogger;

/**
 * Created by saymagic on 2017/2/11.
 */

public class MCrashApplication extends Application {

    private static final String CRASH_INFO_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "crash.info";

    @Override
    public void onCreate() {
        super.onCreate();
        initCrashHandler();
    }

    private void initCrashHandler() {
        MCrash.watchDefault()
                .addCrashLogger(new FileCrashLogger(CRASH_INFO_PATH))
                .addInfoCollector(new TimeInfoCollector())
                .addInfoCollector(new ThreadInfoCollector())
                .addInfoCollector(new ClassMemberCollector(BuildConfig.class))
                .addInfoCollector(new ClassMemberCollector(Build.class))
                .start();
    }
}
