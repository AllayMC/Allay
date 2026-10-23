package org.allaymc.server.utils;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

/**
 * {@code GameLoop} schedules tick callbacks on its calling thread and uses
 * remaining tick time for parking and optional idle work. {@code isRunning}
 * admits future ticks, while {@code loopThread} lets other threads wake the
 * parked loop through {@code wakeUp()}. Wakeups
 * may coalesce or occur spuriously, so idle callbacks process available work
 * rather than corresponding one-to-one with wake-up requests.
 *
 * <p>
 * {@code startLoop()} accumulates the difference between the ideal tick duration
 * and elapsed work. Slow ticks carry a negative balance into later ticks; early
 * wakeups and idle work consume the same outstanding sleep budget. Timing
 * samples cover the latest configured number of tick callbacks, not a fixed
 * wall-clock interval. Callback failures propagate without a {@code finally}
 * block, so they can bypass {@code loopThread} cleanup and {@code onStop}.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class GameLoop {
    /**
     * {@code isRunning} admits future ticks and initially holds {@code true}
     * before any thread starts the loop. {@code stop()} clears it permanently;
     * {@code startLoop()} does not reset this flag.
     */
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    // onStart, onTick, onIdle, and onStop execute on the loop caller's thread.
    // Only onIdle is optional; failures propagate without lifecycle recovery.
    private final Runnable onStart; // callback before tick admission
    private final Consumer<GameLoop> onTick; // callback for each admitted tick
    private final Runnable onIdle; // optional callback after a park returns
    private final Runnable onStop; // callback after normal loop exit
    /**
     * {@code loopThread} identifies the thread to unpark while {@code startLoop()}
     * is active. Normal exit clears it, but an uncaught callback failure can
     * leave the reference set.
     */
    private volatile Thread loopThread;
    /** Configured target ticks per second. */
    @Getter
    private final int loopCountPerSec;
    // tickSummary and MSPTSummary hold one sample per recent tick callback,
    // with loopCountPerSec slots. The loop thread shifts both arrays; concurrent
    // readers do not receive an atomic snapshot of either sample window.
    private final float[] tickSummary; // capped reciprocal tick-duration samples
    private final float[] MSPTSummary; // callback durations in milliseconds
    /**
     * {@code tick} counts completed tick callbacks from the configured starting
     * value. {@code getTick()} does not synchronize concurrent reads with loop
     * updates.
     */
    @Getter
    private long tick;

    /**
     * Retains lifecycle callbacks and initializes the tick counter and sample
     * windows. The target rate must be positive; initial TPS samples equal
     * that rate and initial duration samples are zero. Callbacks are retained
     * without validation, with only {@code onIdle} treated as optional.
     */
    private GameLoop(Runnable onStart, Consumer<GameLoop> onTick, Runnable onIdle, Runnable onStop, int loopCountPerSec, long currentTick) {
        if (loopCountPerSec <= 0) {
            throw new IllegalArgumentException("Loop count per second must be greater than 0! (loopCountPerSec=" + loopCountPerSec + ")");
        }
        this.onStart = onStart;
        this.onTick = onTick;
        this.onIdle = onIdle;
        this.onStop = onStop;
        this.loopCountPerSec = loopCountPerSec;
        this.tick = currentTick;
        // A full window contains this many callbacks, representing one second
        // only when actual callback frequency matches the configured target rate.
        this.tickSummary = new float[loopCountPerSec];
        this.MSPTSummary = new float[loopCountPerSec];
        Arrays.fill(tickSummary, loopCountPerSec);
        Arrays.fill(MSPTSummary, 0f);
    }

    /**
     * Creates a fresh builder targeting twenty ticks per second from tick zero.
     * The builder initializes {@code onStart}, {@code onTick}, and {@code onStop}
     * to empty callbacks, while {@code onIdle} starts as {@code null}.
     */
    public static GameLoopBuilder builder() {
        return new GameLoopBuilder();
    }

    /**
     * Returns average callback duration divided by the configured tick budget.
     * This ratio excludes parking and idle callbacks and is not clamped to
     * one when tick work exceeds its budget.
     */
    public float getTickUsage() {
        return getMSPT() / (1000f / loopCountPerSec);
    }

    /**
     * Returns the mean capped reciprocal duration of recent tick callbacks.
     * Samples exclude parking and idle work, so this estimates callback
     * capacity rather than measuring completed ticks per wall-clock second.
     * Concurrent reads can observe a partially shifted sample window.
     */
    public float getTPS() {
        float sum = 0;
        int count = tickSummary.length;
        for (float tick : tickSummary) {
            sum += tick;
        }
        return sum / count;
    }

    /**
     * Returns the mean recent tick-callback duration in milliseconds.
     * Parking and idle callbacks are excluded, initial samples are zero,
     * and concurrent reads can observe a partially shifted sample window.
     */
    public float getMSPT() {
        float sum = 0;
        int count = MSPTSummary.length;
        for (float mspt : MSPTSummary) {
            sum += mspt;
        }
        return sum / count;
    }

    /**
     * Makes a permit available to the currently published loop thread.
     * Calls before thread publication have no effect. A permit can end parking
     * early, but wakeups may coalesce and do not guarantee a distinct idle
     * callback or interrupt work already running in a callback.
     */
    public void wakeUp() {
        var thread = loopThread;
        if (thread != null) {
            LockSupport.unpark(thread);
        }
    }

    /**
     * Runs the lifecycle and tick loop synchronously on the calling thread.
     * Callers must use one thread per loop instance; concurrent starts would
     * share counters, callbacks, and {@code loopThread} without coordination.
     * A stopped instance does not restart ticking, although another call still
     * invokes {@code onStart} and {@code onStop} around the already-cleared
     * {@code isRunning} flag.
     *
     * <p>
     * {@code onTick} consumes the accumulated sleep budget, including debt from
     * earlier overruns. {@code onIdle} runs after parking returns while the loop
     * remains active, and its elapsed time also reduces that budget. Normal
     * exit clears {@code loopThread} before invoking {@code onStop};
     * exceptions propagate without guaranteeing either cleanup operation.
     */
    public void startLoop() {
        loopThread = Thread.currentThread();
        onStart.run();
        long nanoSleepTime = 0;
        // Integer division discards fractional nanoseconds from each target interval.
        long idealNanoSleepPerTick = 1000000000 / loopCountPerSec;
        while (isRunning.get()) {
            long startTickTime = System.nanoTime();
            onTick.accept(this);
            tick++;
            long timeTakenToTick = System.nanoTime() - startTickTime;
            updateMSPT(timeTakenToTick, MSPTSummary);
            updateTPS(timeTakenToTick);

            long sumOperateTime = System.nanoTime() - startTickTime;
            // Carry overruns into later ticks rather than resetting their debt;
            // otherwise each slow callback would permanently shift the scheduled cadence.
            nanoSleepTime += idealNanoSleepPerTick - sumOperateTime;
            // Retain the remaining budget across wakeups because packet work can
            // wake the loop early without making the next tick due yet.
            while (nanoSleepTime > 0 && isRunning.get()) {
                long sleepStart = System.nanoTime();
                LockSupport.parkNanos(nanoSleepTime);
                // Parking can return through timeout, a permit, or a spurious
                // wakeup; idle handlers must inspect their own available work each time.
                if (onIdle != null && isRunning.get()) {
                    onIdle.run();
                }
                // Count idle work against this tick's remaining budget so repeated
                // early wakeups cannot add an extra full sleep after their processing.
                nanoSleepTime -= System.nanoTime() - sleepStart;
            }
        }
        loopThread = null;
        onStop.run();
    }

    /**
     * Appends the capped reciprocal of the latest callback duration in
     * nanoseconds. A zero duration uses one nanosecond to avoid division by
     * zero; the sample window drops its oldest entry.
     */
    private void updateTPS(long timeTakenToTick) {
        // Cap reciprocal throughput at the configured target, which is the
        // maximum advertised capacity even when individual callbacks finish faster.
        float tick = Math.max(0, Math.min(loopCountPerSec, 1000000000f / (timeTakenToTick == 0 ? 1 : timeTakenToTick)));
        System.arraycopy(tickSummary, 1, tickSummary, 0, tickSummary.length - 1);
        tickSummary[tickSummary.length - 1] = tick;
    }

    /**
     * Appends a callback-duration sample in milliseconds to the supplied window.
     * The duration arrives as a {@code float} in nanoseconds, so precision may already
     * be reduced before conversion; the oldest sample is discarded.
     */
    private void updateMSPT(float timeTakenToTick, float[] msptSummary) {
        System.arraycopy(msptSummary, 1, msptSummary, 0, msptSummary.length - 1);
        // Convert nanoseconds into the milliseconds reported by getMSPT.
        msptSummary[msptSummary.length - 1] = timeTakenToTick / 1000000f;
    }

    /**
     * Prevents future tick admission and wakes the loop if it is parked.
     * This method can be called from another thread and returns without
     * waiting for callbacks or cleanup. Work already admitted may still finish,
     * and the loop invokes its stop callback only after normal exit.
     *
     * <p>
     * {@code stop()} clears {@code isRunning} before wake-up so a returning park
     * observes the stop request before attempting more idle work. Repeated calls retain the
     * stopped state, and the instance never resets itself for another run.
     */
    public void stop() {
        // Publish the stop request before supplying a wake-up permit; reversing
        // them could let the loop return to parking with no further notification.
        isRunning.set(false);
        wakeUp();
    }

    /**
     * Returns whether {@code isRunning} still permits future loop ticks.
     * It starts {@code true} before any thread enters {@code startLoop()} and
     * becomes {@code false} when stopped, independently of whether an admitted
     * callback has finished.
     */
    public boolean isRunning() {
        return isRunning.get();
    }

    /**
     * {@code GameLoopBuilder} collects callback references, target frequency,
     * and the initial counter for a new loop instance.
     */
    public static class GameLoopBuilder {
        // onStart, onTick, and onStop default to empty callbacks; onIdle defaults
        // to null and is skipped. Setters retain supplied references without validation.
        /** Default startup callback. */
        private Runnable onStart = () -> {
        };
        /** Default per-tick callback. */
        private Consumer<GameLoop> onTick = gameLoop -> {
        };
        /** Optional idle callback. */
        private Runnable onIdle;
        /** Default shutdown callback. */
        private Runnable onStop = () -> {
        };
        /** Target ticks per second. */
        private int loopCountPerSec = 20;
        /** Initial tick counter value. */
        private long currentTick = 0;

        /**
         * Retains the startup callback and returns this builder for chaining.
         * The loop invokes it before checking tick admission; {@code null} is accepted
         * here but fails when startup attempts invocation.
         */
        public GameLoopBuilder onStart(Runnable onStart) {
            this.onStart = onStart;
            return this;
        }

        /**
         * Retains the per-tick callback and returns this builder for chaining.
         * The callback receives its loop before the tick counter increments;
         * {@code null} fails when an admitted tick attempts invocation.
         */
        public GameLoopBuilder onTick(Consumer<GameLoop> onTick) {
            this.onTick = onTick;
            return this;
        }

        /**
         * Retains optional idle work and returns this builder for chaining.
         * A {@code null} callback disables idle work. Otherwise the loop invokes this callback
         * after parking returns while still running, including ordinary timeout
         * and spurious wakeups.
         */
        public GameLoopBuilder onIdle(Runnable onIdle) {
            this.onIdle = onIdle;
            return this;
        }

        /**
         * Retains the stop callback and returns this builder for chaining.
         * {@code onStop} runs after normal loop exit, but uncaught callback
         * failures can bypass it; {@code null} fails if normal exit attempts invocation.
         */
        public GameLoopBuilder onStop(Runnable onStop) {
            this.onStop = onStop;
            return this;
        }

        /**
         * Sets the target tick rate and returns this builder for chaining.
         * Values outside one through {@code 1024} throw {@code IllegalArgumentException};
         * the target also determines the number of retained timing samples.
         */
        public GameLoopBuilder loopCountPerSec(int loopCountPerSec) {
            Preconditions.checkArgument(loopCountPerSec > 0 && loopCountPerSec <= 1024);
            this.loopCountPerSec = loopCountPerSec;
            return this;
        }

        /**
         * Sets the initial tick counter and returns this builder for chaining.
         * Negative values are accepted, and the first tick callback observes
         * this value before the loop increments it.
         */
        public GameLoopBuilder currentTick(long currentTick) {
            this.currentTick = currentTick;
            return this;
        }

        /**
         * Creates a loop with fresh counters and sample arrays using these settings.
         * Callback references remain shared with this builder, while later
         * setter calls do not reconfigure a previously built loop.
         */
        public GameLoop build() {
            return new GameLoop(onStart, onTick, onIdle, onStop, loopCountPerSec, currentTick);
        }
    }
}
