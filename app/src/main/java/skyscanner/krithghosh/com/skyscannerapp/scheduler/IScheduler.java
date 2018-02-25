package skyscanner.krithghosh.com.skyscannerapp.scheduler;

import rx.Scheduler;

/**
 * Created by kritarthaghosh on 23/02/18.
 */
public interface IScheduler {

    Scheduler mainThread();

    Scheduler backgroundThread();
}
