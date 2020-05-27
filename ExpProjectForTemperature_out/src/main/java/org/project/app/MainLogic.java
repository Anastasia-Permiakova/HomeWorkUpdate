package org.project.app;

import org.json.JSONObject;
import org.project.dto.Model;
import org.project.utils.ControllerUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * trida s mainlogic, jen static methods
 */

public class MainLogic implements Logic{

    //Trida je singlton

    private static MainLogic mainLogic;

    private MainLogic(){}

    public static MainLogic getInstance(){
        if(mainLogic==null){
            mainLogic = new MainLogic();
        }

        return mainLogic;
    }

    //soubor kde budou ulozena data
    private static final String DIRECTORY = "C:\\Users\\ASUS\\Desktop\\ProjectForTemperature2\\src\\main\\resources";

    //soubor s data, kde misto s - nazev města
    private static final String FILE_LOCATION = "C:\\Users\\ASUS\\Desktop\\ProjectForTemperature2\\src\\main\\resources\\%s.txt";


    /**
     *
     * @param city nayev města
     * @param localDateTime čas
     * @throws Exception pokud meco nejde se stream muze vydat chybu
     */
    //metoda ulozí data do souboru
    @Override
    public void saveTemperature(String city, LocalDateTime localDateTime) throws Exception {
        //dostavani json string z internetu
        String jsonData = ControllerUtils.getJsonData(city);
        //pridani aktuálního času kdy imformaci vyhledavaly
        jsonData+="---"+localDateTime.toString();
        //vytvoreni FileOutputStream na skladani dat
        FileOutputStream fileOutputStream = new FileOutputStream(new File(String.format(FILE_LOCATION, city)), false);
        //skladani dat
        fileOutputStream.write(jsonData.getBytes());
        fileOutputStream.flush();
    }


    /**
     *
     * @param city nazev mesta
     * @return vrati model mesta
     * @throws IOException
     */
    //metoda nahravaji data ze souboru
    @Override
    public Model loadTemperature(String city) throws IOException {

        //Vytvoreni streamu
        FileInputStream fileInputStream = new FileInputStream(new File(String.format(FILE_LOCATION, city)));
        //cteni dat ze souboru


        StringBuilder jsonData = new StringBuilder();

        int read;
        while((read = fileInputStream.read()) != -1){
            jsonData.append((char) read);
        }
        //deli array na 2, 0 - json data o pocasi, 1 - čas kdy data byla uložena
        String[] split = jsonData.toString().split("---");
        //vytvoření json object, použivání knihovny org.json
        JSONObject jsonObject = new JSONObject(split[0]);
        //dostavaní main json
        JSONObject main = jsonObject.getJSONObject("main");

        Model model = new Model();

        //přidání do model teploty,kterou dostavame z main jsoN

        model.setTemp(Double.valueOf(main.get("temp").toString()));

        //přidání nazvu města
        model.setCity(jsonObject.getString("name"));

        //přidání teploty feels_like
        model.setFeels_like(Double.valueOf(main.get("feels_like").toString()));

        //čas vztvorení soubora
        model.setLocalDateTime(LocalDateTime.parse(split[1]));

        return model;
    }

    /**
     *
     * @param city nazev města
     * @param localDateTime čas vztvorení
     */
    @Override
    public void updateAndWriteTemperature(String city, LocalDateTime localDateTime){
        //zkusim uložit soubor, pokud uspěšně, metoda jde dal
        try {
            saveTemperature(city, localDateTime);
        } catch (Exception e) {
            //jestli cod 404
            System.out.println("Město neexistuje");
            return;
        }
        Model model = null;
        //zkusím nahrat ulozena data
        try {
            model = loadTemperature(city);
        } catch (IOException e) {
            System.out.println("oups, se nepodařilo načíst data");
            return;
        }

        //вывод заруженных данных
        String s = "Teplota ve měste %s je %s, cití jako: %s \n";

        System.out.format(s, model.getCity(), model.getTemp(), model.getFeels_like());
    }

    /**
     *
     * @return vratí list uložených modelů
     */
    @Override
    public List<Model> getSavedModels() {
        //použivame metodu Files.walk pro hledani ve souborech
        Stream<Path> walk = null;
        try {
            walk = Files.walk(new File(DIRECTORY).toPath());
        } catch (IOException e) {
            System.out.println("oups, directory neexistuje");
            return null;
        }
        //sbírame stream do collect a třídim
        List<Path> collect = walk.sorted(Comparator.comparing(Path::getFileName)).collect(Collectors.toList());
        //odstranim 0, protoze to je resources
        collect.remove(0);
        //pokud je prazdný, data nebyla uložena
        if(collect.isEmpty()){
            System.out.println("Nic ješte nebylo zapsané");
            return null;
        }

        List<Model> models = new ArrayList<>();

        //pomoci for jdeme pres uložene soubory
        for(Path path:collect){
            //odstraním .txt
            String replace = path.getFileName().toString().replace(".txt", "");
            Model model = null;
            //Zkusim nahrat data ze souboru
            try {
                model = loadTemperature(replace);
            } catch (IOException e) {
                System.out.println("");
                break;
            }
            //přidame model
            models.add(model);
        }
        return models;
    }

    /**
     *
     * @param models list models
     */
    @Override
    public void printModels(List<Model> models){
            //tridim podle teploty od nejchladnejšiho, pres for jdeme v modely a piseme to do terminalu
            models.sort(Comparator.comparing(Model::getTemp));
            for (Model model:models){
                String format = "Uložená data, teplota ve městě %s se rovná %s, ale cití se to jeko %s, %s \n";
                System.out.format(format, model.getCity(), model.getTemp(), model.getFeels_like(), model.getLastUpdate());
            }
    }

}
