package cn.allay.api.block.interfaces.frostedice;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFrostedIceBehavior extends BlockBehavior {
    BlockType<BlockFrostedIceBehavior> FROSTED_ICE_TYPE = BlockTypeBuilder
            .builder(BlockFrostedIceBehavior.class)
            .vanillaBlock(VanillaBlockId.FROSTED_ICE)
            .setProperties(VanillaBlockPropertyTypes.AGE_4)
            .build();
}
