package skyscanner.krithghosh.com.skyscannerapp.view

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.ButterKnife
import skyscanner.krithghosh.com.skyscannerapp.R
import skyscanner.krithghosh.com.skyscannerapp.utils.AppUtils
import java.lang.Boolean.TRUE

/**
 * Created by kritarthaghosh on 20/02/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun getLayoutId(): Int
    protected abstract fun initialSetup(): Unit
    private var progressBar: ProgressBar? = null
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        initialSetup();
    }

    fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(TRUE);
        supportActionBar?.setHomeAsUpIndicator(
                AppUtils.getDrawableResource(applicationContext, R.drawable.back));
    }

    fun updateActionBar(title: String, subTitle: String) {
        supportActionBar?.title = title;
        supportActionBar?.subtitle = subTitle;
    }

    fun setFragmentManager(): FragmentManager {
        return supportFragmentManager;
    }

    fun setupToastAndProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    fun showError(msg: String) {
        if (!toast?.getView()?.isShown()!!) {
            toast?.setText(msg);
            toast?.show();
        }
    }

    fun showLoader() {
        if (!progressBar?.isShown()!!) {
            progressBar?.visibility = View.VISIBLE;
        }
    }

    fun hideLoader() {
        if (progressBar?.isShown()!!) {
            progressBar?.visibility = View.GONE;
        }
    }
}