package skyscanner.krithghosh.com.skyscannerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import skyscanner.krithghosh.com.skyscannerapp.R;
import skyscanner.krithghosh.com.skyscannerapp.model.response.ItineraryDisplay;
import skyscanner.krithghosh.com.skyscannerapp.model.response.PricingDisplay;
import skyscanner.krithghosh.com.skyscannerapp.utils.AppUtils;

/**
 * Created by kritarthaghosh on 24/02/18.
 */

public class PricingListAdapter extends RecyclerView.Adapter<PricingListAdapter.PricingViewHolder> {

    private Context mContext;
    private List<PricingDisplay> mPricingList;

    public PricingListAdapter(Context mContext, List<PricingDisplay> mPricingList) {
        this.mContext = mContext;
        this.mPricingList = mPricingList;
    }

    @Override
    public PricingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.itinerary_card, parent, false);
        return new PricingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PricingViewHolder holder, int position) {
        PricingDisplay item = mPricingList.get(position);
        ItineraryDisplay outbound = item.getOutBound();
        ItineraryDisplay inbound = item.getInBound();

        holder.outboundTime.setText(outbound.getDepartureTime() + " - " + outbound.getArrivalTime());
        holder.outboundCarrier.setText(outbound.getOriginId() + "-" + outbound.getDestinationId()
                + ", " + outbound.getCarrier());
        int stops = outbound.getStops();
        String _stops = stops > 1 ? stops + " STOPS" : stops == 1 ? stops + " STOP" : "DIRECT";
        holder.outboundStops.setText(_stops);
        holder.outboundDuration.setText(outbound.getDuration());
        AppUtils.setImage(mContext, outbound.getImageUrl(), holder.outboundIVCarrier);

        holder.inboundTime.setText(inbound.getDepartureTime() + " - " + inbound.getArrivalTime());
        holder.inboundCarrier.setText(inbound.getOriginId() + "-" + inbound.getDestinationId()
                + ", " + inbound.getCarrier());
        stops = inbound.getStops();
        _stops = stops > 1 ? stops + " STOPS" : stops == 1 ? stops + " STOP" : "DIRECT";
        holder.inboundStops.setText(_stops);
        holder.inboundDuration.setText(inbound.getDuration());
        AppUtils.setImage(mContext, inbound.getImageUrl(), holder.inboundIVCarrier);

        holder.price.setText(item.getCurrencySymbol() + item.getPrice());
        holder.agent.setText("via " + item.getAgent());
    }

    @Override
    public int getItemCount() {
        if (mPricingList == null) return 0;
        return mPricingList.size();
    }

    public void updateList(List<PricingDisplay> mPricingList) {
        this.mPricingList.addAll(mPricingList);
        notifyDataSetChanged();
    }

    public class PricingViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout outboundLayout;
        private LinearLayout inboundLayout;
        private LinearLayout pricingLayout;

        private TextView outboundTime;
        private TextView outboundCarrier;
        private TextView outboundStops;
        private TextView outboundDuration;
        private ImageView outboundIVCarrier;

        private TextView inboundTime;
        private TextView inboundCarrier;
        private TextView inboundStops;
        private TextView inboundDuration;
        private ImageView inboundIVCarrier;

        private TextView price;
        private TextView agent;

        public PricingViewHolder(View itemView) {
            super(itemView);
            outboundLayout = (LinearLayout) itemView.findViewById(R.id.outbound_layout);
            inboundLayout = (LinearLayout) itemView.findViewById(R.id.inbound_layout);
            pricingLayout = (LinearLayout) itemView.findViewById(R.id.pricing_layout);

            outboundTime = (TextView) outboundLayout.findViewById(R.id.tv_time);
            outboundCarrier = (TextView) outboundLayout.findViewById(R.id.tv_carrier);
            outboundStops = (TextView) outboundLayout.findViewById(R.id.tv_stop);
            outboundDuration = (TextView) outboundLayout.findViewById(R.id.tv_duration);
            outboundIVCarrier = (ImageView) outboundLayout.findViewById(R.id.iv_carrier);

            inboundTime = (TextView) inboundLayout.findViewById(R.id.tv_time);
            inboundCarrier = (TextView) inboundLayout.findViewById(R.id.tv_carrier);
            inboundStops = (TextView) inboundLayout.findViewById(R.id.tv_stop);
            inboundDuration = (TextView) inboundLayout.findViewById(R.id.tv_duration);
            inboundIVCarrier = (ImageView) inboundLayout.findViewById(R.id.iv_carrier);

            price = (TextView) pricingLayout.findViewById(R.id.tv_price);
            agent = (TextView) pricingLayout.findViewById(R.id.tv_agent);
        }
    }
}
