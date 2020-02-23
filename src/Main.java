
import javax.swing.JComboBox.KeySelectionManager;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;

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

			var code = key.getCode();
			var snakeDirection = snake.getDirection();

			if (code.equals(KeyCode.RIGHT)
					&& !(snakeDirection.equals(Snake.Direction.LEFT) || snakeDirection.equals(Snake.Direction.RIGHT)))
				snake.moveRight();
			else if (code.equals(KeyCode.DOWN)
					&& !(snakeDirection.equals(Snake.Direction.UP) || snakeDirection.equals(Snake.Direction.DOWN)))
				snake.moveDown();
			else if (code.equals(KeyCode.LEFT)
					&& !(snakeDirection.equals(Snake.Direction.LEFT) || snakeDirection.equals(Snake.Direction.RIGHT)))
				snake.moveLeft();
			else if (code.equals(KeyCode.UP)
					&& !(snakeDirection.equals(Snake.Direction.UP) || snakeDirection.equals(Snake.Direction.DOWN)))
				snake.moveUp();

		});

		var timer = new AnimationTimer() {

			long lastTick = 0;

			@Override
			public void handle(long now) {

				clearScreen();
				drawSnake();
				
				long tick = now / 1000000000L;

				if (lastTick != tick) {

					System.out.println("Snake body: " + snake.getCornersPositions());
					
					var snakeDirection = snake.getDirection();

					if (snakeDirection.equals(Snake.Direction.RIGHT)) {
						snake.moveRight();
					} else if (snakeDirection.equals(Snake.Direction.DOWN)) {
						snake.moveDown();
					} else if (snakeDirection.equals(Snake.Direction.LEFT)) {
						snake.moveLeft();
					} else {
						snake.moveUp();
					}

					lastTick = tick;
				}

			}

			private void clearScreen() {
				root.getChildren().clear();
			}

			private void drawSnake() {
				var corners = snake.getCorners();
				corners.forEach(corner -> {
					var circle = new Circle();
					circle.setCenterX(corner.x);
					circle.setCenterY(corner.y);
					circle.setRadius(5);
					circle.setFill(Color.BLACK);
					
					root.getChildren().add(circle);
				});
			}
		};

		timer.start();

		primaryStage.setTitle("Snake!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
