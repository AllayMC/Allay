package cn.allay.api.block.interfaces.lava;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLavaBehavior extends BlockBehavior {
    BlockType<BlockLavaBehavior> LAVA_TYPE = BlockTypeBuilder
            .builder(BlockLavaBehavior.class)
            .vanillaBlock(VanillaBlockId.LAVA)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
}
