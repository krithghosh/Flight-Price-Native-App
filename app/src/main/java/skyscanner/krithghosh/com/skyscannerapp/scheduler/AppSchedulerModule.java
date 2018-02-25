package skyscanner.krithghosh.com.skyscannerapp.scheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kritarthaghosh on 23/02/18.
 */
@Module
public class AppSchedulerModule {

    @Provides
    @Singleton
    AppScheduler providesAppScheduler() {
        return new AppScheduler();
    }
}
