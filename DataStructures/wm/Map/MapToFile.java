import java.io.*;
import java.util.*;

import javax.swing.text.html.parser.Entity;

public class MapToFile {

  private String fileName;
  private Map<String,String> map;

  public MapToFile(String file){
	  fileName = file;
	  map = new TreeMap<String,String>();
  }

  public Map<String,String> getMap(){
  	  return map;
  }

  public void write() {

    try (PrintWriter writer = new PrintWriter(fileName)) {

      String fileOutput = "";
      for (Map.Entry<String,String> entry: map.entrySet())
			fileOutput+= entry.getKey()+"="+entry.getValue()+"\n";
	  writer.write(fileOutput);
      System.out.println("done!");

    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

  }

  public void read(){
		File name = new File(fileName);
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while((text=input.readLine())!= null)
			{
				String[] fields = text.split("=");
				map.put(fields[0],fields[1]);

			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}
   
   public void print(){
      for (Map.Entry<String,String> entry: map.entrySet())
			System.out.println(entry.getKey()+" capital => "+entry.getValue());
   }

   public void modifyM(){
		for (Map.Entry<String, String> i: map.entrySet()){
			if (i.getKey().startsWith("M")){
				map.put(i.getKey(), "M" + i.getValue());  //change
			}
		}
   }

	public static void main(String[]args){
		MapToFile mtf = new MapToFile("StateAndCapital.txt");
		mtf.read();
		System.out.println(mtf.getMap());
		mtf.getMap().put("Wyoming","Jackson Hole");  //change
		for (int i = 0; i < 1000; i++){
			mtf.modifyM();
			// mtf.print();
			mtf.write();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}




}