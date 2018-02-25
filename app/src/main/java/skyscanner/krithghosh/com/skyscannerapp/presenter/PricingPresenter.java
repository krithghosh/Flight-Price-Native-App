package skyscanner.krithghosh.com.skyscannerapp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import skyscanner.krithghosh.com.skyscannerapp.R;
import skyscanner.krithghosh.com.skyscannerapp.model.response.Agent;
import skyscanner.krithghosh.com.skyscannerapp.model.response.Carrier;
import skyscanner.krithghosh.com.skyscannerapp.model.response.Currency;
import skyscanner.krithghosh.com.skyscannerapp.model.response.Itinerary;
import skyscanner.krithghosh.com.skyscannerapp.model.response.ItineraryDisplay;
import skyscanner.krithghosh.com.skyscannerapp.model.response.Leg;
import skyscanner.krithghosh.com.skyscannerapp.model.response.Place;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingDisplay;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingOption;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingResponse;
import skyscanner.krithghosh.com.skyscannerapp.repository.Repository;
import skyscanner.krithghosh.com.skyscannerapp.scheduler.AppScheduler;
import skyscanner.krithghosh.com.skyscannerapp.utils.AppUtils;
import skyscanner.krithghosh.com.skyscannerapp.view.PricingContract;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public class PricingPresenter extends BasePresenter implements PricingContract.Presenter {

    private Repository repository;
    private AppScheduler appScheduler;
    private PricingContract.View mView;
    private int offset = 0, pageSize = 20, totalSize = 0;
    Subscription subscription;

    @Inject
    public PricingPresenter(Repository repository, AppScheduler appScheduler) {
        this.repository = repository;
        this.appScheduler = appScheduler;
    }

    @Override
    public void setView() {
        this.mView = (PricingContract.View) super.mView;
    }

    @Override
    public void unSubscribe() {
        unSubscribe(subscription);
    }

    @Override
    public void getPricing(final boolean forceUpdate) {
        if (forceUpdate) {
            offset = 0;
            mView.showLoader();
        }
        if (!forceUpdate && offset >= totalSize) return;
        if (forceUpdate && checkNetworkConnection()) return;

        subscription = repository.getPricing(forceUpdate, offset)
                .subscribeOn(appScheduler.backgroundThread())
                .observeOn(appScheduler.mainThread())
                .subscribe(new Subscriber<PricingResponse>() {
                    PricingResponse response;

                    @Override
                    public void onCompleted() {
                        if (forceUpdate) {
                            repository.updatePricingResponse(response);
                            totalSize = response.getItineraries().size();
                        }
                        int current = offset + pageSize;
                        if (current > totalSize) current = totalSize;
                        mView.hideLoader();
                        mView.showPricing(getPricingList(response, offset, current), current, totalSize);
                        offset = current;
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(R.string.pricing_error);
                    }

                    @Override
                    public void onNext(PricingResponse response) {
                        this.response = response;
                    }
                });

    }

    public List<PricingDisplay> getPricingList(PricingResponse response, int offset, int pageSize) {
        List<PricingDisplay> displayList = new ArrayList<>();
        List<Itinerary> itineraries = response.getItineraries();
        List<Leg> legs = response.getLegs();
        List<Place> places = response.getPlaces();
        List<Agent> agents = response.getAgents();
        List<Carrier> carriers = response.getCarriers();
        List<Currency> currencies = response.getCurrencies();
        for (int i = offset; i < pageSize; i++) {
            Itinerary itinerary = itineraries.get(i);
            PricingOption pricingOption = itinerary.getPricingOptions().get(0);
            PricingDisplay pricingDisplay = new PricingDisplay();
            ItineraryDisplay inBound = null, outBound = null;
            for (Leg leg : legs) {
                if (inBound != null && outBound != null) break;
                if (leg.getId().equals(itinerary.getOutboundLegId())) {
                    outBound = getItineraryBounds(outBound, leg, places, carriers);
                }
                if (leg.getId().equals(itinerary.getInboundLegId())) {
                    inBound = getItineraryBounds(inBound, leg, places, carriers);
                }
            }
            pricingDisplay.setOutBound(outBound);
            pricingDisplay.setInBound(inBound);
            pricingDisplay.setAgent(getAgentName(pricingOption.getAgents().get(0), agents));
            pricingDisplay.setPrice(pricingOption.getPrice());
            pricingDisplay.setCurrencySymbol(getCurrencySymbol(currencies));
            displayList.add(pricingDisplay);
        }
        return displayList;
    }

    public ItineraryDisplay getItineraryBounds(ItineraryDisplay bound, Leg leg, List<Place> places,
                                               List<Carrier> carriers) {
        if (bound == null) bound = new ItineraryDisplay();
        bound.setOriginId(getPlaceId(leg.getOriginStation(), places));
        bound.setDestinationId(getPlaceId(leg.getDestinationStation(), places));
        bound.setDepartureTime(AppUtils.getTimeFromDateTime(leg.getDeparture()));
        bound.setArrivalTime(AppUtils.getTimeFromDateTime(leg.getArrival()));
        bound.setStops(leg.getStops().size());
        bound.setDuration(AppUtils.getTimeBreakUp(leg.getDuration()));
        bound = getCarrier(leg.getCarriers().get(0), carriers, bound);
        return bound;
    }

    public String getPlaceId(int id, List<Place> places) {
        for (Place place : places) {
            if (place.getId() == id) return place.getCode();
        }
        return "";
    }

    public ItineraryDisplay getCarrier(int id, List<Carrier> carriers, ItineraryDisplay obj) {
        for (Carrier carrier : carriers) {
            if (carrier.getId() == id) {
                obj.setCarrier(carrier.getName());
                obj.setImageUrl(carrier.getImageUrl());
                return obj;
            }
        }
        return null;
    }

    public String getAgentName(int id, List<Agent> agents) {
        for (Agent agent : agents) {
            if (agent.getId() == id) return agent.getName();
        }
        return "";
    }

    public String getCurrencySymbol(List<Currency> currencies) {
        return currencies.get(0).getSymbol();
    }
}