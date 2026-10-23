package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBanner;

/**
 * {@code BlockWallBannerBehavior} exposes wall banner block behavior together with
 * access to its typed block entity. {@code getBlockEntity} resolves the
 * {@code BlockEntityBanner} at a world position, while the inherited
 * {@code BlockBehavior} contract supplies the ordinary placement, update, and
 * interaction entry points.
 */
public interface BlockWallBannerBehavior extends BlockBehavior, BlockBlockEntityHolderComponent<BlockEntityBanner> {
}
