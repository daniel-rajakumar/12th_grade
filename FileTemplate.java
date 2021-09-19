import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;
public class FileTemplate {

	public FileTemplate() {
		// one("LucasNumFile.txt");
		// two("AmicableNumsFile.txt");
		// three("MastermindFile.txt");
		// four("CensusApportionment.txt");
		five("Sets.txt");

	}

	/***********************************************************************/
	/********************************** One ********************************/
	/***********************************************************************/

	public void one(String fileName){
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
			String text;

			while((text=input.readLine()) != null) {
				System.out.println(one_lucasNumbers((text)));
			}
		}
		catch (IOException io) {
			System.err.println("File does not exist!!!!!");
		}
	}


	public static BigInteger one_lucasNumbers(String num){

		BigInteger a = BigInteger.valueOf(2),
				   b = BigInteger.valueOf(1),
		           c = BigInteger.valueOf(3);

		for (int i = 1; i < Integer.parseInt(num); i++){
			c = a.add(b);
			a = b;
			b = c;
		}

		return b;
	}


	/*  RECURSIVE WAY (takes forever though)
	 *
	public BigInteger one_lucasNumbers(String str){
		int num = Integer.parseInt(str);
		BigInteger bigNum = BigInteger.valueOf(num);

		if (bigNum.equals(new BigInteger("0"))) return BigInteger.valueOf(2);
		if (bigNum.equals(new BigInteger("1"))) return BigInteger.valueOf(1);

		return one_lucasNumbers((num - 1) + "").add(one_lucasNumbers((num - 2) + ""));
	}
	*/

	/***********************************************************************/
	/****************************** Two ************************************/
	/***********************************************************************/


	public void two(String fileName){
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
			String text;

			while((text=input.readLine()) != null) {
				two_Amicable(text);
				System.out.println("\n");
			}
		}
		catch (IOException io) {
			System.err.println("File does not exist!!!!!");
		}
	}

	public void two_Amicable(String nums){
		ArrayList<Integer> factor_list;

		int[] numPair = {Integer.parseInt(nums.split(" ")[0]),
						 Integer.parseInt(nums.split(" ")[1])};

		boolean isProperDivisor_1 = numPair[0] == two_sumFactors(two_getListOfFactors(numPair[1]));
		boolean isProperDivisor_2 = numPair[1] == two_sumFactors(two_getListOfFactors(numPair[0]));

		if (isProperDivisor_1 && isProperDivisor_2)
			System.out.printf("The numbers %s and %s are amicable.\n", numPair[0], numPair[1]);
		else
			System.out.printf("The numbers %s and %s are not amicable.\n", numPair[0], numPair[1]);

		factor_list = two_getListOfFactors(numPair[0]);
		System.out.printf("\t\t Factors of %s are %s. Sum is %s\n", numPair[0], two_formatList(factor_list), two_sumFactors(factor_list));

		factor_list = two_getListOfFactors(numPair[1]);
		System.out.printf("\t\t Factors of %s are %s. Sum is %s\n", numPair[1], two_formatList(factor_list), two_sumFactors(factor_list));
	}

	public ArrayList<Integer> two_getListOfFactors(int num){

		ArrayList<Integer> factors = new ArrayList<>();

		for (int i = 1; i <= num/2; i++)
			if (num % i == 0)
				factors.add(i);

		return factors;
	}

	public String two_formatList(ArrayList<Integer> list){
		if (list.size() < 2) return list.get(0) + "";

		String str = "";

		for (int i = 0; i < list.size() - 2; i++){
			str += list.get(i) + ", ";
		}

		return str + String.format("%s and %s", list.get(list.size() - 2), list.get(list.size() - 1));
	}


	public int two_sumFactors(ArrayList<Integer> list){
		int sum = 0;
		for (Integer i: list) sum += i;
		return sum;
	}

	/***********************************************************************/
	/*************************** Three *************************************/
	/***********************************************************************/

	public void three(String fileName){
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
			String text;

			int lineNum = 0;
			String code = "", guess = "";

			while((text=input.readLine()) != null) {
				lineNum++;
				if (lineNum % 2 == 1){
					code = text;
				} else {
					guess = text;
					three_printOutputInFormat(code, guess);
				}
			}

		} catch (IOException io) {
			System.err.println("File does not exist!!!!!");
		}
	}

	public void three_printOutputInFormat(String code, String guess){
		int count_correctly = 0, count_incorrectly = 0;
		char[] codeChars = code.toCharArray(), guessChars = guess.toCharArray();

		for (int i = 0; i < codeChars.length; i++)
			for (int j = 0; j < guessChars.length; j++)
				if (codeChars[i] == guessChars[j]) {
					if(i == j)	count_correctly++;
					else 		count_incorrectly++;

					break;
				}


		System.out.printf("Code: %s \n", code);
		System.out.printf("Guess: %s \n", guess);
		System.out.printf("Color Correct - Correctly Placed: %s \n", count_correctly);
		System.out.printf("Color Correct - Incorrectly Placed: %s \n\n", count_incorrectly);
	}

	/***********************************************************************/
	/*************************** Four *************************************/
	/***********************************************************************/

	public void four(String fileName){
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
			String text;

			//ignore first line
			input.readLine();

			System.out.println("Population per Congressional District by State: ");

			// the core dataset for this problem
			Object[][] stateData = new Object[50][5];

			int stateCount = -1;
			while((text=input.readLine()) != null) {
				stateData[++stateCount] = four_getFormateData(text);
				// state -> apportionment
				System.out.printf("%s -> %s\n", stateData[stateCount][0], stateData[stateCount][4]);
			}

			int minApportionment = four_MinAndMaxApportionmentIndex(stateData)[0];
			int maxApportionment = four_MinAndMaxApportionmentIndex(stateData)[1];
			String minState = stateData[minApportionment][0].toString();
			String maxState = stateData[maxApportionment][0].toString();
			int minPeople = (int) stateData[minApportionment][4];
			int maxPeople = (int) stateData[maxApportionment][4];
			
			System.out.printf("A congressman from %s represents %s people, ", maxState, maxPeople);
			System.out.printf("while a congressman from %s only represents %s people!\n", minState, minPeople);




		} catch (IOException io) {
			System.err.println("File does not exist!!!!!");
		}
	}

	public Object[] four_getFormateData(String data){
		
		//filter unwanted chars (like spaces and commas) and split it
		String[] list = data.replace(",","").split(" "); 

		String state = "";
		for (int i = 0; i < list.length - 3; i++)
			state += list[i] + " ";
		state = state.trim();
		
		int population = 			Integer.parseInt(list[list.length - 3]);
		int representatives =	 	Integer.parseInt(list[list.length - 2]);
		int representativesChange = Integer.parseInt(list[list.length - 1]);
		int apportionment = 		population / representatives;

		return new Object[]{state, population, representatives, representativesChange, apportionment};
	}

	public int[] four_MinAndMaxApportionmentIndex(Object[][] stateData){
		int min_index = 0, max_index = 0;
		int num, min, max;

		for (int i = 0; i < stateData.length; i++){
			num = (int) stateData[i][4];
			min = (int) stateData[min_index][4];
			max = (int) stateData[max_index][4];

			if (num < min) min_index = i;
			if (num > max) max_index = i;
		}

		return new int[]{min_index, max_index};
	}

	/***********************************************************************/
	/*************************** Five *************************************/
	/***********************************************************************/

	public void five(String fileName){
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
			String text;

			ArrayList<String[]> sets = new ArrayList<>();

			while((text=input.readLine()) != null) {
				sets.add(text.split(" "));
			}

			five_getIntersections(sets);

		} catch (IOException io) {
			System.err.println("File does not exist!!!!!");
		}
	}

	public ArrayList<String> five_getIntersections(ArrayList<String[]> list){
		int current_setLine; 

		for (int line = 0; line < list.size(); line+=3){
			current_setLine = line % 3;

			for (int i = 0; i < list.get(line).length; i++){
				// System.out.print(list.get(line)[i] + " ");

				for (int j = 0; j < list.get(line + 1).length; j++){
					if (list.get(line)[i].equals(list.get(line + 1)[j])){
						System.out.print(" -> " + list.get(line));
					}
				}
			}

			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~");
		}

		return null;
	}





	/***********************************************************************/
	/*************************** ..... *************************************/
	/***********************************************************************/

	public static void main(String[] args) {
		new FileTemplate();
	}
}
