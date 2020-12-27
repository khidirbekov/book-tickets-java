package ru.sfedu.mavenproject.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CsvConverterIds extends AbstractBeanField {
    private Logger log = LogManager.getLogger(CsvConverterIds.class);

    @Override
    public Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        log.info(value);
        List<Long> values = Stream.of(value.substring(value.indexOf("[")+1, value.lastIndexOf("]"))
                .split(",")).map(el -> Long.parseLong(el.trim())).collect(Collectors.toList());
        values.stream().forEach(el -> log.info(el));
        return values;
    }
}
