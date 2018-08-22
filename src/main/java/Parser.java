import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


    protected static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    protected static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
           return matcher.group();
        }
        throw new Exception("Can`t extract date from string!");
    }

    protected static Document getPage() throws IOException {
        String url = "http://pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    protected static int printPartValues(Elements values, int index) {
        int iterationCount = 4;
        if (index == 0) {
            Element valueLn = values.get(0);
            boolean isDay = valueLn.text().contains("День");
            boolean isEvening = valueLn.text().contains("Вечер");
            boolean isNight = valueLn.text().contains("Ночь");
            if (isDay) iterationCount = 3;
            if (isEvening) iterationCount = 2;
            if (isNight) iterationCount = 1;
        }

            for (int i = 0; i < iterationCount; i++) {
                Element valueLine = values.get(index + i);
                for (Element element : valueLine.select("td")) {
                    System.out.print(element.text() + "    ");
                }
                System.out.println("\n");
            }
            return iterationCount;
    }

}
