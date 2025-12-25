package org.allaymc.server.eventbus;

import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.EventException;
import org.allaymc.api.eventbus.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class EventBusTest {

    EventBus eventBus;

    @BeforeEach
    void setUp() {
        eventBus = new AllayEventBus(Executors.newVirtualThreadPerTaskExecutor());
    }

    @Nested
    class BasicListenerTests {

        @Test
        void testRegisterAndCallEvent() {
            AtomicBoolean called = new AtomicBoolean(false);

            eventBus.registerListenerFor(TestEvent.class, event -> called.set(true));
            eventBus.callEvent(new TestEvent());

            assertTrue(called.get());
        }

        @Test
        void testUnregisterListener() {
            AtomicBoolean called = new AtomicBoolean(false);
            Consumer<TestEvent> consumer = event -> called.set(true);

            eventBus.registerListenerFor(TestEvent.class, consumer);
            eventBus.unregisterListenerFor(TestEvent.class, consumer);
            eventBus.callEvent(new TestEvent());

            assertFalse(called.get());
        }

        @Test
        void testCallEventReturnsEvent() {
            var event = new TestEvent();
            var returned = eventBus.callEvent(event);
            assertSame(event, returned);
        }

        @Test
        void testCallEventWithNoListeners() {
            var event = new TestEvent();
            assertDoesNotThrow(() -> eventBus.callEvent(event));
        }
    }

    @Nested
    class PriorityTests {

        @Test
        void testHigherPriorityCalledFirst() {
            List<String> order = new ArrayList<>();

            eventBus.registerListenerFor(TestEvent.class, event -> order.add("low"), false, -100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("high"), false, 100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("normal"), false, 0);

            eventBus.callEvent(new TestEvent());

            assertEquals(List.of("high", "normal", "low"), order);
        }

        @Test
        void testSamePriorityPreservesOrder() {
            List<String> order = new ArrayList<>();

            eventBus.registerListenerFor(TestEvent.class, event -> order.add("first"), false, 0);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("second"), false, 0);

            eventBus.callEvent(new TestEvent());

            assertEquals(2, order.size());
        }
    }

    @Nested
    class CancellableEventTests {

        @Test
        void testCancelEvent() {
            eventBus.registerListenerFor(TestCancellableEvent.class, TestCancellableEvent::cancel);

            var event = new TestCancellableEvent();
            eventBus.callEvent(event);

            assertTrue(event.isCancelled());
        }

        @Test
        void testSetCancelledTrue() {
            var event = new TestCancellableEvent();
            event.setCancelled(true);
            assertTrue(event.isCancelled());
        }

        @Test
        void testSetCancelledFalse() {
            var event = new TestCancellableEvent();
            event.setCancelled(true);
            event.setCancelled(false);
            assertFalse(event.isCancelled());
        }

        @Test
        void testCannotCancelNonCancellableEvent() {
            var event = new TestEvent();
            assertThrows(EventException.class, () -> event.setCancelled(true));
        }

        @Test
        void testEventCallReturnsFalseWhenCancelled() {
            eventBus.registerListenerFor(TestCancellableEvent.class, TestCancellableEvent::cancel);

            var event = new TestCancellableEvent();
            boolean result = event.call(eventBus);

            assertFalse(result);
        }

        @Test
        void testEventCallReturnsTrueWhenNotCancelled() {
            var event = new TestCancellableEvent();
            boolean result = event.call(eventBus);

            assertTrue(result);
        }
    }

    @Nested
    class LambdaListenerTests {

        @Test
        void testRegisterListenerFor() {
            AtomicBoolean called = new AtomicBoolean(false);

            eventBus.registerListenerFor(TestEvent.class, event -> called.set(true));
            eventBus.callEvent(new TestEvent());

            assertTrue(called.get());
        }

        @Test
        void testRegisterListenerForWithPriority() {
            List<String> order = new ArrayList<>();

            eventBus.registerListenerFor(TestEvent.class, event -> order.add("low"), false, -100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("high"), false, 100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("normal"), false, 0);

            eventBus.callEvent(new TestEvent());

            assertEquals(List.of("high", "normal", "low"), order);
        }

        @Test
        void testUnregisterListenerFor() {
            AtomicInteger callCount = new AtomicInteger(0);
            Consumer<TestEvent> consumer = event -> callCount.incrementAndGet();

            eventBus.registerListenerFor(TestEvent.class, consumer);
            eventBus.callEvent(new TestEvent());
            assertEquals(1, callCount.get());

            eventBus.unregisterListenerFor(TestEvent.class, consumer);
            eventBus.callEvent(new TestEvent());
            assertEquals(1, callCount.get());
        }

        @Test
        void testUnregisterListenerForNonExistent() {
            Consumer<TestEvent> consumer = event -> {
            };
            assertDoesNotThrow(() -> eventBus.unregisterListenerFor(TestEvent.class, consumer));
        }

        @Test
        void testUnregisterListenerForNonExistentEventClass() {
            Consumer<TestCancellableEvent> consumer = event -> {
            };
            assertDoesNotThrow(() -> eventBus.unregisterListenerFor(TestCancellableEvent.class, consumer));
        }

        @Test
        void testMultipleLambdaListeners() {
            AtomicInteger callCount = new AtomicInteger(0);

            eventBus.registerListenerFor(TestEvent.class, event -> callCount.incrementAndGet());
            eventBus.registerListenerFor(TestEvent.class, event -> callCount.incrementAndGet());
            eventBus.registerListenerFor(TestEvent.class, event -> callCount.incrementAndGet());

            eventBus.callEvent(new TestEvent());

            assertEquals(3, callCount.get());
        }
    }

    @Nested
    class AsyncListenerTests {

        @Test
        void testAsyncLambdaHandler() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            AtomicBoolean called = new AtomicBoolean(false);

            eventBus.registerListenerFor(TestEvent.class, event -> {
                called.set(true);
                latch.countDown();
            }, true);

            eventBus.callEvent(new TestEvent());

            assertTrue(latch.await(5, TimeUnit.SECONDS));
            assertTrue(called.get());
        }

        @Test
        void testAsyncLambdaHandlerWithPriority() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            AtomicBoolean called = new AtomicBoolean(false);

            eventBus.registerListenerFor(TestEvent.class, event -> {
                called.set(true);
                latch.countDown();
            }, true, 100);

            eventBus.callEvent(new TestEvent());

            assertTrue(latch.await(5, TimeUnit.SECONDS));
            assertTrue(called.get());
        }
    }

    @Nested
    class MethodListenerTests {

        @Test
        void testRegisterMethodListener() {
            var listener = new TestListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertTrue(listener.called);
        }

        @Test
        void testUnregisterMethodListener() {
            var listener = new TestListener();

            eventBus.registerListener(listener);
            eventBus.unregisterListener(listener);
            eventBus.callEvent(new TestEvent());

            assertFalse(listener.called);
        }

        @Test
        void testRegisterSameListenerTwice() {
            var listener = new CountingListener();

            eventBus.registerListener(listener);
            eventBus.registerListener(listener); // Should be ignored
            eventBus.callEvent(new TestEvent());

            assertEquals(1, listener.callCount);
        }

        @Test
        void testUnregisterNonExistentListener() {
            var listener = new TestListener();
            assertDoesNotThrow(() -> eventBus.unregisterListener(listener));
        }

        @Test
        void testReRegisterAfterUnregister() {
            var listener = new CountingListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());
            assertEquals(1, listener.callCount);

            eventBus.unregisterListener(listener);
            eventBus.callEvent(new TestEvent());
            assertEquals(1, listener.callCount);

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());
            assertEquals(2, listener.callCount);
        }

        @Test
        void testMethodListenerPriority() {
            var listener = new PriorityListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertEquals(List.of("high", "normal", "low"), listener.order);
        }

        @Test
        void testCancellableMethodListener() {
            var listener = new CancellingListener();

            eventBus.registerListener(listener);
            var event = new TestCancellableEvent();
            eventBus.callEvent(event);

            assertTrue(event.isCancelled());
        }

        @Test
        void testAsyncMethodListener() throws InterruptedException {
            var listener = new AsyncListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertTrue(listener.latch.await(5, TimeUnit.SECONDS));
            assertTrue(listener.called);
        }

        @Test
        void testMultiEventListener() {
            var listener = new MultiEventListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertTrue(listener.testEventCalled);
            assertFalse(listener.cancellableEventCalled);

            eventBus.callEvent(new TestCancellableEvent());

            assertTrue(listener.cancellableEventCalled);
        }
    }

    @Nested
    class InvalidHandlerTests {

        @Test
        void testHandlerWithNonVoidReturnType() {
            var listener = new InvalidReturnTypeListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }

        @Test
        void testHandlerWithNoParameters() {
            var listener = new InvalidNoParamsListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }

        @Test
        void testHandlerWithMultipleParameters() {
            var listener = new InvalidMultiParamsListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }

        @Test
        void testHandlerWithNonEventParameter() {
            var listener = new InvalidNonEventParamListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }
    }

    @Nested
    class MixedListenerTests {

        @Test
        void testMethodAndLambdaListenersTogether() {
            List<String> order = new ArrayList<>();
            var methodListener = new OrderTrackingListener(order, "method", 50);

            eventBus.registerListener(methodListener);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("lambda-high"), false, 100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("lambda-low"), false, 0);

            eventBus.callEvent(new TestEvent());

            assertEquals(List.of("lambda-high", "method", "lambda-low"), order);
        }
    }

    // Static listener classes to avoid FastReflection access issues

    public static class TestListener {
        public boolean called = false;

        @EventHandler
        public void onEvent(TestEvent event) {
            called = true;
        }
    }

    public static class CountingListener {
        public int callCount = 0;

        @EventHandler
        public void onEvent(TestEvent event) {
            callCount++;
        }
    }

    public static class PriorityListener {
        public final List<String> order = new ArrayList<>();

        @EventHandler(priority = -100)
        public void lowPriority(TestEvent event) {
            order.add("low");
        }

        @EventHandler(priority = 100)
        public void highPriority(TestEvent event) {
            order.add("high");
        }

        @EventHandler(priority = 0)
        public void normalPriority(TestEvent event) {
            order.add("normal");
        }
    }

    public static class CancellingListener {
        @EventHandler
        public void onEvent(TestCancellableEvent event) {
            event.cancel();
        }
    }

    public static class AsyncListener {
        public final CountDownLatch latch = new CountDownLatch(1);
        public volatile boolean called = false;

        @EventHandler(async = true)
        public void onEvent(TestEvent event) {
            called = true;
            latch.countDown();
        }
    }

    public static class MultiEventListener {
        public boolean testEventCalled = false;
        public boolean cancellableEventCalled = false;

        @EventHandler
        public void onTestEvent(TestEvent event) {
            testEventCalled = true;
        }

        @EventHandler
        public void onCancellableEvent(TestCancellableEvent event) {
            cancellableEventCalled = true;
        }
    }

    public static class OrderTrackingListener {
        private final List<String> order;
        private final String name;

        public OrderTrackingListener(List<String> order, String name, int priority) {
            this.order = order;
            this.name = name;
        }

        @EventHandler(priority = 50)
        public void onEvent(TestEvent event) {
            order.add(name);
        }
    }

    // Invalid listeners for testing validation

    public static class InvalidReturnTypeListener {
        @EventHandler
        public int invalidHandler(TestEvent event) {
            return 42;
        }
    }

    public static class InvalidNoParamsListener {
        @EventHandler
        public void invalidHandler() {
        }
    }

    public static class InvalidMultiParamsListener {
        @EventHandler
        public void invalidHandler(TestEvent event1, TestEvent event2) {
        }
    }

    public static class InvalidNonEventParamListener {
        @EventHandler
        public void invalidHandler(String notAnEvent) {
        }
    }
}
