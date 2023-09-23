package cn.allay.api.block.interfaces.blackwool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlackWoolBehavior extends BlockBehavior {
    BlockType<BlockBlackWoolBehavior> BLACK_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBlackWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_WOOL)
            .build();
}
