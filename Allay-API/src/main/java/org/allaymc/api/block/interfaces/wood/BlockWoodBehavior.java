package org.allaymc.api.block.interfaces.wood;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWoodBehavior extends BlockBehavior {
    BlockType<BlockWoodBehavior> WOOD_TYPE = BlockTypeBuilder
            .builder(BlockWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
}
