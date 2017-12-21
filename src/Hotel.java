/*
    Hotel Room Booking System.
    
    Hotel.java
    
    Joe O'Regan
    K00203642
    
    26/10/2017
*/

import java.lang.reflect.Array;
import java.util.*;

/*
  	Construct a hotel with room numbers as specified in roomNums. 
  	The rooms are initially unbooked. 
 */
public class Hotel {
	
	//Lec8 S43 public static Map SynchronizedMap<String,Booking>();
	Map<String,Booking> map = new HashMap<String,Booking>();										// Create a map
	// Tutorialspoint
	Map<String,Booking> bookingList = Collections.synchronizedMap(map);								// Create a synchronised map of bookings

	//System.out.println("Synchronized map is :"+bookingList);
	
	// Check the booking ref exists
	public boolean checkBookingOnList(String ref) {
		return bookingList.containsKey(ref);
	}
	
	
	public Room[] rooms;	// Array of rooms
	
	/* 	]Constructor
	 	Constructs a hotel with room numbers as specified in roomNums
	 */
	public Hotel(int[] roomNums) {
		rooms = new Room[Array.getLength(roomNums)];												// Create rooms list for the hotel
		
		for(int i = 0; i < Array.getLength(roomNums); i++ ) {
			rooms[i] = new Room(roomNums[i]);
			//System.out.println("Room number " + roomNums[i] + " added to hotel.");
			//System.out.println("Room number " + rooms[i].roomNum + " in hotel rooms array.");
		}
		
		// Create booking???
	}
	
	String showRooms(String name) {
		//String hotelRooms = "Hotel " + name + " contains " + Array.getLength(rooms) + " room(s): ";
		String hotelRooms = "";
		
    	System.out.println("Hotel "+name+" contains " + Array.getLength(rooms) + " room(s): ");
    	
		for (int i = 0; i < Array.getLength(rooms); i++) {
			hotelRooms += "\nRoom " + rooms[i].roomNum;
			System.out.println("Room " + rooms[i].roomNum);
		}
		
		return hotelRooms;
	}
		
	/* 
	 * returns true if the room roomNum is booked on any days
	 * specified in days, otherwise return false
	*/
	public synchronized boolean roomBooked(int[] days, int roomNum) {
		System.out.println("roomBooked())\n");
		boolean booked = false;

    	for (int i = 0; i < Array.getLength(rooms); i++) 													// Room
    		for (int j = 0; j < 30; j++)																	// Day
    			for (int k = 0; k < Array.getLength(days); k++)												// Booking day
	    			if (rooms[i].GetBooked(j) ) {
	    				if (j == days[k]) System.out.println("roomBooked(): Room " + rooms[i].roomNum + " is booked already on day " + j);
	    				booked = true;
	    			}
	    			else if (rooms[i].roomNum == roomNum && j == days[k]) System.out.println("roomBooked(): Room " + rooms[i].roomNum + " is available on day " + j);
    			//else System.out.println("Room " + rooms[i].roomNum + " is free on day " + j);
		
		if (booked) System.out.println("The selected day(s) cannot be booked."); 
		
		return booked;																						// booked = false = room available
	}
		
	boolean roomExistsInHotel(int roomNum) {
		boolean roomExists = false;
		
		// check valid room number
		for (int i = 0; i < Array.getLength(rooms); i++) {
			if (roomNum == rooms[i].roomNum) {	// If the room number entered matches a hotel room number
				roomExists = true;				// Valid room number for the hotel
				break;
			}
		}
		
		if (!roomExists) System.out.println("Sorry, room " + roomNum + " is not a valid room number");

		return roomExists;
	}
		
	/* Create a booking with reference bookingRef for the 
	 * room roomNum for each of the days specified in days
	 * Returns true if it is possible to book the room on the given days, 
	 * otherwise (if the room is booked on any of the specified days) return false.
	*/
	public synchronized boolean bookRoom(String bookingRef, int[] days, int roomNum) {
		if(checkBookingOnList(bookingRef)) {
			System.out.println("Booking Ref: " + bookingRef + " is already in our system\nPlease try another reference number for your records");
			return false;
		}

		if ((!roomExistsInHotel(roomNum)) || roomBooked(days, roomNum)) return false;						// If the room doesn't exist or, isn't available, not possible to book
				
		//for (int i = 0; i < Array.getLength(days); i++) {	 }	
    	
		for (int j = 0; j < Array.getLength(rooms); j++) {
			if (rooms[j].roomNum == roomNum) {																// Find the room in the hotels list of rooms
				for (int i = 0; i < Array.getLength(days); i++) {											// For each day that needs to be booked										
					rooms[j].SetBooked(days[i]);															// Set the room as booked
					if(rooms[j].GetBooked(days[i])) System.out.println("bookRoom(): Room " + rooms[j].roomNum + " has been booked for day " + days[i]);
				}
			}
		}			
		
		System.out.print("bookRoom(): Room number " + roomNum + " has been booked for day(s): ");
		
		for (int i = 0; i < Array.getLength(days); i++) {
			System.out.print(days[i]);
			if (i < Array.getLength(days) - 1) System.out.print(", ");
			if (i == Array.getLength(days) - 1) System.out.print("\n");
		}
		
		Booking newBooking = new Booking(bookingRef,days,roomNum);											// Create a booking

		//bookingList.put(bookingRef, Booking(bookingRef,days,roomNum));
		bookingList.put(bookingRef, newBooking);															// Add the booking to the synchronised map

		//System.out.println("Your booking reference is: " + bookingRef);
		System.out.println("\nbookRoom():\n" + newBooking);
		
		return true;
	}
	
	/* Updates the booking with reference bookingRef so that it now 
	 * refers to the specified roomNum for each of the days specified in days. 
	 * Returns true if it is possible to update the booking  (i.e., 
	 * the new booking does not clash with an existing booking), 
	 * otherwise returns false and leaves the original booking unchanged. 
	 * If there is no booking with the specified reference throws NoSuchBookingException.
	 */
	boolean updateBooking(String bookingRef, int[] days, int roomNum) throws NoSuchBookingException {
		//if booking doesn't exist throw NoSuchBookingException(bookingRef);
		// for each bookingref)
		// if bookingref isnt in the list of Booking References
		
		//			throw new NoSuchBookingException("This booking reference doesn't exist: " + bookingRef);
				
		return false;
	}
		
	/* cancels the booking with reference bookingRef. The room booked under this 
	 * booking reference becomes unbooked for the days of the booking. 
	 * If there is no booking with the specified reference throws NoSuchBookingException
	*/
	void cancelBooking(String bookingRef) throws NoSuchBookingException {
		// EXTENDED SPECIFICATION
	}
	
    public static void main(String [] args){
    	System.out.println("Concurrent Algorithms Assignment 1\nHotel Booking\n");

    	//int[] hotel1Rooms = {1,2,3}; 
    	int[] hotel2Rooms = {2,4,6,8}; 

    	//System.out.println("Hotel 1:\n\n");
    	//Hotel hotel1 = new Hotel(hotel1Rooms);
    	Hotel hotel2 = new Hotel(hotel2Rooms);    

    	//hotel1.showRooms();

    	//System.out.println("Hotel 1:\n\n");
    	
    	hotel2.showRooms("2");   	
    	
    	//boolean bookRoom(String bookingRef, int[] days, int roomNum)
    	//System.out.println("\nBooking 1:");
    	//int[] daystobook = {1,2,4};
    	//hotel1.bookRoom("Ref1", daystobook, 1);
    	//hotel1.bookRoom("Ref1b", daystobook, 4);									// Check for valid room number
    	

    	System.out.println("\nBooking 2:");
    	int[] daystobook2 = {3,4,5,6};
    	hotel2.bookRoom("Ref2", daystobook2, 2);									// Try booking room 2 for days 3,4,5,6 - Should work
    	
    	// Check for unique booking references
    	System.out.println("Test: unique booking entered");
    	hotel2.bookRoom("Ref2", daystobook2, 2);									// Try booking room 2 for days 3,4,5,6 - Should work

    	// Check a booking exists
    	if(hotel2.checkBookingOnList("Ref2")) System.out.println("Check Booking: yes for Ref2");
    	if(hotel2.checkBookingOnList("Refx")) 
    		System.out.println("Check Booking: Yes for Refx");
    	else
    		System.out.println("Check Booking: No for Refx");
    	
    	for (int i = 0; i < Array.getLength(hotel2.rooms); i++) 					// show the 
    		for (int j = 0; j < 30; j++)											// check the day
    			if (hotel2.rooms[i].GetBooked(j)) 
    				System.out.println("Main: Room " + hotel2.rooms[i].roomNum + " is booked on day " + j);
    	
    	System.out.println("\nBooking 3:");
    	int[] daystobook3 = {1,3,5,7};												// Try booking room 2 for days 3,5,7 - Should fail
    	hotel2.bookRoom("Ref3",  daystobook3,  2);
    	
    	
    	for (int i = 0; i < Array.getLength(hotel2.rooms); i++) 					// show the 
    		for (int j = 0; j < 30; j++)											// check the day
    			if (hotel2.rooms[i].GetBooked(j)) 
    				System.out.println("Main: Room " + hotel2.rooms[i].roomNum + " is booked on day " + j);   	
    	
    	
    	
    }
}
