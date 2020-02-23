
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		final int WIDTH = 500;
		final int HEIGHT = 500;
		
		var root = new BorderPane();
		var scene = new Scene(root, WIDTH, HEIGHT);
		
		var snake = new Snake(new Snake.Corner(WIDTH / 2, HEIGHT / 2));
		System.out.println(snake);
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			
		});
		
		primaryStage.setTitle("Snake!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
