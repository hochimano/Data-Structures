package CompressionProject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.beans.binding.MapExpression;

public class Compression {
    
    public IntTree compress(String s){
        String[] str = s.split(" ");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String word: str){
            if(map.get(word) == null){
                map.put(word, 1);
            } else map.put(word, map.get(word) + 1);

        }
        Map<Integer, Set<String>> key = new TreeMap<Integer, Set<String>>();
        for(Map.Entry<String, Integer> e: map.entrySet()){
            key.put(e.getValue(), e.getKey());
        }
        return null;
    }
}
