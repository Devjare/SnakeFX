import java.util.ArrayList;
import java.util.List;

public class Snake {

	public static class Corner {
		int x, y;

		Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Corner() {
			// TODO Auto-generated constructor stub
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
		this.corners.add(new Corner(headCorner.x - 10, headCorner.y));
		this.corners.add(new Corner(headCorner.x - 20, headCorner.y));
//		this.corners.add(new Corner(headCorner.x - 30, headCorner.y));
//		this.corners.add(new Corner(headCorner.x - 40, headCorner.y));
//		this.corners.add(new Corner(headCorner.x - 50, headCorner.y));
//		this.corners.add(new Corner(headCorner.x - 60, headCorner.y));

		this.direction = Direction.RIGHT;
		this.length = corners.size();
	}

	public void moveRight() {
		
		this.direction = Direction.RIGHT;

		var head = this.corners.get(0);
		this.corners.set(0, new Corner(head.x + speed, head.y));
		
		var temp = this.corners.get(1);
		this.corners.set(1, head);
		
		for (int i = 2; i < this.corners.size(); i++) {
			var tmp = this.corners.get(i);
			this.corners.set(i, temp);
			temp = tmp;
		}
	}

	public void moveLeft() {

		this.direction = Direction.LEFT;

		var head = this.corners.get(0);
		this.corners.set(0, new Corner(head.x - speed, head.y));
		
		var temp = this.corners.get(1);
		this.corners.set(1, head);
		
		for (int i = 2; i < this.corners.size(); i++) {
			var tmp = this.corners.get(i);
			this.corners.set(i, temp);
			temp = tmp;
		}
	}

	public void moveUp() {

		this.direction = Direction.UP;
		
		var head = this.corners.get(0);
		this.corners.set(0, new Corner(head.x, head.y - speed));
		
		var temp = this.corners.get(1);
		this.corners.set(1, head);
		
		for (int i = 2; i < this.corners.size(); i++) {
			var tmp = this.corners.get(i);
			this.corners.set(i, temp);
			temp = tmp;
		}
	}

	public void moveDown() {
		
		this.direction = Direction.DOWN;
		
		var head = this.corners.get(0);
		this.corners.set(0, new Corner(head.x, head.y + speed));
		
		var temp = this.corners.get(1);
		this.corners.set(1, head);
		
		for (int i = 2; i < this.corners.size(); i++) {
			var tmp = this.corners.get(i);
			this.corners.set(i, temp);
			temp = tmp;
		}
	}

	public Direction getDirection() {
		return this.direction;
	}

	public Corner getHeadCorner() {
		return this.corners.get(0);
	}

	@Override
	public String toString() {
		return "Snake [corners=" + corners + ", length=" + length + ", direction=" + direction + "]";
	}

	public String getCornersPositions() {
		return corners.toString();
	}

	public ArrayList<Corner> getCorners() {
		return this.corners;
	}

	public void eat() {
		var last = this.corners.get(this.corners.size() - 1);
		if(this.direction.equals(Direction.LEFT)) {
			this.corners.add(new Corner(last.x + speed, last.y));
		} else if(this.direction.equals(Direction.RIGHT)) {
			this.corners.add(new Corner(last.x - speed, last.y));
		} else if(this.direction.equals(Direction.DOWN)) {
			this.corners.add(new Corner(last.x, last.y + speed));
		} else if(this.direction.equals(Direction.UP)) {
			this.corners.add(new Corner(last.x + speed, last.y - speed));
		}
	}

	public int getSize() { 
		return this.corners.size();
	}

}
