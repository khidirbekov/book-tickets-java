package ru.sfedu.bookticket;

public class Constants {
    public static final String ERROR_GET_EVENT = "Get event error: wrong event type";
    public static final String ERROR_GET_FOOTBALL_EVENT = "Get football event error: Football event not exist";
    public static final String ERROR_GET_IT_EVENT = "Get it event error: IT event not exist";
    public static final String ERROR_GET_MUSIC_EVENT = "Get music event error: Music event not exist";
    public static final String ERROR_BOOK_TICKET = "Book ticket error: Event not exist";
    public static final String ERROR_GET_TICKET = "Get ticket error: Ticket not exist";
    public static final String ERROR_GET_CITY = "Get city error: City not exist";
    public static final String ERROR_CREATE_CITY = "Create city error: [name] is empty";
    public static final String ERROR_UPDATE_CITY = "Update city error: City not exist";
    public static final String ERROR_DELETE_CITY = "Delete city error: City not exist";
    public static final String ERROR_UPDATE_EVENT = "Update event error: wrong event type";
    public static final String ERROR_UPDATE_FOOTBALL_EVENT = "Update football event error: Football event not exist";
    public static final String ERROR_UPDATE_IT_EVENT = "Update it event error: IT event not exist";
    public static final String ERROR_UPDATE_MUSIC_EVENT = "Update music event error: Music event not exist";
    public static final String ERROR_PLACE_NOT_EXIST = "Error: place not exist";
    public static final String ERROR_CREATE_EVENT = "Create event error: wrong event type";
    public static final String ERROR_CITY_NOT_EXIST = "Error: city not exist";
    public static final String ERROR_PHOTOGRAPHER_NOT_EXIST = "Error: photographer not exist";
    public static final String ERROR_PHOTO_NOT_EXIST = "Error: photo not exist";
    public static final String ERROR_CREATE_PHOTOGRAPHER = "Create photographer error: name is empty";
    public static final String ERROR_UPDATE_PHOTOGRAPHER = "Update photographer error: name is empty";
    public static final String ERROR_DELETE_EVENT = "Delete event error: event not exist";
    public static final String ERROR_WRONG_EVENT_TYPE = "Error: wrong event type";

    public static final String SUCCESS_GET_FOOTBALL_EVENT = "Get football event success";
    public static final String SUCCESS_GET_IT_EVENT = "Get it event success";
    public static final String SUCCESS_GET_MUSIC_EVENT = "Get music event success";
    public static final String SUCCESS_BOOK_TICKET = "Book ticket success";
    public static final String SUCCESS_GET_TICKET = "Get ticket success";
    public static final String SUCCESS_GET_CITY = "Get city success";
    public static final String SUCCESS_CREATE_CITY = "Create city success";
    public static final String SUCCESS_UPDATE_CITY = "Update city success";
    public static final String SUCCESS_DELETE_CITY = "Delete city success";
    public static final String SUCCESS_UPDATE_FOOTBALL_EVENT = "Update football event success";
    public static final String SUCCESS_UPDATE_IT_EVENT = "Update it event success";
    public static final String SUCCESS_UPDATE_MUSIC_EVENT = "Update music event success";
    public static final String SUCCESS_CREATE_FOOTBALL_EVENT = "Create football event success";
    public static final String SUCCESS_CREATE_IT_EVENT = "Create it event success";
    public static final String SUCCESS_CREATE_MUSIC_EVENT = "Create music event success";
    public static final String SUCCESS_CREATE_PLACE = "Create place success";
    public static final String SUCCESS_UPDATE_PLACE = "Update place success";
    public static final String SUCCESS_DELETE_PLACE = "Delete place success";
    public static final String SUCCESS_GET_PLACE = "Get place success";
    public static final String SUCCESS_CREATE_PHOTO = "Create photo success";
    public static final String SUCCESS_DELETE_PHOTO = "Delete photo success";
    public static final String SUCCESS_GET_PHOTO = "Get photo success";
    public static final String SUCCESS_CREATE_PHOTOGRAPHER = "Create photographer success";
    public static final String SUCCESS_UPDATE_PHOTOGRAPHER = "Update photographer success";
    public static final String SUCCESS_DELETE_PHOTOGRAPHER = "Delete photographer success";
    public static final String SUCCESS_GET_PHOTOGRAPHER = "Get photographer success";
    public static final String SUCCESS_DELETE_EVENT = "Delete event success";

    public static final String OBJECT_LEFT_SEPARATOR = "{";
    public static final String OBJECT_RIGHT_SEPARATOR = "}";
    public static final String OBJECT_FIELD_SEPARATOR = ", ";
    public static final String OBJECT_VALUE_SEPARATOR = "=";
    public static final String ARRAY_LEFT_SEPARATOR = "[";
    public static final String ARRAY_RIGHT_SEPARATOR = "]";
    public static final String ARRAY_ELEMENT_SEPARATOR = ",";
    public static final String STRING_SEPARATOR = "'";
    public static final String EMPTY = "";

    public static final String FOOTBALL = "FOOTBALL";
    public static final String IT = "IT";
    public static final String MUSIC = "MUSIC";

    public static final String CITY = "city";
    public static final String FOOTBALL_EVENT = "footballevent";
    public static final String IT_EVENT = "itevent";
    public static final String MUSIC_EVENT = "musicevent";
    public static final String TICKET = "ticket";
    public static final String PHOTO = "photo";
    public static final String PHOTOS = "photos";
    public static final String PHOTOGRAPHER = "photographer";
    public static final String PLACE = "place";
    public static final String TICKET_EVENT = "ticketevent";

    public static final String CSV_EXTENSION = ".csv";
    public static final String CSV_PATH = "CSV_PATH";
    public static final String CSV_TEST_PATH = "CSV_TEST_PATH";

    public static final String XML_EXTENSION = ".xml";
    public static final String XML_PATH = "XML_PATH";
    public static final String XML_TEST_PATH = "XML_TEST_PATH";

    public static final String USER = "USER";
    public static final String PASSWORD = "PASSWORD";
    public static final String DB_EXTENSION = ".mv.db";
    public static final String JDBC_PROTOCOL = "JDBC_PROTOCOL";
    public static final String JDBC_PATH = "JDBC_PATH";
    public static final String JDBC_TEST_PATH = "JDBC_TEST_PATH";
    public static final String JDBC_NAME = "JDBC_NAME";

    public static final String INIT_TABLES = "INIT_TABLES";
    public static final String SELECT_ALL = " SELECT * FROM ";
    public static final String WHERE = " WHERE ";
    public static final String ID = " id = ";
    public static final String EVENT_ID = " eventId = ";
    public static final String EVENT_TYPE = " eventType = ";
    public static final String AND = " and ";

    public static final String INSERT_CITY = "INSERT INTO city(id,name) VALUES (?, ?)";
    public static final String INSERT_PLACE = "INSERT INTO place(id,address, city) VALUES (?, ?, ?)";
    public static final String INSERT_PHOTOGRAPHER = "INSERT INTO photographer(id, name, expirence, site) VALUES (?, ?, ?, ?)";
    public static final String INSERT_PHOTO = "INSERT INTO photo (id, name, path, photographer) VALUES (?, ?, ?, ?)";
    public static final String INSERT_FOOTBALL_EVENT = "INSERT INTO footballevent (id, name, place, date, time, price, photos, team1, team2) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_IT_EVENT = "INSERT INTO itevent (id, name, place, date, time, price, photos, stack) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_MUSIC_EVENT = "INSERT INTO musicevent (id, name, place, date, time, price, photos, artist) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_TICKET = "INSERT INTO ticket(id, buyerName, payTime, event) VALUES (?, ?, ?, ?)";
    public static final String INSERT_TICKET_EVENT = "INSERT INTO ticketevent(id, eventid, eventType) VALUES (?, ?, ?)";
    public static final String UPDATE_CITY  = "UPDATE city SET name = ? WHERE id = ";
    public static final String UPDATE_PLACE  = "UPDATE place SET address = ?, city = ? WHERE id = ";
    public static final String UPDATE_PHOTOGRAPHER = "UPDATE photographer SET name = ?, expirence = ?, site = ? WHERE id = ";
    public static final String UPDATE_FOOTBALL_EVENT = "UPDATE footballevent SET name = ?, place = ?, date = ?, time = ?, price = ?, photos = ?, team1 = ?, team2 = ? WHERE id = ";
    public static final String UPDATE_IT_EVENT = "UPDATE itevent SET name = ?, place = ?, date = ?, time = ?, price = ?, photos = ?, stack = ? WHERE id = ";
    public static final String UPDATE_MUSIC_EVENT = "UPDATE musicevent SET name = ?, place = ?, date = ?, time = ?, price = ?, photos = ?, artist = ? WHERE id = ";
    public static final String DElETE = "DELETE FROM ";
    public static final String VARCHAR = "VARCHAR";

    public static final String DEBUG_GET_EVENT = "Debug: get event";
    public static final String DEBUG_GET_IT_EVENT = "Debug: get it event";
    public static final String DEBUG_GET_MUSIC_EVENT = "Debug: get music event";
    public static final String DEBUG_GET_FOOTBALL_EVENT = "Debug: get footbal event";
    public static final String DEBUG_BOOK_TICKET = "Debug: book ticket";
    public static final String DEBUG_GET_TICKET = "Debug: get ticket";
    public static final String DEBUG_GET_CITY = "Debug: get city";
    public static final String DEBUG_CREATE_CITY = "Debug: create city";
    public static final String DEBUG_UPDATE_CITY = "Debug: update city";
    public static final String DEBUG_DELETE_CITY = "Debug: delete city";
    public static final String DEBUG_UPDATE_EVENT = "Debug: update event";
    public static final String DEBUG_UPDATE_FOOTBALL_EVENT = "Debug: update football event";
    public static final String DEBUG_UPDATE_IT_EVENT = "Debug: update it event";
    public static final String DEBUG_UPDATE_MUSIC_EVENT = "Debug: update music event";
    public static final String DEBUG_CREATE_EVENT = "Debug: create event";
    public static final String DEBUG_CREATE_FOOTBALL_EVENT = "Debug: create football event";
    public static final String DEBUG_CREATE_IT_EVENT = "Debug: create it event";
    public static final String DEBUG_CREATE_MUSIC_EVENT = "Debug: create music event";
    public static final String DEBUG_CREATE_PLACE = "Debug: create place";
    public static final String DEBUG_UPDATE_PLACE = "Debug: update place";
    public static final String DEBUG_DELETE_PLACE = "Debug: delete place";
    public static final String DEBUG_GET_PLACE = "Debug: get place";
    public static final String DEBUG_CREATE_PHOTO = "Debug: create photo";
    public static final String DEBUG_DELETE_PHOTO = "Debug: delete photo";
    public static final String DEBUG_GET_PHOTO = "Debug: get photo";
    public static final String DEBUG_CREATE_PHOTOGRAPHER = "Debug: create photographer";
    public static final String DEBUG_UPDATE_PHOTOGRAPHER = "Debug: update photographer";
    public static final String DEBUG_DELETE_PHOTOGRAPHER = "Debug: delete photographer";
    public static final String DEBUG_GET_PHOTOGRAPHER = "Debug: get photographer";
    public static final String DEBUG_DELETE_EVENT = "Debug: delete event";

    public static final String ENV_PROP_PATH = "envprop";
    public static final String CSV = "csv";
    public static final String XML = "xml";
    public static final String JDBC = "jdbc";
    public static final String WRONG_PROVIDER = "Error: wrong provider";
    public static final String WRONG_METHOD = "Error: wrong method";
    public static final String ARGS_IS_EMPTY = "Error: args is empty";

    public static final String GET_EVENT = "getevent";
    public static final String GET_FOOTBALL_EVENT = "getfootballevent";
    public static final String GET_IT_EVENT = "getitevent";
    public static final String GET_MUSIC_EVENT = "getmusicevent";
    public static final String BOOK_TICKET = "bookticket";
    public static final String GET_TICKET = "getticket";
    public static final String GET_CITY = "getcity";
    public static final String CREATE_CITY = "createcity";
    public static final String PUT_CITY = "updatecity";
    public static final String DELETE_CITY = "deletecity";
    public static final String PUT_EVENT = "updateevent";
    public static final String PUT_FOOTBALL_EVENT = "updatefootballevent";
    public static final String PUT_IT_EVENT = "updateitevent";
    public static final String PUT_MUSIC_EVENT = "updatemusicevent";
    public static final String CREATE_EVENT = "createevent";
    public static final String CREATE_FOOTBALL_EVENT = "createfootballevent";
    public static final String CREATE_IT_EVENT = "createitevent";
    public static final String CREATE_MUSIC_EVENT = "createmusicevent";
    public static final String CREATE_PLACE = "createplace";
    public static final String PUT_PLACE = "updateplace";
    public static final String DELETE_PLACE = "deleteplace";
    public static final String GET_PLACE = "getplace";
    public static final String CREATE_PHOTO = "createphoto";
    public static final String DELETE_PHOTO = "deletephoto";
    public static final String GET_PHOTO = "getphoto";
    public static final String CREATE_PHOTOGRAPHER = "createphotographer";
    public static final String PUT_PHOTOGRAPHER = "updatephotographer";
    public static final String DELETE_PHOTOGRAPHER = "deletephotographer";
    public static final String GET_PHOTOGRAPHER = "getphotographer";
    public static final String DELETE_EVENT = "deleteevent";

    public static final String DEBUG_JDBC_INIT_DATA_SOURCE = "JDBC: Init data source";
    public static final String JDBC_DATA_SOURCE_MESSAGE = "JDBC: Data source inited and generated data";
    public static final String WRONG_ARGS = "Error: wrong args";
}