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
 * {@code EventBusTest} exercises listener discovery, priority dispatch,
 * cancellation, and callback
 * mutation against a fresh event bus. Dedicated fixtures expose invocation
 * state, while asynchronous cases use latches or executor barriers to observe
 * work after submission.
 *
 * @author daoge_cmd
 */
class EventBusTest {

    /**
     * {@code eventBus} holds the bus created before each test. Listener
     * registrations remain local to that scenario rather than sharing server state.
     */
    EventBus eventBus;

    /**
     * Creates a listener-free bus backed by a new virtual-thread executor.
     * Tests call this bus directly, so dispatch behavior can be exercised
     * independently of the server's global event bus.
     */
    @BeforeEach
    void setUp() {
        eventBus = new AllayEventBus(Executors.newVirtualThreadPerTaskExecutor());
    }

    /**
     * {@code BasicListenerTests} groups registration, removal, identity, and
     * empty-dispatch scenarios for the event bus.
     */
    @Nested
    class BasicListenerTests {

        /**
         * Registers a consumer for the test event's exact class.
         * A subsequent synchronous dispatch must invoke that consumer and expose
         * its state change before the assertion runs.
         */
        @Test
        void testRegisterAndCallEvent() {
            AtomicBoolean called = new AtomicBoolean(false);

            eventBus.registerListenerFor(TestEvent.class, event -> called.set(true));
            eventBus.callEvent(new TestEvent());

            assertTrue(called.get());
        }

        /**
         * Registers and then removes the same consumer instance before dispatch.
         * The later event must leave its flag unchanged because no matching
         * consumer registration remains in the bus.
         */
        @Test
        void testUnregisterListener() {
            AtomicBoolean called = new AtomicBoolean(false);
            Consumer<TestEvent> consumer = event -> called.set(true);

            eventBus.registerListenerFor(TestEvent.class, consumer);
            eventBus.unregisterListenerFor(TestEvent.class, consumer);
            eventBus.callEvent(new TestEvent());

            assertFalse(called.get());
        }

        /**
         * Dispatches a new event and retains the returned reference.
         * The bus must return that same event instance rather than replacing
         * it with a copy or another result object.
         */
        @Test
        void testCallEventReturnsEvent() {
            var event = new TestEvent();
            var returned = eventBus.callEvent(event);
            assertSame(event, returned);
        }

        /**
         * Dispatches an event through a bus without any registered handlers.
         * An empty listener registry must accept the event without throwing
         * merely because no matching callbacks are available.
         */
        @Test
        void testCallEventWithNoListeners() {
            var event = new TestEvent();
            assertDoesNotThrow(() -> eventBus.callEvent(event));
        }
    }

    /**
     * {@code PriorityTests} groups priority scenarios for synchronous consumer
     * dispatch within one event class.
     */
    @Nested
    class PriorityTests {

        /**
         * Registers consumers with low, high, and default priorities in mixed
         * order. Dispatch must visit higher numerical priorities first regardless
         * of the order those registrations were added.
         */
        @Test
        void testHigherPriorityCalledFirst() {
            List<String> order = new ArrayList<>();

            eventBus.registerListenerFor(TestEvent.class, event -> order.add("low"), false, -100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("high"), false, 100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("normal"), false, 0);

            eventBus.callEvent(new TestEvent());

            assertEquals(List.of("high", "normal", "low"), order);
        }

        /**
         * Registers two consumers with the same priority and dispatches once.
         * This assertion verifies that both handlers run; it does not compare
         * their recorded order despite the test's name.
         */
        @Test
        void testSamePriorityPreservesOrder() {
            List<String> order = new ArrayList<>();

            eventBus.registerListenerFor(TestEvent.class, event -> order.add("first"), false, 0);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("second"), false, 0);

            eventBus.callEvent(new TestEvent());

            assertEquals(2, order.size());
        }
    }

    /**
     * {@code CancellableEventTests} groups cancellation-state changes and the
     * boolean results returned by event calls.
     */
    @Nested
    class CancellableEventTests {

        /**
         * Registers a handler that cancels a supported event during dispatch.
         * The original event object must expose that cancellation decision after
         * the bus has invoked its synchronous handler.
         */
        @Test
        void testCancelEvent() {
            eventBus.registerListenerFor(TestCancellableEvent.class, TestCancellableEvent::cancel);

            var event = new TestCancellableEvent();
            eventBus.callEvent(event);

            assertTrue(event.isCancelled());
        }

        /**
         * Sets cancellation directly on an event that supports the cancellable
         * contract. Its getter must immediately reflect the {@code true} value without
         * requiring the event to be dispatched first.
         */
        @Test
        void testSetCancelledTrue() {
            var event = new TestCancellableEvent();
            event.setCancelled(true);
            assertTrue(event.isCancelled());
        }

        /**
         * Cancels a supported event and then explicitly clears that decision.
         * Cancellation must be reversible through the setter rather than becoming
         * a permanent state after its first {@code true} value.
         */
        @Test
        void testSetCancelledFalse() {
            var event = new TestCancellableEvent();
            event.setCancelled(true);
            event.setCancelled(false);
            assertFalse(event.isCancelled());
        }

        /**
         * Requests cancellation on the fixture that lacks the cancellable marker.
         * The operation must throw the event-specific exception rather than
         * silently storing a cancellation flag on that unsupported type.
         */
        @Test
        void testCannotCancelNonCancellableEvent() {
            var event = new TestEvent();
            assertThrows(EventException.class, () -> event.setCancelled(true));
        }

        /**
         * Calls a cancellable event through a bus containing a cancelling
         * consumer. The convenience call must return {@code false} after synchronous
         * dispatch has set the event's cancellation decision.
         */
        @Test
        void testEventCallReturnsFalseWhenCancelled() {
            eventBus.registerListenerFor(TestCancellableEvent.class, TestCancellableEvent::cancel);

            var event = new TestCancellableEvent();
            boolean result = event.call(eventBus);

            assertFalse(result);
        }

        /**
         * Calls a cancellable event with no handler changing its initial state.
         * The convenience call must return {@code true} because merely supporting
         * cancellation does not make the new event cancelled.
         */
        @Test
        void testEventCallReturnsTrueWhenNotCancelled() {
            var event = new TestCancellableEvent();
            boolean result = event.call(eventBus);

            assertTrue(result);
        }
    }

    /**
     * {@code LambdaListenerTests} groups consumer registration overloads,
     * duplicate callbacks, and removal behavior.
     */
    @Nested
    class LambdaListenerTests {

        /**
         * Registers a consumer through the overload with default dispatch
         * settings. Calling the matching event must invoke it synchronously
         * before the test inspects the recorded flag.
         */
        @Test
        void testRegisterListenerFor() {
            AtomicBoolean called = new AtomicBoolean(false);

            eventBus.registerListenerFor(TestEvent.class, event -> called.set(true));
            eventBus.callEvent(new TestEvent());

            assertTrue(called.get());
        }

        /**
         * Uses the explicit priority overload to register three synchronous
         * consumers. Their recorded calls must follow descending priority rather
         * than the order used to register their callbacks.
         */
        @Test
        void testRegisterListenerForWithPriority() {
            List<String> order = new ArrayList<>();

            eventBus.registerListenerFor(TestEvent.class, event -> order.add("low"), false, -100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("high"), false, 100);
            eventBus.registerListenerFor(TestEvent.class, event -> order.add("normal"), false, 0);

            eventBus.callEvent(new TestEvent());

            assertEquals(List.of("high", "normal", "low"), order);
        }

        /**
         * Dispatches once to a consumer, removes its registration, and dispatches
         * again. The counter must retain only the first invocation, demonstrating
         * that later calls use the updated handler list.
         */
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

        /**
         * Attempts to remove a consumer that was never registered for its event.
         * Removal must tolerate the missing registration and return without
         * requiring callers to check the bus first.
         */
        @Test
        void testUnregisterListenerForNonExistent() {
            Consumer<TestEvent> consumer = event -> {
            };
            assertDoesNotThrow(() -> eventBus.unregisterListenerFor(TestEvent.class, consumer));
        }

        /**
         * Removes a consumer for an event class absent from the registry.
         * The missing class entry must be treated as an empty removal rather
         * than causing an exception during handler lookup.
         */
        @Test
        void testUnregisterListenerForNonExistentEventClass() {
            Consumer<TestCancellableEvent> consumer = event -> {
            };
            assertDoesNotThrow(() -> eventBus.unregisterListenerFor(TestCancellableEvent.class, consumer));
        }

        /**
         * Registers three separate consumers for the same exact event class.
         * One dispatch must visit every registration, producing three counter
         * increments instead of replacing earlier callbacks with later ones.
         */
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

    /**
     * {@code AsyncListenerTests} groups executor-backed consumer scenarios
     * observed through explicit callback completion signals.
     */
    @Nested
    class AsyncListenerTests {

        /**
         * Registers an asynchronous consumer with the default priority overload.
         * Its latch lets the test observe actual execution after event dispatch
         * returns, rather than assuming submission has already completed.
         */
        @Test
        void testAsyncLambdaHandler() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            AtomicBoolean called = new AtomicBoolean(false);

            // Write the observation before signalling the latch so completion
            // hands the test a state change produced by the callback, not merely its submission.
            eventBus.registerListenerFor(TestEvent.class, event -> {
                called.set(true);
                latch.countDown();
            }, true);

            eventBus.callEvent(new TestEvent());

            // Wait for the callback's signal before inspecting its flag; the
            // bounded wait reports missing executor progress without assuming dispatch waits for completion.
            assertTrue(latch.await(5, TimeUnit.SECONDS));
            assertTrue(called.get());
        }

        /**
         * Registers an asynchronous consumer with an explicit high priority.
         * The completion signal verifies invocation through that overload without
         * claiming an execution order relative to other asynchronous handlers.
         */
        @Test
        void testAsyncLambdaHandlerWithPriority() throws InterruptedException {
            CountDownLatch latch = new CountDownLatch(1);
            AtomicBoolean called = new AtomicBoolean(false);

            // Write the observation before signalling the latch so completion
            // hands the test a state change produced by the callback, not merely its submission.
            eventBus.registerListenerFor(TestEvent.class, event -> {
                called.set(true);
                latch.countDown();
            }, true, 100);

            eventBus.callEvent(new TestEvent());

            // Wait for the callback's signal before inspecting its flag; the
            // bounded wait reports missing executor progress without assuming dispatch waits for completion.
            assertTrue(latch.await(5, TimeUnit.SECONDS));
            assertTrue(called.get());
        }
    }

    /**
     * {@code MethodListenerTests} groups reflective listener discovery, lifecycle,
     * cancellation filtering, and asynchronous method-handler scenarios.
     */
    @Nested
    class MethodListenerTests {

        /**
         * Registers an object with a correctly annotated handler method.
         * Dispatch must discover that method and update the listener's flag
         * before the synchronous event call returns to the test.
         */
        @Test
        void testRegisterMethodListener() {
            var listener = new TestListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertTrue(listener.called);
        }

        /**
         * Removes a method listener after registration and before any dispatch.
         * Its callback flag must remain unchanged when the matching event is
         * later raised through the same bus.
         */
        @Test
        void testUnregisterMethodListener() {
            var listener = new TestListener();

            eventBus.registerListener(listener);
            eventBus.unregisterListener(listener);
            eventBus.callEvent(new TestEvent());

            assertFalse(listener.called);
        }

        /**
         * Registers the same method-listener object twice before dispatch.
         * Duplicate suppression must retain a single handler registration so
         * one event increments the listener's counter only once.
         */
        @Test
        void testRegisterSameListenerTwice() {
            var listener = new CountingListener();

            eventBus.registerListener(listener);
            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertEquals(1, listener.callCount);
        }

        /**
         * Attempts to remove an object that has no indexed method handlers.
         * The bus must ignore this missing listener instead of requiring an
         * earlier successful registration for every removal call.
         */
        @Test
        void testUnregisterNonExistentListener() {
            var listener = new TestListener();
            assertDoesNotThrow(() -> eventBus.unregisterListener(listener));
        }

        /**
         * Registers, removes, and registers the same listener again across
         * separate dispatches. Removal must clear duplicate-tracking state so
         * the final registration can resume receiving events exactly once.
         */
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

        /**
         * Discovers three annotated methods carrying different handler priorities.
         * The bus must dispatch them in descending numerical priority regardless
         * of the order reflection uses to discover their declarations.
         */
        @Test
        void testMethodListenerPriority() {
            var listener = new PriorityListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertEquals(List.of("high", "normal", "low"), listener.order);
        }

        /**
         * Registers a method listener whose handler cancels its supplied event.
         * Dispatch must pass the original cancellable instance through reflection
         * so the caller observes the handler's decision afterward.
         */
        @Test
        void testCancellableMethodListener() {
            var listener = new CancellingListener();

            eventBus.registerListener(listener);
            var event = new TestCancellableEvent();
            eventBus.callEvent(event);

            assertTrue(event.isCancelled());
        }

        /**
         * Runs a high-priority cancelling method before a method that ignores
         * cancelled events. The first handler must execute, while the later
         * handler must leave its invocation flag unchanged.
         */
        @Test
        void testIgnoreCancelledMethodListener() {
            var listener = new IgnoreCancelledListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestCancellableEvent());

            assertTrue(listener.cancelled);
            assertFalse(listener.ignoreCancelledCalled);
        }

        /**
         * Blocks the executor while a lower-priority synchronous method cancels
         * an event already submitted to an asynchronous handler. When released,
         * the queued handler must recheck cancellation and skip its callback.
         */
        @Test
        void testAsyncIgnoreCancelledMethodListenerChecksWhenTaskRuns() throws Exception {
            var asyncExecutor = Executors.newSingleThreadExecutor();
            var blockerStarted = new CountDownLatch(1);
            var releaseBlocker = new CountDownLatch(1);

            // Occupy the only executor thread before dispatch, ensuring the
            // asynchronous handler cannot check cancellation until the synchronous cancelling method has finished.
            asyncExecutor.submit(() -> {
                blockerStarted.countDown();
                assertTrue(releaseBlocker.await(5, TimeUnit.SECONDS));
                return null;
            });
            // Wait for the blocker to take the worker before submitting the
            // event; otherwise scheduling could let the asynchronous callback run before cancellation.
            assertTrue(blockerStarted.await(5, TimeUnit.SECONDS));

            var localEventBus = new AllayEventBus(asyncExecutor);
            var listener = new AsyncIgnoreCancelledListener();

            try {
                localEventBus.registerListener(listener);

                var event = new TestCancellableEvent();
                localEventBus.callEvent(event);

                assertTrue(event.isCancelled());
                // Release the worker after synchronous cancellation on this
                // thread. The latch handoff makes that cancellation visible before
                // the queued handler performs its invocation-time check.
                releaseBlocker.countDown();

                // A sentinel submitted behind the handler on this single worker
                // completes only after the handler has either skipped or invoked its callback.
                var queuedTasksCompleted = asyncExecutor.submit(() -> {
                });
                queuedTasksCompleted.get(5, TimeUnit.SECONDS);

                assertFalse(listener.ignoreCancelledCalled);
            } finally {
                // Always release the worker before requesting shutdown, including
                // assertion failures, so this test cannot strand its executor inside the blocking fixture.
                releaseBlocker.countDown();
                asyncExecutor.shutdownNow();
            }
        }

        /**
         * Registers an annotated method configured for asynchronous execution.
         * Waiting on the listener's latch must expose its completed callback
         * state without assuming event dispatch waits for executor tasks.
         */
        @Test
        void testAsyncMethodListener() throws InterruptedException {
            var listener = new AsyncListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            // Observe the listener only after its completion signal, with a
            // bounded wait so failure to invoke the method cannot indefinitely block this test.
            assertTrue(listener.latch.await(5, TimeUnit.SECONDS));
            assertTrue(listener.called);
        }

        /**
         * Registers a listener whose annotated handler comes from an interface
         * default method. Discovery must include that inherited declaration and
         * invoke the listener's concrete state-changing implementation.
         */
        @Test
        void testInterfaceDefaultMethodHandler() {
            var listener = new DefaultMethodListener();

            eventBus.registerListener(listener);
            eventBus.callEvent(new TestEvent());

            assertTrue(listener.called);
        }

        /**
         * Registers one listener with methods for two distinct event classes.
         * Dispatching each class must select its matching method without
         * invoking the other class's handler during the first event call.
         */
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

    /**
     * {@code InvalidHandlerTests} groups rejected signatures that cannot satisfy
     * method-handler registration rules.
     */
    @Nested
    class InvalidHandlerTests {

        /**
         * Registers an annotated method that returns an integer instead of {@code void}.
         * The bus must reject this signature during registration before any
         * event is dispatched to the invalid listener.
         */
        @Test
        void testHandlerWithNonVoidReturnType() {
            var listener = new InvalidReturnTypeListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }

        /**
         * Registers an annotated method with no event parameter at all.
         * Signature validation must report the missing parameter as an event
         * registration error rather than accepting an unusable callback.
         */
        @Test
        void testHandlerWithNoParameters() {
            var listener = new InvalidNoParamsListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }

        /**
         * Registers an annotated method accepting two event parameters.
         * The bus must reject its arity because dispatch supplies exactly one
         * event instance to every registered method handler.
         */
        @Test
        void testHandlerWithMultipleParameters() {
            var listener = new InvalidMultiParamsListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }

        /**
         * Registers an annotated method whose only parameter is a string.
         * Validation must reject a parameter outside the {@code Event} hierarchy even
         * though the method otherwise has the required arity and return type.
         */
        @Test
        void testHandlerWithNonEventParameter() {
            var listener = new InvalidNonEventParamListener();
            assertThrows(EventException.class, () -> eventBus.registerListener(listener));
        }
    }

    /**
     * {@code ConcurrentModificationTests} groups registration changes performed
     * reentrantly by callbacks during event traversal.
     */
    @Nested
    class ConcurrentModificationTests {

        /**
         * Adds a consumer from inside another consumer's event callback.
         * The new handler must wait for a later dispatch because the current
         * traversal retains the handler array captured before registration changed.
         */
        @Test
        void testRegisterLambdaListenerDuringDispatch() {
            AtomicBoolean newListenerCalled = new AtomicBoolean(false);

            eventBus.registerListenerFor(TestEvent.class, event ->
                    eventBus.registerListenerFor(TestEvent.class, e -> newListenerCalled.set(true))
            );

            assertDoesNotThrow(() -> eventBus.callEvent(new TestEvent()));
            // The current traversal uses its earlier copy-on-write array, so
            // registration inside the callback cannot add another invocation to this same dispatch.
            assertFalse(newListenerCalled.get());

            eventBus.callEvent(new TestEvent());
            assertTrue(newListenerCalled.get());
        }

        /**
         * Registers a method listener from within a consumer callback.
         * The current dispatch must retain its existing traversal, while the
         * following dispatch must include the newly registered method handler.
         */
        @Test
        void testRegisterMethodListenerDuringDispatch() {
            var newListener = new TestListener();

            eventBus.registerListenerFor(TestEvent.class, event ->
                    eventBus.registerListener(newListener)
            );

            assertDoesNotThrow(() -> eventBus.callEvent(new TestEvent()));
            assertFalse(newListener.called);

            eventBus.callEvent(new TestEvent());
            assertTrue(newListener.called);
        }

        /**
         * Removes a consumer from a higher-priority callback during dispatch.
         * This assertion checks that mutation does not throw; it does not
         * establish how many times the removed consumer executes.
         */
        @Test
        void testUnregisterListenerDuringDispatch() {
            AtomicInteger callCount = new AtomicInteger(0);
            Consumer<TestEvent> victim = event -> callCount.incrementAndGet();

            eventBus.registerListenerFor(TestEvent.class, victim);
            eventBus.registerListenerFor(TestEvent.class, event ->
                    eventBus.unregisterListenerFor(TestEvent.class, victim), false, 100
            );

            assertDoesNotThrow(() -> eventBus.callEvent(new TestEvent()));
        }
    }

    /**
     * {@code MixedListenerTests} groups priority interactions between consumer
     * registrations and reflectively discovered method handlers.
     */
    @Nested
    class MixedListenerTests {

        /**
         * Combines a method handler with consumers above and below its priority.
         * Dispatch must order all three through one shared priority list,
         * regardless of how each handler was registered.
         */
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

    /**
     * {@code TestListener} records delivery to one public method handler on a
     * listener fixture.
     */
    public static class TestListener {
        /** Handler invocation observation flag. */
        public boolean called = false;

        /** Marks this listener as called when its registered handler receives an event. */
        @EventHandler
        public void onEvent(TestEvent event) {
            called = true;
        }
    }

    /**
     * {@code CountingListener} counts method-handler invocations across listener
     * registration and removal scenarios.
     */
    public static class CountingListener {
        /** Observed method-handler invocation count. */
        public int callCount = 0;

        /** Increments the observed invocation count for each matching dispatched event. */
        @EventHandler
        public void onEvent(TestEvent event) {
            callCount++;
        }
    }

    /**
     * {@code PriorityListener} records the dispatch sequence of three methods
     * with distinct priorities.
     */
    public static class PriorityListener {
        /** Observed synchronous dispatch order. */
        public final List<String> order = new ArrayList<>();

        /** Appends the low-priority label when this method receives an event. */
        @EventHandler(priority = -100)
        public void lowPriority(TestEvent event) {
            order.add("low");
        }

        /** Appends the high-priority label when this method receives an event. */
        @EventHandler(priority = 100)
        public void highPriority(TestEvent event) {
            order.add("high");
        }

        /** Appends the default-priority label when this method receives an event. */
        @EventHandler(priority = 0)
        public void normalPriority(TestEvent event) {
            order.add("normal");
        }
    }

    /**
     * {@code CancellingListener} supplies a method handler that cancels the
     * fixture's supported event type.
     */
    public static class CancellingListener {
        /**
         * Marks the supplied event as cancelled during synchronous dispatch.
         * The fixture uses a cancellable event type, allowing the test to
         * inspect that decision on the original object afterward.
         */
        @EventHandler
        public void onEvent(TestCancellableEvent event) {
            event.cancel();
        }
    }

    /**
     * {@code IgnoreCancelledListener} records whether priority dispatch reaches
     * cancelling and filtered methods. {@code cancelled} records the first
     * method's execution, while {@code ignoreCancelledCalled} exposes an
     * unexpected invocation after that method
     * has set the event's cancellation state.
     */
    public static class IgnoreCancelledListener {
        /** Cancelling handler invocation flag. */
        public boolean cancelled = false;
        /** Cancellation-filtered handler invocation flag. */
        public boolean ignoreCancelledCalled = false;

        /**
         * Records its own invocation and cancels the event before lower-priority
         * handlers are considered. The separate flag distinguishes this handler
         * running from a fixture that was cancelled before dispatch began.
         */
        @EventHandler(priority = 100)
        public void cancel(TestCancellableEvent event) {
            cancelled = true;
            event.cancel();
        }

        /** Records whether dispatch incorrectly invokes this cancellation-filtered test method. */
        @EventHandler(ignoreCancelled = true)
        public void ignoreCancelled(TestCancellableEvent event) {
            ignoreCancelledCalled = true;
        }
    }

    /**
     * {@code AsyncIgnoreCancelledListener} combines an asynchronous filtered
     * handler with a synchronous cancelling method. The asynchronous method's
     * higher priority submits it before
     * cancellation; the test's blocked executor delays its actual invocation.
     */
    public static class AsyncIgnoreCancelledListener {
        /**
         * {@code ignoreCancelledCalled} exposes asynchronous handler execution
         * to the test thread. Volatile visibility lets the assertion observe an
         * unexpected invocation after the executor barrier.
         */
        public volatile boolean ignoreCancelledCalled = false;

        /** Records an asynchronous invocation that the cancellation recheck should suppress. */
        @EventHandler(async = true, ignoreCancelled = true, priority = 100)
        public void ignoreCancelled(TestCancellableEvent event) {
            ignoreCancelledCalled = true;
        }

        /**
         * Cancels the event after the higher-priority asynchronous handler was
         * submitted. The test keeps that handler queued until this method
         * returns, making cancellation visible before the worker resumes.
         */
        @EventHandler
        public void cancel(TestCancellableEvent event) {
            event.cancel();
        }
    }

    /**
     * {@code AsyncListener} records asynchronous method delivery and signals its
     * completion to a waiting test. The handler sets {@code called} before
     * decrementing {@code latch} so a
     * successful wait observes the published invocation state.
     */
    public static class AsyncListener {
        /** First asynchronous invocation signal. */
        public final CountDownLatch latch = new CountDownLatch(1);
        /** Asynchronous handler invocation flag. */
        public volatile boolean called = false;

        /**
         * Publishes the invocation flag and releases the waiting test afterward.
         * The latch supplies a completion signal for the callback, allowing the
         * test to observe execution independently of event-dispatch return.
         */
        @EventHandler(async = true)
        public void onEvent(TestEvent event) {
            // Publish the flag before releasing the waiter so latch completion
            // proves the callback reached its state update, rather than only entering dispatch.
            called = true;
            latch.countDown();
        }
    }

    /**
     * {@code MultiEventListener} records delivery separately for two unrelated
     * concrete event fixture classes.
     */
    public static class MultiEventListener {
        /** Ordinary-event handler invocation flag. */
        public boolean testEventCalled = false;
        /** Cancellable-event handler invocation flag. */
        public boolean cancellableEventCalled = false;

        /** Marks the ordinary-event flag when its matching handler is invoked. */
        @EventHandler
        public void onTestEvent(TestEvent event) {
            testEventCalled = true;
        }

        /** Marks the cancellable-event flag when its matching handler is invoked. */
        @EventHandler
        public void onCancellableEvent(TestCancellableEvent event) {
            cancellableEventCalled = true;
        }
    }

    /**
     * {@code OrderTrackingListener} appends a configured label to shared
     * synchronous dispatch-order observations.
     */
    public static class OrderTrackingListener {
        /**
         * {@code order} retains the test's mutable observation list. Method
         * and consumer callbacks append to that same ordered sequence without
         * copying it.
         */
        private final List<String> order;
        /** Configured callback observation label. */
        private final String name;

        /**
         * Retains a shared observation list and the label appended on invocation.
         * {@code priority} is unused; this fixture's actual dispatch
         * priority comes from its handler annotation and remains fifty.
         */
        public OrderTrackingListener(List<String> order, String name, int priority) {
            this.order = order;
            this.name = name;
        }

        /** Appends this listener's configured label to the shared dispatch sequence. */
        @EventHandler(priority = 50)
        public void onEvent(TestEvent event) {
            order.add(name);
        }
    }

    /**
     * {@code InvalidReturnTypeListener} supplies a handler whose return type
     * violates event registration requirements.
     */
    public static class InvalidReturnTypeListener {
        /** Returns a fixed value from a deliberately invalid event-handler declaration. */
        @EventHandler
        public int invalidHandler(TestEvent event) {
            return 42;
        }
    }

    /**
     * {@code InvalidNoParamsListener} supplies an invalid handler that omits the
     * required event parameter.
     */
    public static class InvalidNoParamsListener {
        /** Declares an empty callback with the deliberately invalid parameterless signature. */
        @EventHandler
        public void invalidHandler() {
        }
    }

    /**
     * {@code InvalidMultiParamsListener} supplies an invalid handler with more
     * than one event parameter.
     */
    public static class InvalidMultiParamsListener {
        /** Declares a callback whose two event parameters fail registration validation. */
        @EventHandler
        public void invalidHandler(TestEvent event1, TestEvent event2) {
        }
    }

    /**
     * {@code InvalidNonEventParamListener} supplies a handler whose parameter is
     * outside the event hierarchy.
     */
    public static class InvalidNonEventParamListener {
        /** Declares a string-accepting callback that cannot receive a dispatched event. */
        @EventHandler
        public void invalidHandler(String notAnEvent) {
        }
    }

    /**
     * {@code HasDefaultEventHandler} supplies an annotated default method for
     * inherited listener-discovery tests.
     */
    public interface HasDefaultEventHandler {
        /** Records invocation in the concrete listener implementing this test interface. */
        void markCalled();

        /** Forwards default-handler invocation to the concrete listener's observation method. */
        @EventHandler
        default void onDefaultEvent(TestEvent event) {
            markCalled();
        }
    }

    /**
     * {@code DefaultMethodListener} records calls delegated through an inherited
     * annotated interface default method.
     */
    public static class DefaultMethodListener implements HasDefaultEventHandler {
        /** Inherited handler invocation flag. */
        public boolean called = false;

        /** Marks this concrete listener as called by the inherited handler. */
        @Override
        public void markCalled() {
            called = true;
        }
    }
}
