package scoalainformala.ro.OnlineLibrary.service.web_scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import scoalainformala.ro.OnlineLibrary.domain.Book;
import scoalainformala.ro.OnlineLibrary.domain.Genre;
import scoalainformala.ro.OnlineLibrary.service.impl.BookServiceImpl;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BookScraper {
    private final BookServiceImpl bookServiceImpl;

    //arbitrarily chose some ages for the recommended age
    private final static int[] ages = {7, 14, 18};

    public BookScraper(BookServiceImpl bookService) {
        this.bookServiceImpl = bookService;
    }

    @PostConstruct
    public void scrapeWikipedia() {
        List<Book> bookList = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_best-selling_books")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                    .timeout(30000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert doc != null;

        //only going through the first 5 tables on the wiki page
        for (int i = 1; i <= 5; i++) {
            //selecting all rows in each table
            for (Element tableRow : doc.select(
                    "table.sortable.wikitable:nth-of-type(" + i + ") tr")) {

                //sometimes tables have an empty element and this checks for it and moves on
                if (tableRow.select("td:nth-of-type(1)").text().equals("")) {
                    continue;
                }

                //selecting the data
                String title = tableRow.select("td:nth-of-type(1)").text().split("\\(")[0];
                String author = tableRow.select("td:nth-of-type(2)").text().split(";")[0].split("and")[0];
                String language = tableRow.select("td:nth-of-type(3)").text();
                //taking only the first defining genre without the details
                String genre = tableRow.select("td:nth-of-type(6)").text()
                        .split(" ")[0].split(",")[0].split("'")[0];

                //it's not the same everywhere so this is needed
                if (genre.contains("-")) {
                    genre = genre.replace("-", "_");
                }

                if (genre.isEmpty()) {
                    genre = Genre.getRandomGenre().toString();
                }

                double price = new Random().nextInt(200 - 50) + 50;
                int quantity = new Random().nextInt(100 - 3) + 3;
                int recAge = ages[new Random().nextInt(ages.length)];
                int pages = new Random().nextInt(600 - 100) + 100;

                Book book = new Book(author, title, price, language, quantity, pages, recAge, Genre.valueOf(genre.toUpperCase()));
                bookList.add(book);
            }
        }
        List<Book> booksAlreadyInDb;
        booksAlreadyInDb = bookServiceImpl.getAllForBackend();

        bookList.removeAll(booksAlreadyInDb);

        bookList.forEach(bookServiceImpl::add);
    }
}
