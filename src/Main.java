import  java.util.LinkedList;
public class Main {

	public static void main(String[] args) {
        World world = new World();

        Arthropoda arth = world.getArthropoda(0);
        System.out.println(arth.getPosition().toString());
        System.out.println("--------------------------------");
        System.out.println(arth.getOrientation());
        arth.turn("Right");
        System.out.println("--------------------------------");
        System.out.println(arth.getOrientation());
        LinkedList<Position> list=world.moveArthropoda(40, 0);
        if(list.getLast() !=null){
            System.out.println("--------------------------------5");
        }
        System.out.println("--------------------------------");
        System.out.println(arth.getPosition().toString());



    }
}
