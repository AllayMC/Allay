package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChestBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.chest.BlockChestBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockEntityHolderComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChestBehaviorInitializer {
  static void init() {
    BlockTypes.CHEST_TYPE = BlockTypeBuilder
            .builder(BlockChestBehavior.class)
            .vanillaBlock(VanillaBlockId.CHEST)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .setBlockBaseComponentSupplier(BlockChestBaseComponentImpl::new)
            .bindBlockEntity(BlockEntityTypes.CHEST_TYPE)
            .build();
  }
}
