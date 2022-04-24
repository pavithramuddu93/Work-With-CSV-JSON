package blz;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriterusingPOJO {
    public static final String OBJECT_LIST = "object_list.csv";

    public static void main(String[] args) throws IOException,
            CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{

        try (Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST));
        ){
            StatefulBeanToCsv<CSVUser>beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            List<CSVUser> csvUsers = new ArrayList<>();
            csvUsers.add(new CSVUser("Sunder Pichai","sunder.google@gmail.com","+1-1143423","India"));
            csvUsers.add(new CSVUser("Dipesh","dipesh.bzu@gamil.com","+8269602271","India"));

            beanToCsv.write(csvUsers);
        }
    }
}