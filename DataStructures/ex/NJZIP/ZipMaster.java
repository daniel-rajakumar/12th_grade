import java.io.*;
import java.lang.*;
import java.util.*;
public class ZipMaster
{
  private ArrayList<Zip> zips;
  private Scanner scan;
	public ZipMaster(String fileName)
	{

    scan = new Scanner(System.in);
		File name = new File(fileName);
		try
		{
      zips = new ArrayList<Zip>();
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text,output="";
			while((text=input.readLine())!= null)
			{
				//System.out.println(text);
				if (Character.isDigit(text.charAt(0)))
          zips.add(new Zip(text.substring(0,5), text.substring(6)));
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
    String zip = "";
    while(!zip.equalsIgnoreCase("quit")){
      System.out.print("Enter a zip for lookup (or type quit): ");
      zip = scan.nextLine();
      System.out.println(zip+" -> "+findTown(zip));
    }
	}

  public String findTown(String zCode){
    for (int i = 0; i < zips.size(); i++)
        if (zips.get(i).getZip().equals(zCode))
          return zips.get(i).getTown();
    return "Not a valid NJ Zip Code";
  }

}