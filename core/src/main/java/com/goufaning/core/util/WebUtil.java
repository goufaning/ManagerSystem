package com.goufaning.core.util;

import java.util.Collection;

public class WebUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
