package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.particle.BlockBreakParticle;

/**
 * {@code EatGrassExecutor} times a grass-eating animation and queues a terrain
 * replacement attempt after normal completion. The sequential action matches
 * short grass at the captured feet position or a grass block below it, attempts
 * replacement, and invokes the eating hook even when placement is rejected. Mob
 * griefing gates both the attempt and hook.
 *
 * @author daoge_cmd
 */
public class EatGrassExecutor implements BehaviorExecutor {

    /**
     * Eating animation duration in ticks.
     */
    protected final int duration;
    /**
     * {@code tickCounter} counts executions since {@code onStart}. Execution
     * completes when it reaches {@code duration}, and interruption does not
     * invoke the terrain-changing normal stop callback.
     */
    protected int tickCounter;

    /**
     * Creates an eating executor with the supplied duration in execution ticks.
     * The duration is retained without validation, and nonpositive durations
     * finish on the first execution after activation.
     */
    public EatGrassExecutor(int duration) {
        this.duration = duration;
    }

    /**
     * Creates an eating executor with a forty-tick animation duration.
     * Activation resets its timer and sends the eating action; terrain changes
     * occur only after normal completion through {@code onStop}.
     */
    public EatGrassExecutor() {
        this(40);
    }

    /**
     * Resets the execution timer and sends the grass-eating entity action.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        entity.applyAction(SimpleEntityAction.EAT_GRASS);
    }

    /**
     * Returns whether the incremented eating timer remains below its duration.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;
        return tickCounter < duration;
    }

    /**
     * Emits the eating particle and, when mob griefing is enabled, queues a
     * terrain replacement attempt. The action uses the position captured here
     * but rechecks blocks during sequential execution. Matching supported grass
     * invokes {@code onEatGrass} after the attempt even if {@code
     * setBlockState} returns {@code false}.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        var dimension = entity.getDimension();
        var loc = entity.getLocation();
        int x = (int) Math.floor(loc.x());
        int y = (int) Math.floor(loc.y());
        int z = (int) Math.floor(loc.z());

        dimension.addParticle(loc, new BlockBreakParticle(BlockTypes.SHORT_GRASS.getDefaultState()));

        if (!dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.MOB_GRIEFING)) {
            return;
        }

        // The executor runs during parallel AI processing. Queue terrain writes so
        // they execute in the sequential entity phase and recheck the captured position.
        entity.getBehaviorGroup().addSyncedAction(() -> {
            var feetBlock = dimension.getBlockState(x, y, z);
            if (feetBlock != null && feetBlock.getBlockType() == BlockTypes.SHORT_GRASS) {
                dimension.setBlockState(x, y, z, BlockTypes.AIR.getDefaultState());
                onEatGrass(entity);
                return;
            }

            var belowBlock = dimension.getBlockState(x, y - 1, z);
            if (belowBlock != null && belowBlock.getBlockType() == BlockTypes.GRASS_BLOCK) {
                dimension.setBlockState(x, y - 1, z, BlockTypes.DIRT.getDefaultState());
                onEatGrass(entity);
            }
        });
    }

    /**
     * Marks a baby animal as adult after recognized grass consumption. The
     * sequential action invokes this hook after attempting replacement of
     * matching grass, including rejected placement; disabled mob griefing
     * prevents the attempt and hook.
     */
    protected void onEatGrass(EntityIntelligent entity) {
        if (entity instanceof EntityAnimal animal && animal.isBaby()) {
            animal.setBaby(false);
        }
    }
}
