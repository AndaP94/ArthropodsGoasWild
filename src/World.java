import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
 /**
 * @author Andreas Pichler
 * @version 3.3.18
 * The world keeps a list of arthropods, can add arthropods, give back, move, rotate and fight each other
 *
 * The world contains boundaries
 *
 */
public class World {
	/**
	 * The limit of the width
	 */
	private int xLimit;
	/**
	 * The limit of the height
	 */
	private int yLimit;
	/**
	 * Example animal one
	 */
	private Arthropoda animal1;
	/**
	 * Example animal two
	 */
	private Arthropoda animal2;
	/**
	 * Example animal three
	 */
	private Arthropoda animal3;
	/**
	 * ArrayList of the arthropoda
	 */
	private ArrayList<Arthropoda> arthList;
	/**
	 * Random number for the fight
	 */
	private Random rnd;
	/**
	 * Creat a new World with the limites and the animal examples
	 *
	 */
	public World(){
		arthList=new ArrayList<>();
		this.xLimit=100;
		this.yLimit=100;
		animal1=new Arthropoda(2, -470, 8, new Position(50, 1 ), "Sepp" );	//speed=2 , orientation= 180 , feed=8, Position x:50 y:1
		animal2=new Arthropoda(5, 95, 8, new Position(20, 30), "Tim" );	//speed=5 , orientation= 95 , feed=8, Position x:20 y:30
		animal3=new Arthropoda(5, 275, 4, new Position(100,29), "Lucas");		//speed=5 , orientation= 275 , feed=4, Position x:50 y:50
		arthList.add(animal1);
		arthList.add(animal2);
		arthList.add(animal3);
		rnd=new Random();
	}
	/**
	 * Add an arthropoda
	 *
	 * @param feed Number of limbs of the arthropoda
	 * @param orientation Orientation in degrees from 0-359
	 * @param speed Speed ​​of this arthropod
	 * @param position This is where the position of the arthropod is stored in the world
	 */
	public void addArthropoda(int feed, int orientation, int speed, Position position, String name){

		Arthropoda arth=new Arthropoda(feed, orientation, speed, position, name);
		arthList.add(arth);
	}
	/**
	 *
	 * Returns the Arthropoda with the index
	 * @param index
	 * @return Arthropoda
	 */
	public Arthropoda getArthropoda(int index){
		boolean a=arthList.isEmpty();
		Arthropoda arth=null;
		if(!a){
			for(int i=0; i<arthList.size(); i++){
				if(i==index){
				arth=arthList.get(i);
				}
			}
		}
		return arth;
	}
	/**
	 *
	 * Check the position if the animal is out of range
	 * @param x xPosition of the animal
	 * @param y	yPosition of the anomal
	 * @return boolean
	 */
	private boolean outOfRange(Position position){
		int x=position.getX();
		int y=position.getY();
		if(x>this.xLimit || y>this.yLimit){
			return true;
		}
		if(x<0 || y<0){
			return true;
		}
		return false;
	}
	/**
	 *
	 * Returns the limit positions if the animal is out auf range
	 * @param x
	 * @param y
	 * @return Position
	 */
	private Position getPositionNew(int x, int y){
		int x1 = x;
		int y1 = y;
		Position pos=new Position(x,y);
		if(outOfRange(pos)){
			if(x>xLimit){
				x1=xLimit;
			}
			if(y>yLimit){
				y1=yLimit;
			}
			if(x<0){
				x1=0;
			}
			if(y<0){
				y1=0;
			}
		}
		Position newPosition=new Position(x1,y1);
		return newPosition;
	}

	/**
	 * Move arthropoda with the arthropoda index. If the animal is out of Range, it turns left and go the rest,
	 * or if it against another arthropoda then they fight
	 * @param distance
	 * @param arthropodaIndex
	 * @return Position from arthropoda
	 */

	public LinkedList<Position> moveArthropoda(int distance, int arthropodaIndex){
		Arthropoda arthropoda=arthList.get(arthropodaIndex);
		LinkedList<Position> posList = new LinkedList<>();
		// posList.addLast(null);
		int x=arthropoda.getPosition().getX();
		int y=arthropoda.getPosition().getY();
		Position latestPos = new Position(x,y);
		/*
		 * At 0 degrees
		 */
		if(arthropoda.getOrientation()==0){
			y=y+distance*arthropoda.getSpeed();
			latestPos.setY(y);
			if(outOfRange(latestPos)){
				arthropoda.setPosition(turnBy0(latestPos, arthropoda));
				posList.addFirst(arthropoda.getPosition());
			}
			else{
				arthropoda.setPosition(latestPos);
				posList.addFirst(arthropoda.getPosition());
			}
			/*
			 * Check if the arthrpoda is touching anoter animal and then fight
			 */

			if(isTouch(arthropoda.getPosition().getX(),arthropoda.getPosition().getY(), arthropoda)) {
				// arthropoda.getPosition().setY(arthropoda.getY()-1); //Stay one box in front of it
				// fight(arthropodaIndex, getArthropodaFromPosition(x,y));
				posList.addLast(arthropoda.getPosition());

			}
		}
		/*
		 * At 90 degrees
		 */
		else if(arthropoda.getOrientation()==90){
			x=x+distance*arthropoda.getSpeed();
			latestPos.setX(x);
			/*
			 * Check if the animal is out of range
			 */
			 if(outOfRange(latestPos)){
				arthropoda.setPosition(turnBy90(latestPos, arthropoda));
 				posList.addFirst(arthropoda.getPosition());
 			}
 			else{
 				arthropoda.setPosition(latestPos);
 				posList.addFirst(arthropoda.getPosition());
 			}
			/*
			* Check if the arthrpoda is touching anoter animal and then fight
			*/
			if(isTouch(arthropoda.getPosition().getX(),arthropoda.getPosition().getY(), arthropoda)){
				// arthropoda.getPosition().setX(arthropoda.getX()-1); //Stay one box in front of it
				// fight(arthropodaIndex, getArthropodaFromPosition(x,y));
				posList.addLast(arthropoda.getPosition());
			}
		}
		/*
		* At 180 degrees
		*/
		else if(arthropoda.getOrientation()==180){
			y=y-distance*arthropoda.getSpeed();
			latestPos.setY(y);
			/*
			* Out of rage
			*/
			if(outOfRange(latestPos)){
				arthropoda.setPosition(turnBy180(latestPos, arthropoda));
				posList.addFirst(arthropoda.getPosition());
			}
			else{
				arthropoda.setPosition(latestPos);
				posList.addFirst(arthropoda.getPosition());
			}
				/*
				* if the animal against another
				*/
			if(isTouch(arthropoda.getPosition().getX(),arthropoda.getPosition().getY(), arthropoda)){
				// arthropoda.getPosition().setY(y+1);
				// fight(arthropodaIndex, getArthropodaFromPosition(x,y));
				posList.addLast(arthropoda.getPosition());
					System.out.println("Hello");

			}
		}
		/*
		* At 270 degrees
		*/
		else if(arthropoda.getOrientation()==270){
			x=x-distance*arthropoda.getSpeed();
			latestPos.setY(x);
			/*
			* Check if the animal is out of range
			*/
			if(outOfRange(latestPos)){
				arthropoda.setPosition(turnBy270(latestPos, arthropoda));
				posList.addFirst(arthropoda.getPosition());
			}
			else{
				arthropoda.setPosition(latestPos);
				posList.addFirst(arthropoda.getPosition());
			}
				/*
				* if the animal against another arthropoda
				*/
			if(isTouch(arthropoda.getPosition().getX(),arthropoda.getPosition().getY(), arthropoda)){
				// arthropoda.getPosition().setX(x-1);
				// fight(arthropodaIndex, getArthropodaFromPosition(x,y));
				posList.addLast(arthropoda.getPosition());
			}
		}
		return posList;
	}
	/**
	 * Turns the arthrpoda left if you insert "LEFT" or somthig else right
	 * @param direction Distanz wie weit
	 *
	 * @param arthropodaIndex
	 */
	public void turnArthropoda(String direction, int arthropodaIndex){
		Arthropoda arthropoda=arthList.get(arthropodaIndex);
		arthropoda.turn(direction);
	}
	/**
	 * Returns the position of the arthrpoda with the index
	 *
	 * @param arthropodaIndex
	 * @return Position
	 */
	public Position getPositionFromArthropoda(int arthropodaIndex){
		Arthropoda arthropoda=arthList.get(arthropodaIndex);
		return arthropoda.getPosition();
	}
	/**
	 * Check if the arthrpoda is touchung another arthrpoda
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isTouch( int x, int y, Arthropoda arthropoda){
		for(Arthropoda arthropods : arthList){
			if(arthropods.getPosition().getX()==x && arthropods.getPosition().getY()==y){
				if(arthropods !=arthropoda){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Returns the arthrpoda from the position
	 * @param x
	 * @param y
	 * @return Arthropoda
	 */
	public Arthropoda getArthropodaFromPosition(int x, int y){
		Arthropoda arth=null;
		for(Arthropoda arthropodas : arthList){
			if(arthropodas.getPosition().getX()==x && arthropodas.getPosition().getY()==y){
				arth=arthropodas;
			}
		}
			return arth;
	}
	/**
	 * Fight of the arthropodas
	 * @param arthropodaIndex arthrpoda you move
	 * @param arthToFight the animal that against you
	 *
	 * @return String results
	 */
	public void fight(int arthropodaIndex, Arthropoda arthToFight){
		Arthropoda arth=arthList.get(arthropodaIndex);
		String output=null;
		boolean turn=true;
		/*
		 * Damage from the arthropda that is about to attack
		 */
		int arthrpodaDamage=rnd.nextInt(100);

		if(turn){
			arth.setLifePoints(arth.getLifePoints()-arthrpodaDamage);
			output="Your animal meets another and fight. You win and the nother is dead ";

		}
		else{
			arthList.remove(arthropodaIndex);
			output="Your animal meets another and fight. Sorry you lose. Game Over. Your animal is dead";

		}
		// return output;
	}
	private Position turnBy0(Position position, Arthropoda arthropoda){

			Position positionFrom = getPositionNew(position.getX(),position.getY());
			int rest=position.getY()-positionFrom.getY();
			arthropoda.getPosition().setY(positionFrom.getY());
			arthropoda.turn("LEFT");
			arthropoda.move(rest);
				/*
				 * if the animal is out of range again
				 */
			if(outOfRange(arthropoda.getPosition())) {
				Position positionNeu = getPositionNew(arthropoda.getPosition().getX(), arthropoda.getPosition().getY());

				int restNew=(arthropoda.getPosition().getX()-positionNeu.getX())*-1;
				arthropoda.getPosition().setX(positionNeu.getX());
				arthropoda.turn("LEFT");
				arthropoda.move(restNew);
				}
		return arthropoda.getPosition();
	}
	private Position turnBy90(Position position, Arthropoda arthropoda){

		Position positionFrom = getPositionNew(position.getX(),position.getY());
		int rest=position.getX()-positionFrom.getX();
		arthropoda.getPosition().setX(positionFrom.getX());
		arthropoda.turn("LEFT");
		arthropoda.move(rest);
		/*
		* If the animal is out auf range again
		*/
		if(outOfRange(arthropoda.getPosition())){
			Position positionNeu = getPositionNew(arthropoda.getPosition().getX(), arthropoda.getPosition().getY());

			int restNew=(arthropoda.getPosition().getY()-positionNeu.getY());
			arthropoda.getPosition().setY(positionNeu.getX());
			arthropoda.turn("LEFT");
			arthropoda.move(restNew);
		}
		return arthropoda.getPosition();
	}
	private Position turnBy180(Position position, Arthropoda arthropoda){
		/*
		* The rest is multiplied y by -1 to go further
		*/
		Position positionFrom = getPositionNew(position.getX(),position.getY());
		int rest=position.getY()*(-1);
		arthropoda.getPosition().setY(positionFrom.getY());
		arthropoda.turn("LEFT");
		arthropoda.move(rest);
		/*
		* again
		*/
		if(outOfRange(arthropoda.getPosition())) {
			Position positionNeu = getPositionNew(arthropoda.getPosition().getX(), arthropoda.getPosition().getY());

			int restNew=(arthropoda.getPosition().getX()-positionNeu.getX());
			arthropoda.getPosition().setX(positionNeu.getX());
			arthropoda.turn("LEFT");
			arthropoda.move(restNew);
		}
		return arthropoda.getPosition();
	}
	private Position turnBy270(Position position, Arthropoda arthropoda){

		Position positionFrom = getPositionNew(position.getX(),position.getY());
		int rest=position.getX()*(-1);
		arthropoda.getPosition().setX(positionFrom.getX());
		arthropoda.turn("LEFT");
		arthropoda.move(rest);
		/*
		 * if the animal is out of range again
		 */
		if(outOfRange(arthropoda.getPosition())) {
			Position positionNeu = getPositionNew(arthropoda.getPosition().getX(), arthropoda.getPosition().getY());

			int restNew=(arthropoda.getPosition().getY()-positionNeu.getY())*-1;
			arthropoda.getPosition().setY(positionNeu.getY());
			arthropoda.turn("LEFT");
			arthropoda.move(restNew);
		}
		return arthropoda.getPosition();
	}
}
