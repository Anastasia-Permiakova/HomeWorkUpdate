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
 * класс с главной логикой, содержит только статические методы
 */

public class MainLogic implements Logic{

    //Данный класс является singlton, т.е объект данногокласса можно создать только 1

    private static MainLogic mainLogic;

    private MainLogic(){}

    public static MainLogic getInstance(){
        if(mainLogic==null){
            mainLogic = new MainLogic();
        }

        return mainLogic;
    }

    //директория, где будут сохраняться данные
    private static final String DIRECTORY = "C:\\Users\\ASUS\\Desktop\\ProjectForTemperature2\\src\\main\\resources";

    //нахождние файла с данными, где вместо s название города
    private static final String FILE_LOCATION = "C:\\Users\\ASUS\\Desktop\\ProjectForTemperature2\\src\\main\\resources\\%s.txt";


    /**
     *
     * @param city название города
     * @param localDateTime текущее время
     * @throws Exception может выбрасывать ошибку, если что-то не так с потоками
     */
    //метод сохраняет данные в файл
    @Override
    public void saveTemperature(String city, LocalDateTime localDateTime) throws Exception {
        //получение json строки из сети
        String jsonData = ControllerUtils.getJsonData(city);
        //добавление текущего времени выполнения запроса
        jsonData+="---"+localDateTime.toString();
        //создание поток на сохранение данных
        FileOutputStream fileOutputStream = new FileOutputStream(new File(String.format(FILE_LOCATION, city)), false);
        //запись в файл
        fileOutputStream.write(jsonData.getBytes());
        fileOutputStream.flush();
    }


    /**
     *
     * @param city название горожа
     * @return возвращает модель данных по городу
     * @throws IOException
     */
    //метод загружает данные из файла
    @Override
    public Model loadTemperature(String city) throws IOException {

        //Создание потока
        FileInputStream fileInputStream = new FileInputStream(new File(String.format(FILE_LOCATION, city)));
        //чтение данных из файла в строку


        StringBuilder jsonData = new StringBuilder();

        int read;
        while((read = fileInputStream.read()) != -1){
            jsonData.append((char) read);
        }
        //разбиение на массив из 2 данных, 0 - это json данные по погоде, 1 - это время, когда эти данные были сохранены
        String[] split = jsonData.toString().split("---");
        //Создание json объекта, использование библиотеки org.json
        JSONObject jsonObject = new JSONObject(split[0]);
        //получение main json
        JSONObject main = jsonObject.getJSONObject("main");

        Model model = new Model();

        //добавление в model температуры, которая достается из main jsoN

        model.setTemp(Double.valueOf(main.get("temp").toString()));

        //добавление названия города
        model.setCity(jsonObject.getString("name"));

        //добавление температуры по ощущениям
        model.setFeels_like(Double.valueOf(main.get("feels_like").toString()));

        //время создания файла
        model.setLocalDateTime(LocalDateTime.parse(split[1]));

        return model;
    }

    /**
     *
     * @param city названи города
     * @param localDateTime время создания
     */
    @Override
    public void updateAndWriteTemperature(String city, LocalDateTime localDateTime){
        //пытаемся сохранить данные, если успешно, то продолжается выполнение метожа
        try {
            saveTemperature(city, localDateTime);
        } catch (Exception e) {
            //если был код 404
            System.out.println("Данного города не существует, попробуйте снова");
            return;
        }
        Model model = null;
        //пытаемся загрузить сохраненные данные
        try {
            model = loadTemperature(city);
        } catch (IOException e) {
            System.out.println("oups, не удалось загрузить данные");
            return;
        }

        //вывод заруженных данных
        String s = "Температура в городе %s равна %s, по ощущениям: %s \n";

        System.out.format(s, model.getCity(), model.getTemp(), model.getFeels_like());
    }

    /**
     *
     * @return возвращает список сохраненных моделей
     */
    @Override
    public List<Model> getSavedModels() {
        //используем метод Files.walk чтобы пройтись по файлам в папке
        Stream<Path> walk = null;
        try {
            walk = Files.walk(new File(DIRECTORY).toPath());
        } catch (IOException e) {
            System.out.println("oups, нет директории");
            return null;
        }
        //собираем поток в список, сортируя его
        List<Path> collect = walk.sorted(Comparator.comparing(Path::getFileName)).collect(Collectors.toList());
        //удаляем 0 элемент, потому что это resources
        collect.remove(0);
        //если оказался пуст, то данные не были сохранены
        if(collect.isEmpty()){
            System.out.println("Данные пока что не были записаны");
            return null;
        }

        List<Model> models = new ArrayList<>();

        //проходимся в цикле по сохраненным файлам
        for(Path path:collect){
            //убираем разрешение .txt
            String replace = path.getFileName().toString().replace(".txt", "");
            Model model = null;
            //пытаемся загрузить данные из файла
            try {
                model = loadTemperature(replace);
            } catch (IOException e) {
                System.out.println("не удалось загрузить данные");
                break;
            }
            //добавляем модель в список
            models.add(model);
        }
        return models;
    }

    /**
     *
     * @param models список моделей
     */
    @Override
    public void printModels(List<Model> models){
            //сортируем по температуре, и проходимся в цикле по моделям и выводим сообщения на экран
            models.sort(Comparator.comparing(Model::getTemp));
            for (Model model:models){
                String format = "Сохраненные данные, температура в городе %s равна %s, по ощущениям %s, %s \n";
                System.out.format(format, model.getCity(), model.getTemp(), model.getFeels_like(), model.getLastUpdate());
            }
    }

}
