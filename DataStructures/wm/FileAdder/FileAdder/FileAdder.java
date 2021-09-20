import java.io.*;
import java.util.*;
public class FileAdder
{
	public FileAdder()
	{
   // Get stuff out of a file and add it
		File name = new File("NumsToAdd.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
      text = ""; // set text to an empty string
			while((text=input.readLine())!= null)
			{
				String[]  numStrs = text.split(" ");

        for (int i = 0; i < numStrs.length; i++){
           System.out.print(numStrs[i]+" ");
        }
        int[] nums = new int[numStrs.length];

        for (int i = 0; i < numStrs.length; i++)
           nums[i] = Integer.parseInt(numStrs[i]);
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
          sum += nums[i];
        }
        // Print out what the sum is below
        System.out.println(" ==> sum is "+sum);

			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}

  public static void main(String[] args)
	{
		new FileAdder();
	}

}