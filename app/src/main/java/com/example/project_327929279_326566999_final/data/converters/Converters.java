package com.example.project_327929279_326566999_final.data.converters;

import androidx.room.TypeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {
    @TypeConverter
    public String fromList(List<String> list) {
        return list == null ? null : String.join(",", list);
    }

    @TypeConverter
    public List<String> toList(String data) {
        return data == null || data.isEmpty()
                ? null
                : Arrays.asList(data.split(","));
    }
}
