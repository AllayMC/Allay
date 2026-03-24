package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.AttackSound;
import org.joml.Vector3d;

/**
 * Executes a simple melee chase-and-attack behavior against a target entity stored in memory.
 */
public class MeleeAttackExecutor implements BehaviorExecutor {

    protected static final double DEFAULT_ATTACK_RANGE = Math.sqrt(2.5);

    protected final MemoryType<Long> targetIdMemory;
    protected final float speed;
    protected final double maxSenseRangeSquared;
    protected final boolean clearTargetAfterLose;
    protected final int coolDown;
    protected final double attackRangeSquared;

    protected int attackTick;
    protected Vector3d lastTargetPos;

    /**
     * Creates a melee attack executor that keeps the target memory when the behavior stops.
     *
     * @param targetIdMemory the memory entry that stores the target entity runtime id.
     * @param speed the movement speed while chasing the target.
     * @param maxSenseRange the maximum target tracking range in blocks.
     * @param coolDown the attack cooldown in ticks.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange, int coolDown) {
        this(targetIdMemory, speed, maxSenseRange, false, coolDown, DEFAULT_ATTACK_RANGE);
    }

    /**
     * Creates a melee attack executor with a custom attack range.
     *
     * @param targetIdMemory the memory entry that stores the target entity runtime id.
     * @param speed the movement speed while chasing the target.
     * @param maxSenseRange the maximum target tracking range in blocks.
     * @param coolDown the attack cooldown in ticks.
     * @param attackRange the maximum melee attack range in blocks.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange,
                               int coolDown, double attackRange) {
        this(targetIdMemory, speed, maxSenseRange, false, coolDown, attackRange);
    }

    /**
     * Creates a melee attack executor with configurable target clearing behavior.
     *
     * @param targetIdMemory the memory entry that stores the target entity runtime id.
     * @param speed the movement speed while chasing the target.
     * @param maxSenseRange the maximum target tracking range in blocks.
     * @param clearTargetAfterLose whether to clear the target memory when the behavior stops.
     * @param coolDown the attack cooldown in ticks.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange,
                               boolean clearTargetAfterLose, int coolDown) {
        this(targetIdMemory, speed, maxSenseRange, clearTargetAfterLose, coolDown, DEFAULT_ATTACK_RANGE);
    }

    /**
     * Creates a melee attack executor with configurable target clearing behavior and attack range.
     *
     * @param targetIdMemory the memory entry that stores the target entity runtime id.
     * @param speed the movement speed while chasing the target.
     * @param maxSenseRange the maximum target tracking range in blocks.
     * @param clearTargetAfterLose whether to clear the target memory when the behavior stops.
     * @param coolDown the attack cooldown in ticks.
     * @param attackRange the maximum melee attack range in blocks.
     */
    public MeleeAttackExecutor(MemoryType<Long> targetIdMemory, float speed, double maxSenseRange,
                               boolean clearTargetAfterLose, int coolDown, double attackRange) {
        this.targetIdMemory = targetIdMemory;
        this.speed = speed;
        this.maxSenseRangeSquared = maxSenseRange * maxSenseRange;
        this.clearTargetAfterLose = clearTargetAfterLose;
        this.coolDown = coolDown;
        this.attackRangeSquared = attackRange * attackRange;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        attackTick = 0;
        lastTargetPos = null;
        entity.setMovementSpeed(speed);
        entity.setPitchEnabled(true);
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        attackTick++;

        var targetId = entity.getMemoryStorage().get(targetIdMemory);
        if (targetId == null) {
            return false;
        }

        var targetEntity = entity.getDimension().getEntityManager().getEntity(targetId);
        if (!(targetEntity instanceof EntityLivingComponent targetLiving) || !isTargetValid(targetEntity)) {
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
        if (lastTargetPos == null || isInDifferentBlock(lastTargetPos, targetPos)) {
            entity.getBehaviorGroup().setRouteUpdateRequired(true);
        }
        lastTargetPos = targetPos;

        EntityControlHelper.setLookTarget(entity, new Vector3d(
                targetLoc.x(), targetLoc.y() + targetEntity.getEyeHeight(), targetLoc.z()
        ));

        if (distanceSquared <= attackRangeSquared && attackTick > coolDown) {
            var damage = getAttackDamage(entity);
            if (damage <= 0) {
                return false;
            }

            var attackSuccess = targetLiving.attack(DamageContainer.entityAttack(entity, damage));
            entity.applyAction(SimpleEntityAction.SWING_ARM);
            entity.getDimension().addSound(targetLoc, new AttackSound(attackSuccess));
            if (attackSuccess) {
                attackTick = 0;
            }
        }

        return true;
    }

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

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }

    protected boolean isInDifferentBlock(Vector3d oldTargetPos, Vector3d newTargetPos) {
        return Math.floor(oldTargetPos.x()) != Math.floor(newTargetPos.x()) ||
               Math.floor(oldTargetPos.y()) != Math.floor(newTargetPos.y()) ||
               Math.floor(oldTargetPos.z()) != Math.floor(newTargetPos.z());
    }

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

    protected float getAttackDamage(EntityIntelligent entity) {
        return switch (entity.getWorld().getWorldData().getDifficulty()) {
            case PEACEFUL -> 0f;
            case EASY -> 2.5f;
            case NORMAL -> 3f;
            case HARD -> 4.5f;
        };
    }
}
