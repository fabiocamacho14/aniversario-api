package com.oranet.aniversarioapi.api.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Period;

@JsonComponent
public class PeriodSerializer extends JsonSerializer<Period> {

    @Override
    public void serialize(Period period, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (period == null) {
            throw new IOException("Period é nulo");
        }

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        jsonGenerator.writeString(String.format("%02d/%02d/%03d", days, months, years));
    }
}
