package cn.allay.api.block.interfaces.mangrovedoor;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveDoorBehavior extends BlockBehavior {
    BlockType<BlockMangroveDoorBehavior> MANGROVE_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockMangroveDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
}
