package org.allaymc.server.block.dispenser;

import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;

public class ChestBoatDispenseBehavior extends BoatDispenseBehavior {

    @Override
    protected EntityType<?> getEntityType() {
        return EntityTypes.CHEST_BOAT;
    }
}