package skyscanner.krithghosh.com.skyscannerapp.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kritarthaghosh on 23/02/18.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    LocalRepository providesLocalRepository() {
        return new LocalRepository();
    }

    @Provides
    @Singleton
    RemoteRepository providesRemoteRepository() {
        return new RemoteRepository();
    }
}
