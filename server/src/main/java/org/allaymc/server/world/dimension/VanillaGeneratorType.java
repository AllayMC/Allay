package org.allaymc.server.world.dimension;

import org.cloudburstmc.protocol.bedrock.packet.DimensionDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;

/**
 * Generator types and ids used in vanilla, they can be found in level.dat, {@link DimensionDataPacket} and {@link StartGamePacket}.
 *
 * @author daoge_cmd
 */
public enum VanillaGeneratorType {
    /**
     * Old is the old generator type used in the early version of mcbe (aka. mcpe) where the world
     * is limited in a small area.
     */
    OLD,
    /**
     * Infinite is the generator type used for the modern mcbe world where the world is infinite.
     */
    INFINITE,
    /**
     * Flat is the generator type used for worlds with a flat terrain layout.
     */
    FLAT,
    /**
     * Nether is the generator type used in nether worlds.
     */
    NETHER,
    /**
     * The end is the generator type used in end worlds.
     */
    THE_END,
    /**
     * Void is the generator used in void worlds. Especially, this is also the type used in {@link DimensionDataPacket} if
     * the dimension's bounds are modified.
     */
    VOID
}
