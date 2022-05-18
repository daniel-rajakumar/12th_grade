import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Final {
    ArrayList<Integer> list;
    int[][] arr;
    Stack<Integer> stack = new Stack<>();
    HashSet<Integer> hashSet;
    PriorityQueue<Integer> priorityQueue;
    TreeMap<Integer, TreeSet<Integer>> treeMap;

    Final(){
        System.out.println("\none:    "); one();
        System.out.println("\ntwo:    "); two();
        System.out.println("\nthree:  "); three();
        System.out.println("\nfour:   "); four();
        System.out.println("\nfive:   "); five();
        System.out.println("\nsix:    "); six();
        System.out.println("\nseven:  "); seven();
        System.out.println("\neight:  "); eight();
        System.out.println("\nnine:   "); nine();
        System.out.println("\nten:    "); ten();
        System.out.println("\neleven: "); eleven();
        System.out.println("\ntwelve: "); twelve();
    }

    void one(){
        list = IntStream.range(1, random(50, 150))
                .mapToObj(i -> random(1, 100))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void two(){
        System.out.println(list);
    }

    void three(){
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
        Arrays.stream(arr)
              .map(Arrays::toString)
              .forEachOrdered(System.out::println);
    }

    void five(){
        IntStream.range(0, arr.length)
                .forEach(i -> IntStream.range(0, arr.length)
                .forEach(j -> stack.push(arr[j][i])));
    }

    void six(){
        System.out.println(stack.toString().replace("[", "{").replace("]", "}"));
    }

    void seven(){
        hashSet = new HashSet<>(stack);
        stack.clear();
    }

    void eight(){
        System.out.println(hashSet);
    }

    void nine(){
        priorityQueue = new PriorityQueue<>(hashSet);
        hashSet.clear();
    }

    void ten(){
        System.out.println(priorityQueue);
    }

    void eleven(){
        treeMap = new TreeMap<>();
        int evenNum = 0;
        for (int num: priorityQueue){
            if (num % 2 == 0) treeMap.computeIfAbsent(evenNum = num, k -> new TreeSet<>());
            else              treeMap.get(evenNum).add(num);
        }
        priorityQueue.clear();
    }

    void twelve(){
        treeMap.forEach((k, v) -> System.out.println(k + ": " + v.toString()
                                                                 .replace("]", "}")
                                                                 .replace("[", "{")));
    }

    int random(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void main(String[] args) {
        new Final();
    }
}
