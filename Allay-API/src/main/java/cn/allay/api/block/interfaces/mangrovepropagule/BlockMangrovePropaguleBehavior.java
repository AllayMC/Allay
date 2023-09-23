package cn.allay.api.block.interfaces.mangrovepropagule;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangrovePropaguleBehavior extends BlockBehavior {
    BlockType<BlockMangrovePropaguleBehavior> MANGROVE_PROPAGULE_TYPE = BlockTypeBuilder
            .builder(BlockMangrovePropaguleBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE)
            .setProperties(VanillaBlockPropertyTypes.HANGING, VanillaBlockPropertyTypes.PROPAGULE_STAGE)
            .build();
}
