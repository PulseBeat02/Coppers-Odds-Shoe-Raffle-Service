package com.pulsebeat02.main.gui.windows.paypal;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RunScript {

	public static boolean checkApproval() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByExtension("php");

		try {

			File phpScript = new File("paymentStatus.php");
			String path = phpScript.getAbsolutePath();

			String script = readFile(path, StandardCharsets.UTF_8);

			String output = (String) engine.eval(script);
			
			if (output.equals("Completed")) {
				
				return true;
				
			}
			
			else {
				
				return false;
				
			}

		} catch (ScriptException e) {
			System.err.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
