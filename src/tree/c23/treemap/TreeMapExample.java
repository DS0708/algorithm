package tree.c23.treemap;

import java.util.*;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5,"c");
        treeMap.put(7,"d");
        treeMap.put(3,"b");
        treeMap.put(1,"a");

        System.out.println("treeMap 출력");
        for(Map.Entry<Integer, String> entry : treeMap.entrySet() ) System.out.println(entry.getKey() + " : " + entry.getValue());
        System.out.println();

        int lowerKey = treeMap.lowerKey(5);
        System.out.println("lowerKey Of 5 : " + lowerKey);
        Integer higherKey = treeMap.higherKey(9);
        System.out.println("higherKey Of 5 : " + higherKey);
        int floorKey = treeMap.floorKey(5);
        System.out.println("floorKey Of 5 : " + floorKey);
        int floorKey2 = treeMap.floorKey(6);
        System.out.println("floorKey Of 6 : " + floorKey2);
        int ceilingKey = treeMap.ceilingKey(5);
        System.out.println("ceilingKey Of 5 : " + ceilingKey);
        int ceilingKey2 = treeMap.ceilingKey(6);
        System.out.println("ceilingKey Of 6 : " + ceilingKey2);


        //------------------------------------
        System.out.println("특정 key 왼쪽 부분을 거꾸로 순회");
        NavigableMap<Integer, String> navigableMap = treeMap.headMap(5, false).descendingMap();
        for(Map.Entry<Integer, String> entry : navigableMap.entrySet() ) System.out.println(entry.getKey() + " : " + entry.getValue());
        System.out.println("특정 key의 왼쪽 부분을 처음부터순회");
        NavigableMap<Integer, String> navigableMap2 = treeMap.headMap(5, false);
        for(Map.Entry<Integer, String> entry : navigableMap2.entrySet() ) System.out.println(entry.getKey() + " : " + entry.getValue());
        System.out.println("특정 key의 오른쪽 부분을 처음부터 순회");
        NavigableMap<Integer, String> navigableMap3 = treeMap.tailMap(4, true);
        for(Map.Entry<Integer, String> entry : navigableMap3.entrySet() ) System.out.println(entry.getKey() + " : " + entry.getValue());
        System.out.println("특정 key의 오른쪽 부분을 거꾸로 순회");
        NavigableMap<Integer, String> navigableMap4 = treeMap.tailMap(4, true).descendingMap();
        for(Map.Entry<Integer, String> entry : navigableMap4.entrySet()) System.out.println(entry.getKey() + " : " + entry.getValue());


        //delete 연산하기
        System.out.println("delete 실행 !");
        for(Map.Entry<Integer, String> entry : navigableMap4.entrySet()) treeMap.remove(entry.getKey());
        System.out.println("treeMap 출력");
        for(Map.Entry<Integer, String> entry : treeMap.entrySet() ) System.out.println(entry.getKey() + " : " + entry.getValue());
    }
}
