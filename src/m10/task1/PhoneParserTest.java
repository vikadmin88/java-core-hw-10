package m10.task1;


import java.io.*;
import java.util.Scanner;

public class PhoneParserTest {
    public static final String FILE_PATH = "src/m10/task1/file.txt";

    /*
    987-123-4567
    123 456 7890
    (123) 456-7890
    --------------
    987-123-4567
    (123) 456-7890*/

    public static void main(String[] args) {
        bufferedInputStream();
    }

    private static void bufferedInputStream() {
        try(BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(FILE_PATH))) {
            Scanner scanner = new Scanner(fileInputStream);
            String pat1 = "\\([0-9]{3}\\) ([0-9]{3})-([0-9]{4})";
            String pat2 = "([0-9]{3})-([0-9]{3})-([0-9]{4})";
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.matches(pat1) || line.matches(pat2)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
