package cn.allay.api.block.interfaces.bluewool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueWoolBehavior extends BlockBehavior {
    BlockType<BlockBlueWoolBehavior> BLUE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBlueWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_WOOL)
            .build();
}
