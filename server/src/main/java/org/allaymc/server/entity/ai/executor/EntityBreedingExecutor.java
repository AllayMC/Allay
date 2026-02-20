package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.math.location.Location3d;
import org.joml.Vector3d;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.*;

/**
 * Generic breeding executor. Finds the nearest in-love entity of the same type,
 * moves both toward each other, and spawns a baby after the breeding duration.
 *
 * @author daoge_cmd
 */
public class EntityBreedingExecutor implements BehaviorExecutor {

    protected static final int FINDING_RANGE_SQUARED = 256; // 16 blocks

    protected final int duration;
    protected final float speed;

    protected int tickCounter;
    protected Entity spouse;
    protected boolean isInitiator;
    protected Vector3d lastTargetPos;

    public EntityBreedingExecutor(int duration, float speed) {
        this.duration = duration;
        this.speed = speed;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        spouse = null;
        isInitiator = false;
        lastTargetPos = null;
        entity.setMovementSpeed(speed);
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;

        if (spouse == null || spouse.isDead()) {
            spouse = null;
            // Check if another entity's executor already paired with us
            var spouseId = entity.getMemoryStorage().get(MemoryTypes.ENTITY_SPOUSE);
            if (spouseId != null) {
                var resolved = entity.getDimension().getEntityManager().getEntity(spouseId);
                if (resolved != null && !resolved.isDead()) {
                    spouse = resolved;
                }
            }

            if (spouse == null) {
                spouse = findSpouse(entity);
                if (spouse == null) {
                    return true; // keep searching
                }
                isInitiator = true;
                entity.getMemoryStorage().put(MemoryTypes.ENTITY_SPOUSE, spouse.getRuntimeId());
                if (spouse instanceof EntityIntelligent spouseIntelligent) {
                    spouseIntelligent.getMemoryStorage().put(MemoryTypes.ENTITY_SPOUSE, entity.getRuntimeId());
                }
            }
        }

        // Move toward spouse, re-path only when target moved >1 block
        var spouseLoc = spouse.getLocation();
        var targetPos = new Vector3d(spouseLoc.x(), spouseLoc.y(), spouseLoc.z());
        if (lastTargetPos == null || lastTargetPos.distanceSquared(targetPos) > 1.0) {
            setRouteTarget(entity, targetPos);
            lastTargetPos = targetPos;
        }

        setLookTarget(entity, new Vector3d(
                spouseLoc.x(), spouseLoc.y() + spouse.getEyeHeight(), spouseLoc.z()
        ));

        // Only the initiator checks for breeding completion and spawns the baby
        if (isInitiator) {
            double distSq = entity.getLocation().distanceSquared(spouseLoc);
            if (distSq < 4.0 && tickCounter >= duration) {
                spawnBaby(entity);
                entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
                if (spouse instanceof EntityIntelligent spouseIntelligent) {
                    spouseIntelligent.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
                }
                return false;
            }
        }

        return tickCounter < duration + 60; // timeout after duration + buffer
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        clearEntityState(entity);
        if (spouse instanceof EntityIntelligent spouseIntelligent) {
            clearEntityState(spouseIntelligent);
        }
        spouse = null;
        isInitiator = false;
        lastTargetPos = null;
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }

    protected void clearEntityState(EntityIntelligent entity) {
        removeRouteTarget(entity);
        removeLookTarget(entity);
        entity.getMemoryStorage().clear(MemoryTypes.ENTITY_SPOUSE);
    }

    protected Entity findSpouse(EntityIntelligent entity) {
        Entity nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (var candidate : entity.getDimension().getEntities().values()) {
            if (candidate == entity) continue;
            if (candidate.getEntityType() != entity.getEntityType()) continue;
            if (!(candidate instanceof EntityIntelligent candidateIntelligent)) continue;
            if (!candidateIntelligent.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE)) continue;
            // Skip babies
            if (candidate instanceof EntityAnimal animal && animal.isBaby()) continue;
            // Skip already paired entities
            if (candidateIntelligent.getMemoryStorage().get(MemoryTypes.ENTITY_SPOUSE) != null) continue;

            double distSq = entity.getLocation().distanceSquared(candidate.getLocation());
            if (distSq > FINDING_RANGE_SQUARED) continue;
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
        // Prevent baby from breeding immediately
        if (baby instanceof EntityIntelligent babyIntelligent) {
            babyIntelligent.getMemoryStorage().put(MemoryTypes.LAST_IN_LOVE_TIME, entity.getTick());
        }
        entity.getDimension().getEntityManager().addEntity(baby);
    }
}
