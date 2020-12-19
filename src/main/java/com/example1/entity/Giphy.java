package com.example1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Giphy {
    @JsonProperty
    private Map<Object,Object> data;

    public Giphy(Map<Object,Object> data) {
        this.data = data;
    }

    public Giphy() {
    }

    public Map<Object,Object> getData() {
        return data;
    }
}
