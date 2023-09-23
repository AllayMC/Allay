package cn.allay.api.block.interfaces.basalt;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBasaltBehavior extends BlockBehavior {
    BlockType<BlockBasaltBehavior> BASALT_TYPE = BlockTypeBuilder
            .builder(BlockBasaltBehavior.class)
            .vanillaBlock(VanillaBlockId.BASALT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
