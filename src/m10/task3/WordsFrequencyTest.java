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
            HashMap<String, Integer> words = new HashMap<>();

            while (scanner.hasNext()) {
                String key = scanner.next();
//                if (words.get(key) != null) {
//                    words.put(key, words.get(key) + 1);
//                } else {
//                    words.put(key, 1);
//                }
                words.merge(key, 1, Integer::sum);
            }
            scanner.close();

            for (Map.Entry<String, Integer> entry: words.entrySet()) {
                System.out.println(entry.getKey() +" "+ entry.getValue());
            }

        } catch (IOException ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

    }
}
