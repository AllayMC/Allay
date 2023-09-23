package cn.allay.api.block.interfaces.strippedwarpedhyphae;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedWarpedHyphaeBehavior extends BlockBehavior {
    BlockType<BlockStrippedWarpedHyphaeBehavior> STRIPPED_WARPED_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockStrippedWarpedHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
