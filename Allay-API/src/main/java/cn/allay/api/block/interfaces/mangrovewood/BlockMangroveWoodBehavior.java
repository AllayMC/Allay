package cn.allay.api.block.interfaces.mangrovewood;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveWoodBehavior extends BlockBehavior {
    BlockType<BlockMangroveWoodBehavior> MANGROVE_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockMangroveWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
            .build();
}
