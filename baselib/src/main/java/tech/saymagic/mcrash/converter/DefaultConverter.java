package tech.saymagic.mcrash.converter;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import tech.saymagic.mcrash.entity.Crash;

/**
 * Created by saymagic on 2017/2/10.
 */

public class DefaultConverter implements IConverter {

    @Override
    public byte[] convert(Crash crash) throws IOException {
        Writer writer = new StringWriter();
        Map<String, String> extra = crash.getExtraInfo();
        if (extra != null) {
            for (Map.Entry<String, String> entry: extra.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                writer.append(key + "=" + value + "\n");
            }
        }
        Throwable throwable = crash.getThrowable();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String ret = writer.toString();
        return ret.getBytes();
    }

}
