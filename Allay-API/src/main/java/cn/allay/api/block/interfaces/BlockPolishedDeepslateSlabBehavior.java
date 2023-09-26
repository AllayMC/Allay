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
public interface BlockPolishedDeepslateSlabBehavior extends BlockBehavior {
    BlockType<BlockPolishedDeepslateSlabBehavior> POLISHED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
}
