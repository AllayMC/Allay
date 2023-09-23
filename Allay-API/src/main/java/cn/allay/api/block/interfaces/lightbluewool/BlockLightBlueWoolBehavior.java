package cn.allay.api.block.interfaces.lightbluewool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueWoolBehavior extends BlockBehavior {
    BlockType<BlockLightBlueWoolBehavior> LIGHT_BLUE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL)
            .build();
}
