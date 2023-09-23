package cn.allay.api.block.interfaces.soullantern;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSoulLanternBehavior extends BlockBehavior {
    BlockType<BlockSoulLanternBehavior> SOUL_LANTERN_TYPE = BlockTypeBuilder
            .builder(BlockSoulLanternBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_LANTERN)
            .setProperties(VanillaBlockPropertyTypes.HANGING)
            .build();
}
