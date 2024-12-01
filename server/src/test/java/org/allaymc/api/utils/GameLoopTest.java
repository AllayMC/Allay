package org.allaymc.api.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
class GameLoopTest {
    @Test
    void testLoop() {
        var counter = new AtomicInteger(0);
        GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick((loop -> {
                    if (counter.incrementAndGet() == 100)
                        loop.stop();
                }))
                .build()
                .startLoop();
        assertEquals(100, counter.get());
    }

    @SneakyThrows
    @Test
    void testSlowLoop() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onTick((loop -> {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < 10000; i++) {
                        list.add((int) Math.ceil(Math.random() * 10));
                        Collections.sort(list);
                    }
                    if (loop.getTick() == 20) {
                        loop.stop();
                    }
                }))
                .build();
        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();
        assertTrue(gameLoop.getTPS() <= 20);
    }
}