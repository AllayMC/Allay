package cn.allay.api.block.interfaces.driedkelpblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDriedKelpBlockBehavior extends BlockBehavior {
    BlockType<BlockDriedKelpBlockBehavior> DRIED_KELP_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDriedKelpBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK)
            .build();
}
