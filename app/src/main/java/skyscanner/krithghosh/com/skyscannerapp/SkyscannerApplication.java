package skyscanner.krithghosh.com.skyscannerapp;

import android.app.Application;

import skyscanner.krithghosh.com.skyscannerapp.component.ApplicationComponent;
import skyscanner.krithghosh.com.skyscannerapp.component.DaggerApplicationComponent;
import skyscanner.krithghosh.com.skyscannerapp.module.ApplicationModule;
import skyscanner.krithghosh.com.skyscannerapp.module.NetworkModule;
import skyscanner.krithghosh.com.skyscannerapp.repository.RepositoryModule;
import skyscanner.krithghosh.com.skyscannerapp.scheduler.AppSchedulerModule;

/**
 * Created by kritarthaghosh on 23/02/18.
 */

public class SkyscannerApplication extends Application {

    protected static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    private void initializeComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .appSchedulerModule(new AppSchedulerModule())
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
