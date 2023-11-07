package m10.task3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class WordsFrequencyTest {
    /*
    words.txt:
    ----------
    the day is sunny the the
    the sunny is is
    ----------
    the 4
    is 3
    sunny 2
    day 1
    * */

    private static final String FILE = "words.txt";

    public static void main(String[] args) {
        calcWordsFrequency();
    }

    private static void calcWordsFrequency() {
        try (BufferedInputStream bufInput = new BufferedInputStream(new FileInputStream(FILE))) {
            Scanner scanner = new Scanner(bufInput);
            Map<String, Integer> words = new HashMap<>();

            while (scanner.hasNext()) {
                String key = scanner.next();

                words.merge(key, 1, Integer::sum);
            }
            scanner.close();

            List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(words.entrySet());
            sortedWords.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

            for (Map.Entry<String, Integer> entry: sortedWords) {
                System.out.println(entry.getKey() +" "+ entry.getValue());
            }

        } catch (IOException ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

    }
}
