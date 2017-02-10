package tech.saymagic.mcrash.logger;

import java.io.IOException;

import tech.saymagic.mcrash.entity.Crash;

/**
 * Created by saymagic on 2017/2/10.
 */

public interface ICrashLogger {

    void log(Crash crash) throws IOException;

}
