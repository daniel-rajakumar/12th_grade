import java.io.*;
import java.util.*;
import java.math.*;

public class Olympics {

	private ArrayList<OlympicTeam> teams;


	Olympics(String name){

		teams = new ArrayList<>();

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


	public static void main(String[] args){
		Olympics tokyo2020 = new Olympics("olympic-medals.csv");
		System.out.println(tokyo2020);

	}

}