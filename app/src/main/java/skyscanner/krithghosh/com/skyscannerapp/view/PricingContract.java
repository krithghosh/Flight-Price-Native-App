package skyscanner.krithghosh.com.skyscannerapp.view;

import java.util.List;

import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingDisplay;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public interface PricingContract {

    interface View extends BaseView {
        void showPricing(List<PricingDisplay> mPricingList, int offset, int total);
    }

    interface Presenter {
        void unSubscribe();

        void getPricing(boolean forceUpdate);
    }
}
