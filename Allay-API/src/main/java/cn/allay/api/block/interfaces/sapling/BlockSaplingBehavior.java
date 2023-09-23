package cn.allay.api.block.interfaces.sapling;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
