package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSoulSoilBehavior extends BlockBehavior {
    BlockType<BlockSoulSoilBehavior> SOUL_SOIL_TYPE = BlockTypeBuilder
            .builder(BlockSoulSoilBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_SOIL)
            .build();
}
