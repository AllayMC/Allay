package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityPigBaseComponentImpl} supplies pig collision dimensions and
 * adult item drops while inheriting passive-animal feeding and experience
 * behavior. Baby state suppresses this component's item additions, and adult
 * loot uses the current burning state and the event's looting level when its
 * drops are queried.
 *
 * <p>
 * Adult drops combine a random base of one to three porkchops with a separate
 * zero-to-looting bonus. Burning selects cooked porkchops. The two random draws
 * remain separate, so their sum does not have the same distribution as a single
 * uniform draw over the combined minimum and maximum counts.
 */
public class EntityPigBaseComponentImpl extends EntityAnimalBaseComponentImpl {

    /**
     * Creates the pig base component using the supplied entity initialization
     * data.
     */
    public EntityPigBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns a fresh unscaled pig collision box in local block coordinates.
     * The box is centered horizontally at the entity origin, with width
     * {@code 0.9} and height {@code 0.9}; callers do not receive retained
     * geometry.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.45, 0.0, -0.45, 0.45, 0.9, 0.45);
    }

    /**
     * Adds adult porkchop drops using the event's looting level and current
     * fire state. Babies leave the existing drop list unchanged. The count sums
     * a one-to-three base draw and an independent bonus from zero through the
     * looting level.
     *
     * <p>
     * Separate draws weight intermediate totals differently from a single
     * uniform choice over the full range. Burning chooses cooked porkchops, and
     * the resulting stack is appended alongside drops already supplied by other
     * components.
     */
    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (babyComponent.isBaby()) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        // Draw the base and looting bonus independently; combining their limits
        // into one uniform draw would change the distribution of porkchop counts.
        int porkchopCount = rand.nextInt(1, 4) + rand.nextInt(event.getLootingLevel() + 1);
        var porkchopType = livingComponent.isOnFire() ? ItemTypes.COOKED_PORKCHOP : ItemTypes.PORKCHOP;
        event.getDrops().add(porkchopType.createItemStack(porkchopCount));
    }
}
