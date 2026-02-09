package com.fx.service;

import com.fx.dto.FilmDto;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvService {

    private static final String FILE_NAME = "100imdb.csv";

    public static void writeCsvFile(List<FilmDto> films) throws IOException {
        File csvFile = new File(FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvFile)){
            films.forEach(pw::println);
        }
    }
}
