package ru.sfedu.mavenproject.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mavenproject.models.City;
import ru.sfedu.mavenproject.models.Event;
import ru.sfedu.mavenproject.models.Place;

import java.util.Arrays;
import java.util.List;

public class CsvConverterEvent extends AbstractBeanField {
    private Logger log = LogManager.getLogger(CsvConverterEvent.class);
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        String params = value.substring(value.indexOf("{")+1, value.lastIndexOf("}"));
        String eventString = params.substring(0, params.lastIndexOf("}"));
        String eventParams = params.substring(eventString.indexOf("{")+1, eventString.lastIndexOf("}"));
        String simpleParamsString = eventParams.substring(0, eventParams.indexOf("photos")-2);
        List<String> simpleParams = BaseUtil.parseCsvObject("{" + simpleParamsString + "}");

        Event event = new Event();
        event.setId(Long.parseLong(simpleParams.get(0)));
        event.setName(simpleParams.get(1));
        event.setDate(simpleParams.get(2));
        event.setTime(simpleParams.get(3));
        event.setPrice(simpleParams.get(4));

        String photosParamsString = value.substring(value.indexOf("photos"), value.indexOf("]")+1);
        List<String> photosParams = (List<String>) new CsvConverterIds().convert(photosParamsString);
        event.setPhotos(photosParams);

        String placeParamsString = eventParams.substring(eventParams.indexOf("place"), eventParams.indexOf("}")+1);
        Place place = (Place) new CsvConverterPlace().convert(placeParamsString + "}");
        event.setPlace(place);

        return event;
    }
}
