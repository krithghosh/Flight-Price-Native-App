package skyscanner.krithghosh.com.skyscannerapp.network;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingResponse;
import skyscanner.krithghosh.com.skyscannerapp.model.response.SessionResponse;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST
    Observable<Response<SessionResponse>> createSession(
            @Url String url,
            @FieldMap Map<String, String> fields);

    @GET
    Observable<PricingResponse> getPricing(
            @Url String url,
            @Query("apikey") String apiKey);
}