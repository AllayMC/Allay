package org.allaymc.api.block.interfaces.wool;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOrangeWoolBehavior extends BlockBehavior {
  BlockType<BlockOrangeWoolBehavior> ORANGE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockOrangeWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_WOOL)
          .build();
}
