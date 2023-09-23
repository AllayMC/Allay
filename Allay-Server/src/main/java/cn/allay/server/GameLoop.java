package cn.allay.server;

import cn.allay.api.utils.MathUtils;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Allay Project 2023/4/14
 *
 * @author daoge_cmd
 */
@Slf4j
public final class GameLoop {
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    @Getter
    private final int loopCountPerSec;

    private final Runnable onStart;
    private final Consumer<GameLoop> onTick;
    private final Runnable onStop;
    @Getter
    private long currentTick;
    private final float[] tickAverage = {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

    public float getCurrentTps() {
        float sum = 0;
        int count = this.tickAverage.length;
        for (float aTickAverage : this.tickAverage) {
            sum += aTickAverage;
        }
        return (float) MathUtils.round(sum / count, 2);
    }

    private GameLoop(Runnable onStart, Consumer<GameLoop> onTick, Runnable onStop, int loopCountPerSec) {
        if (loopCountPerSec <= 0)
            throw new IllegalArgumentException("Loop count per second must be greater than 0! (loopCountPerSec=" + loopCountPerSec + ")");
        this.onStart = onStart;
        this.onTick = onTick;
        this.onStop = onStop;
        this.loopCountPerSec = loopCountPerSec;
    }

    public static GameLoopBuilder builder() {
        return new GameLoopBuilder();
    }

    public void startLoop() {
        onStart.run();
        long nanoSleepTime = 0;
        long idealNanoSleepPerTick = 1000000000 / loopCountPerSec;
        while (this.isRunning.get()) {
            // Figure out how long it took to tick
            long startTickTime = System.nanoTime();
            onTick.accept(this);
            currentTick++;
            long timeTakenToTick = System.nanoTime() - startTickTime;
            if (timeTakenToTick == 0) timeTakenToTick = 1;
            float tick = (float) Math.max(0, Math.min(20, 1000000000 / timeTakenToTick));
            System.arraycopy(this.tickAverage, 1, this.tickAverage, 0, this.tickAverage.length - 1);
            this.tickAverage[this.tickAverage.length - 1] = tick;

            long sumOperateTime = System.nanoTime() - startTickTime;
            // Sleep for the ideal time but take into account the time spent running the tick
            nanoSleepTime += idealNanoSleepPerTick - sumOperateTime;
            long sleepStart = System.nanoTime();
            try {
                if (nanoSleepTime > 0) {
                    //noinspection BusyWait
                    Thread.sleep(TimeUnit.NANOSECONDS.toMillis(nanoSleepTime));
                }
            } catch (InterruptedException exception) {
                log.error("GameLoop interrupted", exception);
                onStop.run();
                return;
            }
            // How long did it actually take to sleep?
            // If we didn't sleep for the correct amount,
            // take that into account for the next sleep by
            // leaving extra/less for the next sleep.
            nanoSleepTime -= System.nanoTime() - sleepStart;
        }
        onStop.run();
    }

    public void stop() {
        this.isRunning.set(false);
    }

    // Builder pattern

    public boolean isRunning() {
        return isRunning.get();
    }

    public static class GameLoopBuilder {
        private Runnable onStart = () -> {
        };
        private Consumer<GameLoop> onTick = (gameLoop) -> {
        };
        private Runnable onStop = () -> {
        };
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
            Preconditions.checkArgument(loopCountPerSec > 0 && loopCountPerSec <= 1024);
            this.loopCountPerSec = loopCountPerSec;
            return this;
        }

        public GameLoop build() {
            return new GameLoop(onStart, onTick, onStop, loopCountPerSec);
        }
    }
}
