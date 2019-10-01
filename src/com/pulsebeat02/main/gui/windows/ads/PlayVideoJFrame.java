package com.pulsebeat02.main.gui.windows.ads;

//https://www.youtube.com/embed/uZJ2bq12JSI

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class PlayVideoJFrame extends Application {

	static String cwd = System.getProperty("user.dir");

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
		stage.setTitle("This Ad Supports Coppers Odds, Please Wait 5-10 Seconds to Skip It");
		stage.initStyle(StageStyle.UTILITY);
		stage.centerOnScreen();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override public void handle(WindowEvent t) {
		        stage.show();
		    }
		});
		stage.show();
		
//		Label label = new Label("Please Wait 5-10 Seconds to Skip the Ad");
//		
//		JFXPanel jfxPanel = new JFXPanel();
//		jfxPanel.setScene(scene);
//		jfxPanel.add(label);
//		
//		SwingUtilities.invokeLater(() -> {
//            frame.add(jfxPanel);
//            frame.setResizable(false);
//            frame.setTitle("The Following Ad Supports Coppers Odds");
//            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//            frame.setUndecorated(true);
//            frame.getContentPane().setBackground(Color.BLACK);
//            frame.pack();
//            frame.setVisible(true);
//        });
		
		// stage.getIcons().add(new Image(getClass().getResourceAsStream(cwd +
		// "/ad.jpg")));
		
        // frame.getContentPane().add(stage, BorderLayout.CENTER);

        
        
		Thread.sleep(10000);

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override public void handle(WindowEvent t) {
		        stage.close();
		    }
		});

	}
}