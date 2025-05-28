package algorithm.归纳法;

import org.apache.commons.collections.MultiHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author KCWang
 * @version 1.0
 * @date 2025/5/20 21:50
 */
public class 航班路线 {


    public static void main(String[] args) {

        /**
         *
         *
         *
         Imagine we work for a company whose business is scheduling flights for customers.
         Today, only journeys with a single flight from one airport to another (which we will call direct flights) are supported.
         I'd like you to write a function that extends this functionality by returning one path from two airports,
         even when there is no direct flight between them.

         With direct flights:
         Seattle <-> Portland
         Seattle <-> Houston
         Seattle <-> New York City
         New York City <-> Philadelphia
         Houston <-> New York City

         If a customer wants to go from Seattle -> Philadelphia, the valid paths are:
         1. Seattle -> Houston -> New York City -> Philadelphia
         2. Seattle -> New York City -> Philadelphia

         */


        MultiHashMap directLightMap = new MultiHashMap();
        directLightMap.put("Seattle", new ArrayList<String>() {{
            add("Portland");
            add("Houston");
            add("New York City");
        }});
        directLightMap.put("New York City", new ArrayList<String>() {{
            add("Philadelphia");
        }});

        directLightMap.put("Houston", new ArrayList<String>() {{
            add("New York City");
        }});


        MultiHashMap tepResut = new MultiHashMap();

        StringBuilder path = new StringBuilder("");

        String start = "Seattle";
        String end = "Philadelphia";


        if (directLightMap.containsKey(start)) {
            path.append(start + " -->");
            List list1 = (ArrayList<String>) directLightMap.get(start);
            List list = (ArrayList<String>) list1.get(0);
            for (int i = 0; i < list.size(); i++) {
                boolean flag = true;
                Object object = list.get(i);
                if (list.get(i).equals(end)) {
                    path.append(end);
                    tepResut.put("Seattle->Philadelphia", path);
                } else {
                    if (directLightMap.containsKey(list.get(i))) {
                        getpath(directLightMap, (String) list.get(i), path, tepResut, start, end);
                    }
                }
                path = new StringBuilder(start + " -->");

            }
        }

        List tep = Collections.singletonList(((ArrayList<String>) tepResut.get("Seattle->Philadelphia")));
        for (int i = 0; i < tep.size(); i++) {
            System.out.println(" Seattle->Philadelphia flight :" + i + "  path : " + tep.get(i));
        }


    }

    static Boolean getpath(MultiHashMap directLightMap, String key, StringBuilder path, HashMap tepResut, String start, String end) {

        boolean flag = true;
        List list1 = (ArrayList<String>) directLightMap.get(key);
        List list = (ArrayList<String>) list1.get(0);
        path.append(key + " -->");
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).equals(end)) {
                path.append(end);
                tepResut.put("Seattle->Philadelphia", path);
                path = new StringBuilder(start);
            } else {
                if (directLightMap.containsKey(list.get(j))) {
                    flag = getpath(directLightMap, (String) list.get(j), path, tepResut, start, end);
                } else {
                    return false;
                }

            }

        }
        return flag;
    }
}
