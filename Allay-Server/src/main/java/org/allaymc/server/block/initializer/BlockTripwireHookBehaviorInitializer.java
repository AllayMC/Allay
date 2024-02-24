package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTripwireHookBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTripwireHookBehaviorInitializer {
  static void init() {
    BlockTypes.TRIPWIRE_HOOK_TYPE = BlockTypeBuilder
            .builder(BlockTripwireHookBehavior.class)
            .vanillaBlock(VanillaBlockId.TRIPWIRE_HOOK)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
