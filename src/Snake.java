import java.util.ArrayList;
import java.util.List;

public class Snake {

	public static class Corner {
		int x, y;

		Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[x: " + x + ", y: " + y + "]";
		}
	}

	static enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private ArrayList<Corner> corners;
	private int length;
	private Direction direction;
	private int speed = 5;

	public Snake(Corner headCorner) {
		this.corners = new ArrayList<Corner>();
		this.corners.add(headCorner);
		this.corners.add(new Corner(headCorner.x - 5, headCorner.y));
		this.corners.add(new Corner(headCorner.x - 10, headCorner.y));

		this.direction = Direction.RIGHT;
		this.length = corners.size();
	}

	public void moveRight() {
		
		this.direction = Direction.RIGHT;

		var head = this.corners.get(0);

		for (int i = 0; i < this.corners.size() - 1; i++) {
			if (i > 1) {
				
				this.corners.set(i, this.corners.get(i - 1));
			} else if (i == 0) {
				this.corners.get(i).x += speed;
			} else {
				if(this.direction.equals(Direction.RIGHT))
					this.corners.set(1, head);
			}
		}
	}

	public void moveLeft() {

		this.direction = Direction.LEFT;

		var head = this.corners.get(0);

		for (int i = 0; i < this.corners.size() - 1; i++) {
			if (i > 1) {
				this.corners.set(i, this.corners.get(i - 1));
			} else if (i == 0) {
				this.corners.get(i).x -= speed;
			} else {
				this.corners.set(i, head);
			}
		}
	}

	public void moveUp() {

		this.direction = Direction.UP;
		
		var head = this.corners.get(0);

		for (int i = 0; i < this.corners.size() - 1; i++) {
			if (i > 1) {
				this.corners.set(i, this.corners.get(i - 1));
			} else if (i == 0) {
				this.corners.get(i).y -= speed;
			} else {
				this.corners.set(i, head);
			}
		}
	}

	public void moveDown() {
		
		this.direction = Direction.DOWN;
		
		var head = this.corners.get(0);

		for (int i = 0; i < this.corners.size() - 1; i++) {
			if (i > 1) {
				this.corners.set(i, this.corners.get(i - 1));
			} else if (i == 0) {
				this.corners.get(i).y += speed;
			} else {
				this.corners.set(i, head);
			}
		}
	}

	public Direction getDirection() {
		return this.direction;
	}

	public Corner getHeadCorner() {
		return this.corners.get(0);
	}

	public ArrayList<Corner> getCorners() {
		return this.corners;	
	}
	
	@Override
	public String toString() {
		return "Snake [corners=" + corners + ", length=" + length + ", direction=" + direction + "]";
	}

	public String getCornersPositions() {
		return corners.toString();
	}

}
