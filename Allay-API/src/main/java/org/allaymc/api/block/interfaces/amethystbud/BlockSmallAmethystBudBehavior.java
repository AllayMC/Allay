package org.allaymc.api.block.interfaces.amethystbud;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSmallAmethystBudBehavior extends BlockBehavior {
  BlockType<BlockSmallAmethystBudBehavior> SMALL_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockSmallAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.SMALL_AMETHYST_BUD)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
          .build();
}
