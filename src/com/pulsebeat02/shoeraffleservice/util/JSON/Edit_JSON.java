package com.pulsebeat02.main.util.JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Map;

import com.google.gson.Gson;
import com.pulsebeat02.main.gui.Shoe;
import com.pulsebeat02.main.util.logging.Logger;

public class Edit_JSON {

	static String cwd = System.getProperty("user.dir");

	public static void edit (int index, int value) {

		Logger.LOG.info("Editing JSON");

		File f = new File(cwd + "/shoes.json");

		String str = null;
		try {
			str = readFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		Shoes shoes = gson.fromJson(str, Shoes.class);

		shoes.getShoes().get(index).totalRaffles = value;

		String json = gson.toJson(shoes);

		String cwd = System.getProperty("user.dir");

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(cwd + "/shoes.json", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println(json);
		writer.close();

	}

	public static int getOriginal(int shoeNumber) {

		File f = new File(cwd + "/shoes.json");

		String str = null;
		try {
			str = readFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		@SuppressWarnings("unchecked")
		Map<String, Shoe> map = gson.fromJson(str, Map.class);

		return map.get("totalRaffles").totalRaffles;

	}

	public static String readFile(File file) throws IOException {
		return new String(Files.readAllBytes(file.toPath()));
	}

}
