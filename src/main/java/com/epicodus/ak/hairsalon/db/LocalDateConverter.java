package com.epicodus.ak.hairsalon.db;

import org.sql2o.converters.Converter;
import org.sql2o.converters.ConverterException;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateConverter implements Converter<LocalDate> {
    @Override
    public LocalDate convert(Object val) throws ConverterException {
        if (val == null)
            return null;
        if (val instanceof java.sql.Date) {
            return ((Date) val).toLocalDate();
        }
        throw new ConverterException("Invalid type to convert to java.time.LocalDate");
    }

    @Override
    public Object toDatabaseParam(LocalDate val) {
        if (val == null)
            return null;
        return java.sql.Date.valueOf(val);
    }
}