package com.oranet.aniversarioapi.api.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss XXX");

    @Override
    public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (offsetDateTime == null) {
            throw new IOException("OffsetDateTime é nulo");
        }
        jsonGenerator.writeString(DATE_TIME_FORMATTER.format(offsetDateTime));
    }
}
