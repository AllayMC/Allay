package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityCampfireBaseComponent;

/**
 * {@code BlockEntityCampfire} combines campfire item-slot and cooking
 * operations with persistent block-entity state. Ordinary and soul campfire
 * factories both expose this interface, while their configured base components
 * select the recipe behavior used for the shared inventory and cooking
 * contract.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCampfire extends BlockEntity, BlockEntityCampfireBaseComponent {
}
