package org.allaymc.server.eventbus;

import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code TestCancellableEvent} supplies a cancellable fixture for dispatch
 * filtering and cancellation-result tests.
 *
 * @author daoge_cmd
 */
public class TestCancellableEvent extends Event implements CancellableEvent {
}
