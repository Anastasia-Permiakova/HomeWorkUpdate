package org.project.utils;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

//класс для получения данных из сети
public class ControllerUtils {

    private ControllerUtils(){}

    //openweather api, ссылка на запрос, где вместо s нужно подставить город
    private static final String DEFAULT_CONNECT = "https://api.openweathermap.org/data/2.5/weather?q=%s&&units=metric&&appid=ada41553a2ad1f904423c8b287507d37";


    /**
     *
     * @param city название города
     * @return json строка с данными
     * @throws Exception е
     */
    //возвращает Json строку по погоде в городе
    public static String getJsonData(String city) throws Exception {
        //Запрос на сервер openweather
        URL url = new URL(String.format(DEFAULT_CONNECT, city));
        //Сканнер для считывание ответа
        Scanner in = new Scanner((InputStream) url.getContent());
        StringBuilder result = new StringBuilder();
        while (in.hasNext()){
            result.append(in.nextLine());
        }

        String string = result.toString();

        JSONObject jsonObject = new JSONObject(string);

        //если в сообщении код 404, то данного города не существует, тогда нужно сгенерировать  ошибку
        if(jsonObject.getInt("cod") == 404){
            throw new IllegalArgumentException("Город не существует");
        }

        return result.toString();

    }
}
