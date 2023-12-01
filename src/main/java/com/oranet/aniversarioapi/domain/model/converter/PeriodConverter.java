package com.oranet.aniversarioapi.domain.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Period;

@Converter
public class PeriodConverter implements AttributeConverter<Period, String> {

    @Override
    public String convertToDatabaseColumn(Period attribute) {
        int years = attribute.getYears();
        int months = attribute.getMonths();
        int days = attribute.getDays();

        return String.format("%02d/%02d/%03d", days, months, years);
    }

    @Override
    public Period convertToEntityAttribute(String dbData) {
        String[] numbers = dbData.split("/");


//        Period period = Period.of(
//                Integer.
//        )

        return Period.of(
                Integer.parseInt(numbers[0]),
                Integer.parseInt(numbers[1]),
                Integer.parseInt(numbers[2])
        );
    }
}
