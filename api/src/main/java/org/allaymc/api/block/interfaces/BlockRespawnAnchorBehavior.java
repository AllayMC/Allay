package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockRespawnPointComponent;

/**
 * {@code BlockRespawnAnchorBehavior} combines ordinary respawn-anchor block
 * operations with the player respawn-point contract. Callers request a destination
 * through {@code onPlayerRespawn}; the supplied respawn component determines the
 * applicable charge, standing-space, and declined-destination rules for that
 * request.
 */
public interface BlockRespawnAnchorBehavior extends BlockBehavior, BlockRespawnPointComponent {
}
