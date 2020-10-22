package com.pulsebeat02.shoeraffleservice.application.ads;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;

//https://www.youtube.com/embed/uZJ2bq12JSI

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class PlayVideoJFrame extends Application {

    public static String url;

    public static void startVideo(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

	WebView webview = new WebView();
	webview.getEngine().load(url);
	webview.setPrefSize(640, 390);

	stage.setScene(new Scene(webview));
	stage.setTitle("This Ad Supports Coppers Odds, Please Wait 10 Seconds to Skip It");
	stage.initStyle(StageStyle.UTILITY);
	stage.centerOnScreen();
	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	    @Override
	    public void handle(WindowEvent t) {
		stage.show();
	    }
	});
	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	    @Override
	    public void handle(WindowEvent event) {
		event.consume();
	    }
	});
	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	    @SuppressWarnings("deprecation")
	    @Override
	    public void handle(WindowEvent t) {
		stage.close();
		ShoeRaffleService.service.getInstanceManager().STARTING_WINDOW.adThread.stop();
	    }
	});
	stage.show();

	Platform.setImplicitExit(false);
	Thread.sleep(10000);

    }
    
    public String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        PlayVideoJFrame.url = url;
    }
    
}