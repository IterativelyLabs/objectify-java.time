package com.iteratively.objectify.translate;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.TimestampValue;
import com.google.cloud.datastore.Value;
import com.google.cloud.datastore.ValueType;
import com.googlecode.objectify.impl.translate.SimpleTranslatorFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class JavaTimeLocalDateTimeTimestampTranslatorFactory extends SimpleTranslatorFactory<LocalDateTime, Timestamp> {

    private final ZoneOffset UTCZoneOffset = ZoneOffset.UTC;
    private final ZoneId UTCZoneId = UTCZoneOffset.normalized();

    public JavaTimeLocalDateTimeTimestampTranslatorFactory() {
        super(java.time.LocalDateTime.class, ValueType.TIMESTAMP);
    }

    @Override
    protected java.time.LocalDateTime toPojo(final Value<Timestamp> timestampValue) {
        Instant instant = Instant.ofEpochSecond(timestampValue.get().getSeconds());
        return LocalDateTime.ofInstant(instant, UTCZoneId);
    }

    @Override
    protected Value<Timestamp> toDatastore(final LocalDateTime localDateTime) {
        return new TimestampValue(Timestamp.ofTimeSecondsAndNanos(localDateTime.toEpochSecond(UTCZoneOffset),0));
    }




}
