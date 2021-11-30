import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class StackPractice {

    StackPractice() {
        // one(15);
        // two("hello world");
        three();
    }

    void one(int num) {
        Stack<Integer> stack = new Stack<>();
        while (num != 0) {
            stack.push(num % 2);
            num /= 2;
        }

        while (!stack.empty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }

    void two(String str){
        Stack<Character> stack = new Stack<>();
        while (str.length() != 0){
            stack.push(str.charAt(0));
            str = str.substring(1);
        }

        while (!stack.empty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }


    void three() {
        String line;
        BufferedReader bufferedReader;
        ArrayList<Stack<Municipality>> county;

        try {
            String file = StackPractice.class.getResource("NJMunicipalities.csv").getPath();
            bufferedReader = new BufferedReader(new FileReader(file));
            county = new ArrayList<>();

            bufferedReader.readLine();
 
            while ((line = bufferedReader.readLine()) != null) {
                Municipality municipality = new Municipality(line.split(","));

                for (Stack<Municipality> s: county) {
                    if (s.peek().getCounty().equals(municipality.getCounty())){
                        county.add(new Stack<>());

                    }
                }

                System.out.println(line.replace(",", " | "));
            }

        } catch (Exception e) { e.printStackTrace(); }

    }

    public static void main(String[] args) {
        new StackPractice();

    }

    class Municipality {
        String name, county, type;

        Municipality(String[] line){
            this.name = line[0];
            this.county = line[1];
            this.type = line[2];
        }

        public String getCounty() {
            return county;
        }
    }
}
