package com.cengizhanyavuz.wordvault.config;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Slf4j
@Component
public class DummyData {

    private final WordRepository wordRepository;

    public DummyData(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void dummyWords() throws IOException {
        FileInputStream excelFile = new FileInputStream(new File("src\\main\\resources\\static\\kelimedb.xlsx"));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        List<Word> words = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            String column1 = row.getCell(1).getStringCellValue();
            String column2 = row.getCell(2).getStringCellValue();

            if (column1.contains(",")) {
                column1 = column1.split(",")[0];
            }
            if (column2.contains(",")) {
                column2 = column2.split(",")[0];
            }

            Word word = new Word();
            word.setTr(column2);
            word.setEn(column1);
            words.add(word);
        }
        wordRepository.saveAll(words);
        workbook.close();
        excelFile.close();
    }


    @Bean
    public CommandLineRunner commandLineRunnerMethod() {
        return args -> {
            if (wordRepository.count() == 0) {
                System.out.println("Dummy data is being created");
                log.info("Dummy data is being created");
                dummyWords();
            }
        };
    }
}
