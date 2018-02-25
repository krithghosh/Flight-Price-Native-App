package skyscanner.krithghosh.com.skyscannerapp.component;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import skyscanner.krithghosh.com.skyscannerapp.module.ApplicationModule;
import skyscanner.krithghosh.com.skyscannerapp.module.NetworkModule;
import skyscanner.krithghosh.com.skyscannerapp.repository.RemoteRepository;
import skyscanner.krithghosh.com.skyscannerapp.repository.Repository;
import skyscanner.krithghosh.com.skyscannerapp.repository.RepositoryModule;
import skyscanner.krithghosh.com.skyscannerapp.scheduler.AppScheduler;
import skyscanner.krithghosh.com.skyscannerapp.scheduler.AppSchedulerModule;
import skyscanner.krithghosh.com.skyscannerapp.view.PricingFragment;

/**
 * Created by kritarthaghosh on 23/02/18.
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetworkModule.class,
                AppSchedulerModule.class,
                RepositoryModule.class
        }
)
public interface ApplicationComponent {

    Retrofit retrofit();

    Repository repository();

    AppScheduler appScheduler();

    void inject(RemoteRepository remoteRepository);

    void inject(PricingFragment pricingFragment);
}
