package org.allaymc.api.eventbus.event.server;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;

/**
 * {@code ServerStopEvent} announces entry into the server's shutdown sequence
 * after its tick loop ends.
 * Listeners run before player disconnection, network and scheduler shutdown,
 * plugin disable callbacks, and world closure. The event does not support
 * cancellation, so it cannot keep the server running.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.SERVER)
public class ServerStopEvent extends ServerEvent {
}
