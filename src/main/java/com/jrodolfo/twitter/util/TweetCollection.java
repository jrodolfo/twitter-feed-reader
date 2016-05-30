package com.jrodolfo.twitter.util;

import java.util.*;

/**
 * Created by Rod on 25-May-2016.
 */
public class TweetCollection {

    Map<Long, Tweet> map = new TreeMap<>();

    public String toJson(Map<Long, Tweet> map) {
        int numberOfElements = map.size();
        if (numberOfElements == 0) return "[]";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        int index = 1;
        for (Map.Entry<Long, Tweet> entry : map.entrySet()) {
            stringBuilder.append(entry.getValue().toJson());
            if (index < numberOfElements) {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String toJson(ArrayList<Tweet> list) {
        int numberOfElements = list.size();
        if (numberOfElements == 0) return "[]";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        int index = 1;
        for (Tweet tweet : list) {
            stringBuilder.append(tweet.toJson());
            if (index < numberOfElements) {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public ArrayList<Tweet> getListFromMap(Map<Long, Tweet> map) {
        ArrayList<Tweet> list = new ArrayList<>();
        for (Map.Entry<Long, Tweet> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }


    public ArrayList<Tweet> reverseList(ArrayList<Tweet> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        return list;
    }

    Map<Long, Tweet> getFirstElementsFromMap(int numberOfFirstElements)  {
        Map<Long, Tweet> subMap = new TreeMap<>();
        int numberOfElements = map.size();
        if (numberOfFirstElements <= 0) {
            return subMap;
        } else if (numberOfFirstElements >= numberOfElements) {
            return map;
        } else {
            for (Map.Entry<Long, Tweet> entry : map.entrySet()) {
                subMap.put(entry.getKey(), entry.getValue());
                numberOfFirstElements--;
                if (numberOfFirstElements == 0) break;
            }
            return subMap;
        }
    }

    Map<Long, Tweet> getLastElementsFromMap(int numberOfLastElements)  {
        Map<Long, Tweet> subMap = new TreeMap<>();
        int numberOfElements = map.size();
        if (numberOfLastElements <= 0) {
            return subMap;
        } else if (numberOfLastElements >= numberOfElements) {
            return map;
        } else {
            int index = 1;
            int numberOfElementsToSkip = numberOfElements - numberOfLastElements;
            for (Map.Entry<Long, Tweet> entry : map.entrySet()) {
                if (index <= numberOfElementsToSkip) {
                    index++;
                    continue;
                } else {
                    subMap.put(entry.getKey(), entry.getValue());
                }
            }
            return subMap;
        }
    }

    public Map<Long, Tweet> getMap() {
        return map;
    }

}
