package org.project.utils;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

//třida pro dastavani dat z internetu
public class ControllerUtils {

    private ControllerUtils(){}

    //openweather api, web kde misto s musim dat mesto
    private static final String DEFAULT_CONNECT = "https://api.openweathermap.org/data/2.5/weather?q=%s&&units=metric&&appid=ada41553a2ad1f904423c8b287507d37";


    /**
     *
     * @param city město
     * @return json string
     * @throws Exception е
     */
    //vrati Json string s počasí ve městě
    public static String getJsonData(String city) throws Exception {
        //openweather url
        URL url = new URL(String.format(DEFAULT_CONNECT, city));
        //Scanner na sčitani otpovědi
        Scanner in = new Scanner((InputStream) url.getContent());
        StringBuilder result = new StringBuilder();
        while (in.hasNext()){
            result.append(in.nextLine());
        }

        String string = result.toString();

        JSONObject jsonObject = new JSONObject(string);

        //pokud je kod 404, to znamena, ze město neexistuje
        if(jsonObject.getInt("cod") == 404){
            throw new IllegalArgumentException("Město neexistuje");
        }

        return result.toString();

    }
}
