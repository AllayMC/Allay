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
public interface BlockDeepslateBrickSlabBehavior extends BlockBehavior {
    BlockType<BlockDeepslateBrickSlabBehavior> DEEPSLATE_BRICK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBrickSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
}
