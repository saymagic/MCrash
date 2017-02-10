package tech.saymagic.mcrash;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tech.saymagic.mcrash.collector.IInfoCollector;
import tech.saymagic.mcrash.entity.Crash;
import tech.saymagic.mcrash.logger.ICrashLogger;

/**
 * Created by saymagic on 2017/2/10.
 */

public class UncaughtExceptionDelegate implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private List<IInfoCollector> mInfoCollectters;

    private List<ICrashLogger> mCrashLoggers;

    public UncaughtExceptionDelegate(List<IInfoCollector> infoCollectters,
                                     List<ICrashLogger> crashLoggers,
                                     Thread.UncaughtExceptionHandler defaultHandler) {
        this.mInfoCollectters = new LinkedList<>(infoCollectters == null ? Collections.<IInfoCollector>emptyList() : infoCollectters);
        this.mCrashLoggers = new LinkedList<>(crashLoggers == null ? Collections.<ICrashLogger>emptyList() : crashLoggers);
        this.mDefaultHandler = defaultHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Map<String, String> extraInfos = new HashMap<>();
        for (IInfoCollector infoCollectter : mInfoCollectters) {
            extraInfos = infoCollectter.collect(extraInfos);
        }
        Crash crash = new Crash(throwable, extraInfos, thread);
        for (ICrashLogger logger : mCrashLoggers) {
            try {
                logger.log(crash);
            } catch (IOException e) {
               //wtf?
            }
        }
        if (mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, throwable);
        }
    }
}
