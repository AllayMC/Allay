package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBarrelBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.barrel.BlockBarrelBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBarrelBehaviorInitializer {
  static void init() {
    BlockTypes.BARREL_TYPE = BlockTypeBuilder
            .builder(BlockBarrelBehavior.class)
            .vanillaBlock(VanillaBlockId.BARREL)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
            .setBlockBaseComponentSupplier(BlockBarrelBaseComponentImpl::new)
            .bindBlockEntity(BlockEntityTypes.BARREL_TYPE)
            .build();
  }
}
