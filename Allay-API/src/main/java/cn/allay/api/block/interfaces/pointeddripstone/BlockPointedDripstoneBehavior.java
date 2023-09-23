package cn.allay.api.block.interfaces.pointeddripstone;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPointedDripstoneBehavior extends BlockBehavior {
    BlockType<BlockPointedDripstoneBehavior> POINTED_DRIPSTONE_TYPE = BlockTypeBuilder
            .builder(BlockPointedDripstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE)
            .setProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
            .build();
}
