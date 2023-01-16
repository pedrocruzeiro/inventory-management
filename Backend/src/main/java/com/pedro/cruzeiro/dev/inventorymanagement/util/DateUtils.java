package com.pedro.cruzeiro.dev.inventorymanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

  public static Timestamp getCurrentTimestamp(Long milliseconds) {
    LocalDateTime dateTime =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneOffset.UTC);
    return Timestamp.valueOf(
        LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneOffset.UTC));
  }

  public static Timestamp getCurrentTimestamp() {
    return Timestamp.valueOf(
        LocalDateTime.ofInstant(
            Instant.ofEpochMilli(Instant.now().toEpochMilli()), ZoneOffset.UTC));
  }
}
