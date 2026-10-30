package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityChickenBaseComponentImpl} supplies chicken collision dimensions
 * and adult item drops while inheriting passive-animal feeding and experience
 * behavior. Baby state suppresses this component's item additions, and adult
 * loot uses the current burning state and the event's looting level when its
 * drops are queried.
 *
 * <p>
 * Adult drops include one chicken item plus a random bonus from zero through
 * the looting level. Feathers use a separate base draw from zero through two
 * and a separate looting bonus; an empty feather draw contributes no stack.
 * Burning changes the chicken item to its cooked form.
 */
public class EntityChickenBaseComponentImpl extends EntityAnimalBaseComponentImpl {

    /**
     * Creates the chicken base component using the supplied entity
     * initialization data.
     */
    public EntityChickenBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns a fresh unscaled chicken collision box in local block
     * coordinates. The box is centered horizontally at the entity origin, with
     * width {@code 0.6} and height {@code 0.8}; callers do not receive retained
     * geometry.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.3, 0.0, -0.3, 0.3, 0.8, 0.3);
    }

    /**
     * Adds adult chicken meat and feather drops to the supplied event. Babies
     * leave the drop list unchanged. Meat starts at one item and adds an
     * independent random bonus from zero through the looting level; the current
     * fire state chooses raw or cooked meat.
     *
     * <p>
     * Feathers add a separate zero-to-two base draw and zero-to-looting bonus.
     * A zero feather total contributes no stack, and existing event drops
     * remain available alongside the stacks added here.
     */
    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (babyComponent.isBaby()) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        // The looting bonus is drawn separately from base meat so higher looting
        // raises the possible count without replacing the guaranteed meat item.
        int chickenCount = 1 + rand.nextInt(event.getLootingLevel() + 1);
        var chickenType = livingComponent.isOnFire() ? ItemTypes.COOKED_CHICKEN : ItemTypes.CHICKEN;
        event.getDrops().add(chickenType.createItemStack(chickenCount));

        // Separate feather and looting draws retain the base zero-drop outcome
        // when both draws are zero, rather than forcing an empty item stack.
        int featherCount = rand.nextInt(3) + rand.nextInt(event.getLootingLevel() + 1);
        if (featherCount > 0) {
            event.getDrops().add(ItemTypes.FEATHER.createItemStack(featherCount));
        }
    }
}
