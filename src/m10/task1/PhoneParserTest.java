package m10.task1;


import java.io.*;
import java.util.Scanner;

public class PhoneParserTest {

    /*987-123-4567
    123 456 7890
    (123) 456-7890
    --------------
    987-123-4567
    (123) 456-7890*/

    public static final String FILE = "file1.txt";

    public static void main(String[] args) {
        parsePhoneNumbers();
    }

    private static void parsePhoneNumbers() {
        try (BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(FILE))) {
            Scanner scanner = new Scanner(fileInputStream);
            String pattern = "^\\([0-9]{3}\\) ([0-9]{3})-([0-9]{4})|([0-9]{3})-([0-9]{3})-([0-9]{4})$";
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.matches(pattern)) {
                    System.out.println(line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
