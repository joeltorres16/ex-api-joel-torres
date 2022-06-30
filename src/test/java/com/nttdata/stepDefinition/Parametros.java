package com.nttdata.stepDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parametros {
    protected Map<String, Object> listaParametros(List<Map<String, String>> list) {

        Map<String, Object> map = new HashMap<>();

        for (Map<String, String> stringStringMap : list) {
            map.put(stringStringMap.get("parametros"), stringStringMap.get("valor"));
        }
        return map;
    }
}
