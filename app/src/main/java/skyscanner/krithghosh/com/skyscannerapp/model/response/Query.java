package skyscanner.krithghosh.com.skyscannerapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kritarthaghosh on 24/02/18.
 */

public class Query {

    @SerializedName("Adults")
    @Expose
    private int adults;
    @SerializedName("Children")
    @Expose
    private int children;
    @SerializedName("Infants")
    @Expose
    private int infants;
    @SerializedName("OriginPlace")
    @Expose
    private String originPlace;
    @SerializedName("DestinationPlace")
    @Expose
    private String destinationPlace;
    @SerializedName("OutboundDate")
    @Expose
    private String outboundDate;
    @SerializedName("InboundDate")
    @Expose
    private String inboundDate;
    @SerializedName("CabinClass")
    @Expose
    private String cabinClass;

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }
}
