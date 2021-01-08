package ru.sfedu.bookticket.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import ru.sfedu.bookticket.models.Photographer;

import java.util.List;

public class CsvConverterPhotographer extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        List<String> params = BaseUtil.parseCsvObject(value);

        Photographer photographer = new Photographer();
        photographer.setId(Long.parseLong(params.get(0)));
        photographer.setName(params.get(1));
        photographer.setExpirence(Long.parseLong(params.get(2)));
        photographer.setSite(params.get(3));
        return photographer;
    }
}
