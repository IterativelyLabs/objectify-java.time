[![Java CI with Gradle](https://github.com/IterativelyLabs/objectify-java.time/actions/workflows/gradle.yml/badge.svg)](https://github.com/IterativelyLabs/objectify-java.time/actions/workflows/gradle.yml)

# objectify-java.time

Utility classes to enable use of java.time classes within Objectify entities.

## Initiasing

To add support for `LocalDateTime` and `LocalDate`, use `JavaTimeTranslators` to add everything in one go.

```java
        ObjectifyFactory objectifyFactory = ObjectifyService.factory();
        JavaTimeTranslators.add(objectifyFactory);
```

Alternatively, if you only want specific support, you can add the individual factories yourself.
```java
  # LocalDate
  objectifyFactory.getTranslators().addEarly(JavaTimeLocalDateTimestampTranslatorFactory());
```

```java
  # LocalDateTime
  objectifyFactory.getTranslators().addEarly(JavaTimeLocalDateTimeTimestampTranslatorFactory());
```
