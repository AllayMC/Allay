package org.allaymc.server.entity.component.item;

import lombok.Getter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityTntBaseComponent;
import org.allaymc.api.eventbus.event.entity.EntityExplodeEvent;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntityTntBaseComponentImpl} represents primed TNT with a persisted
 * fuse countdown and cancellable explosion processing. Positive fuse values
 * decrement each entity tick, while nonpositive values attempt expiration.
 * State publication becomes more frequent during the final second of the fuse,
 * giving viewers the changing countdown without sending every early value.
 *
 * <p>
 * Expiration combines the world explosion rule, current water contact, and
 * event cancellation. A disabled world rule removes the TNT without a blast,
 * while accepted explosion execution follows a removal request. Cancellation
 * retains the expired fuse and permits another attempt on a later tick.
 * Persistence narrows the countdown to a signed short, which limits the
 * runtime values that can survive a round trip.
 *
 * @author daoge_cmd
 */
public class EntityTntBaseComponentImpl extends EntityBaseComponentImpl implements EntityTntBaseComponent {

    /**
     * {@code TAG_FUSE} names the signed short countdown persisted by TNT
     * entity loading and saving.
     */
    public static final String TAG_FUSE = "Fuse";

    /**
     * {@code fuseTime} stores remaining entity ticks and initially equals
     * {@code 80}. Nonpositive values attempt explosion processing rather than
     * another countdown decrement.
     */
    @Getter
    protected int fuseTime = 80;

    /**
     * Creates the primed TNT base with its entity initialization context.
     */
    public EntityTntBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Completes inherited initialization and plays the TNT priming sound in
     * the current dimension. The initialization context must already provide
     * the location used for that sound through the base component.
     */
    @OnInitFinish
    @Override
    public void onInitFinish(EntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        getDimension().addSound(location, SimpleSound.TNT);
    }

    /**
     * Runs inherited entity ticking before advancing the TNT fuse or
     * attempting expiration. The TNT-specific path can queue removal or
     * execute an explosion according to game rules and event cancellation.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickTnt();
    }

    /**
     * Decrements a positive fuse or processes expiration for a nonpositive
     * fuse. Disabling {@code GameRule.TNT_EXPLODES} removes expired TNT
     * without dispatching an explosion event. Otherwise, water contact
     * controls block destruction and the event can cancel execution.
     *
     * <p>
     * An accepted event queues removal before executing the original
     * configured explosion object at a slightly raised location. Cancellation
     * leaves the expired fuse unchanged, so later ticks attempt the event
     * again. Countdown state is published every five ticks before the final
     * second and every tick during that final interval.
     */
    protected void tickTnt() {
        if (fuseTime <= 0) {
            if (!getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.TNT_EXPLODES)) {
                this.remove();
                return;
            }

            var explosion = new Explosion();
            explosion.setDestroyBlocks(!isTouchingWater());
            explosion.setEntity(thisEntity);
            var event = new EntityExplodeEvent(thisEntity, explosion);
            if (event.call()) {
                this.remove();
                // Raise the blast origin slightly above the entity foot position
                // used for TNT movement.
                explosion.explode(getDimension(), location.x, location.y + 0.06125f, location.z);
            }
        } else {
            fuseTime--;
            if (fuseTime % 5 == 0 || fuseTime < 20) {
                // Publish sparsely during the early countdown, then each tick in
                // the final second so viewers receive the rapid fuse animation.
                broadcastState();
            }
        }
    }

    /**
     * Loads inherited entity data and restores a present signed-short fuse
     * through {@code setFuseTime}. That setter publishes state, while a
     * missing fuse tag preserves the current countdown value.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForShort(TAG_FUSE, this::setFuseTime);
    }

    /**
     * Returns inherited entity data with the fuse narrowed to a signed short.
     * Values outside the short range wrap during this conversion, so callers
     * requiring round-trip fidelity must use representable fuse values.
     */
    @Override
    public NbtMap saveNBT() {
        // The stored fuse uses the signed-short entity format, so larger integer
        // countdowns lose their high bits.
        return super.saveNBT()
                .toBuilder()
                .putShort(TAG_FUSE, (short) fuseTime)
                .build();
    }

    /**
     * Returns the primed TNT local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        // Inset the nearly full-block box slightly to avoid full-cell edge overlap.
        return new AABBd(-0.49, 0.0, -0.49, 0.49, 0.98, 0.49);
    }

    /**
     * Replaces the fuse countdown and broadcasts updated TNT state.
     * Nonpositive values select expiration processing on the next tick, while
     * this method itself neither removes the TNT nor explodes it.
     */
    @Override
    public void setFuseTime(int fuseTime) {
        this.fuseTime = fuseTime;
        broadcastState();
    }
}
