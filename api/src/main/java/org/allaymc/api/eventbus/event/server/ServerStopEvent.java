package org.allaymc.api.eventbus.event.server;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;

/**
 * @author daoge_cmd
 */
@CallerThread(ThreadType.SERVER)
public class ServerStopEvent extends ServerEvent {
}
