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
public interface BlockObserverBehavior extends BlockBehavior {
    BlockType<BlockObserverBehavior> OBSERVER_TYPE = BlockTypeBuilder
            .builder(BlockObserverBehavior.class)
            .vanillaBlock(VanillaBlockId.OBSERVER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_FACING_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
}
