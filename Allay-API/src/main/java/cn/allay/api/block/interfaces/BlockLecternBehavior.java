package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLecternBehavior extends BlockBehavior {
    BlockType<BlockLecternBehavior> LECTERN_TYPE = BlockTypeBuilder
            .builder(BlockLecternBehavior.class)
            .vanillaBlock(VanillaBlockId.LECTERN)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
}
