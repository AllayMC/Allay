package cn.allay.api.block.interfaces.floweringazalea;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFloweringAzaleaBehavior extends BlockBehavior {
    BlockType<BlockFloweringAzaleaBehavior> FLOWERING_AZALEA_TYPE = BlockTypeBuilder
            .builder(BlockFloweringAzaleaBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWERING_AZALEA)
            .build();
}
