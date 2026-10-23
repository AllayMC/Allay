package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityFlowerPotBaseComponent;

/**
 * {@code BlockEntityFlowerPot} exposes flower pot plant state together with the
 * block-entity contract. The pot component exposes planting and item conversion
 * while the common base supplies persistent identity and position for the same
 * object and its stored plant.
 *
 * @author Cdm2883
 */
public interface BlockEntityFlowerPot extends BlockEntity, BlockEntityFlowerPotBaseComponent {
}
