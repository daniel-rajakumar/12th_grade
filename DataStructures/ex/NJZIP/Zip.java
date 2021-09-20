public class Zip{
  private String zip;
  private String town;

  public Zip(String zipCode, String townName){
    zip = zipCode;
    town = townName;
  }

  public String getZip(){ return zip; }

  public String getTown(){ return town; }

}