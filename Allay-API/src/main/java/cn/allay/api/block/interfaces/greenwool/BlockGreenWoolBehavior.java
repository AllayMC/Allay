package cn.allay.api.block.interfaces.greenwool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenWoolBehavior extends BlockBehavior {
    BlockType<BlockGreenWoolBehavior> GREEN_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockGreenWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_WOOL)
            .build();
}
