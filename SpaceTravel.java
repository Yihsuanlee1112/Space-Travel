import java.text.*;
import java.util.*;
public class SpaceTravel {
	public static final double ROCKET_SPEED_IN_KM_PER_S = 4.0;
	private static Planet choosePlanet(Scanner scan)
	{
		String choose;
		Planet[] PlanetArray = Planet.values();
		for (int i=0; i<PlanetArray.length;i++) {
			System.out.println(PlanetArray[i].getFrenchName()+ " ");
		}//list the planets
		
		do
		{
			System.out.println("Please choose a planet:");
			choose = scan.next();
			for(int j=0; j<PlanetArray.length; j++) {
					if (!Character.isUpperCase(PlanetArray.length)) {
						if(choose.startsWith(PlanetArray[j].getFrenchName().substring(0,3))) {
							//a=false;
							return PlanetArray[j];
						}
					}//allow the user to input first three letters according to the planet(in english or in french)
			}System.out.println("Please enter the planets in the list.\nAnd the first letter as upper case and lower case for the rest.");
		 }while(choose !=null);//check if the user entered the correct information
	     return PlanetArray[0];
	}// return the planet the user input
	
	private static Date chooseDate(Scanner scan, Calendar calendar) 
	{//get from the keyboard a specific date (year, month, day, hour, minute, second)
		Date myDate = calendar.getTime();
		int year,month,day,hour,minute,second;
		System.out.println("Year: ");
		year= scan.nextInt();
		System.out.println("Month: ");
		month= scan.nextInt();
		System.out.println("Day: ");
		day= scan.nextInt();
		System.out.println("Hour: ");
		hour= scan.nextInt();
		System.out.println("Minute: ");
		minute= scan.nextInt();
		System.out.println("Second: ");
		second= scan.nextInt();
		
		calendar.set(year, month-1, day, hour, minute, second);
		myDate = calendar.getTime();
		return myDate;	
	}//return the corresponding Date object
	
	public static void main(String[] args)
	{
		int TravelTime=0;
		boolean verification =false;
		//to check if the user has done case d 
		Calendar myCalendar = Calendar.getInstance();//get calendar 'now'
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");// you can adjust the Date and Time format as you want
		System.out.println("Welcome to the SpaceTravel agency!!");
		Scanner scan = new Scanner(System.in);
		String choice;
		
		do{
		System.out.println("What do you want to do? [h for help]");
		choice = scan.next();
		switch(choice) {
			case "h" :
				System.out.println("l: list the planets");
				System.out.println("h: print this help screen");
				System.out.println("t: your departure time");
				System.out.println("d: choose  an origin planet and a destination planet");
				System.out.println("a: check if departure planet is already setted.");
				System.out.println("q: quit the program");
				break;
			case "q":
				System.out.print("Bye Bye");
				System.exit(0);
				break;
			case "l":
				for (Planet p:Planet.values()) {//Planet.values() return an array with all the enum Planet
					System.out.println(p);
				}
				break;
			case "d":
				Scanner Origin = new Scanner(System.in);
				Scanner Destination = new Scanner(System.in);
				//scan the user's input
				System.out.println("What is your departure planet?");
				Planet planetOrigin = choosePlanet(Origin);//call choosePlanet()
				System.out.println("Departure planet set to: " + planetOrigin.getFrenchName());
				
				System.out.println("What is your arrival planet?");
				Planet planetDestination = choosePlanet(Destination);//call choosePlanet()
				System.out.println("Arrival planet set to: " + planetDestination.getFrenchName());
				
				System.out.println("The distance between " +planetOrigin.getFrenchName() +"and " + planetDestination.getFrenchName() + " is " + planetOrigin.distanceInUATo(planetDestination));
				System.out.println("It is equivalent to " + (planetOrigin.distanceInKMTo(planetDestination)/1000000)+ " million of Km!" );
				System.out.println("At the speed of light, you will need " + (planetOrigin.travelTimeInSTo(planetDestination))/60 + " minutes" );
				System.out.println("But with our current technology it's more " + TravelTime/60/60/24/30 +" months");
				
				TravelTime = (int) Math.round(planetOrigin.travelTimeInSTo(planetDestination, ROCKET_SPEED_IN_KM_PER_S));//count travel time
				verification =true;//check if the user finishes case d
				break;
			case "t":
				
				if (verification == false) {
					System.out.print("Please choose first your planet with key 'd' ");
					break;
				}//check if the user has done case d
				else {
					System.out.println("Departure time:");
					System.out.println("When do you want to leave?");
					Scanner DepartureDay = new Scanner(System.in);
					Date DepartureTime = chooseDate(DepartureDay,myCalendar);
					System.out.println("\nRocket departure time: " + dateFormatter.format(DepartureTime));
					myCalendar.add(Calendar.SECOND,TravelTime);//add travel time according to the format I want 
					Date ArrivalTime = myCalendar.getTime();// extract the date from the calendar
					System.out.println("Rocket arrival time: "+dateFormatter.format(ArrivalTime));
					break;
				}
			case "a":
				if (verification == false) {
					System.out.println("Please Press 'd' to set your departure and arrival planet first.");
				}
				System.out.println("When do you want to arrive?");
				Scanner ArrivalDay = new Scanner(System.in);
				Date ArrivalDate = chooseDate(ArrivalDay, myCalendar);
				System.out.println("Rocket arrival time:" + dateFormatter.format(ArrivalDate));
				myCalendar.add(Calendar.SECOND,-TravelTime);//minus travel time according to the format I want 
				Date DepartureDate = myCalendar.getTime();// extract the date from the calendar
				System.out.println("Rocket departure time: "+dateFormatter.format(DepartureDate));
				break;
			default:
				System.out.println("Unknown command. Type h for help");
				break;	
			}
		}while(choice!="z");
	}//end of main
}//end of SpaceTravel
	

