package skyscanner.krithghosh.com.skyscannerapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public class AppUtils {
    private static final String TAG = "AppUtils";
    private static Picasso picassoInstance;

    public static void showToast(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }

    public static boolean isThereInternetConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    public static boolean isCollectionEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static String getStringResource(Context context, int resId) {
        return context.getString(resId);
    }

    public static String getTimeFromDateTime(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat result = new SimpleDateFormat("HH:mm");
        try {
            Date date = df.parse(dateString);
            return result.format(date);
        } catch (ParseException e) {
            Log.e(TAG, "getTimeFromDateTime: ", e);
            return "";
        }
    }

    public static String getTimeBreakUp(int time) {
        int hours = time / 60;
        int minutes = time - (hours * 60);
        return hours + "h" + " " + minutes + "m";
    }

    public static Drawable getDrawableResource(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    public static void replaceFragment(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId,
            boolean shouldAddToBackStack,
            String fragmentTag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, fragmentTag);
        if (shouldAddToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }
        transaction.commitAllowingStateLoss();
    }

    public static Picasso getPicassoInstance(Context mContext) {
        if (picassoInstance == null) {
            picassoInstance =
                    new Picasso.Builder(mContext)
                            .listener(
                                    (picasso1, uri, exception) -> {
                                        Log.e(TAG, "getPicassoInstance: ", exception);
                                    })
                            .build();
        }
        return picassoInstance;
    }

    public static void setImage(Context mContext, String imageUrl, ImageView view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams.width <= 0 || layoutParams.height <= 0) {
            return;
        }
        getPicassoInstance(mContext)
                .load(imageUrl)
                .noPlaceholder()
                .resize(layoutParams.width, layoutParams.height)
                .noFade()
                .centerCrop()
                .into(view);
    }
}
