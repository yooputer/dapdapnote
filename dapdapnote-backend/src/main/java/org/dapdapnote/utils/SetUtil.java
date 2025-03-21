package org.dapdapnote.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUtil {
    public static <T> Set<T> objectToSet(T obejct) {
        return new HashSet<>(Collections.singleton(obejct));
    }

    public static <T> Set<T> objectListToSet(List<T> list) {
        return new HashSet<>(list);
    }
}