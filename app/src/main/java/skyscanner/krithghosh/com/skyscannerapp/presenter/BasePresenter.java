package skyscanner.krithghosh.com.skyscannerapp.presenter;

import android.content.Context;

import rx.Subscription;
import skyscanner.krithghosh.com.skyscannerapp.R;
import skyscanner.krithghosh.com.skyscannerapp.utils.AppUtils;
import skyscanner.krithghosh.com.skyscannerapp.view.BaseView;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public abstract class BasePresenter {
    public Context mContext;
    public BaseView mView;

    public void setView(Context mContext, BaseView mView) {
        this.mContext = mContext;
        this.mView = mView;
        setView();
    }

    public abstract void setView();

    public boolean checkNetworkConnection() {
        boolean conn = !AppUtils.isThereInternetConnection(mContext);
        if (conn) showError(R.string.internet_not_available);
        return conn;
    }

    public void unSubscribe(Subscription subscription) {
        if (!(subscription == null || subscription.isUnsubscribed())) {
            subscription.unsubscribe();
        }
    }

    public void showError(int resId) {
        mView.showError(AppUtils.getStringResource(mContext, resId));
    }
}
