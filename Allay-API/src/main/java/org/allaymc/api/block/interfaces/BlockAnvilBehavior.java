package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAnvilBehavior extends BlockBehavior {
  BlockType<BlockAnvilBehavior> ANVIL_TYPE = BlockTypeBuilder
          .builder(BlockAnvilBehavior.class)
          .vanillaBlock(VanillaBlockId.ANVIL)
          .setProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .build();
}
