package com.pulsebeat02.shoeraffleservice.util.arrayutil;

import java.util.ArrayList;

public class NonNullableArrayList<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<E> arrayList;
	
	public NonNullableArrayList(ArrayList<E> list) {
		
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i) == null) {
				
				list.remove(i);
				
			}
			
		}
		
		this.arrayList = list;
		
	}
	
}
