package hse.algosim.server;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FiniteQueueExecutor {

    public final static String hostname = Optional.ofNullable(System.getenv("HOSTNAME")).orElse("undefined");

    public final static ThreadPoolExecutor singleThreadExecutor = new ThreadPoolExecutor(
            1,
            1,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(2));
}