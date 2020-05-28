package org.project.utils;


import org.project.dto.Model;

import java.util.Comparator;

public class CompareTemp implements Comparator<Model> {


    @Override
    public int compare(Model o1, Model o2) {

        return o1.getTemp().compareTo(o2.getTemp());
    }
}