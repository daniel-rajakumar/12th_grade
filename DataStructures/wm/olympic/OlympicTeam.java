


public class OlympicTeam {

	private String country;
	private int numGold, numSilver, numBronze;

	OlympicTeam(String country, int numGold, int numSilver, int numBronze){
		this.country = country;
		this.numGold = numGold;
		this.numSilver = numSilver;
		this.numBronze = numBronze;
	}

	// getters
	public String getCountry()	 {return this.country;}
	public int 	  getNumGold()	 {return this.numGold;}
	public int 	  getNumSilver() {return this.numSilver;}
	public int 	  getNumBronze() {return this.numBronze;}

	// setters
	public void setCountry(String country)	{this.country = country;}
	public void setNumGold(int numGold)		{this.numGold = numGold;}
	public void setNumSilver(int numSilver)	{this.numSilver = numSilver;}
	public void setNumBronze(int numBronze)	{this.numBronze = numBronze;}


	public String toString(){
		return String.format("[Gold: %s, Silver: %s, Bronze: %s] %s", this.numGold, this.numSilver,
															 		  this.numBronze, this.country);
	}


	public static void main(String[] args){

		Olympics tokyo2020 = new Olympics("olympic-medals.csv");

		System.out.println(tokyo2020);

	}

}