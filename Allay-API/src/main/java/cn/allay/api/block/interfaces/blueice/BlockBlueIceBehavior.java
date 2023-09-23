package cn.allay.api.block.interfaces.blueice;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueIceBehavior extends BlockBehavior {
    BlockType<BlockBlueIceBehavior> BLUE_ICE_TYPE = BlockTypeBuilder
            .builder(BlockBlueIceBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_ICE)
            .build();
}
