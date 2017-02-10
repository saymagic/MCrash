package tech.saymagic.mcrash.converter;

import java.io.IOException;
import java.io.OutputStream;

import tech.saymagic.mcrash.entity.Crash;

/**
 * Created by saymagic on 2017/2/10.
 */

public interface IConverter {

    byte[] convert(Crash crash) throws IOException;

}
