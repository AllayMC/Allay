package org.allaymc.server.eventbus;

import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.EventException;
import org.allaymc.api.eventbus.EventHandler;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class EventBusTest {
    private final EventBus eventBus = new AllayEventBus(Executors.newVirtualThreadPerTaskExecutor());
    private String str;
    private boolean bool;

    {
        eventBus.registerListener(this);
    }

    @EventHandler(priority = 100)
    public void highPriorityHandler(TestEvent event) {
        str = "highPriorityHandler";
    }


    @EventHandler(priority = -100)
    public void lowPriorityHandler(TestEvent event) {
        str = "lowPriorityHandler";
    }

    @Test
    void testHandlerPriority() {
        eventBus.callEvent(new TestEvent());
        assertEquals("lowPriorityHandler", str);
    }

    @EventHandler
    public void cancelHandler(TestCancellableEvent event) {
        event.cancel();
    }

    @Test
    void testEventCancel() {
        TestCancellableEvent event = new TestCancellableEvent();
        eventBus.callEvent(event);
        assertTrue(event.isCancelled());
    }

    @Test
    void testUnregister() {
        eventBus.unregisterListener(this);
        var event = new TestCancellableEvent();
        eventBus.callEvent(event);
        assertFalse(event.isCancelled());
        eventBus.registerListener(this);
    }

    @Test
    void testCannotCancelNormalEvent() {
        var event = new TestEvent();
        assertThrows(EventException.class, () -> event.setCancelled(true));
    }
}
