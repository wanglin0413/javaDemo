package com.example.demo.basic;

import com.example.demo.concurrent.Mutex;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StringTest implements Comparable<Object>{

    private Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
    private String outerName = "OuterName";
    public String fatherName = "fatherName";
    public static String staticName = "staticName";
    public ArrayList<?> list = new ArrayList<>();
    public ArrayList<StringTest> list2 = new ArrayList<>();

    public InnerClass getInner(){
        return new InnerClass();
    }

    public static void sort1(Comparator<String> comparator){}



    public <T extends Comparable<Object>> T max(List<T> list){
        return null;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }


    public static class InnerClass{
        private static String innerStaticName;
        public String innerName = "InnerName";
        public String getOuterField(){
            return new StringTest().outerName;
        }
    }

    public void sort0(){}

    public static void sort(){
        sort1(new Comparator<String>() {
            int inner = 0;
            @Override
            public int compare(String o1, String o2) {
                String staticName1 = staticName;
                return 0;
            }
        });
    }

    public void printList(List<?> list){
        for(Object each:list){
            System.out.println(each.getClass() + "-" + each);
        }
    }

    public void add(ArrayList list){
        list.add("newString2");
        list.add(1);
        list.add(new Mutex());
    }

    public static void main(String[] args){
        StringTest stringTest = new StringTest();
        ArrayList<String> list1 = null;
        if(stringTest.list instanceof List){
             list1 = (ArrayList<String>) stringTest.list;
        }
        stringTest.add(list1);
        list1.add("newString");

        stringTest.printList(list1);

        stringTest.list2.add(new StringTest());
        StringTest stringTest1  = stringTest.max(stringTest.list2);

        Class klass  = StringTest.class;
        System.out.println(klass instanceof Class);
    }

}
