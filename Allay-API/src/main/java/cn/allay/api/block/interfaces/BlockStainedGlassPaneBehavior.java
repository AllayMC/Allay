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
public interface BlockStainedGlassPaneBehavior extends BlockBehavior {
    BlockType<BlockStainedGlassPaneBehavior> STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.STAINED_GLASS_PANE)
            .setProperties(VanillaBlockPropertyTypes.COLOR)
            .build();
}
