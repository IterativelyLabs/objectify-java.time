package com.iteratively.objectify.translate;

import com.googlecode.objectify.ObjectifyFactory;

public class JavaTimeTranslators {

    private JavaTimeTranslators() {
    }

    public static void add(ObjectifyFactory factory) {
        factory.getTranslators().add(new JavaTimeLocalDateTimestampTranslatorFactory());
        factory.getTranslators().add(new JavaTimeLocalDateTimeTimestampTranslatorFactory());
    }
}
