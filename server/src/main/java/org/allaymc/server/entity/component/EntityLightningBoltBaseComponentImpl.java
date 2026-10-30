package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLightningBoltBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.fire.BlockFireBaseComponentImpl;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityLightningBoltBaseComponentImpl} manages a transient lightning
 * bolt's flashes, sounds, damage, and ignition attempts. {@code state}
 * separates active flashes from waiting intervals, while {@code liveTime}
 * counts remaining repeat flashes after the initial appearance. The
 * constructor disables persistence because this component's strike progression
 * is temporary.
 *
 * <p> {@code tickLightning} emits the initial sounds, advances the flash
 * state, and removes the bolt only when its waiting state has no repeats
 * remaining. The {@code effect} flag suppresses damage and ignition while
 * preserving visual progression and sounds. Accepted ignition additionally
 * requires the fire-tick game rule and a cancellable block event, while living
 * targets receive an independent fire-duration assignment after their damage
 * attempt.
 *
 * @author daoge_cmd
 */
public class EntityLightningBoltBaseComponentImpl extends EntityBaseComponentImpl implements EntityLightningBoltBaseComponent {

    /**
     * {@code LIGHTNING_DAMAGE} supplies five damage points to each living
     * target reached during an active lightning flash; ordinary living damage
     * checks still apply.
     */
    protected static final float LIGHTNING_DAMAGE = 5.0f;

    /**
     * {@code effect} enables nearby damage and block ignition attempts.
     * Disabling it preserves the bolt's flash counters, sound emission, and
     * eventual removal.
     */
    @Getter
    @Setter
    protected boolean effect = true;

    // state counts down through each active flash and its waiting interval;
    // liveTime decreases only when a waiting interval starts another flash.

    /**
     * Current flash phase and negative inter-flash countdown.
     */
    protected int state;
    /**
     * Remaining repeat flashes after the initial lightning appearance.
     */
    protected int liveTime;

    /**
     * Creates base entity state for a temporary lightning bolt and disables
     * persistence. Flash counters are initialized after component setup, so
     * the bolt's active sequence begins in {@code onInitFinish}.
     */
    public EntityLightningBoltBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.persistent = false;
    }

    /**
     * Loads common entity initialization and starts the lightning sequence
     * with its initial flash. A random count of one to three repeat flashes
     * follows that initial appearance before the bolt removes itself.
     */
    @OnInitFinish
    @Override
    public void onInitFinish(EntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        this.state = 2;
        this.liveTime = ThreadLocalRandom.current().nextInt(3) + 1;
    }

    /**
     * Advances the common entity tick and then the lightning flash sequence.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickLightning();
    }

    /**
     * Advances the active flash or waiting interval and applies the enabled
     * strike effects. Initial state {@code 2} emits thunder and explosion
     * sounds before the phase decreases. Negative phases either finish a bolt
     * with no repeats or wait until the random retry threshold starts another
     * flash.
     *
     * <p> A repeated flash decrements {@code liveTime} before resetting
     * {@code state}, and it attempts block ignition only when effects and the
     * fire-tick game rule are enabled. Every nonnegative phase with effects
     * enabled can attempt nearby damage, so repeated ticks rely on
     * living-component damage rules rather than a per-target strike record.
     */
    protected void tickLightning() {
        if (state == 2) {
            getDimension().addSound(location, SimpleSound.THUNDER);
            getDimension().addSound(location, SimpleSound.EXPLOSION);
        }

        state--;

        // After an active flash ends, remove exhausted bolts before considering
        // the random waiting threshold that starts another repeat flash.
        if (state < 0) {
            if (liveTime == 0) {
                this.remove();
                return;
            } else if (state < -ThreadLocalRandom.current().nextInt(10)) {
                liveTime--;
                state = 1;

                if (effect && getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
                    tryIgniteBlock();
                }
            }
        }

        if (state >= 0 && effect) {
            damageNearbyEntities();
        }
    }

    /**
     * Attempts to place fire at the bolt's floored block position. Only air or
     * grass above a fire-supporting block is eligible, and cancellation of the
     * block ignition event leaves that position unchanged.
     */
    protected void tryIgniteBlock() {
        var dimension = getDimension();
        var blockPos = new Position3i((int) Math.floor(location.x()), (int) Math.floor(location.y()), (int) Math.floor(location.z()), dimension);
        var blockState = dimension.getBlockState(blockPos);
        var blockType = blockState.getBlockType();

        if (blockType != BlockTypes.AIR && blockType != BlockTypes.SHORT_GRASS && blockType != BlockTypes.TALL_GRASS) {
            return;
        }

        var downPos = new Position3i(blockPos.x(), blockPos.y() - 1, blockPos.z(), dimension);
        var downBlockState = dimension.getBlockState(downPos);
        if (!BlockFireBaseComponentImpl.canSupportFire(downBlockState)) {
            return;
        }

        var event = new BlockIgniteEvent(
                new Block(downBlockState, downPos, 0),
                null,
                (EntityLightningBolt) thisEntity,
                BlockIgniteEvent.BlockIgniteCause.LIGHTNING
        );

        if (event.call()) {
            dimension.setBlockState(blockPos, BlockTypes.FIRE.getDefaultState());
        }
    }

    /**
     * Attempts lightning strikes against other entities intersecting the
     * expanded bolt box.
     */
    protected void damageNearbyEntities() {
        var aabb = thisEntity.getOffsetAABB();
        // Reach three blocks beyond each bolt-box face for nearby strike candidates.
        var expandedAABB = new AABBd(
                aabb.minX() - 3, aabb.minY() - 3, aabb.minZ() - 3,
                aabb.maxX() + 3, aabb.maxY() + 3, aabb.maxZ() + 3
        );

        var entities = getDimension().getEntityManager().getPhysicsService().computeCollidingEntities(expandedAABB);
        for (Entity entity : entities) {
            if (entity != thisEntity) {
                onStruckEntity(entity);
            }
        }
    }

    /**
     * Attempts lightning damage against a living target and requests eight
     * seconds of fire. Other lightning bolts are ignored; the fire request
     * still occurs when damage is rejected, but fireproof targets can
     * independently refuse it.
     */
    protected void onStruckEntity(Entity entity) {
        if (entity instanceof EntityLightningBolt) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            var damage = DamageContainer.lightning(thisEntity, LIGHTNING_DAMAGE);
            living.attack(damage);

            // Eight seconds of burning corresponds to 160 entity ticks.
            living.setOnFireTicks(160);
        }
    }

    /**
     * Returns a new narrow bolt box extending two blocks above its origin.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.1, 0.0, -0.1, 0.1, 2.0, 0.1);
    }
}
