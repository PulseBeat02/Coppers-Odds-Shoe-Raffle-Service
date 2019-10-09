package com.pulsebeat02.main.util.arrayutil;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map.Entry;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;

public /* abstract */ class Sort /* implements java.lang.Comparable<T> */ {

//	public <T> ConcurrentHashMap<T, String> sortHashMapByValues(ConcurrentHashMap<T, String> passedMap) {
//		
//		List<?> mapKeys = new ArrayList<>(passedMap.keySet());
//		List<String> mapValues = new ArrayList<>(passedMap.values());
//		Collections.sort(mapValues);
//		Collections.sort(mapKeys);
//
//		ConcurrentHashMap<T, String> sortedMap = new ConcurrentHashMap<>();
//
//		Iterator<String> valueIt = mapValues.iterator();
//		while (valueIt.hasNext()) {
//			String val = valueIt.next();
//			Iterator<?> keyIt = mapKeys.iterator();
//
//			while (keyIt.hasNext()) {
//				Object key = keyIt.next();
//				String comp1 = passedMap.get(key);
//				String comp2 = val;
//
//				if (comp1.equals(comp2)) {
//					keyIt.remove();
//					sortedMap.put((T) key, val);
//					break;
//				}
//			}
//		}
//		return sortedMap;
//	}

	/*
	 * Custom Sort Algorithm Steps:
	 * 
	 * Step #1: Create a ConcurrentHashMap for the New Sorted Map Step #2: Get the
	 * List of map keys from map. (T) Step #3: Get the List of map values from map.
	 * (String) Step #4: Use for loop to loop through each element in the list of
	 * Strings and categorize by alphabetical order. Step #5: When moving a value in
	 * the loop, also move the key in the loop Step #6: Get the final sort of the
	 * two lists. Step #7: Use the zipToMap method to convert the list of keys and
	 * values to a new ConcurrentHashMap.
	 * 
	 * qian jin qian jin qian zin zin Qian Jin
	 */

//	public static void main(String[] args) {
//
//		ConcurrentHashMap<Integer, String> test = new ConcurrentHashMap<Integer, String>();
//		test.put(5, "apple");
//		test.put(1, "bannana");
//		test.put(2, "orange");
//		test.put(3, "grape");
//		test.put(4, "strawberry");
//
//		ConcurrentHashMap<Integer, String> finalMap = sort(test);
//		System.out.println(finalMap);
//
//	}

//	public static <K, V> ConcurrentHashMap<K, String> sort(ConcurrentHashMap<K, String> map) {
//
//		ConcurrentHashMap<K, String> sortedMap = new ConcurrentHashMap<K, String>(map.size());
//
//		List<K> mapKeys = new ArrayList<>(map.keySet());
//		List<String> mapValues = new ArrayList<>(map.values());
//
//		String temp;
//
//		for (int i = 0; i < mapValues.size(); i++) {
//			for (int j = i + 1; j < mapValues.size(); j++) {
//				if (mapValues.get(i).compareTo(mapValues.get(j)) > 0) {
//					
//					System.out.println("Current Map Keys: " + mapKeys);
//					System.out.println("Current Map Values: " + mapValues);
//					
//					temp = mapValues.get(i);
//
//					mapValues.set(i, mapValues.get(j));
//					mapValues.set(j, temp);
//					
//					mapKeys.set(j, mapKeys.get(j));
//					
//				}
//			}
//		}
//		
//		sortedMap = zipToMap(mapKeys, mapValues);
//		
//		return sortedMap;
//
//	}
//
//	public static <K, V> ConcurrentHashMap<K, V> zipToMap(List<K> keys, List<V> values) {
//		return new ConcurrentHashMap<>(
//				IntStream.range(0, keys.size()).boxed().collect(Collectors.toMap(keys::get, values::get)));
//	}

//	public static <T> sortbykey(ConcurrentHashMap<T, String> map) {
//		// TreeMap to store values of HashMap
//		ArrayList<String> sortedKeys = 
//                new ArrayList<String>(map.keySet()); 
//      
//		Collections.sort(sortedKeys); 
//	}

}
