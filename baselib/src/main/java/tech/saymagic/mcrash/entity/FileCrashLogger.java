package tech.saymagic.mcrash.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import tech.saymagic.mcrash.converter.DefaultConverter;
import tech.saymagic.mcrash.converter.IConverter;
import tech.saymagic.mcrash.logger.ICrashLogger;

/**
 * Created by saymagic on 2017/2/10.
 */

public class FileCrashLogger implements ICrashLogger {

    private String mFilePath;

    private IConverter mConverter;

    public FileCrashLogger(String filePath) {
        this(filePath, new DefaultConverter());
    }

    public FileCrashLogger(String filePath, IConverter converter) {
        this.mFilePath = filePath;
        this.mConverter = converter;
    }

    @Override
    public void log(Crash crash) throws IOException {
        byte[] serialization = mConverter.convert(crash);
        FileOutputStream outputStream = new FileOutputStream(mFilePath);
        outputStream.write(serialization);
    }

}
