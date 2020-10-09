package com.pulsebeat02.main.gui.application.paypal;

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

    public boolean checkApproval() throws IOException, ScriptException {

	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByExtension("php");

	File phpScript = new File("paymentStatus.php");
	String path = phpScript.getAbsolutePath();

	String script = readFile(path, StandardCharsets.UTF_8);

	String output = (String) engine.eval(script);

	if (output.equals("Completed")) {

	    return true;

	} else {

	    return false;

	}

    }

    public String readFile(String path, Charset encoding) throws IOException {
	byte[] encoded = Files.readAllBytes(Paths.get(path));
	return new String(encoded, encoding);
    }

}
