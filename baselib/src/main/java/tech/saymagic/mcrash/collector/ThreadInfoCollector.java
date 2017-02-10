package tech.saymagic.mcrash.collector;

import java.util.Map;

import tech.saymagic.mcrash.Constants;

/**
 * Created by saymagic on 2017/2/10.
 */

public class ThreadInfoCollector implements IInfoCollector {

    @Override
    public Map<String, String> collect(Map<String, String> origin) {
        Thread thread = Thread.currentThread();
        origin.put(Constants.THREAD_ID, String.valueOf(thread.getId()));
        origin.put(Constants.THREAD_NAME, String.valueOf(thread.getName()));
        origin.put(Constants.THREAD_PRIORITY, String.valueOf(thread.getPriority()));
        return origin;
    }

}
