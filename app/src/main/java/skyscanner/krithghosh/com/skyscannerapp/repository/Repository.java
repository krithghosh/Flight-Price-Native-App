package skyscanner.krithghosh.com.skyscannerapp.repository;

import javax.inject.Inject;

import rx.Observable;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingResponse;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public class Repository {
    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;
    private PricingResponse pricingResponse = null;

    @Inject
    public Repository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public Observable<PricingResponse> getPricing(boolean forceRefresh, int offset) {
        if (!forceRefresh && pricingResponse != null)
            return Observable.just(pricingResponse);

        if (!forceRefresh) pricingResponse = null;
        return remoteRepository.getPricing();
    }

    public void updatePricingResponse(PricingResponse pricingResponse) {
        this.pricingResponse = pricingResponse;
    }
}
