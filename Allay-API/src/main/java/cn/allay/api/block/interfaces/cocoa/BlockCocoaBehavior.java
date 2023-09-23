package cn.allay.api.block.interfaces.cocoa;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCocoaBehavior extends BlockBehavior {
    BlockType<BlockCocoaBehavior> COCOA_TYPE = BlockTypeBuilder
            .builder(BlockCocoaBehavior.class)
            .vanillaBlock(VanillaBlockId.COCOA)
            .setProperties(VanillaBlockPropertyTypes.AGE_3, VanillaBlockPropertyTypes.DIRECTION)
            .build();
}
