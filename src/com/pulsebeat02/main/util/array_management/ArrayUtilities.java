package com.pulsebeat02.main.util.array_management;

import java.util.ArrayList;

import com.pulsebeat02.main.util.logging.Logger;

public class ArrayUtilities {

	public static <E> Object getRidOfNull(E[] array) {

		ArrayList<E> newArray = new ArrayList<E>();

		for (int i = 0; i < array.length; i++) {

			if (array[i] != null) {

				newArray.add(array[i]);

			}

		}

		Logger.LOG.warn("Array has null element");

		return newArray.toArray(array);

	}

	public static <E> Object getRidOfNull(ArrayList<E> arraylist) {

		for (int i = 0; i < arraylist.size(); i++) {

			if (arraylist.get(i) == null) {

				arraylist.remove(arraylist.get(i));

			}

		}

		Logger.LOG.warn("Arraylist has null element");

		return arraylist;

	}


}
