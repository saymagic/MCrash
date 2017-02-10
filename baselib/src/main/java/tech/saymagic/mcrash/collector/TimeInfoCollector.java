package tech.saymagic.mcrash.collector;

import java.util.Map;

import tech.saymagic.mcrash.Constants;

/**
 * Created by saymagic on 2017/2/10.
 */

public class TimeInfoCollector implements IInfoCollector {

    @Override
    public Map<String, String> collect(Map<String, String> origin) {
        origin.put(Constants.CRASH_TIME, String.valueOf(System.currentTimeMillis()));
        return origin;
    }

}
