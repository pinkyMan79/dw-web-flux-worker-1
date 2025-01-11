package one.terenin.webfluxconsumer1.holder;

import java.util.List;

public class DataHolder {
    public static ThreadLocal<List<String>> jsonDataHolder = new ThreadLocal<>();
    public static ThreadLocal<List<byte[]>> parquetDataHolder = new ThreadLocal<>();
}
