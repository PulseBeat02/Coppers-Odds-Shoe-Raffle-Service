//package com.pulsebeat02.main.util.JSON_reader;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pulsebeat02.main.gui.Shoe;
//
//public class ReadJSON {
//
//	// static Shoe[] shoes;
//
//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@JsonPropertyOrder({ "shoes" })
//
//	@JsonProperty("shoes")
//	public List<Shoe> shoes = null;
//	@JsonIgnore
//	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//	@JsonAnyGetter
//	public Map<String, Object> getAdditionalProperties() {
//		return this.additionalProperties;
//	}
//
//	@JsonAnySetter
//	public void setAdditionalProperty(String name, Object value) {
//		this.additionalProperties.put(name, value);
//	}
//
//	public static void main(String[] args) {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Shoe shoe = mapper.readValue(new File(""), Shoe.class);
//		} catch (JsonParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (JsonMappingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
////		JSONParser parser = new JSONParser();
//
//		try {
//
////			String cwd = System.getProperty("user.dir");
////			System.out.println("Current working directory : " + cwd);
////
////			Object obj = parser.parse(new FileReader(cwd + "/shoes.json"));
////
////			List<String> lines = Files.readAllLines(Paths.get(cwd + "\\shoes.json"), StandardCharsets.UTF_8);
////
////			int NumberOfShoes = 0;
////
////			for (int i = 0; i < lines.size(); i++) {
////
////				if (lines.get(i).contains("shoeName")) {
////
////					NumberOfShoes++;
////
////				}
////
////			}
////
////			ObjectMapper objectMapper = new ObjectMapper();
////
////			File file = new File("data/car.json");
////
////			Shoe shoe = objectMapper.readValue(file, Shoe.class);
//
//			// shoes = new Shoe[NumberOfShoes - 1];
//
//			// StartingWindow.shoesInGui = shoes;
//
////			public Shoe(int shoePrice, int shipping, int tax, int subtotal, double review,
////
////					int totalRaffles, int rafflesBought,
////
////					String shoeName, String clientID, String clientSecret, String style, String typeOfShoes, String brand,
////
////					Image[] images,
////
////					String name, String description, String[] colors, String[] sizes,
////
////					boolean isSold) {
////			
//
////            String name = (String) jsonObject.get("Name");
////            String author = (String) jsonObject.get("Author");
////            JSONArray companyList = (JSONArray) jsonObject.get("Company List");
////            
////            @SuppressWarnings("unchecked")
////			Iterator<String> iterator = companyList.iterator();
////            
////            while (iterator.hasNext()) {
////            	
////                System.out.println(iterator.next());
////                
////            }
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
////	private static void parseShoeObject(JSONObject emp, int index) {
////		// TODO Auto-generated method stub
////
////		JSONObject shoeObject = new JSONObject();
////		shoeObject = (JSONObject) emp.get("shoes");
////
////		int shoePrice = (int) shoeObject.get("shoePrice");
////		int shipping = (int) shoeObject.get("shipping");
////		int tax = (int) shoeObject.get("tax");
////		int subtotal = (int) shoeObject.get("sub-total");
////
////		// int totalUsers = (int) shoeObject.get("totalUsers");
////
////		int totalRaffles = (int) shoeObject.get("totalRaffles");
////
////		String[] sizes = (String[]) shoeObject.get("Sizes");
////
////		double review = (double) shoeObject.get("review");
////
////		String shoeName = (String) shoeObject.get("shoeName");
////		String brand = (String) shoeObject.get("brand");
////		String typeOfShoes = (String) shoeObject.get("typeOfShoes");
////		String style = (String) shoeObject.get("style");
////		String description = (String) shoeObject.get("description");
////
////		String[] colors = (String[]) shoeObject.get("Color");
////		String[] imageURLs = (String[]) shoeObject.get("images");
////
////		boolean isSold = (boolean) shoeObject.get("isSold");
////
//////		shoes[i].add(shoePrice);
//////		shoes[i].add(shipping);
//////		shoes[i].add(tax);
//////		shoes[i].add(subtotal);
//////		shoes[i].add(totalUsers);
//////		shoes[i].add(totalRaffles); 
//////		shoes[i].add(sizes); 
//////		shoes[i].add(review);
//////		shoes[i].add(shoeName); 
//////		shoes[i].add(brand);
//////		shoes[i].add(typeOfShoes); 
//////		shoes[i].add(style); 
//////		shoes[i].add(description); 
//////		shoes[i].add(clientID_Paypal);
//////		shoes[i].add(clientSecret_Paypal); 
//////		shoes[i].add(colors); 
//////		shoes[i].add(imageURLs);
////
////		ArrayList<Image> imagesPre = new ArrayList<Image>();
////
////		for (int i = 0; i < imageURLs.length; i++) {
////
////			Image currentImage = null;
////			URL url = null;
////			try {
////				url = new URL(imageURLs[i]);
////			} catch (MalformedURLException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			try {
////				currentImage = ImageIO.read(url);
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			imagesPre.add(currentImage);
////
////		}
////
////		Image[] images = (Image[]) imagesPre.toArray();
////
////		Shoe shoe = new Shoe(shoePrice, shipping, tax, subtotal, review, totalRaffles, 0, shoeName, style, typeOfShoes,
////				brand, images, description, colors, sizes, isSold);
////
////		shoes[index] = shoe;
////
////		index++;
////
////	}
//
//}

package com.pulsebeat02.main.util.JSON;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pulsebeat02.main.gui.Shoe;
import com.pulsebeat02.main.gui.windows.StartingWindow;

public class ReadJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		parseJSON();

	}

	public static void parseJSON() {

		String cwd = System.getProperty("user.dir");

		File f = new File(cwd + "/shoes.json");

		String str = null;
		try {
			str = readFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		Type dataType = (new TypeToken<Shoes>() {
		}).getType();

		Shoes shoeList = gson.fromJson(str, dataType);
		
		List<Shoe> shoes = shoeList.getShoes();
		
		Shoe [] shoesFinal = new Shoe[shoes.size()];
		
		for (int i = 0; i < shoesFinal.length; i++) {
			
			shoesFinal[i] = shoes.get(i);
			
		}
		
		StartingWindow.shoesInGui = shoesFinal;
		
		System.out.println(shoeList);
		System.out.println("Ran");

	}

	public static String readFile(File file) throws IOException {
		return new String(Files.readAllBytes(file.toPath()));
	}

}
