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
public interface BlockHardStainedGlassPaneBehavior extends BlockBehavior {
    BlockType<BlockHardStainedGlassPaneBehavior> HARD_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS_PANE)
            .setProperties(VanillaBlockPropertyTypes.COLOR)
            .build();
}
