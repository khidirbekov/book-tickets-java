package ru.sfedu.mavenproject.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mavenproject.models.City;

import java.util.List;

public class CsvConverterCity extends AbstractBeanField {
    private Logger log = LogManager.getLogger(CsvConverterCity.class);
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        List<String> params = BaseUtil.parseCsvObject(value);

        City city = new City();
        city.setId(Long.parseLong(params.get(0)));
        city.setName(params.get(1));

        log.info(city);
        return city;
    }
}
