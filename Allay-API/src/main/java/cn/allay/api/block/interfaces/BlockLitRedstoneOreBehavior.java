package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLitRedstoneOreBehavior extends BlockBehavior {
    BlockType<BlockLitRedstoneOreBehavior> LIT_REDSTONE_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLitRedstoneOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_REDSTONE_ORE)
            .build();
}
