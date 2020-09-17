//Enumerations serve the purpose of representing a group of named constants
public enum Planet { 
	MERCURY(0.387,"Mercure"), VENUS(0.722,"Venus"), EARTH(1.0,"Terre"), MARS(1.52,"Mars"), JUPITER(5.2,"Jupiter"), SATURN(9.58,"Saturne"), URANUS(19.2,"Uranus"), NEPTUNE(30.1,"Neptune");
	static final double UA_IN_KM = 149597871.0;
	static final double LIGHT_SPEED_IN_KM_PER_S = 299792.458;
	private double Distance;
	private String FrenchName;
	private Planet(double Distance,String FrenchName) {
		this.Distance = Distance;
		this.FrenchName = FrenchName;
	}
	public String getFrenchName() {
		return FrenchName;
	}//return FrenchName
	public double getDistanceFromSunInAstronomicalUnit() {	
		return Distance;
	}//return Distance
	
	public String toString(){
		return (FrenchName +" (" + Distance + " UA)");
	}//returns the current string without any changes
	public double distanceInUATo(Planet otherPlanet) {
		return Math.abs(Distance - otherPlanet.Distance);
	}//return absolute value between two different planets in UA
	public double distanceInKMTo(Planet otherPlanet) {
		return Math.abs((Distance - otherPlanet.Distance)*UA_IN_KM);	
	}//modify UA into KM(absolute value)
	public double travelTimeInSTo(Planet otherPlanet) {//return travel time in seconds with light speed
		return Math.abs((Distance - otherPlanet.Distance)*UA_IN_KM/LIGHT_SPEED_IN_KM_PER_S);
	}//count the travel time in seconds between two different planets in light speed(absolute value)
	public double travelTimeInSTo(Planet otherPlanet, double speedInKmPerS) {
		return Math.abs((Distance - otherPlanet.Distance)*UA_IN_KM/speedInKmPerS);	
	}//returns the travel time according to the given speed (km/s)

}
	
	
