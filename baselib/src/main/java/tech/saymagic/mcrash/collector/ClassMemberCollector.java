package tech.saymagic.mcrash.collector;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by saymagic on 2017/2/10.
 */

public class ClassMemberCollector implements IInfoCollector{

    private Object mHostObj;

    private Class mHostClz;

    public ClassMemberCollector(Object host) {
        this.mHostObj = host;
        this.mHostClz = host.getClass();
    }

    public ClassMemberCollector(Class clz) {
        this.mHostClz = clz;
    }

    @Override
    public Map<String, String> collect(Map<String, String> origin) {
        Field[] fields = mHostClz.getDeclaredFields();
        for (Field field: fields) {
            try {
                field.setAccessible(true);
                if (Modifier.isStatic(field.getModifiers())) {
                    origin.put(field.getName(), field.get(null).toString());
                } else if (mHostObj != null) {
                    origin.put(field.getName(), field.get(mHostObj).toString());
                }
            } catch (Exception e) {
                //ignore
            }
        }
        return origin;
    }
}
