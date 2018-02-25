package skyscanner.krithghosh.com.skyscannerapp.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;
import skyscanner.krithghosh.com.skyscannerapp.BuildConfig;
import skyscanner.krithghosh.com.skyscannerapp.SkyscannerApplication;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingResponse;
import skyscanner.krithghosh.com.skyscannerapp.network.ApiService;

/**
 * Created by kritarthaghosh on 23/02/18.
 */
@Singleton
public class RemoteRepository {

    @Inject
    Retrofit retrofit;

    private final String CREATE_SESSION = "apiservices/pricing/v1.0";
    private final String HEADER_LOCATION = "location";

    public RemoteRepository() {
        SkyscannerApplication.getApplicationComponent().inject(this);
    }

    private String getBaseUrl() {
        return retrofit.baseUrl().url().toString();
    }

    private String getApiKey() {
        return BuildConfig.API_KEY;
    }

    public Observable<PricingResponse> getPricing() {

        String sessionUrl = getBaseUrl() + CREATE_SESSION;
        Map<String, String> fields = new HashMap<>();
        fields.put("cabinclass", "Economy");
        fields.put("country", "UK");
        fields.put("currency", "GBP");
        fields.put("locale", "en-GB");
        fields.put("locationSchema", "sky");
        fields.put("originplace", "EDI-sky");
        fields.put("destinationplace", "LOND-sky");
        fields.put("outbounddate", "2018-03-05");
        fields.put("inbounddate", "2018-03-06");
        fields.put("adults", "1");
        fields.put("children", "0");
        fields.put("infants", "0");
        fields.put("apikey", BuildConfig.API_KEY);

        return retrofit.create(ApiService.class).createSession(sessionUrl, fields)
                .flatMap(response -> {
                    String pricingUrl = response.headers().get(HEADER_LOCATION);
                    return retrofit.create(ApiService.class).getPricing(pricingUrl, getApiKey());
                });
    }
}
