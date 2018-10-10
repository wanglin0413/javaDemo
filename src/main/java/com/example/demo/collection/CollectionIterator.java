package com.example.demo.collection;

import java.util.*;
import java.util.Map.Entry;

public class CollectionIterator{

    private int i = 0;

    public CollectionIterator(int i) {
        this.i = i;
    }

    public static void main(String[] args){
        Map<Integer,String> map = new HashMap<>(100);
        for(int i = 0; i<96; i++){
            String ci1 = "aaa";
            map.put(i, ci1);
        }
        String ci2 = "bbb";
        map.put(97, ci2);
        Integer i = 1;
        map.computeIfAbsent(100, key -> key + "ss"
        );
        map.computeIfPresent(3,(key,value) -> key + value);
        System.out.println("100:" + map.get(100));
        System.out.println("3:" + map.get(3));

        Set<Entry<Integer, String>> entrySet = map.entrySet();
        for(Entry each: entrySet){
            each.getKey();
            each.getValue();

        }
        entrySet.forEach(each ->{
            each.getKey();
            each.getValue();
        });

        CollectionIterator collectionIterator = new CollectionIterator(1);

        System.out.println(collectionIterator.hashCode());
        int h =  collectionIterator.hashCode();
        System.out.println(h >>> 16);
        System.out.println(h);
        System.out.println(h ^ (h >>> 16));
        System.out.println(99 & (h ^ (h >>> 16)));
    }
}
