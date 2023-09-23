package cn.allay.api.block.interfaces.kelp;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockKelpBehavior extends BlockBehavior {
    BlockType<BlockKelpBehavior> KELP_TYPE = BlockTypeBuilder
            .builder(BlockKelpBehavior.class)
            .vanillaBlock(VanillaBlockId.KELP)
            .setProperties(VanillaBlockPropertyTypes.KELP_AGE)
            .build();
}
