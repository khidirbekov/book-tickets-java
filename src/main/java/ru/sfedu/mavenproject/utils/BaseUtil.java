package ru.sfedu.mavenproject.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseUtil<T> {

    public static long generateID() {
        final long max = 1000000;
        final long min = 100000;
        return (long) (System.currentTimeMillis() / (Math.random() * (max - min + 1) + min));
    }

    public static List<String> parseCsvObject(String value) {
        String args = value.substring(value.indexOf("{")+1,value.lastIndexOf("}"));
        List<String> values = Stream.of(args.split(", "))
                .map(el -> el.split("=")[1]
                        .replaceAll("'", "")
                ).collect(Collectors.toList());
        return values;
    }

    public List<T> transformStringArgumentsToList(String args) {
        return Stream.of(args.split(", ")).map(el -> (T) el.trim()).collect(Collectors.toList());
    }

    public static boolean isEmpty(String value) {
        return value.trim().length() > 0;
    }
}
