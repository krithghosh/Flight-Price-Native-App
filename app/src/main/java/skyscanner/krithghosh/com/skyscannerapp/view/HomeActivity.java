package skyscanner.krithghosh.com.skyscannerapp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import skyscanner.krithghosh.com.skyscannerapp.R;
import skyscanner.krithghosh.com.skyscannerapp.utils.AppUtils;

import static java.lang.Boolean.FALSE;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public class HomeActivity extends BaseActivity implements PricingFragment.PricingFragmentListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_count)
    TextView resultsCount;

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;

    private FragmentManager fragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initialSetup() {
        setupToolbar(toolbar);
        fragmentManager = setFragmentManager();
        setupToastAndProgressBar(progressBar);
        updateActionBar("EDI- LOND", "05 Mar – 06 Mar, 1 adult, economy");
        setupFragment();
    }

    private void setupFragment() {
        Bundle bundle = new Bundle();
        PricingFragment fragment = PricingFragment.newInstance(bundle);
        AppUtils.replaceFragment(fragmentManager, fragment, R.id.content_frame, FALSE,
                PricingFragment.class.getName());
    }

    @Override
    public void updateResultCount(int currentCount, int totalCount) {
        resultsCount.setText(getString(R.string.results, currentCount, totalCount));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
