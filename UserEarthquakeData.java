package eathquake1;
//class for earthquake data that will go into the user list
public class UserEarthquakeData {
	private String userDate;
    private float userMagnitude;
    private String userState;

    public UserEarthquakeData(){

    }

    public UserEarthquakeData(String userDate, float userMagnitude, String userState){
        super();
        this.userDate = userDate;
        this.userMagnitude = userMagnitude;
        this.userState = userState;
    }

    public String getUserDate(){
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public float getUserMagnitude(){
        return userMagnitude;
    }

    public void setUserMagnitude(float userMagnitude) {
        this.userMagnitude = userMagnitude;
    }

    public String getUserState(){
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String toString(){
        return "Date:" + userDate + "; Magnitude:" + userMagnitude + '\n';
    }

}
