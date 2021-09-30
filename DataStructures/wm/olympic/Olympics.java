import java.io.*;
import java.util.*;

public class Olympics {

	private ArrayList<OlympicTeam> teams =  new ArrayList<>();


	Olympics(String name){
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			input.readLine(); // skip a line

			String text;
			while((text = input.readLine()) != null){
				if (text.length() > 0){
					String[] array = text.split(",");

					teams.add(new OlympicTeam(
						array[0],
						Integer.valueOf(array[1]),
						Integer.valueOf(array[2]),
						Integer.valueOf(array[3])
					));

				}
			}
		} catch (IOException io){
			System.err.println("File does not exists");
		}
	}

	public String toString(){
		String str = "";
		for (OlympicTeam ot: teams)
			str += ot + "\n";

		return str;
	}

	public int getSumOfCountry(){
		int sum = 0;

		for (OlympicTeam team: teams)
			if (team.getNumBronze() > team.getCountry().length())
				sum += team.getNumBronze();

		return sum;
	}

	public void sortByCountry(){

		Collections.sort(teams, new Comparator<OlympicTeam>(){

			public int compare(OlympicTeam a, OlympicTeam b){
				return a.getCountry().compareTo(b.getCountry());
			}

		});

	}


	public static void main(String[] args){

		Olympics olympics = new Olympics("olympic-medals.csv");
		System.out.println("Unsorted");
		System.out.println(olympics);
		olympics.sortByCountry();
		System.out.println("\n\nsorted");
		System.out.println(olympics);


	}

}