package application;



import java.nio.file.Paths;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;


public class Controller{
	
    @FXML
	private TextField search; 
	@FXML
	private MediaView mv;
	@FXML
	private ImageView sound;
	@FXML
	private MediaPlayer mp;
	@FXML
	public ListView<String> list;
	@FXML
	public AnchorPane menu;
	@FXML
	private HBox HBox;
	@FXML
	private AnchorPane cityPane;
	@FXML
	private ImageView stop;
	@FXML
	private Label city;
	@FXML
	private ImageView back;
	events events = new events();
	private int a;
    @FXML
    public void initialize(){	
     HBox.setVisible(false);
     back.setVisible(false);
     cityPane.setVisible(false);
     events events = new events();
     Media m = new Media(Paths.get("res/1.mp4").toUri().toString());
	 mp = new MediaPlayer(m);
	 mv.setMediaPlayer(mp);
	 mv.setFitWidth(640);
	 mv.setFitHeight(360);
	 mp.play();
	 
	 //Шукає індекс натисненого та обирає місто через цей індекс:
	 list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    try{
		    	a = list.getSelectionModel().getSelectedIndex();
		    	city.setText(events.cityarray.get(a));
		    }
		    catch(Exception e){
		    	
		    }
		}
	});
	 
	 
	 
	 //Подія натискання Enter для запиту на сервер
	 search.setOnKeyPressed(new EventHandler<KeyEvent>() {
	     
			@Override
			public void handle(KeyEvent key) {
				  
				if(key.getCode().equals(KeyCode.ENTER)) {
					try {	
						
						events.query = search.getText();
					    events.Tours();  
					    list.setItems(events.data);
					    
					    back.setVisible(true);
					    HBox.setVisible(true);
					    cityPane.setVisible(true); 
					    menu.setVisible(false);
					    
					} catch (Exception e) {
					}	
				}	
			}
		});
    }
    //Кнопка виключення звуку
    @FXML
    public void onClicked(){
     if(mp.getStatus()==Status.PLAYING) {
    	 mp.stop();
    	 mv.setVisible(false);
    	 
     }else if(mp.isMute()) {
    	 mp.setMute(true);
     }
     
     
     
     
    }
    
    @FXML
    public void onBack() {
   	 HBox.setVisible(false);
   	 cityPane.setVisible(false);
   	 back.setVisible(false);
   	 menu.setVisible(true);
   	 city.setText("");
     
   	 
    }
    
    
    
    @FXML
    public void onMute() {
    	mp.setMute(true);
    }
    }
    
    
 
