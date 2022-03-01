/*

NOTE:  This MapHash does not support null keys
This is a shortcut, so a null value at a key in the
key Object[] represents the absence of that key

Methods:
- put (K key, V value) returns previous value (or null)
- get (k key) returns value
- containsKey(K key) returns boolean if key is present (not null)
- keySet() returns a Set<K> based on all keys (not null)
- getSize() returns current size of the map
- remove(K key) removes key and value and returns value V


*/
import java.security.Key;
import java.util.*;  // No using maps, but you can make a keySet

public class MapHash<K,V>{

    public static final int DEFAULT_MAP_SIZE =  13000000;
    private final int mapSize;
    private final Object[] keys;
    private final Object[] vals;
    private int size;

    public MapHash(int capacity){
        mapSize = capacity;
        keys = new Object[mapSize]; //Parallel Arrays based on
        vals = new Object[mapSize]; // hash index
    }

    // put (K key, V value) returns previous value (or null)
    public V put(K key, V value) {

        V previousValue = (V) vals[hash(key)];

        keys[hash(key)] = key;
        vals[hash(key)] = value;

        if (previousValue == null) size++;

        return previousValue;
    }

    // get (k key) returns value
    public V get(K key) {
        return (V) vals[hash(key)];
    }

    // containsKey(K key) returns boolean if key is present (not null)
    public boolean containsKey(K key){
        return get(key) != null;
    }


    // keySet() returns a Set<K> based on all keys (not null)
    public Set<K> keySet(){
        Set<K> set = new HashSet<>();

        for (int i = 0; i < DEFAULT_MAP_SIZE; i++)
            if (keys[i] != null)
                set.add((K) keys[i]);

        return set;
    }

    public int size() {
        return size;
    }

    // getSize() returns current size of the map
    public int getSize() {
        return size;
    }

    // remove(K key) removes key and value and returns value V
    public V remove(K key) {
        V value = get(key);

        for (int i = 0; i < DEFAULT_MAP_SIZE; i++)
            if (keys[i] == key){
                keys[i] = null;
                vals[i] = null;
                size--;
            }

        return value;
    }



    public MapHash(){ // Set to default value
        this(DEFAULT_MAP_SIZE);
    }

    private int hash(K s){ // This is used to keep index values in range
        return Math.abs(s.hashCode() % mapSize);
    }

    public static void main(String[] args) {
        MapHash<String,String> map = new MapHash<String,String>();
        map.put("Bachman Turner","Overdrive");
        map.put("Mad","Hatter");
        map.put("I love rock and roll","So put another dime in the jukebox baby");
        System.out.println(map.get("Bachman Turner"));
        System.out.println(map.get("Mad"));
        System.out.println(map.get("I love rock and roll"));

        System.out.println("\n Key Set:");
        for (String s: map.keySet())
            System.out.println(s);

        System.out.println("\n\n");
        MapHash<Character,Integer> frq = new MapHash<Character,Integer>();
        String s = "This is a sentence, how many of each character does it have?";
        for (int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);
            if (frq.containsKey(c)){
                frq.put(c,frq.get(c)+1);
            }
            else
                frq.put(c,1);
        }

// Print Frequency Table
        System.out.println("Original Freq Table, size = "+frq.size());
        for (Character c: frq.keySet())
            System.out.println(c+" -> "+frq.get(c));

// Remove Spaces and keys that occur exactly 3 times
        frq.remove(' ');

        for (Character c: frq.keySet())
            if (frq.get(c) == 3)
                frq.remove(c);

        System.out.println("\nAdjusted Freq Table, size = "+frq.size());
        for (Character c: frq.keySet())
            System.out.println(c+" -> "+frq.get(c));
    }



}