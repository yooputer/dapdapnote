package org.dapdapnote.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static String listToJson(List<String> list) {
        Gson gson = new Gson();

        return gson.toJson(list);
    }

    public static List<String> jsonToStringList(String json) {
        try{
            Gson gson = new Gson();
            gson.fromJson(json, List.class);

            Type listType = new TypeToken<List<String>>() {}.getType();
            return gson.fromJson(json, listType);
        }catch (Exception e){
            return new ArrayList();
        }
    }
}