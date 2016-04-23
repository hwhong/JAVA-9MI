import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Random;


public class SimpleWar {

  private static final int NUM_SUITS = 13;
  private static final int NUM_CARDS = 52;
    
  private static int counter = 0; 
  private static CounterStat values = new CounterStat(0,0,0);
  
  private static ArrayList<String> stringList = new ArrayList<String>(); 
  
  
  public static void main( String[] args )
	{
		int[] numberOfUsedKinds = new int[NUM_SUITS];  //Use it to count the number of used KINDS
		String[] usedCards = new String[NUM_CARDS]; //Use it to count used cards


		Scanner input=new Scanner(System.in); //Scanner object that takes an input from a user

		String[] suits = {"Hearts","Diamonds","Spades","Clubs"}; //0,1,2,3
		String[] kinds = {"Two","Three","Four","Five", "Six", //0, 1, 2, 3, 4
		"Seven", "Eight", "Nine", "Ten", "Jack",  // 5,6,7,8,9
		"Queen", "King", "Ace"}; //10, 11, 12 

		//stores computer moves
		LinkedList<String> computerMoves = new LinkedList<String>();

		//stores users moves
		LinkedList<String> userMoves = new LinkedList<String>();

		int suitIndex, kindIndex; //suit and kind chosen randomly by a computer
		
		int value = 0;
		
		while (value != -1 ) { //check if the user wants to end or stop game
			suitIndex = (int) Math.floor(Math.random()*4);
			kindIndex = (int) Math.floor(Math.random()*13);
			
			String tempString = kinds[kindIndex] + suits[suitIndex];
			
			while (stringList.contains(tempString)) {
				suitIndex = (int) Math.floor(Math.random()*4);//if the suit appeared more than 4 times, regenerate (computer)
				kindIndex = (int) Math.floor(Math.random()*13);
				tempString = kinds[kindIndex] + suits[suitIndex];
			}
			
			stringList.add(tempString);
			
			computerMoves.add(kinds[kindIndex]+" of "+suits[suitIndex]); //add the computer's move to linked list 
			
			System.out.println("My Card is: " + computerMoves.get(counter)); //prints out the computer's move 
			counter++;  //increment so that the next string is added into the next block 
			
			
			System.out.print("What is your card (kind)? (2-14, -1 to finish the game):");
			value = input.nextInt(); //gets the user input
			
			if (value == -1) {  //checks if the user wants to quit 
				break;
			}
					
			int suitIndexu = (int) Math.floor(Math.random()*4); //get a random suit for the user 
			String tempStringUser = kinds[value-2] + suits[suitIndexu];
			
			while (stringList.contains(tempStringUser)) {	
				String test1 = kinds[value-2] + suits[0];
				String test2 = kinds[value-2] + suits[1];
				String test3 = kinds[value-2] + suits[2];
				String test4 = kinds[value-2] + suits[3];
				
				if(stringList.contains(test1)&&stringList.contains(test2)&&stringList.contains(test3)&&stringList.contains(test4)) { 
					System.out.print("All cards of this type have been played. Pick another one");
					value = input.nextInt();
					tempStringUser = kinds[value-2] + suits[suitIndexu];
				}
				else {
					if (!(stringList.contains(test1))) { 
						tempStringUser = kinds[value-2]+suits[0];
						suitIndexu = 0;
					}
					else if (!(stringList.contains(test2))) {
						tempStringUser = kinds[value-2]+suits[1];
						suitIndexu = 1;
					}
					else if (!(stringList.contains(test3))) {
						tempStringUser = kinds[value-2]+suits[2];
						suitIndexu = 2;
					}
					else { 
						tempStringUser = kinds[value-2]+suits[3];
						suitIndexu = 3;
					}
				}
			}
			stringList.add(tempStringUser); //add the user's card to the library of cards 
			
			userMoves.add(kinds[value-2]+ " of "+suits[suitIndexu]);  //add the user's move to the linkedlist 
			System.out.println(kinds[value-2]+" of "+suits[suitIndexu]); //prints out the user's move 
			
	
			if (kindIndex+2 > value){
				System.out.println("I won");
				values.incrementComputerWins();
			}
			else if (kindIndex+2 < value) {
				System.out.println("You won");
				values.incrementUserWins();
			}
			else {
				System.out.println("A tie");
				values.incrementTies();
			}	
			System.out.println();
			if (stringList.size()==52) { 
				values.reset();
				value = -1; 
			}
				
		}
		
		
		if(value == -1) {	
			values.reset();
		    System.out.print("Player Won:"+ values.averageGames(0)+"%");  
		    System.out.print("     ");
			System.out.print("Computer Won:"+ values.averageGames(1)+"%");
			System.out.print("     ");
			System.out.print("Tied:"+ values.averageGames(2)+"%");
			System.out.println();
			
			for (int i = 0; i < userMoves.size(); i++){ 
				System.out.println("My Move:"+ computerMoves.get(i)+" "+"Your Move:"+ userMoves.get(i));
			}
			System.out.print("Play again?");
			String c = input.next(); 
			if (c.equalsIgnoreCase("n")) {
				System.out.println("Bye, see you later!");
			}	
		}
	}
}
