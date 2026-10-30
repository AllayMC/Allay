package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.World;

/**
 * {@code WorldDataSaveEvent} announces an impending write of a world's
 * metadata to its storage implementation. The world dispatches this
 * notification before invoking the storage writer, allowing listeners to
 * update metadata without implying that a write has already completed.
 *
 * @author Dhaiven
 */
@CallerThread(ThreadType.WORLD)
public class WorldDataSaveEvent extends WorldEvent {
    /**
     * Creates a metadata write notification referring to the supplied world.
     */
    public WorldDataSaveEvent(World world) {
        super(world);
    }
}
