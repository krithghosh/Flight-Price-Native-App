package skyscanner.krithghosh.com.skyscannerapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

/**
 * Created by kritarthaghosh on 20/02/18.
 */
abstract class BaseFragment : Fragment() {

    protected abstract fun getLayout(): Int
    protected abstract fun injectModules(): Unit
    private var mBaseListener: BaseListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(getLayout(), container, false)
        ButterKnife.bind(this, view!!)
        return view
    }

    fun showError(msg: String) {
        mBaseListener?.hideLoader();
        mBaseListener?.showError(msg);
    }

    fun showLoader() {
        mBaseListener?.showLoader();
    }

    fun hideLoader() {
        mBaseListener?.hideLoader();
    }

    fun setBaseListener(mBaseListener: BaseListener) {
        this.mBaseListener = mBaseListener;
    }
}