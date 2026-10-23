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
 * {@code EntityBreedingExecutor} pairs same-type intelligent entities, guides
 * them toward each other, and lets the initiating executor create a baby. Each
 * participant stores its partner's runtime ID in {@code ENTITY_SPOUSE}; an
 * atomic per-key claim prevents replacement of an already nonempty spouse entry
 * during acquisition, while the reciprocal local entry is written separately.
 *
 * <p>
 * The execution timer begins at activation rather than at pairing. A paired
 * initiator can complete breeding after the duration when close enough, and
 * paired executions otherwise time out after an additional buffer. An execution
 * finding no spouse returns before that timeout check and keeps searching.
 * Normal stop and interruption clear both participants' route, look, and spouse
 * memories; the cleanup does not verify that those memories still describe the
 * same pairing.
 *
 * @author daoge_cmd
 */
public class EntityBreedingExecutor implements BehaviorExecutor {

    /**
     * {@code FINDING_RANGE_SQUARED} limits partner selection to a sixteen-block
     * radius, using squared world distance to compare candidates without taking
     * square roots.
     */
    protected static final int FINDING_RANGE_SQUARED = 256;

    /**
     * Minimum breeding execution ticks.
     */
    protected final int duration;
    /**
     * Partner approach movement speed.
     */
    protected final float speed;

    // tickCounter measures the activation lifetime while spouse and isInitiator
    // identify the pairing and its baby-creation owner. lastTargetPos independently
    // records when partner movement last justified a new route request.
    /**
     * Execution ticks since activation.
     */
    protected int tickCounter;
    /**
     * Selected or resolved breeding partner.
     */
    protected Entity spouse;
    /**
     * Baby creation responsibility flag.
     */
    protected boolean isInitiator;
    /**
     * Latest position used for repathing.
     */
    protected Vector3d lastTargetPos;

    /**
     * Creates a breeding executor with the supplied execution duration and
     * movement speed.
     */
    public EntityBreedingExecutor(int duration, float speed) {
        this.duration = duration;
        this.speed = speed;
    }

    /**
     * Resets local pairing state and installs the approach movement speed.
     * Existing spouse memory remains available for the first execution to
     * resolve a pairing already established by another entity.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        spouse = null;
        isInitiator = false;
        lastTargetPos = null;
        entity.setMovementSpeed(speed);
    }

    /**
     * Resolves or acquires a partner, updates approach targets, and checks
     * breeding completion. A participant first honors a resolvable live spouse
     * ID already in its memory; otherwise it searches for an eligible nearby
     * candidate and attempts that candidate's per-key claim.
     *
     * <p>
     * Only the initiator spawns a baby after the duration and within the
     * completion radius, then clears both love flags. Paired work stops after
     * the duration plus buffer, but a missing partner or failed claim returns
     * {@code true} before that timeout is checked. Partner movement exceeding
     * one block requests another path; each executing participant updates its
     * own look target toward the partner's eyes.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;

        if (spouse == null || spouse.isDead()) {
            spouse = null;
            // Honor a partner ID written by another executor before making a new
            // claim, allowing the other participant to retain responsibility for baby creation.
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
                    // Partner absence skips the timeout below, so this execution remains
                    // active and retries discovery on a later behavior tick without a time limit.
                    return true;
                }
                isInitiator = true;
                if (spouse instanceof EntityIntelligent spouseIntelligent) {
                    // Resolve competing claims inside the candidate's memory operation. A
                    // failed claim drops local pairing state and retries discovery on the next tick.
                    if (!spouseIntelligent.getMemoryStorage().putIfAbsent(MemoryTypes.ENTITY_SPOUSE, entity.getRuntimeId())) {
                        spouse = null;
                        isInitiator = false;
                        return true;
                    }
                }
                entity.getMemoryStorage().put(MemoryTypes.ENTITY_SPOUSE, spouse.getRuntimeId());
            }
        }

        // Ignore partner movement of one block or less when refreshing the route.
        var spouseLoc = spouse.getLocation();
        var targetPos = new Vector3d(spouseLoc.x(), spouseLoc.y(), spouseLoc.z());
        if (lastTargetPos == null || lastTargetPos.distanceSquared(targetPos) > 1.0) {
            setRouteTarget(entity, targetPos);
            lastTargetPos = targetPos;
        }

        setLookTarget(entity, new Vector3d(
                spouseLoc.x(), spouseLoc.y() + spouse.getEyeHeight(), spouseLoc.z()
        ));

        // The acquiring executor owns baby creation; a participant resolving an existing
        // spouse ID only follows, so both sides do not normally spawn a baby.
        if (isInitiator) {
            double distSq = entity.getLocation().distanceSquared(spouseLoc);
            // Completion requires partners within two blocks after the configured duration.
            if (distSq < 4.0 && tickCounter >= duration) {
                spawnBaby(entity);
                entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
                if (spouse instanceof EntityIntelligent spouseIntelligent) {
                    spouseIntelligent.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
                }
                return false;
            }
        }

        // Allow paired participants sixty additional ticks to close the remaining distance.
        return tickCounter < duration + 60;
    }

    /**
     * Clears route, look, and spouse memories for both retained participants,
     * then resets local pairing state. Cleanup does not compare spouse IDs
     * before removing them and does not restore movement speed.
     */
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

    /**
     * Uses normal-stop cleanup to release both participants' retained pairing
     * memories.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }

    /**
     * Clears one participant's route target, look target, and spouse entry.
     * Removing the route target also clears movement segment endpoints; the
     * method leaves love eligibility and movement speed unchanged.
     */
    protected void clearEntityState(EntityIntelligent entity) {
        removeRouteTarget(entity);
        removeLookTarget(entity);
        entity.getMemoryStorage().clear(MemoryTypes.ENTITY_SPOUSE);
    }

    /**
     * Returns the nearest unpaired in-love candidate of the same entity type
     * within the configured squared radius. Animal babies are excluded, while
     * other intelligent candidate types receive no baby-state check. This scan
     * does not claim the selected entry or separately filter dead candidates.
     */
    protected Entity findSpouse(EntityIntelligent entity) {
        Entity nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (var candidate : entity.getDimension().getEntities().values()) {
            if (candidate == entity) continue;
            if (candidate.getEntityType() != entity.getEntityType()) continue;
            if (!(candidate instanceof EntityIntelligent candidateIntelligent)) continue;
            if (!candidateIntelligent.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE)) continue;
            if (candidate instanceof EntityAnimal animal && animal.isBaby()) continue;
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

    /**
     * Creates a same-type entity at the parent's current location and adds it
     * to the dimension. Animals become babies, and intelligent offspring
     * receive the parent's current tick as their initial love timestamp.
     */
    protected void spawnBaby(EntityIntelligent entity) {
        var loc = entity.getLocation();
        var babyLoc = new Location3d(loc.x(), loc.y(), loc.z(), loc.dimension());
        var baby = entity.getEntityType().createEntity(
                EntityInitInfo.builder().loc(babyLoc).build()
        );
        if (baby instanceof EntityAnimal animalBaby) {
            animalBaby.setBaby(true);
        }
        // Breeding eligibility reads LAST_IN_LOVE_TIME for its cooldown. This write uses
        // the parent's current tick, while later offspring checks use their own tick clock.
        if (baby instanceof EntityIntelligent babyIntelligent) {
            babyIntelligent.getMemoryStorage().put(MemoryTypes.LAST_IN_LOVE_TIME, entity.getTick());
        }
        entity.getDimension().getEntityManager().addEntity(baby);
    }
}
