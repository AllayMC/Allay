package org.allaymc.server.entity.component.item;

import lombok.Getter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityEnderCrystalBaseComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityExplodeEvent;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntityEnderCrystalBaseComponentImpl} represents an ender crystal with
 * configurable base visibility and damage-triggered explosion handling. The
 * crystal has no entity push collision, and a spawned crystal with recorded
 * damage requests a cancellable explosion before removal.
 *
 * @author ClexaGod | daoge_cmd
 */
public class EntityEnderCrystalBaseComponentImpl extends EntityBaseComponentImpl implements EntityEnderCrystalBaseComponent {

    /**
     * {@code TAG_SHOW_BOTTOM} names the persisted base-visibility flag used
     * when restoring or saving a crystal.
     */
    protected static final String TAG_SHOW_BOTTOM = "ShowBottom";

    /**
     * Injected recorded damage state component.
     */
    @Dependency
    protected EntityLivingComponent livingComponent;

    /**
     * Current crystal base visibility flag.
     */
    @Getter
    protected boolean baseVisible;

    /**
     * Creates the crystal base with the supplied entity initialization
     * context.
     */
    public EntityEnderCrystalBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Returns the ender crystal local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-1, 0.0, -1, 1, 2, 1);
    }

    /**
     * Returns {@code false} so the crystal does not participate as a pushable
     * entity.
     */
    @Override
    public boolean hasEntityCollision() {
        return false;
    }

    /**
     * Replaces the base-visibility flag and broadcasts the updated crystal
     * state. The new value is also written by subsequent entity serialization
     * through the crystal visibility persistence tag.
     */
    @Override
    public void setBaseVisibility(boolean value) {
        this.baseVisible = value;
        broadcastState();
    }

    /**
     * Returns inherited entity data with the crystal base visibility
     * persisted.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putBoolean(TAG_SHOW_BOTTOM, this.baseVisible)
                .build();
    }

    /**
     * Loads inherited entity data and restores a present base-visibility flag.
     * A missing tag leaves the current flag unchanged, and loading assigns it
     * without a separate state broadcast.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForBoolean(TAG_SHOW_BOTTOM, value -> this.baseVisible = value);
    }

    /**
     * Attempts a power-six explosion for a spawned crystal with recorded
     * damage. Cancellation leaves the crystal without this removal request;
     * acceptance queues removal before executing the original explosion while
     * manager membership can still remain.
     */
    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        if (!isSpawned()) {
            return;
        }

        var lastDamage = livingComponent.getLastDamage();
        if (lastDamage == null) {
            return;
        }

        // Crystal blasts use power six, with block destruction governed separately
        // by mob griefing.
        var explosion = new Explosion(6);
        explosion.setEntity(thisEntity);
        explosion.setDestroyBlocks(thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.MOB_GRIEFING));
        if (new EntityExplodeEvent(thisEntity, explosion).call()) {
            remove();
            explosion.explode(getDimension(), location.x, location.y, location.z);
        }
    }
}
