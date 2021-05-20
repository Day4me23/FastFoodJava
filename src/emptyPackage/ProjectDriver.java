package emptyPackage;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat; 
/*
 * Dayne Farris
 * CPS 220 - Fast Food Project
 * 04/06/2021
 */
public class ProjectDriver {
	//constant declarations
	final static double chickenBurger = 1.29;
	final static double beefBurger = 1;
	final static double fishBurger = 3.79;

	final static double largeFry = 1.89;
	final static double mediumFry = 1.79;
	final static double smallFry = 1.39;

	final static double coffee = 1.29;
	final static double soda = 1;
	final static double juice = 1.59;

	public static void main(String[] args) {
		//object declaration
		Scanner sc = new Scanner(System.in);
		Order[] totalOrder = new Order[1000];
		DecimalFormat df = new DecimalFormat("0.00");
		//variable declaration
		int numOrders = 0;
		int cancelCounter = 0;
		int chickenCounter = 0;
		int beefCounter = 0;
		int fishCounter = 0;
		int smallCounter = 0;
		int mediumCounter = 0;
		int largeCounter = 0;
		int coffeeCounter = 0;
		int sodaCounter = 0;
		int juiceCounter = 0;

		double finalTotal = 0.0;
		double userCash = 0.0;
		boolean temp = false;
		boolean breakLoop = true;
		
		String input;
		String userIn;
		String test;
		
		//while loop which incases the entire ordering process. The only way to exit this while is when 
		//the final order is declared at the end of each order process.
		while(breakLoop == true) {
			Order order = new Order();
			//first order counter begins
			numOrders++;
			//prints the available food menu for the user to display.
			PrintMenu();

			//do while loop which takes the user individual orders based on switch statement. Once an item
			//is ordered, it is then sent to order class for counting. Exit do while when q is entered by user.
			do {
				input = sc.nextLine();
				input = input.toLowerCase();
				
				//switch covering all available items/validation for user to enter.
				switch (input) {

				case "chicken burger":  		            	
					order.addItem("chicken burger", chickenBurger);
					break;

				case "beef burger": 		            	
					order.addItem("beef burger", beefBurger);
					break;

				case "fish burger":  
					order.addItem("fish burger", fishBurger);
					break;

				case "small fry":  
					order.addItem("small fry", smallFry);
					break;

				case "medium fry":  
					order.addItem("medium fry", mediumFry);
					break;

				case "large fry":  
					order.addItem("large fry", largeFry);
					break;

				case "coffee":  
					order.addItem("coffee", coffee);
					break;

				case "soda":  
					order.addItem("soda", soda);
					break;

				case "juice":  
					order.addItem("juice", juice);
					break;

				case "q":
					temp = true;
					break;

				case "":
					break;
					//if unknown string is entered, default this statement for user clarity.
				default: System.out.println("Invalid item entered. Please enter a valid item. (E.G Beef Burger, Soda, Q To Submit Order)");
				break;
				} //end switch
			} while(temp == false); //end do while loop
			//set temp back to false once exiting the do while for whenever the user enters another order.
			temp = false;
			
			//printing of the order summary through instanced counters from order class.
			System.out.println("Order Summary: ");
			//printing the amount of items ordered based on the individual order listed above.
			if (order.chickenCounter > 0 ) {
				System.out.println("Chicken Burgers: " + order.chickenCounter + " -- $" + order.chickenCounter * chickenBurger);
			} //end if

			if (order.beefCounter > 0 ) {
				System.out.println("Beef Burgers: " + order.beefCounter + " -- $" + order.beefCounter * beefBurger);
			} //end if

			if (order.fishCounter > 0 ) {
				System.out.println("Fish Burgers: " + order.fishCounter + " -- $" + order.fishCounter * fishBurger);
			} //end if


			if (order.smallCounter > 0 ) {
				System.out.println("Small Fries: " + order.smallCounter + " -- $" + order.smallCounter * smallFry);
			} //end if

			if (order.mediumCounter > 0 ) {
				System.out.println("Medium Fries: " + order.mediumCounter + " -- $" + order.mediumCounter * mediumFry);
			} //end if

			if (order.largeCounter > 0 ) {
				System.out.println("Large Fries: " + order.largeCounter + " -- $" + order.largeCounter * largeFry);
			} //end if

			if (order.coffeeCounter > 0 ) {
				System.out.println("Coffee: " + order.coffeeCounter + " -- $" + order.coffeeCounter * coffee);
			} //end if

			if (order.sodaCounter > 0 ) {
				System.out.println("Sodas: " + order.sodaCounter + " -- $" + order.sodaCounter * soda);
			} //end if

			if (order.juiceCounter > 0 ) {
				System.out.println("Juice: " + order.juiceCounter + " -- $" + order.juiceCounter * juice);
			} //end if
			
			System.out.println("Total cost of order above: $" + df.format(order.totalCost) + "\n");
			
			//do while validation checking of if the user wishes to modify their order.
			do {
				System.out.println("Would you like to modify your order? (Y-N)");
				userIn = sc.nextLine();
				userIn = userIn.toLowerCase();
			} while (!((userIn.equals("y")) || (userIn.equals("n"))));
			if (userIn.equals("y")) {
				//if yes, cancel the order and add an order to totalOrder as well as adding to cancelCounter.
				order.setCancel();
				totalOrder[numOrders - 1]  = order;
				cancelCounter++;
			} //end if 

			//if no, proceed to checkout.
			else {
				System.out.println("Proceeding to checkout! \n");
				//do while validation checking for user payment of cash or credit.
				do {
					System.out.println("Welcome to checkout. Your total today is: $" + df.format(order.totalCost) + " Will that be cash or credit?");
					userIn = sc.next();
					userIn = userIn.toLowerCase();
				} while (!((userIn.equals("cash")) || (userIn.equals("credit"))));
				if (userIn.equals("cash")) {
					//while loop validation checking for userCash to be a double value.
					while (true) {
					    try {
					    	System.out.println("Please enter the amount of cash you are going to pay with.");
							userCash = sc.nextDouble();
							
					        break;
					    } catch (InputMismatchException e) {
					        //give the user an error message
					        System.out.println("Invalid data entered, expecting a positive double. ");
					        sc.next();
					       
					    } //end catch
					} //end while
					
					
					//checking user cash entered compared to totalCost of user specific order.
					if (userCash < order.totalCost) {
						//if userCash is less than totalCost cancel order, add order to totalOrder, and add to cancelCounter.
						System.out.println("The total bill was $" + df.format(order.totalCost) + " and you only gave $" + userCash);
						System.out.println("Insufficient funds recieved. Cancelling order.");
						order.setCancel();
						totalOrder[numOrders - 1]  = order;
						cancelCounter++;
					} //end if
					
					else if (userCash >= order.totalCost) {
						//if userCash is covering the totalCost then complete an order for totalOrder, and give change.
						double userChange = userCash - order.totalCost;

						System.out.println("The total bill was $" + df.format(order.totalCost) + " and you gave $" + userCash);
						System.out.println("Thank you, your change is $" + df.format(userChange) + "\n");
						totalOrder[numOrders - 1]  = order;
					} //end else if
				} //end if
				else if (userIn.equals("credit")) {
					//if credit complete order and add to totalOrder.
					System.out.println("The total bill was $" + df.format(order.totalCost) + " and you have paid $" + df.format(order.totalCost));
					System.out.println("Payment recieved, Thank you.");
					totalOrder[numOrders - 1]  = order;
				} //end else if
			} //end else

			//230 makes code unreachable when user decides to modify their order on line 165.
			if (!(userIn.equals("y"))) {
				//do while validation checking user input of final order.
				do {
					System.out.println("Was that the final order of the day? (Y-N)");
					test = sc.next();
					test = test.toLowerCase();
				} while (!((test.equals("y")) || (test.equals("n"))));
				if (test.equals("y")) {
					//if this was the final order, make total counters, and print to file/console all info.
					for (int i = 0; i < numOrders; i++) {
						//if order doesn't get cancelled increment counters.
						if (totalOrder[i].isCancelled == false) {
							//create a new instance of counters to increment by totalOrder information. 
							finalTotal += totalOrder[i].totalCost;
							beefCounter += totalOrder[i].beefCounter;
							chickenCounter += totalOrder[i].chickenCounter;
							fishCounter += totalOrder[i].fishCounter;

							smallCounter += totalOrder[i].smallCounter;
							mediumCounter += totalOrder[i].mediumCounter;
							largeCounter += totalOrder[i].largeCounter;

							coffeeCounter += totalOrder[i].coffeeCounter;
							sodaCounter += totalOrder[i].sodaCounter;
							juiceCounter += totalOrder[i].juiceCounter;
						} //end if
					} //end for
					
					//try catch of writing all the information from for loop above to a file named filename.txt.
					try {
						FileWriter myWriter = new FileWriter("filename.txt", false);
						myWriter.write("Total Orders: " + numOrders);
						myWriter.write("\nProcessed Orders: " + (numOrders - cancelCounter));
						myWriter.write("\nCancelled Orders: " + cancelCounter);
						myWriter.write("\nTotal Amount Earned: " + finalTotal);
						myWriter.write("\nTotal Items Ordered: ");
						myWriter.write("\nBeef Burgers: " + beefCounter + " \nChicken Burgers: " + chickenCounter + " \nFish Burger: " + fishCounter
								+ " \nSmall Fries: " + smallCounter + " \nMedium Fries: " + mediumCounter + " \nLarge Fries: " + largeCounter
								+ " \nCoffee: " + coffeeCounter + " \nSoda's: " + sodaCounter + " \nJuice: " + juiceCounter);
						myWriter.close();
						//after printing to file, then print to console.
						System.out.println("Total Orders: " + numOrders);
						System.out.println("Processed Orders: " + (numOrders - cancelCounter));
						System.out.println("Cancelled Orders: " + cancelCounter);

						System.out.println("Total Amount Earned: " + finalTotal);
						System.out.println("Total Items Ordered: ");
						System.out.println("Beef Burgers: " + beefCounter + " \nChicken Burgers: " + chickenCounter + " \nFish Burger: " + fishCounter
								+ " \nSmall Fries: " + smallCounter + " \nMedium Fries: " + mediumCounter + " \nLarge Fries: " + largeCounter
								+ " \nCoffee: " + coffeeCounter + " \nSoda's: " + sodaCounter + " \nJuice: " + juiceCounter);
					} catch (IOException e) { //end try
						System.out.println("An error occurred.");
						e.printStackTrace();
					} //end catch
					//exits the while in main that contains the ordering process.
					breakLoop = false;
				} //end if
			} //end if
		} //end while loop
	} //end main

	static void PrintMenu() {
		//Beginning of the scope of the program by printing out the menu options with prices
		System.out.println("Welcome to my fast food drive through! What would you like to order today? (E.G Beef Burger, Soda, Q To Submit Order)\n");
		System.out.println("Burgers:\nChicken Burger: $" + chickenBurger + "\n" + "Beef Burger: $" + beefBurger + 
				"\n" + "Fish Burger: $" + fishBurger + "\n");

		System.out.println("French Fries:\nSmall Fry: $" + smallFry + "\n" + "Medium Fry: $" + mediumFry + 
				"\n" + "Large Fry: $" + largeFry + "\n");

		System.out.println("Drinks:\nCoffee: $" + coffee + "\n" + "Soda: $" + soda + 
				"\n" + "Juice: $" + juice + "\n");

	} //end PrintMenu
} //end ProjectDriver
