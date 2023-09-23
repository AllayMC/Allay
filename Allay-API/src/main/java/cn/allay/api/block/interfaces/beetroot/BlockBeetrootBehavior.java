package cn.allay.api.block.interfaces.beetroot;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBeetrootBehavior extends BlockBehavior {
    BlockType<BlockBeetrootBehavior> BEETROOT_TYPE = BlockTypeBuilder
            .builder(BlockBeetrootBehavior.class)
            .vanillaBlock(VanillaBlockId.BEETROOT)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
}
