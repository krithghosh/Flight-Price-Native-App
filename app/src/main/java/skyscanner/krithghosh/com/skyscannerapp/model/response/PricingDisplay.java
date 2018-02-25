package skyscanner.krithghosh.com.skyscannerapp.model.response;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public class PricingDisplay {

    private ItineraryDisplay outBound;
    private ItineraryDisplay inBound;
    private double price;
    private String agent;
    private String currencySymbol;

    public ItineraryDisplay getOutBound() {
        return outBound;
    }

    public void setOutBound(ItineraryDisplay outBound) {
        this.outBound = outBound;
    }

    public ItineraryDisplay getInBound() {
        return inBound;
    }

    public void setInBound(ItineraryDisplay inBound) {
        this.inBound = inBound;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
