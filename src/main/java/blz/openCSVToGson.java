package blz;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class openCSVToGson {
    private static final String CSV_FILE_PATH = "user.csv";
    private static final String JSON_FILE_PATH = "user.json";


    public static void main(String[] args) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
            CsvToBeanBuilder<CSVUser> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVUser.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVUser>csvToBean = csvToBeanBuilder.build();
            List<CSVUser> csvUsers = csvToBean.parse();

            Gson gson = new Gson();
            String json = gson.toJson(csvUsers);
            FileWriter writer = new FileWriter(JSON_FILE_PATH);
            writer.write(json);
            writer.close();
            BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH));
            CSVUser[] usrObj = gson.fromJson(br,CSVUser[].class);
            List<CSVUser> csvUserList = Arrays.asList(usrObj);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}