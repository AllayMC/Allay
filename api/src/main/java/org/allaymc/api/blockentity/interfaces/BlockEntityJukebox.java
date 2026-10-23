package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityJukeboxBaseComponent;

/**
 * {@code BlockEntityJukebox} exposes jukebox disc storage and playback together
 * with the block-entity contract. The jukebox component supplies music-disc
 * access and playback controls while the common base associates the same object
 * with its type, dimension, location, and persistent NBT data.
 *
 * @author IWareQ
 */
public interface BlockEntityJukebox extends BlockEntity, BlockEntityJukeboxBaseComponent {
}
