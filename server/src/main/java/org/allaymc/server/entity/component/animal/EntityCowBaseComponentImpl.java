package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityCowBaseComponentImpl} supplies cow collision dimensions and
 * adult item drops while inheriting passive-animal feeding and experience
 * behavior. Baby state suppresses this component's item additions, and adult
 * loot uses the current burning state and the event's looting level when its
 * drops are queried.
 *
 * <p>
 * Adult drops combine a zero-to-two leather draw and a one-to-two beef draw
 * with independent zero-to-looting bonuses. Burning changes beef to its cooked
 * form. Adult cows also accept a bucket interaction that consumes from the
 * player's hand and supplies one milk bucket before playing the milking sound.
 */
public class EntityCowBaseComponentImpl extends EntityAnimalBaseComponentImpl {

    /**
     * Creates the cow base component using the supplied entity initialization
     * data.
     */
    public EntityCowBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns a fresh unscaled cow collision box in local block coordinates.
     * The box is centered horizontally at the entity origin, with width
     * {@code 0.9} and height {@code 1.3}; callers do not receive retained
     * geometry.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.45, 0.0, -0.45, 0.45, 1.3, 0.45);
    }

    /**
     * Milks an adult cow when the player presents a bucket, otherwise delegates
     * feeding. A {@code null} player returns {@code false}. Milking attempts
     * hand consumption before adding a milk bucket and playing its sound; a
     * non-{@code null} player must supply a non-{@code null} {@code itemStack}.
     */
    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        if (player == null) {
            return false;
        }

        if (itemStack.getItemType() == ItemTypes.BUCKET && !babyComponent.isBaby()) {
            player.tryConsumeItemInHand();
            player.tryAddItem(ItemTypes.MILK_BUCKET.createItemStack(1));
            player.getDimension().addSound(this.location, SimpleSound.MILKING);
            return true;
        }

        // Milking is the cow-specific interaction. Delegate other items so the
        // shared animal feeding path records AI memory and consumes recognized breeding items.
        return super.onInteract(player, itemStack);
    }

    /**
     * Adds adult leather and beef drops to the supplied mutable event list.
     * Babies leave existing drops unchanged. Leather uses a zero-to-two base
     * draw plus an independent zero-to-looting bonus, and a zero total adds no
     * leather stack.
     *
     * <p>
     * Beef uses a separate one-to-two base draw and another looting bonus. The
     * cow's current burning state selects cooked beef instead of raw beef,
     * while previously added event drops remain in the list.
     */
    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (babyComponent.isBaby()) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        // Leather keeps its own base and looting draws, allowing a zero total
        // while preserving the separate meat draw later in this drop calculation.
        int leatherCount = rand.nextInt(3) + rand.nextInt(event.getLootingLevel() + 1);
        if (leatherCount > 0) {
            event.getDrops().add(ItemTypes.LEATHER.createItemStack(leatherCount));
        }

        // The beef base guarantees meat for adults; its independent looting draw
        // adds a bonus without coupling the count to the leather result.
        int beefCount = rand.nextInt(1, 3) + rand.nextInt(event.getLootingLevel() + 1);
        var beefType = livingComponent.isOnFire() ? ItemTypes.COOKED_BEEF : ItemTypes.BEEF;
        event.getDrops().add(beefType.createItemStack(beefCount));
    }
}
