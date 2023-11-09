package org.allaymc.api.block.interfaces.sapling;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSaplingBehavior extends BlockBehavior {
    BlockType<BlockSaplingBehavior> SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockSaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
            .build();
}
