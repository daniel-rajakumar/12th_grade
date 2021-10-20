import java.util.*;

public class OlympicTeam implements Comparable<OlympicTeam>{
	private String country;
	private int numGold;
	private int numSilver;
	private int numBronze;

	public OlympicTeam(String countryName, int gold, int silver, int bronze){
		country = countryName;
		numGold = gold;
		numSilver = silver;
		numBronze = bronze;
	}

	public String getCountry() { return country; }

	public int getNumGold() { return numGold; }

	public int getNumSilver() { return numSilver; }

	public int getNumBronze() { return numBronze; }

	public String toString(){
		return country + " => G = "+numGold+", S = "+numSilver+", B = "+numBronze;
	}

  /**
   * true if both team as same amount of every type of medals, else false;
   * @param other
   * @return result of comparison
   */
  public boolean equals(OlympicTeam other){
      if (this.getCountry() == other.getCountry())
        if (this.getNumGold() == other.getNumGold())
            if (this.getNumSilver() == other.getNumSilver())
              if (this.getNumBronze() == other.getNumBronze())
                return true;
    
        return false;
  }

  // sum of all type of medals (silver + gold + bronze)
  public int getTotalMedals(){
    int sum = 0;
    if (numGold > 0 )
      sum += numGold;
    if (numSilver > 0)
      sum += numSilver;
    if (numBronze > 0)
      sum += numBronze;
    return sum;
  }

  // sum of the least two type of medal. example: 2 gold, 6 silver, 4 bronze will give some of 2 + 4  -- the smallest is gold (2) and bronze (4)
  public int sumLeastTwo(){
    int sum = 0;

    if (numGold > numBronze && numGold > numSilver)
      return numBronze + numSilver;

    if (numSilver > numBronze && numSilver > numGold)
      return numBronze + numGold;

    if (numSilver == numGold)
      if (numSilver < numBronze)
          return 2 * numSilver;
      else
          return numSilver + numBronze;
    
    if (numSilver == numBronze)
      if (numSilver < numGold)
          return 2 * numSilver;
      else
          return numSilver + numGold;

	if (numGold == numBronze)
		if (numGold < numSilver)
			return 2*numGold;
    return numGold + numSilver;
  }




  /**
   * compare current team's total goal medal with given team's total gold medal. 
   * @return different of goal medals
   */
  public int compareTo(OlympicTeam other){
    return this.getNumGold() - other.getNumGold();
  }
  
  public static void main(String[]args){
	  OlympicTeam t1 = new OlympicTeam("team 1",2,2,1);
	  System.out.println(t1.sumLeastTwo());
  }



}