package org.allaymc.api.blockentity.interfaces;

/**
 * {@code BlockEntityGlowItemFrame} exposes glowing item frame contents and
 * rotation together with the block-entity contract. It inherits the ordinary
 * item frame operations without adding a second content model, so consumers use
 * the same item and rotation contract for either frame variant.
 *
 * @author daoge_cmd
 */
public interface BlockEntityGlowItemFrame extends BlockEntityItemFrame {
}
