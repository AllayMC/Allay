package cn.allay.api.block.interfaces.skull;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSkullBehavior extends BlockBehavior {
    BlockType<BlockSkullBehavior> SKULL_TYPE = BlockTypeBuilder
            .builder(BlockSkullBehavior.class)
            .vanillaBlock(VanillaBlockId.SKULL)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
