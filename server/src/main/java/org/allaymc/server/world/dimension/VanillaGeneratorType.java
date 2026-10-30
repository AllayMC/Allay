package org.allaymc.server.world.dimension;

import org.cloudburstmc.protocol.bedrock.packet.DimensionDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;

/**
 * {@code VanillaGeneratorType} names vanilla generator categories whose
 * ordinals are used in world metadata and dimension packets. These values
 * describe compatibility fields rather than Allay generation functions; callers
 * choose a category appropriate to the dimension identity and effective height
 * bounds.
 *
 * @author daoge_cmd
 */
public enum VanillaGeneratorType {
    /**
     * Legacy finite-world generator category used by older Bedrock worlds.
     */
    OLD,
    /**
     * Modern infinite-world generator compatibility category.
     */
    INFINITE,
    /**
     * Flat-terrain generator compatibility category.
     */
    FLAT,
    /**
     * Nether dimension generator compatibility category.
     */
    NETHER,
    /**
     * End dimension generator compatibility category.
     */
    THE_END,
    /**
     * {@code VOID} identifies void generation and supplies the compatibility
     * category in {@link DimensionDataPacket} when configured dimension bounds
     * differ from the vanilla defaults.
     */
    VOID
}
