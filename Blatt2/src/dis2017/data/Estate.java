package dis2017.data;

public class Estate
{
	int _id;
	String _city;
	int _postalCode;
	String _street;
	String _streetNumber;
	int _squareArea;
	String _estateAgent;
	
	public Estate() {
		
	}
	
	public Estate( int id, String city, int postalCode,
			String street, String streetNumber, int squareArea, String estateAgent ) {
			this._id = id;
			this._city = city;
			this._postalCode = postalCode;
			this._street = street;
			this._streetNumber = streetNumber;
			this._squareArea = squareArea;
			this._estateAgent = estateAgent;
	}
	
	public Estate( String city, int postalCode,
					String street, String streetNumber, int squareArea) {
		this._city = city; 
		this._postalCode = postalCode;
		this._street = street;
		this._streetNumber = streetNumber;
		this._squareArea = squareArea;
	}
	
	public int getID(){
		return this._id;
	}
	
	public String getCity() {
		return this._city;
	}
	
	public int getPostalCode() {
		return this._postalCode;
	}
	
	public String getStreet() {
		return this._street;
	}
	
	public String getStreetNumber() {
		return this._streetNumber;
	}
	
	public int getSquareArea() {
		return this._squareArea;
	}
	
	public String getEstateAgent() {
		return this._estateAgent;
	}
	
}
