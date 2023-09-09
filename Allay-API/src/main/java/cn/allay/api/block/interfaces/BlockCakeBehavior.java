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
public interface BlockCakeBehavior extends BlockBehavior {
    BlockType<BlockCakeBehavior> CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CAKE)
            .setProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
            .build();
}
