package com.iteratively.objectify.translate;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.LoadEngine;
import com.googlecode.objectify.impl.Path;
import com.googlecode.objectify.impl.translate.*;
import com.googlecode.objectify.test.util.LocalDatastoreExtension;
import com.googlecode.objectify.test.util.LocalMemcacheExtension;
import com.googlecode.objectify.test.util.ObjectifyExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith({LocalDatastoreExtension.class, ObjectifyExtension.class, LocalMemcacheExtension.class})
public class JavaTimeTranslatorsTest {

    @Test
    public void test_expected_translators_are_added() {
        ObjectifyFactory objectifyFactory = ObjectifyService.factory();

        Translators translators = objectifyFactory.getTranslators();
        assertNotNull(translators);

        JavaTimeTranslators.add(objectifyFactory);

        assertNotNull(translators.get(new TypeKey<Timestamp>(LocalDate.class), new CreateContext(objectifyFactory), Path.root()));
        assertNotNull(translators.get(new TypeKey<Timestamp>(LocalDateTime.class), new CreateContext(objectifyFactory), Path.root()));
    }

    @Test
    public void test_localdate_translation_works_as_expected() {
        final LoadEngine loadEngine = mock(LoadEngine.class);

        ObjectifyFactory objectifyFactory = ObjectifyService.factory();

        JavaTimeTranslators.add(objectifyFactory);


        Translators translators = objectifyFactory.getTranslators();
        assertNotNull(translators);

        final Timestamp timestamp = Timestamp.now();
        final LocalDate now = LocalDate.now();

        Translator<LocalDate, Timestamp> translator = translators.get(new TypeKey<Timestamp>(LocalDate.class), new CreateContext(objectifyFactory), Path.root());
        assertNotNull(translator);

        Value<Timestamp> timestampValue = translator.save(now, true, new SaveContext(), Path.root());
        assertNotNull(timestampValue);

        LocalDate localDate = translator.load(new TimestampValue(timestamp) ,new LoadContext(loadEngine), Path.root());
        assertNotNull(localDate);
        assertEquals(now, localDate);
    }

    @Test
    public void test_localdatetime_translation_works_as_expected() {
        final LoadEngine loadEngine = mock(LoadEngine.class);

        ObjectifyFactory objectifyFactory = ObjectifyService.factory();

        JavaTimeTranslators.add(objectifyFactory);


        Translators translators = objectifyFactory.getTranslators();
        assertNotNull(translators);

        final Timestamp timestamp = Timestamp.now();

        Translator<LocalDateTime, Timestamp> translator = translators.get(new TypeKey<Timestamp>(LocalDateTime.class), new CreateContext(objectifyFactory), Path.root());
        assertNotNull(translator);

        LocalDateTime localDateTime = translator.load(new TimestampValue(timestamp) ,new LoadContext(loadEngine), Path.root());
        assertNotNull(localDateTime);
    }
}
