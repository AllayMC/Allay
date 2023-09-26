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
public interface BlockBlackstoneSlabBehavior extends BlockBehavior {
    BlockType<BlockBlackstoneSlabBehavior> BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
}
