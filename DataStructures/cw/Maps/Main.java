import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Main {

    Main() throws IOException {
       one("1.61803398874989484820458683436563811772030917980576286213544862270526046281890244970720720418939113748475408807538689175212663386222353693179318006076672635");
       two();
    }

    public void one(String number){
        number = number.substring(number.indexOf(".") + 1);
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < number.length() - 1; i++){
            String key = number.substring(i, i + 2);
            if (map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            } else {
               map.put(key, 1) ;
            }
        }
        System.out.print(map.size() + " 2-digit combinations");
        map.forEach((key, value) -> {
            if (value > 4)
                System.out.print(", " + key + " occurred " + value + " times");
        });
    }

    public void two() throws IOException {

       Map<Integer, PriorityQueue<Bowler>> map = new TreeMap<>();

        Files.lines(Paths.get("BowlingData.txt")).forEach(line -> {
            String firstName = line.split(" ")[0];
            String lastName = line.split(" ")[1];
            int score = Integer.parseInt(line.split(" ")[2]);

            if (map.get(score) == null){
                map.put(score, new PriorityQueue<>());
            }

            map.get(score).add(new Bowler(firstName, lastName, score));
        });


        map.forEach((key, value) -> {
            System.out.print(key + "=[" + (!value.isEmpty() ? value.poll() : " "));
            while (!value.isEmpty())
                System.out.print("," + value.poll());
            System.out.println("]");
        });

    }

    class Bowler implements Comparable<Bowler> {
        private String firstName, lastName;
        private int score;

        Bowler(String firstName, String lastName, int score){
            this.firstName = firstName;
            this.lastName = lastName;
            this.score = score;
        }

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public int getScore() { return score; }

        @Override
        public int compareTo(Main.Bowler o) {
            if (!this.getLastName().equals(o.getLastName()))
                return this.getLastName().compareTo(o.getLastName());
            return this.getFirstName().compareTo(o.getFirstName());
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }



    public static void main(String[] args) throws IOException {
        new Main();
    }
}
