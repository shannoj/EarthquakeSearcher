package eathquake1;
 //class for earthquake data coming from csv file
public class EathquakeData1 {
	private String date;
    private float magnitude;
    private String location;

    public EathquakeData1() {

    }

    public EathquakeData1(String date, float magnitude, String location){
        super();
        this.date = date;
        this.magnitude = magnitude;
        this.location = location;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public float getMagnitude(){
        return magnitude;
    }

    public void setMagnitude(float magnitude){
        this.magnitude = magnitude;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String toString(){
        return date + " : " + magnitude + " : " + location;
    }

}
