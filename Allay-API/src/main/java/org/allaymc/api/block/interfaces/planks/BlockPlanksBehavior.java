package org.allaymc.api.block.interfaces.planks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPlanksBehavior extends BlockBehavior {
    BlockType<BlockPlanksBehavior> PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.PLANKS)
            .setProperties(VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
}
