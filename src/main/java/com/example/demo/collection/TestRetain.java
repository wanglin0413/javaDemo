package com.example.demo.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestRetain {

    List<String> uids = new ArrayList<>();

    public TestRetain() {
        for(int i = 0; i < 30000; i++){
            String base = "soekc983kdolsiejf";
            uids.add(base + i);
        }
    }

    public static void main(String[] args) {
        TestRetain retain = new TestRetain();
        List<String> list_to_find = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i< 10; i++){
            list_to_find.add(retain.uids.get(random.nextInt(retain.uids.size())));
        }
        List<String> test1 = new ArrayList<>(retain.uids);
        long start = System.nanoTime();
        test1.retainAll(list_to_find);
        long end = System.nanoTime();
        System.out.println("retainAll: " + (end - start));

        List<String> test2 = new ArrayList<>(retain.uids);
        long nextStart = System.nanoTime();
        List<String> diff = retain.getSame(test2, list_to_find);
        long nextEnd = System.nanoTime();
        System.out.println("get same:  " + (nextEnd - nextStart));
    }

    private List<String> getSame(List<String> list1, List<String> list2) {
        List<String> same = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if(list2.size() > list1.size())
        {
            maxList = list2;
            minList = list1;
        }
        Map<String,Integer> map = new HashMap<String,Integer>(maxList.size());
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            if(map.get(string) != null)
            {
                same.add(string);
            }
        }

        return same;
    }

}
