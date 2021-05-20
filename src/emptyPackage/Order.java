package emptyPackage;
/*
 * Dayne Farris
 * CPS 220 - Fast Food Project
 * 04/06/2021
 */
public class Order {
	//variable declarations
	String[] foodItems = new String[100];
	double totalCost;
	
	int numOfFood;
	int chickenCounter = 0;
	int beefCounter = 0;
	int fishCounter = 0;	
	int smallCounter = 0;
	int mediumCounter = 0;
	int largeCounter = 0;		
	int coffeeCounter = 0;
	int sodaCounter = 0;
	int juiceCounter = 0;
	
	boolean isCancelled;
	
	//addItem takes in userIn string and price of selected item for processing.
	public void addItem(String foodName, double price) {
		
		foodItems[numOfFood] = foodName;
		numOfFood++;
		totalCost += price;
		
		//creating counters for every possible instance of what the user may enter in their entire order.
		if (foodName.equals("chicken burger")) {
			chickenCounter++;
		} //end if
		if (foodName.equals("beef burger")) {
			beefCounter++;
		} //end if
		if (foodName.equals("fish burger")) {
			fishCounter++;
		} //end if
		if (foodName.equals("small fry")) {
			smallCounter++;
		} //end if
		if (foodName.equals("medium fry")) {
			mediumCounter++;
		} //end if
		if (foodName.equals("large fry")) {
			largeCounter++;
		} //end if
		if (foodName.equals("coffee")) {
			coffeeCounter++;
		} //end if
		if (foodName.equals("soda")) {
			sodaCounter++;
		} //end if
		if (foodName.equals("juice")) {
			juiceCounter++;
		} //end if
		
	} //end addItem

	//if order is cancelled throughout program, call setCancel.
	public void setCancel() {
		isCancelled = true;
	} //end setCancel
} //end Order
