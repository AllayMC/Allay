package org.allaymc.api.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.item.ItemStack;

/**
 * {@code DispenseBehavior} defines the action performed for one item selected by a
 * dispenser. The dispenser behavior registry associates item types with these
 * handlers, and {@code DispenseResult} tells the caller whether to consume the
 * selected item and whether a replacement item remains.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface DispenseBehavior {
    /**
     * Performs the world action for one selected item and reports its outcome. The
     * built-in dispenser passes a copied stack whose count is one, consumes the
     * original only on success, and handles a returned remainder only for
     * successful results. Implementations may modify the supplied copy.
     */
    DispenseResult dispense(Block block, BlockFace face, ItemStack item);
}
