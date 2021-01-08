package ru.sfedu.bookticket.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import ru.sfedu.bookticket.Constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CsvConverterIds extends AbstractBeanField {
    @Override
    public Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        List<Long> values = Stream.of(
                value.substring(
                        value.indexOf(Constants.ARRAY_LEFT_SEPARATOR) + 1,
                        value.lastIndexOf(Constants.ARRAY_RIGHT_SEPARATOR)
                ).split(Constants.ARRAY_ELEMENT_SEPARATOR)
        ).map(el -> Long.parseLong(el.trim())).collect(Collectors.toList());
        return values;
    }
}
