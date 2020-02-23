
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

import java.util.ArrayList;
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

			private final int SIZE = 5;
			
			long lastTick = 0;
			private int foodCount = 0;

			int max = WIDTH - 10;
			int min = 10;
			int randomX = min + (int) (Math.random() * (max - min + 1));
			int randomY = min + (int) (Math.random() * (max - min + 1));
			private Circle food = new Circle(randomX, randomY, SIZE, Color.RED);
			private boolean isFoodEaten = false;

			@Override
			public void handle(long now) {

				clearScreen();

				drawSnake();

				drawFood();

				long tick = now / 100000000L;

				if (lastTick != tick) {

// 					System.out.println("Snake body: " + snake.getCornersPositions());

					var snakeDirection = snake.getDirection();

					if (isOnFood(snake)) {
						snake.eat();
						System.out.println("Snake size: " + snake.getSize());
						isFoodEaten = true;
					}
					
					if (isOnSelf(snake)) {
						gameOver();
					}
					
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

			private void gameOver() {
				clearScreen();
				System.out.println("GAME OVER!");
				System.exit(0);
			}

			private boolean isOnSelf(Snake snake) {
				int x = snake.getHeadCorner().x;
				int y = snake.getHeadCorner().y;
				
				var corners = snake.getCorners();
				
				for(int i = 1;i < corners.size();i++) {
					if(x == corners.get(i).x && y == corners.get(i).y) 
						return true;
				}
				
				return false;
			}

			private boolean isOnFood(Snake snake) {
				int xOffset = SIZE + 1;
				int yOffset = SIZE + 1;

				int foodXCenter = (int) food.getCenterX();
				int foodYCenter = (int) food.getCenterY();

				int xFoodStart = foodXCenter - xOffset;
				int xFoodEnd = foodXCenter + xOffset;

				int yFoodStart = foodYCenter - yOffset;
				int yFoodEnd = foodYCenter + yOffset;

				int snakeXCorner = snake.getHeadCorner().x;
				int snakeYCorner = snake.getHeadCorner().y;

				return snakeXCorner >= xFoodStart && snakeXCorner < xFoodEnd && snakeYCorner >= yFoodStart
						&& snakeYCorner < yFoodEnd;
			}

			private void drawFood() {

				if (!isFoodEaten) {
					root.getChildren().add(food);
					return;
				}

				int max = WIDTH - 10;
				int min = 10;
				int randomX = min + (int) (Math.random() * (max - min + 1));
				int randomY = min + (int) (Math.random() * (max - min + 1));

				food = new Circle();
				food.setCenterX(randomX);
				food.setCenterY(randomY);
				food.setFill(Color.RED);
				food.setRadius(SIZE);

				root.getChildren().add(food);
				isFoodEaten = false;
			}

			private boolean isFoodOnPlane() {
				return isFoodEaten;
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
					circle.setRadius(SIZE);
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
