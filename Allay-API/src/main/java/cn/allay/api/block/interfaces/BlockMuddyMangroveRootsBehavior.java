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
public interface BlockMuddyMangroveRootsBehavior extends BlockBehavior {
    BlockType<BlockMuddyMangroveRootsBehavior> MUDDY_MANGROVE_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockMuddyMangroveRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
