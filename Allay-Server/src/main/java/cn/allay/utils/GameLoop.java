package cn.allay.utils;

import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/14 <br>
 * Allay Project <br>
 */
public final class GameLoop {

    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    @Getter
    private final int loopCountPerSec;

    private final Runnable onStart;
    private final Consumer<GameLoop> onTick;
    private final Runnable onStop;
    @Getter
    private long currentTick;
    @Getter
    private int currentTps;

    private GameLoop(Runnable onStart, Consumer<GameLoop> onTick, Runnable onStop, int loopCountPerSec) {
        if (loopCountPerSec <= 0)
            throw new IllegalArgumentException("Loop count per second must be greater than 0! (loopCountPerSec=" + loopCountPerSec + ")");
        this.onStart = onStart;
        this.onTick = onTick;
        this.onStop = onStop;
        this.loopCountPerSec = loopCountPerSec;
    }

    public void startLoop() {
        onStart.run();
        long nextTpsRecording = 0;
        long nanoSleepTime = 0;
        while (this.isRunning.get()) {
            long idealNanoSleepPerTick = TimeUnit.SECONDS.toNanos(1) / loopCountPerSec;

            // Figure out how long it took to tick
            long startTickTime = System.nanoTime();
            onTick.accept(this);
            currentTick++;
            currentTps++;
            long timeTakenToTick = System.nanoTime() - startTickTime;

            // Sleep for the ideal time but take into account the time spent running the tick
            nanoSleepTime += idealNanoSleepPerTick - timeTakenToTick;
            long sleepStart = System.nanoTime();
            try {
                Thread.sleep(Math.max(TimeUnit.NANOSECONDS.toMillis(nanoSleepTime), 0));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                onStop.run();
                return;
            }

            // How long did it actually take to sleep?
            // If we didn't sleep for the correct amount,
            // take that into account for the next sleep by
            // leaving extra/less for the next sleep.
            nanoSleepTime -= System.nanoTime() - sleepStart;

            // Record TPS every second
            if (System.nanoTime() > nextTpsRecording) {
                currentTps = 0;
                nextTpsRecording = System.nanoTime() + TimeUnit.SECONDS.toNanos(1);
            }
        }
        onStop.run();
    }

    public void stop() {
        this.isRunning.set(false);
    }

    public boolean isRunning() {
        return isRunning.get();
    }

    // Builder pattern

    public static GameLoopBuilder builder() {
        return new GameLoopBuilder();
    }

    public static class GameLoopBuilder {
        private Runnable onStart = () -> {};
        private Consumer<GameLoop> onTick = (gameLoop) -> {};
        private Runnable onStop = () -> {};
        private int loopCountPerSec = 20;

        public GameLoopBuilder onStart(Runnable onStart) {
            this.onStart = onStart;
            return this;
        }

        public GameLoopBuilder onTick(Consumer<GameLoop> onTick) {
            this.onTick = onTick;
            return this;
        }

        public GameLoopBuilder onStop(Runnable onStop) {
            this.onStop = onStop;
            return this;
        }

        public GameLoopBuilder loopCountPerSec(int loopCountPerSec) {
            this.loopCountPerSec = loopCountPerSec;
            return this;
        }

        public GameLoop build() {
            return new GameLoop(onStart, onTick, onStop, loopCountPerSec);
        }
    }
}
