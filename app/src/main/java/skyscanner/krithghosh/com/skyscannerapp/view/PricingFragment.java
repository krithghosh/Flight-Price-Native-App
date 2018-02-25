package skyscanner.krithghosh.com.skyscannerapp.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import skyscanner.krithghosh.com.skyscannerapp.R;
import skyscanner.krithghosh.com.skyscannerapp.SkyscannerApplication;
import skyscanner.krithghosh.com.skyscannerapp.adapter.PricingListAdapter;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingDisplay;
import skyscanner.krithghosh.com.skyscannerapp.presenter.PricingPresenter;

import static java.lang.Boolean.FALSE;

public class PricingFragment extends BaseFragment implements PricingContract.View {

    @BindView(R.id.tv_error)
    TextView tvError;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    PricingPresenter mPresenter;

    private PricingFragmentListener mListener;
    private LinearLayoutManager mLayoutManager;
    private List<PricingDisplay> mPricingList;
    private PricingListAdapter mAdapter;

    public interface PricingFragmentListener extends BaseListener {
        void updateResultCount(int currentCount, int totalCount);
    }

    public static PricingFragment newInstance(Bundle bundle) {
        PricingFragment fragment = new PricingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (PricingFragmentListener) context;
        setBaseListener(mListener);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_pricing;
    }

    @Override
    protected void injectModules() {
        SkyscannerApplication.getApplicationComponent().inject(this);
        mPresenter.setView(getContext(), this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectModules();
        mPricingList = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter(getContext());
        setupRecyclerView(getContext());
        mPresenter.getPricing(true);
    }

    private void setupAdapter(Context context) {
        mAdapter = new PricingListAdapter(context, mPricingList);
    }

    private void setupRecyclerView(Context context) {
        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener =
            new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        mPresenter.getPricing(FALSE);
                    }
                }
            };

    @Override
    public void showPricing(List<PricingDisplay> mPricingList, int offset, int total) {
        this.mPricingList = mPricingList;
        mAdapter.updateList(mPricingList);
        mListener.updateResultCount(offset, total);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
