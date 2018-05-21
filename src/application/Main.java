package application;

import java.io.IOException;
 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	@Override
    public void start(Stage primaryStage) {		
		try {
       AnchorPane pane = FXMLLoader.load(Main.class.getResource("video.fxml")); 
       primaryStage.setScene(new Scene(pane));
       primaryStage.show();
    }catch(IOException e) {
    	e.printStackTrace();}
    }
	public static void main(String[] args) {
		launch(args);			
	}	
 }

