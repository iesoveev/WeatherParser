import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws Exception {
        Document page = Parser.getPage();
        Element tableWth = page.select("table[class=wt]").first();
        Elements names = tableWth.select("tr[class=wth]");
        Elements values = tableWth.select("tr[valign=top]");
        int index = 0;
        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text();
            String date = Parser.getDateFromString(dateString);
            System.out.println(date + "                Явления             Температура   Давл. Влажность         Ветер");
            int iterationCount = Parser.printPartValues(values, index);
            index += iterationCount;
        }
    }
}
