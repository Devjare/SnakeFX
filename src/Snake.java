import java.util.ArrayList;
import java.util.List;

public class Snake {

	public static class Corner {
		int x, y;

		Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	private List<Corner> corners;
	private int length;
	private Direction direction;

	public Snake(Corner headCorner) {
		this.corners = List.of(headCorner, new Corner(headCorner.x - 5, headCorner.y),
				new Corner(headCorner.x - 10, headCorner.y));
		this.direction = Direction.RIGHT;
		this.length = corners.size();
	}

	public void moveRight() {
		this.direction = Direction.RIGHT;
	}
	
	public void moveLeft() {
		this.direction = Direction.LEFT;
	}
	
	public void moveUp() {
		this.direction = Direction.UP;
	}
	
	public void moveDown() {
		this.direction = Direction.DOWN;
	}
	
	@Override
	public String toString() {
		return "Snake [corners=" + corners + ", length=" + length + ", direction=" + direction + "]";
	}
	
	
}
