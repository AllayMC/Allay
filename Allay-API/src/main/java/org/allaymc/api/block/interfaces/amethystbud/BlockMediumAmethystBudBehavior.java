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
public interface BlockMediumAmethystBudBehavior extends BlockBehavior {
  BlockType<BlockMediumAmethystBudBehavior> MEDIUM_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockMediumAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
          .build();
}
