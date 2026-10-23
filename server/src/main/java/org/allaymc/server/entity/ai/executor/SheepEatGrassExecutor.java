package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.entity.interfaces.EntitySheep;

/**
 * {@code SheepEatGrassExecutor} adds wool regrowth to recognized grass
 * consumption. The parent owns animation timing and deferred terrain
 * replacement attempts; this hook applies animal growth and clears a sheep's
 * sheared flag after matching grass is found, even if its replacement is
 * rejected.
 *
 * @author daoge_cmd
 */
public class SheepEatGrassExecutor extends EatGrassExecutor {

    /**
     * Applies the parent growth hook and regrows wool for a sheared sheep.
     * Matching grass triggers this callback after a replacement attempt, even
     * when placement is rejected; disabled mob griefing prevents the hook
     * entirely.
     */
    @Override
    protected void onEatGrass(EntityIntelligent entity) {
        super.onEatGrass(entity);
        if (entity instanceof EntitySheep sheep && sheep.isSheared()) {
            sheep.setSheared(false);
        }
    }
}
