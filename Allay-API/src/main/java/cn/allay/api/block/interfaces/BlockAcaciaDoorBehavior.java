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
public interface BlockAcaciaDoorBehavior extends BlockBehavior {
    BlockType<BlockAcaciaDoorBehavior> ACACIA_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_DOOR)
            .setProperties(
                    VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.DOOR_HINGE_BIT,
                    VanillaBlockPropertyTypes.OPEN_BIT,
                    VanillaBlockPropertyTypes.UPPER_BLOCK_BIT
            )
            .build();
}
