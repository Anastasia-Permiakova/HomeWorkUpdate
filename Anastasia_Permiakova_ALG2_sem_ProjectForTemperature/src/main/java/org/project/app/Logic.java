package org.project.app;

import org.project.dto.Model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface Logic {
    void saveTemperature(String city, LocalDateTime localDateTime) throws Exception;
    Model loadTemperature(String city) throws IOException;
    void updateAndWriteTemperature(String city, LocalDateTime localDateTime);
    List<Model> getSavedModels();
    void printModels(List<Model> models);
}
