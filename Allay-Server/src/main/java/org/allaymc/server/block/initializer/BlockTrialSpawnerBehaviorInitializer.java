package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTrialSpawnerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTrialSpawnerBehaviorInitializer {
  static void init() {
    BlockTypes.TRIAL_SPAWNER_TYPE = BlockTypeBuilder
            .builder(BlockTrialSpawnerBehavior.class)
            .vanillaBlock(VanillaBlockId.TRIAL_SPAWNER)
            .setProperties(VanillaBlockPropertyTypes.TRIAL_SPAWNER_STATE)
            .build();
  }
}
