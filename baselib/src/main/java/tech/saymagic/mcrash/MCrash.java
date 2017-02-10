package tech.saymagic.mcrash;

import java.util.LinkedList;
import java.util.List;

import tech.saymagic.mcrash.collector.IInfoCollector;
import tech.saymagic.mcrash.logger.ICrashLogger;

public class MCrash {

    public static MCrashBuilder watchCurrentThread() {
        Thread currentThread = Thread.currentThread();
        return new MCrashBuilder(currentThread);
    }

    public static MCrashBuilder watchThread(Thread thread) {
        return new MCrashBuilder(thread);
    }

    public static MCrashBuilder watchDefault() {
        return new MCrashBuilder(null);
    }

    public static class MCrashBuilder{

        private Thread mWatchThread;

        private List<IInfoCollector> mInfoCollectters;

        private List<ICrashLogger> mCrashLoggers;

        private boolean mIgnoreOtherHandler;

        public MCrashBuilder(Thread watchThread) {
            mWatchThread = watchThread;
            mInfoCollectters = new LinkedList<>();
            mCrashLoggers = new LinkedList<>();
        }

        public MCrashBuilder addInfoCollector(IInfoCollector collectter) {
            mInfoCollectters.add(collectter);
            return this;
        }

        public MCrashBuilder addCrashLogger(ICrashLogger logger) {
            mCrashLoggers.add(logger);
            return this;
        }

        public MCrashBuilder ignoreOtherHandler(boolean ignoreOtherHandler) {
            this.mIgnoreOtherHandler = ignoreOtherHandler;
            return this;
        }

        public void start() {
            if (mWatchThread == null) {
                Thread.UncaughtExceptionHandler defaultHandler = mIgnoreOtherHandler ? null : Thread.getDefaultUncaughtExceptionHandler();
                Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionDelegate(mInfoCollectters, mCrashLoggers, defaultHandler));
            } else {
                Thread.UncaughtExceptionHandler defaultHandler = mIgnoreOtherHandler ? null : mWatchThread.getDefaultUncaughtExceptionHandler();
                mWatchThread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionDelegate(mInfoCollectters, mCrashLoggers, defaultHandler));
            }
        }
    }
}
