import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Final {
    ArrayList<Integer> list;
    int[][] arr;
    Stack<Integer> stack = new Stack<>();
    HashSet<Integer> hashSet;
    PriorityQueue<Integer> priorityQueue;
    TreeMap<Integer, int[]> treeMap;

    Final(){
        one();
        two();
        three();
        four();
        five();
        six();
        seven();
        eight();
        nine();
        ten();
        eleven();
        twelve();
//
//        list.forEach(e -> System.out.println(list.indexOf(e) + " = " + e));
    }

    void one(){
        IntStream.range(9, random(81, 81))
                .collect(toList());
//        System.out.println("\none: ");
//        list = new ArrayList<>(List.of(2, 3, 3, 4, 5, 5, 7, 8, 8, 4, 9, 9, 10, 11, 13, 15, 16));
    }

    void two(){
        System.out.println("\ntwo: ");
        System.out.println(list);
//        IntStream.range(0, list.size())
//                .mapToObj(e -> list.get(e) + (e == list.size() - 1 ? "\n" : " "))
//                .forEach(System.out::print);
//
//        for (int i = 1; i < list.size(); i++){
//            System.out.println(list.get(i));
//        }

    }

    void three(){
        System.out.println("\nthree: ");
//        System.out.println("size: " + list.size());
//        System.out.println("fit: " + Math.ceil(Math.sqrt(list.size())));

        int size = (int) Math.ceil(Math.sqrt(list.size()));
        arr = new int[size][size];

        while (!list.isEmpty()){
            int random_x = random(0, size - 1),
                random_y = random(0, size - 1);

           if (arr[random_x][random_y] == 0)
                arr[random_x][random_y] = list.remove(0);
        }
    }

    void four(){
        System.out.println("\nfour: ");
        Arrays.stream(arr)
              .map(Arrays::toString)
              .forEachOrdered(System.out::println);
    }

    void five(){
        System.out.println("\nfive: ");
        IntStream.range(0, arr.length)
                .forEach(i -> IntStream.range(0, arr.length)
                .forEach(j -> stack.push(arr[j][i])));
    }

    void six(){
        System.out.println("\nsix: ");
        System.out.println(stack.toString().replace("[", "{").replace("]", "}"));
    }

    void seven(){
        System.out.println("\nseven: ");
//        System.out.println("seven \n");
//        stack.forEach(System.out::println);
        hashSet = new HashSet<>(stack);
        stack.clear();
    }

    void eight(){
        System.out.println("\neight: ");
        System.out.println(hashSet);
    }

    void nine(){
        System.out.println("\nnine: ");
        priorityQueue = new PriorityQueue<>(hashSet);
        hashSet.clear();
    }

    void ten(){
        System.out.println("\nten: ");
        System.out.println(priorityQueue);

        priorityQueue = new PriorityQueue<>();
//        priorityQueue.addAll(new TreeSet<>(List.of(2, 3, 3, 4, 5, 5, 7, 8, 8, 4, 9, 9, 10, 11, 13, 15, 16)));
    }

    void eleven(){
        System.out.println("\neleven: ");
        treeMap = new TreeMap<>();

//        System.out.println("\neleven: ");
//        priorityQueue.stream(). .filter(e -> e%2 == 0).forEach(e -> System.out.print(e + " "));
//        System.out.println();
//        priorityQueue.stream().filter(e -> e%2 != 0).forEach(e -> System.out.print(e + " "));

//        treeMap = Maps

    }

    void twelve(){
        System.out.println("\ntwelve: ");
        System.out.println(treeMap);
    }

    int random(int min, int max){
        return (int) (Math.random() * (max-min+1)) + min;
    }

    public static void main(String[] args) {
        new Final();
    }
}
