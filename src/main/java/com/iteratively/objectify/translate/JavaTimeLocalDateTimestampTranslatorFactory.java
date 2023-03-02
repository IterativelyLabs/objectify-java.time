package com.iteratively.objectify.translate;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.TimestampValue;
import com.google.cloud.datastore.Value;
import com.google.cloud.datastore.ValueType;
import com.googlecode.objectify.impl.translate.SimpleTranslatorFactory;

import java.sql.Date;
import java.time.*;

public class JavaTimeLocalDateTimestampTranslatorFactory extends SimpleTranslatorFactory<LocalDate, Timestamp> {

    private final ZoneId UTCZoneId = ZoneOffset.UTC.normalized();

    public JavaTimeLocalDateTimestampTranslatorFactory() {
        super(LocalDate.class, ValueType.TIMESTAMP);
    }

    @Override
    protected LocalDate toPojo(final Value<Timestamp> timestampValue) {
        Instant instant = Instant.ofEpochSecond(timestampValue.get().getSeconds());
        return LocalDate.ofInstant(instant, UTCZoneId);
    }

    @Override
    protected Value<Timestamp> toDatastore(final LocalDate localDate) {
        Instant instant = Instant.from(localDate.atStartOfDay(UTCZoneId));
        return new TimestampValue(Timestamp.of(Date.from(instant)));
    }
}
