package m10.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserParserTest {
    /*
        file2.txt:
        name age
        alice 21
        ryan 30
        -----------
        user.json:
        [
            {
                "name": "alice",
                "age":21
            },
            {
                "name": "ryan",
                "age":30
            }
        ]
    */

    private static final String FILE = "file2.txt";
    private static final String FILE_JSON = "user.json";

    public static void main(String[] args) {
        ArrayList<User> users = getUsersFromFile();
        if (users == null) {
            System.out.println("Input file format is not correct or empty. exit...");
            System.exit(-1);
        }
        System.out.println("users = " + users);

        String json = getJsonFromList(users);
        System.out.println("json = " + json);

        if (saveJsonToFile(json)) {
            System.out.println("JSON was saved to " + FILE_JSON);
        }

    }

    private static ArrayList<User> getUsersFromFile() {
        try (BufferedInputStream bufInput = new BufferedInputStream(new FileInputStream(FILE))) {
            Scanner scanner = new Scanner(bufInput);

            if (!scanner.hasNext()) {
                System.out.println("File " + FILE + " is empty!");
                return null;
            }

            scanner.nextLine();
            ArrayList<User> users = new ArrayList<>();
            String name;
            int age;
            while (scanner.hasNext()) {
                name = scanner.next();
                age = scanner.nextInt();
                users.add(new User(name, age));
            }
            scanner.close();
            return users;

        } catch (IOException ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

        return null;
    }

    private static String getJsonFromList(ArrayList<User> users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(users);
    }

    private static boolean saveJsonToFile(String json) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_JSON)) {
            byte[] buffer = json.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
