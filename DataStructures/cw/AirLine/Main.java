import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore.Entry;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private Map<Airline, IncidentData> map;

    Main() throws IOException {
        String str = "hello world";
        //world hello

        for (String c: str.split(" ")){

        }




//        map = new HashMap<>();
//
//        loadData();
//        printAll();
    }

    void loadData() throws IOException {
        Files.readAllLines(Path.of("AirlineCrashData.csv")).stream().skip(1).forEach(e -> {
            String[] arr = e.split(",");

            String name = arr[0];
            long size = Long.parseLong(arr[1]);
            int incidents_85_99 = Integer.parseInt(arr[2]);
            int fatal_accidents_85_99 =  Integer.parseInt(arr[3]);
            int fatalities_85_99 = Integer.parseInt(arr[4]);
            int incidents_00_14 = Integer.parseInt(arr[5]);
            int fatal_accidents_00_14 = Integer.parseInt(arr[6]);
            int fatalities_00_14 = Integer.parseInt(arr[7]);

            map.put(
                    new Airline(name, size),
                    new IncidentData(incidents_85_99, fatal_accidents_85_99, fatalities_85_99, incidents_00_14, fatal_accidents_00_14, fatalities_00_14)
            );

            // Set<Airline> set = map.keySet();
            System.out.println(getByName("Avianca"));
        });
    }

    void printAll(){
        System.out.println(map);
    }

   Airline getByName(String name){
       for (var entry: map.entrySet()) {
           if (entry.getKey().getName().equals(name)){
               return entry.getKey();
           }
       }
       return null;
   }


    IncidentData getIncidentsBySize(Airline airline){
        return map.get(airline);
    }

    static Map<Integer, Integer> freqMap(int[] parameter){
        Map<Integer, Integer> map = new HashMap<>();

        for (int i: parameter){
            map.merge(i, 1, Integer::sum);
        }

        return map;
    }



    public static void main(String[] args) throws IOException {
        int[] arr = {1,2,2,2,3,2,1,2,3,1,1,1,2,1,2,3,1,1,1,1};
        Map<Integer,Integer> map = freqMap(arr);
        System.out.println(map);
        System.out.println(     );
    }
}

class Airline {
    private String name;
    private long size;

    public Airline(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} ({1})", name, size);
    }
}


//incidents_85_99,fatal_accidents_85_99,fatalities_85_99,incidents_00_14,fatal_accidents_00_14,fatalities_00_14

class IncidentData {

    private int incidents_85_99;
    private int fatal_accidents_85_99;
    private int fatalities_85_99;
    private int incidents_00_14;
    private int fatal_accidents_00_14;
    private int fatalities_00_14;


    public IncidentData(int i85_99, int fa85_99, int f85_99, int i00_14, int fa00_14, int f00_14){
        incidents_85_99 = i85_99;
        fatal_accidents_85_99 = fa85_99;
        fatalities_85_99 = f85_99;
        incidents_00_14 = i00_14;
        fatal_accidents_00_14 = fa00_14;
        fatalities_00_14 = f00_14;
    }

    public int getIncidents_85_99 (){ return incidents_85_99; }
    public int getFatal_accidents_85_99 (){ return fatal_accidents_85_99; }
    public int getFatalities_85_99 (){ return fatalities_85_99; }
    public int getIncidents_00_14 (){ return incidents_00_14; }
    public int getFatal_accidents_00_14 (){ return fatal_accidents_00_14; }
    public int getFatalities_00_14 (){ return fatalities_00_14; }

    @Override
    public String toString() {
        return MessageFormat.format("\nIncidentData '{'incidents_85_99={0}, fatal_accidents_85_99={1}, fatalities_85_99={2}, incidents_00_14={3}, fatal_accidents_00_14={4}, fatalities_00_14={5}'}'\n\n", incidents_85_99, fatal_accidents_85_99, fatalities_85_99, incidents_00_14, fatal_accidents_00_14, fatalities_00_14);
    }

}