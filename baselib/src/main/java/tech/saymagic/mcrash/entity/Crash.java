package tech.saymagic.mcrash.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by saymagic on 2017/2/10.
 */

public class Crash {

    private Thread mThread;

    private Throwable mThrowable;

    private Map<String, String> mExtraInfo;

    public Crash(Throwable throwable) {
        this.mThrowable = throwable;
        this.mExtraInfo = new HashMap<>();
    }

    public Crash(Throwable throwable, Map<String, String> extraInfo) {
        this.mThrowable = throwable;
        this.mExtraInfo = new HashMap<>(extraInfo);
    }

    public Crash(Throwable throwable, Map<String, String> extraInfo, Thread thread) {
        this.mThrowable = throwable;
        this.mExtraInfo = new HashMap<>(extraInfo);
        this.mThread = thread;
    }

    public void setThrowable(Throwable throwable) {
        mThrowable = throwable;
    }

    public void setExtraInfo(Map<String, String> extraInfo) {
        mExtraInfo = extraInfo;
    }

    public Crash addExtraInfo(String k, String v) {
        mExtraInfo.put(k, v);
        return this;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public Map<String, String> getExtraInfo() {
        return mExtraInfo;
    }
}
