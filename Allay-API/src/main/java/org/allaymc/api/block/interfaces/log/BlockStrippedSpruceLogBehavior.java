package org.allaymc.api.block.interfaces.log;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedSpruceLogBehavior extends BlockBehavior {
  BlockType<BlockStrippedSpruceLogBehavior> STRIPPED_SPRUCE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedSpruceLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_SPRUCE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
