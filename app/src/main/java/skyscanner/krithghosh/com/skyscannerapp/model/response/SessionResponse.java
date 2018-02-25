package skyscanner.krithghosh.com.skyscannerapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public class SessionResponse {

    @SerializedName("ServiceQuery")
    @Expose
    private ServiceQuery serviceQuery;

    public ServiceQuery getServiceQuery() {
        return serviceQuery;
    }

    public void setServiceQuery(ServiceQuery serviceQuery) {
        this.serviceQuery = serviceQuery;
    }

    public SessionResponse withServiceQuery(ServiceQuery serviceQuery) {
        this.serviceQuery = serviceQuery;
        return this;
    }
}

class ServiceQuery {

    @SerializedName("DateTime")
    @Expose
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ServiceQuery withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
