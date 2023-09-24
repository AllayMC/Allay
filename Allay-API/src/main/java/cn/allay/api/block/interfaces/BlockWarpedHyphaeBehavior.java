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
public interface BlockWarpedHyphaeBehavior extends BlockBehavior {
    BlockType<BlockWarpedHyphaeBehavior> WARPED_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockWarpedHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
