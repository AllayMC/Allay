package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code RespawnAnchorChargeSound} describes the charging sound for a respawn
 * anchor. The runtime sends the supplied state hash with the charge event so
 * the client receives the block payload; this descriptor does not consume
 * glowstone or change the anchor charge.
 *
 * @param blockState anchor state supplying the charge payload
 *
 * @author IWareQ
 */
public record RespawnAnchorChargeSound(BlockState blockState) implements Sound {
}
