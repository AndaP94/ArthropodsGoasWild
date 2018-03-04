/**
* @author Andreas Pichler
* @version 2.3.18
* Saves a position in the world
*
*/
public class Position {
	/**
	 * x position in the world
	 */
	private int x;
	/**
	 * y position in the world
	 */
	private int y;
	public Position(int x, int y){
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String toString(){
		return "Position x: " + getX() + " \nPosition y: " + getY(); 
	}
}
