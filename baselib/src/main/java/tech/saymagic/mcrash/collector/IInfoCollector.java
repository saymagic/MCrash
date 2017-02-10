package tech.saymagic.mcrash.collector;

import java.util.Map;

/**
 * Created by saymagic on 2017/2/10.
 */

public interface IInfoCollector {

    Map<String, String> collect(Map<String, String> origin);

}
