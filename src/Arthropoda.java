/**
 *
 * @author Andreas Pichler
 * @version 3.3.18
 * Arthropods include insects and arachnids
 *
 */
public class Arthropoda {
	/**
	 * Number of limbs of the arthropod
	 */
	private int feet;
	/**
	 * Orientation in degrees from 0-359
	 */
	private int orientation;
	/**
	 * This is where the position of the arthropod is stored in the world
	 */
	private Position position;
	/**
	 * Speed ​​of this arthropoda
	 */
	private int speed;
	/**
	* Name of the arthrpoda
	*/
	private String name;
	/**
	* Live points of the arthrpoda maximum 100
	*/
	private int lifePoints;
	/**
	 * Create a new arthropod and set the data fields.
	 *
	 * @param speed
	 * @param orientation
	 * @param feed
	 * @param position
	 * @param Name
	 *
	 */
	public Arthropoda(int speed, int orientation, int feed, Position position, String name){
		this.speed=speed;
		setOrientation(orientation);
		this.feet=feed;
		this.position=position;
		setName(name);
		this.lifePoints=100;
	}
	/**
	* @return name
	*/
	public String getName(){
		return this.name;
	}
	/**
	* Set the name and check the length
	* @param name
	*/
	public void setName(String name){
		if(name.length()>3){
			this.name=name;
		}
		else{
			this.name="setYourName";
		}
	}
	/**
	* @return lifePoints
	*/
	public int getLifePoints(){
		return this.lifePoints;
	}
	/**
	* Set the life points of the arthrpoda
	* @param life
	*/
	public void setLifePoints(int life){
		this.lifePoints=life;
	}
	/**
	 * @return feed
	 */
	public int getFeet() {
		return feet;
	}
	public void setFeet(int feet) {
		this.feet = feet;
	}
	public int getOrientation() {
		return orientation;
	}
	/**
	 *
	 * The orientation should only be set in multiples of 90 between 0 and 359.
	 * If a value above 359 is transferred, this must be corrected.
	 * If you pass a value that is not a multiple of 90, set a multiple of 90, which is close to the given value.
	 *
	 *
	 * @param orientation
	 *
	 *
	 */
	public void setOrientation(int orientation) {
		int setOrientation = 0;
		//Check the orientation if it is between 0 and -360 and make it posetiv
		if(orientation<0 && orientation>=(-360)) {
			orientation=360+orientation;
		}
		//multiple of 90
		if(orientation % 90 == 0){
			setOrientation = orientation;
		}
		else {
			// integer division
			int times90 = orientation / 90;
			setOrientation = (times90 * 90);
		}
		setOrientation = setOrientation % 360;
		//If it is still negativ make it posetiv 
		if(setOrientation<0){
			setOrientation=setOrientation*(-1);
		}
		this.orientation = setOrientation;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 *
	 * The arthropod is moved by distance in the direction of view (orientation).
	 *
	 * @param distnace
	 */
	public void move(int distance){

		int y=position.getY();
		int x=position.getX();
		boolean a=false;
		if(orientation==0){
			y+=distance;
			a=true;
		}
		else if(orientation==90){
			x+=distance;
			a=true;
		}
		else if(orientation==180){
			y-=distance;
			a=true;
		}
		else if(orientation==270){
			x-=distance;
			a=true;
		}
		if(a){
			position.setX(x);
			position.setY(y);
		}
	}
	/**
	 *
	 * Turn the arthropod in the direction indicated.
	 * If direction is "LEFT", it is rotated 90 degrees to the left, otherwise 90 degrees to the right.
	 *
	 * @param direction
	 */
	public void turn(String direction){
		int turnBy = 90;
		if(direction.equals("LEFT")){
			turnBy=turnBy*-1;     //makes turnBy negative
		}
		setOrientation(getOrientation()+turnBy);
	}

}
