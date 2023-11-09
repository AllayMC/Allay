package org.allaymc.api.block.interfaces.leaves;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLeavesBehavior extends BlockBehavior {
    BlockType<BlockLeavesBehavior> LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.LEAVES)
            .setProperties(VanillaBlockPropertyTypes.OLD_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
}
