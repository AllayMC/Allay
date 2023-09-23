package cn.allay.api.block.interfaces.bell;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBellBehavior extends BlockBehavior {
    BlockType<BlockBellBehavior> BELL_TYPE = BlockTypeBuilder
            .builder(BlockBellBehavior.class)
            .vanillaBlock(VanillaBlockId.BELL)
            .setProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
            .build();
}
