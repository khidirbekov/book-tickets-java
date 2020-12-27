package ru.sfedu.mavenproject.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mavenproject.models.City;
import ru.sfedu.mavenproject.models.Photographer;

import java.util.List;

public class CsvConverterPhotographer extends AbstractBeanField {
    private Logger log = LogManager.getLogger(CsvConverterCity.class);
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        List<String> params = BaseUtil.parseCsvObject(value);

        Photographer photographer = new Photographer();
        photographer.setId(Long.parseLong(params.get(0)));
        photographer.setName(params.get(1));
        photographer.setExpirence(Long.parseLong(params.get(2)));
        photographer.setSite(params.get(3));
        log.info(photographer);
        return photographer;
    }
}
