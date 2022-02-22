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
    private int mapSize;
    private Object[] keys, vals;
    private int size;

    public MapHash(int capacity){
        mapSize = capacity;
        keys = new Object[mapSize]; //Parallel Arrays based on
        vals = new Object[mapSize]; // hash index
    }

    public V put(K key, V value) {

        V previousValue = (V) vals[hash(key)];

        keys[hash(key)] = key;
        vals[hash(key)] = value;

        if (previousValue == null) size++;

        return previousValue;
    }

    public V get(K key) {
        return (V) vals[hash(key)];
    }

    public boolean containsKey(K key){
        return get(key) != null;
    }


    public Set<K> keySet(){
        Set<K> set = new HashSet<>();

        for (int i = 0; i < DEFAULT_MAP_SIZE; i++)
            if (keys[i] != null)
                set.add((K) keys[i]);

        return set;
    }

    public int getSize() {
        return size;
    }

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
        return Math.abs(s.hashCode()%mapSize);
    }

    public static void main(String[] args) {
        MapHash<String, String> map = new MapHash<>();
        System.out.println("\nadding...");
        System.out.println(map.put("apple", "jacks") + " size: " + map.size);
        System.out.println(map.put("honey", "combs1") + " size: " + map.size);
        System.out.println(map.put("honey", "combs2") + " size: " + map.size);

        System.out.println("\ncontains...");
        System.out.println(map.containsKey("apple"));
        System.out.println(map.containsKey("appe"));
        System.out.println(map.containsKey("test"));
        System.out.println(map.containsKey("honey"));

        System.out.println("\nkeyset...");
        System.out.println(map.keySet());

        System.out.println("\nremove...");
        System.out.println(map.remove("apple"));
        System.out.println(map.keySet() + " size: " + map.size);
    }


}