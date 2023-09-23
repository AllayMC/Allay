package cn.allay.api.block.interfaces.jungledoor;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockJungleDoorBehavior extends BlockBehavior {
    BlockType<BlockJungleDoorBehavior> JUNGLE_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockJungleDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
}
