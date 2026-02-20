package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.particle.BlockBreakParticle;

/**
 * Sheep-specific grass eating executor. Plays the eat animation,
 * then destroys short grass or converts grass block to dirt.
 *
 * @author daoge_cmd
 */
public class EatGrassExecutor implements BehaviorExecutor {

    protected final int duration;
    protected int tickCounter;

    public EatGrassExecutor(int duration) {
        this.duration = duration;
    }

    public EatGrassExecutor() {
        this(40);
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        entity.applyAction(SimpleEntityAction.EAT_GRASS);
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;
        return tickCounter < duration;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        var dimension = entity.getDimension();
        var loc = entity.getLocation();
        int x = (int) Math.floor(loc.x());
        int y = (int) Math.floor(loc.y());
        int z = (int) Math.floor(loc.z());

        // Spawn block break particle
        dimension.addParticle(loc, new BlockBreakParticle(BlockTypes.SHORT_GRASS.getDefaultState()));

        if (!dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.MOB_GRIEFING)) {
            return;
        }

        // Check for short grass at feet level
        var feetBlock = dimension.getBlockState(x, y, z);
        if (feetBlock != null && feetBlock.getBlockType() == BlockTypes.SHORT_GRASS) {
            dimension.setBlockState(x, y, z, BlockTypes.AIR.getDefaultState());
            onEatGrass(entity);
            return;
        }

        // Check for grass block below feet
        var belowBlock = dimension.getBlockState(x, y - 1, z);
        if (belowBlock != null && belowBlock.getBlockType() == BlockTypes.GRASS_BLOCK) {
            dimension.setBlockState(x, y - 1, z, BlockTypes.DIRT.getDefaultState());
            onEatGrass(entity);
        }
    }

    protected void onEatGrass(EntityIntelligent entity) {
        if (entity instanceof EntityAnimal animal && animal.isBaby()) {
            animal.setBaby(false);
        }
    }
}
