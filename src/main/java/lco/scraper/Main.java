package lco.scraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lco.scraper.entity.Meta;
import lco.scraper.entity.Metas;
import lco.scraper.services.Scraper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(args[0]);
        if(args.length < 3) {
            System.out.println("Missing arguments. 3 arguments requested : MODE (url or file), INPUT, OUTPUT");
        } else {
            if(args[0].equals("file")) {

                Metas metas = new Metas();

                Reader in = new FileReader(args[1]);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withFirstRecordAsHeader()
                        .parse(in);
                for (CSVRecord record : records) {
                    String id = record.get("id");
                    String page = record.get("page");
                    Meta meta = Scraper.scrapPage(id, page);
                    metas.getMetaList().add(meta);
                }

                ObjectMapper objectMapper = new ObjectMapper();
                File output = new File(args[2] + "/output.json");
                objectMapper.writeValue(output, metas);

            } else if(args[0].equals("url")) {

                Meta meta = Scraper.scrapPage("0", args[1]);
                ObjectMapper objectMapper = new ObjectMapper();
                File output = new File(args[2] + "/output.json");
                objectMapper.writeValue(output, meta);

            } else {
                System.out.println("First argument is wrong. Did you mean url or file ?");
            }
        }

    }
}