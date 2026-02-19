package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.math.location.Location3d;
import org.joml.Vector3d;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeLookTarget;
import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeRouteTarget;

/**
 * Generic breeding executor. Finds the nearest in-love entity of the same type,
 * moves both toward each other, and spawns a baby after the breeding duration.
 *
 * @author daoge_cmd
 */
public class EntityBreedingExecutor implements BehaviorExecutor {

    protected final int duration;
    protected final float speed;

    protected int tickCounter;
    protected Entity spouse;

    public EntityBreedingExecutor(int duration, float speed) {
        this.duration = duration;
        this.speed = speed;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        spouse = null;
        entity.setMovementSpeed(speed);
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;

        if (spouse == null || spouse.isDead()) {
            spouse = findSpouse(entity);
            if (spouse == null) {
                return false;
            }
            entity.getMemoryStorage().put(MemoryTypes.ENTITY_SPOUSE, spouse.getRuntimeId());
        }

        // Move toward spouse
        var spouseLoc = spouse.getLocation();
        EntityControlHelper.setRouteTarget(entity, new Vector3d(spouseLoc.x(), spouseLoc.y(), spouseLoc.z()));
        EntityControlHelper.setLookTarget(entity, new Vector3d(spouseLoc.x(), spouseLoc.y() + spouse.getEyeHeight(), spouseLoc.z()));

        // Check if close enough and enough time has passed
        var loc = entity.getLocation();
        double distSq = loc.distanceSquared(spouseLoc);
        if (distSq < 4.0 && tickCounter >= duration) {
            // Spawn baby
            spawnBaby(entity);
            // Reset love state for both parents
            entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
            if (spouse instanceof EntityIntelligent spouseIntelligent) {
                spouseIntelligent.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
            }
            return false;
        }

        return tickCounter < duration + 60; // timeout after duration + buffer
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        removeRouteTarget(entity);
        removeLookTarget(entity);
        entity.getMemoryStorage().clear(MemoryTypes.ENTITY_SPOUSE);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }

    protected Entity findSpouse(EntityIntelligent entity) {
        Entity nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (var candidate : entity.getDimension().getEntities().values()) {
            if (candidate == entity) continue;
            if (candidate.getEntityType() != entity.getEntityType()) continue;
            if (!(candidate instanceof EntityIntelligent candidateIntelligent)) continue;
            if (!Boolean.TRUE.equals(candidateIntelligent.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE))) continue;

            double distSq = entity.getLocation().distanceSquared(candidate.getLocation());
            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = candidate;
            }
        }

        return nearest;
    }

    protected void spawnBaby(EntityIntelligent entity) {
        var loc = entity.getLocation();
        var babyLoc = new Location3d(loc.x(), loc.y(), loc.z(), loc.dimension());
        var baby = entity.getEntityType().createEntity(
                EntityInitInfo.builder().loc(babyLoc).build()
        );
        if (baby instanceof EntityAnimal animalBaby) {
            animalBaby.setBaby(true);
        }
        entity.getDimension().getEntityManager().addEntity(baby);
    }
}
