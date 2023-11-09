package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTripwireHookBehavior extends BlockBehavior {
  BlockType<BlockTripwireHookBehavior> TRIPWIRE_HOOK_TYPE = BlockTypeBuilder
          .builder(BlockTripwireHookBehavior.class)
          .vanillaBlock(VanillaBlockId.TRIPWIRE_HOOK)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .build();
}
