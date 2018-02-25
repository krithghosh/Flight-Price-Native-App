package skyscanner.krithghosh.com.skyscannerapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kritarthaghosh on 24/02/18.
 */

public class PricingOption {

    @SerializedName("Agents")
    @Expose
    private List<Integer> agents = null;
    @SerializedName("Price")
    @Expose
    private double price;

    public List<Integer> getAgents() {
        return agents;
    }

    public void setAgents(List<Integer> agents) {
        this.agents = agents;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
