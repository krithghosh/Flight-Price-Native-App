package skyscanner.krithghosh.com.skyscannerapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kritarthaghosh on 24/02/18.
 */

public class Leg {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("OriginStation")
    @Expose
    private int originStation;
    @SerializedName("DestinationStation")
    @Expose
    private int destinationStation;
    @SerializedName("Departure")
    @Expose
    private String departure;
    @SerializedName("Arrival")
    @Expose
    private String arrival;
    @SerializedName("Duration")
    @Expose
    private int duration;
    @SerializedName("Stops")
    @Expose
    private List<Object> stops = null;
    @SerializedName("Carriers")
    @Expose
    private List<Integer> carriers = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOriginStation() {
        return originStation;
    }

    public void setOriginStation(int originStation) {
        this.originStation = originStation;
    }

    public int getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(int destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Object> getStops() {
        return stops;
    }

    public void setStops(List<Object> stops) {
        this.stops = stops;
    }

    public List<Integer> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Integer> carriers) {
        this.carriers = carriers;
    }
}
