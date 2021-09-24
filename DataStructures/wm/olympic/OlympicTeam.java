import java.io.*;
import java.util.*;
import java.math.*;

public class OlympicTeam {

	private String country;
	private int numGold, numSilver, numBronze, sum;

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
		//System.out.println(new Olympics("olympic-medals.csv"));
		Olympics olympics = new Olympics("olympic-medals.csv");
		System.out.println(olympics);
		System.out.println(olympics.getSumOfCountry());


	}

}