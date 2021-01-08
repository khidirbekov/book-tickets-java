package ru.sfedu.bookticket.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import ru.sfedu.bookticket.models.City;

import java.util.List;

public class CsvConverterCity extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        List<String> params = BaseUtil.parseCsvObject(value);
        City city = new City();
        city.setId(Long.parseLong(params.get(0)));
        city.setName(params.get(1));
        return city;
    }
}
