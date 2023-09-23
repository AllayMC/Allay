package cn.allay.api.block.interfaces.pearlescentfroglight;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPearlescentFroglightBehavior extends BlockBehavior {
    BlockType<BlockPearlescentFroglightBehavior> PEARLESCENT_FROGLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockPearlescentFroglightBehavior.class)
            .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
