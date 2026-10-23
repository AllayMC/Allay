package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityBannerBaseComponent;

/**
 * {@code BlockEntityBanner} combines persistent block-entity identity with
 * banner color, type, and pattern operations. Consumers access visual state
 * through the banner component while inherited base methods provide location,
 * custom naming, and NBT persistence for the same object.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBanner extends BlockEntity, BlockEntityBannerBaseComponent {
}
