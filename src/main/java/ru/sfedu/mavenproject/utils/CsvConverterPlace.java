package ru.sfedu.mavenproject.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.Any;
import ru.sfedu.mavenproject.models.City;
import ru.sfedu.mavenproject.models.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvConverterPlace extends AbstractBeanField {
    private Logger log = LogManager.getLogger(CsvConverterPlace.class);
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        String params = value.substring(value.indexOf("{")+1, value.lastIndexOf("}"));
        int firstSeparator = params.indexOf(", ");
        int secondSeparator = params.indexOf(", ", firstSeparator + 2);
        List<String> args = Arrays.asList(
                params.substring(0, firstSeparator),
                params.substring(firstSeparator + 2, secondSeparator),
                params.substring(secondSeparator + 2)
        );

        Place place = new Place();
        place.setId(Long.parseLong(args.get(0).split("=")[1]));
        place.setAddress(args.get(1).split("=")[1].replaceAll("'", ""));
        City city = (City) new CsvConverterCity().convert(args.get(2));
        place.setCity(city);

        log.info(place);
        return place;
    }
}
