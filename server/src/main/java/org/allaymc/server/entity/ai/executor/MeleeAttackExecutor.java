package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.AttackSound;
import org.joml.Vector3d;

/**
 * {@code MeleeAttackExecutor} chases and attacks a living entity identified by
 * a memory key. The executor rejects dead targets and players outside survival
 * or adventure mode, stops beyond its tracking radius, and updates route
 * requests only when the target crosses a block boundary.
 *
 * <p>
 * Attack eligibility combines range with a timer that resets only after
 * successful damage. Failed attacks still emit swing and sound feedback but can
 * retry on the next execution. Optional fire spreading applies only to
 * successful hits from a burning attacker. Cleanup clears movement and look
 * targets, disables pitch, installs the default movement speed, and optionally
 * removes the target memory rather than restoring prior control settings.
 */
public class MeleeAttackExecutor implements BehaviorExecutor {

    /**
     * {@code DEFAULT_ATTACK_RANGE} supplies the default melee radius so its
     * squared comparison uses a two-and-a-half square-block limit for target
     * distance.
     */
    protected static final double DEFAULT_ATTACK_RANGE = Math.sqrt(2.5);

    /**
     * Attack target runtime-ID key.
     */
    protected final MemoryType<Long> targetIdMemory;
    /**
     * Chase movement speed value.
     */
    protected final float speed;
    /**
     * Maximum squared target tracking distance.
     */
    protected final double maxSenseRangeSquared;
    /**
     * Target memory cleanup enable flag.
     */
    protected final boolean clearTargetAfterLose;
    /**
     * Successful-hit spacing in execution ticks.
     */
    protected final int coolDown;
    /**
     * Maximum squared melee attack distance.
     */
    protected final double attackRangeSquared;
    /**
     * Successful-hit fire transfer flag.
     */
    private final boolean canSpreadOnFire;

    /**
     * {@code attackTick} counts executions since activation or the last
     * successful hit. Failed damage attempts leave it unchanged after the tick
     * increment, permitting immediate retries.
     */
    protected int attackTick;
    /**
     * Latest resolved attack target position.
     */
    protected Vector3d lastTargetPos;

    /**
     * Creates a melee executor using the default attack radius and preserving
     * target memory on cleanup. Fire spreading is disabled, and both range
     * inputs are interpreted as block distances before squared comparisons.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange, int coolDown) {
        this(targetIdMemory, speed, maxSenseRange, false, coolDown, DEFAULT_ATTACK_RANGE, false);
    }

    /**
     * Creates a melee executor with a custom attack radius and retained target
     * memory. Fire spreading is disabled, and the supplied tracking and attack
     * distances are squared for later comparisons.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange,
                               int coolDown, double attackRange) {
        this(targetIdMemory, speed, maxSenseRange, false, coolDown, attackRange, false);
    }

    /**
     * Creates a melee executor with configurable target cleanup and the default
     * attack radius. Fire spreading is disabled, while tracking distance and
     * attack cooldown retain the supplied behavior settings.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange,
                               boolean clearTargetAfterLose, int coolDown) {
        this(targetIdMemory, speed, maxSenseRange, clearTargetAfterLose, coolDown, DEFAULT_ATTACK_RANGE, false);
    }

    /**
     * Creates a melee executor retaining target, movement, cleanup, cooldown,
     * and fire settings. Tracking and attack ranges are squared without sign
     * validation, so callers should supply meaningful nonnegative block
     * distances.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange,
                               boolean clearTargetAfterLose, int coolDown, double attackRange, boolean canSpreadOnFire) {
        this.targetIdMemory = targetIdMemory;
        this.speed = speed;
        // Compare squared block distances later without computing a square root per tick.
        this.maxSenseRangeSquared = maxSenseRange * maxSenseRange;
        this.clearTargetAfterLose = clearTargetAfterLose;
        this.coolDown = coolDown;
        this.attackRangeSquared = attackRange * attackRange;
        this.canSpreadOnFire = canSpreadOnFire;
    }

    /**
     * Resets attack tracking and installs chase speed with pitch enabled.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        attackTick = 0;
        lastTargetPos = null;
        entity.setMovementSpeed(speed);
        entity.setPitchEnabled(true);
    }

    /**
     * Chases a valid resolved target and attempts damage when range and
     * cooldown permit. A missing or invalid target, excessive tracking
     * distance, or nonpositive calculated damage ends execution. Current
     * movement speed and pitch are reapplied while chasing if other work
     * changed them.
     *
     * <p>
     * The move target follows every observed position, but route refresh
     * requests occur only after a block-boundary change. Attacks require the
     * incremented timer to exceed {@code coolDown}; only successful damage
     * resets it. Failed damage still produces animation and sound, while
     * optional fire transfer extends the victim's burning time to at least
     * forty ticks.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        attackTick++;

        var targetId = entity.getMemoryStorage().get(targetIdMemory);
        if (targetId == null) {
            return false;
        }

        var targetEntity = entity.getDimension().getEntityManager().getEntity(targetId);
        if (!(targetEntity instanceof EntityLiving targetLiving) || !isTargetValid(targetEntity)) {
            return false;
        }

        var targetLoc = targetEntity.getLocation();
        var distanceSquared = entity.getLocation().distanceSquared(targetLoc);
        if (distanceSquared > maxSenseRangeSquared) {
            return false;
        }

        if (!entity.isPitchEnabled()) {
            entity.setPitchEnabled(true);
        }
        if (entity.getMovementSpeed() != speed) {
            entity.setMovementSpeed(speed);
        }

        var targetPos = new Vector3d(targetLoc.x(), targetLoc.y(), targetLoc.z());
        entity.setMoveTarget(targetPos);
        // Repath on block-boundary changes while retaining the exact target position,
        // avoiding a new search for every small movement inside the same block.
        if (lastTargetPos == null || isInDifferentBlock(lastTargetPos, targetPos)) {
            entity.getBehaviorGroup().setRouteUpdateRequired(true);
        }
        lastTargetPos = targetPos;

        EntityControlHelper.setLookTarget(entity, new Vector3d(
                targetLoc.x(), targetLoc.y() + targetEntity.getEyeHeight(), targetLoc.z()
        ));

        if (distanceSquared <= attackRangeSquared && attackTick > coolDown) {
            var damage = getAttackDamage(entity, targetLiving);
            if (damage <= 0) {
                return false;
            }

            var attackSuccess = targetLiving.attack(DamageContainer.entityAttack(entity, damage));
            if (attackSuccess && entity.isOnFire() && canSpreadOnFire) {
                // A transferred flame supplies at least two seconds of burning.
                targetLiving.setOnFireTicks(Math.max(targetLiving.getOnFireTicks(), 40));
            }
            entity.applyAction(SimpleEntityAction.SWING_ARM);
            entity.getDimension().addSound(targetLoc, new AttackSound(attackSuccess));
            if (attackSuccess) {
                attackTick = 0;
            }
        }

        return true;
    }

    /**
     * Clears chase targets, disables pitch, and installs the default movement
     * speed. The configured cleanup flag additionally clears target memory;
     * previous pitch and speed values are not restored.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        EntityControlHelper.removeRouteTarget(entity);
        EntityControlHelper.removeLookTarget(entity);
        entity.setPitchEnabled(false);
        entity.setMovementSpeed(MemoryTypes.MOVEMENT_SPEED.defaultData().get());
        lastTargetPos = null;
        if (clearTargetAfterLose) {
            entity.getMemoryStorage().clear(targetIdMemory);
        }
    }

    /**
     * Uses normal-stop cleanup to release melee movement and target state.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }

    /**
     * Returns whether the two target positions occupy different floored block
     * coordinates.
     */
    protected boolean isInDifferentBlock(Vector3d oldTargetPos, Vector3d newTargetPos) {
        return Math.floor(oldTargetPos.x()) != Math.floor(newTargetPos.x()) ||
               Math.floor(oldTargetPos.y()) != Math.floor(newTargetPos.y()) ||
               Math.floor(oldTargetPos.z()) != Math.floor(newTargetPos.z());
    }

    /**
     * Returns whether the target is alive and eligible for melee pursuit.
     * Players must be in survival or adventure mode, while other living targets
     * require no game-mode check.
     */
    protected boolean isTargetValid(Entity targetEntity) {
        if (!targetEntity.isAlive()) {
            return false;
        }

        if (targetEntity instanceof EntityPlayer player) {
            var gameMode = player.getGameMode();
            return gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE;
        }

        return true;
    }

    /**
     * Calculates melee damage by selecting the larger difficulty base and
     * held-item result. Difficulty supplies zero on peaceful, two and a half on
     * easy, three on normal, and four and a half on hard. An entity without
     * container-holder capability uses only that base.
     *
     * <p>
     * A container holder calculates the held item's damage against {@code
     * victim} and compares it with the base. Positive item damage can therefore
     * keep a peaceful attack eligible, while a lower item result cannot reduce
     * the difficulty base. Container holders must provide an {@code
     * ENTITY_HAND} container and a usable held stack; missing equipment does
     * not select a fallback in this helper.
     */
    protected float getAttackDamage(EntityIntelligent entity, EntityLiving victim) {
        var damage = switch (entity.getWorld().getWorldData().getDifficulty()) {
            case PEACEFUL -> 0f;
            case EASY -> 2.5f;
            case NORMAL -> 3f;
            case HARD -> 4.5f;
        };

        if (entity instanceof EntityContainerHolderComponent containerHolderComponent) {
            var itemDamage = containerHolderComponent.getContainer(ContainerTypes.ENTITY_HAND).getItemInHand().calculateAttackDamage(entity, victim);
            damage = Math.max(damage, itemDamage);
        }

        return damage;
    }
}
