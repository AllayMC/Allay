package cn.allay.api.block.interfaces.witherrose;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWitherRoseBehavior extends BlockBehavior {
    BlockType<BlockWitherRoseBehavior> WITHER_ROSE_TYPE = BlockTypeBuilder
            .builder(BlockWitherRoseBehavior.class)
            .vanillaBlock(VanillaBlockId.WITHER_ROSE)
            .build();
}
