package cn.allay.api.block.interfaces.emeraldblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockEmeraldBlockBehavior extends BlockBehavior {
    BlockType<BlockEmeraldBlockBehavior> EMERALD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockEmeraldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.EMERALD_BLOCK)
            .build();
}
