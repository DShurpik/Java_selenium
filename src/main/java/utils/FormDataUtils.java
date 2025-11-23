package utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FormDataUtils {

    public static long randomMobile() {
        return ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L + 1);
    }

    public static String safePick(List<String> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalStateException("Excel data is empty or not loaded! Check file/path/sheet.");
        }
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
