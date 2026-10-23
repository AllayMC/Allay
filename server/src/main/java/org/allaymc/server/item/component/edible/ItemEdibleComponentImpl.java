package org.allaymc.server.item.component.edible;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerEatEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.component.event.CItemTryUseInAirEvent;
import org.allaymc.server.item.component.event.CItemUseInAirEvent;

/**
 * {@code ItemEdibleComponentImpl} supplies configurable nutrition and
 * consumption eligibility for edible item stacks. Use-event handlers check the
 * eating duration and cancellable eating event before invoking
 * {@code onEaten}, then attempt held-item consumption; subclasses add effects
 * within that callback.
 *
 * @author IWareQ
 */
@Getter
@RequiredArgsConstructor
public class ItemEdibleComponentImpl implements ItemEdibleComponent {
    /**
     * {@code IDENTIFIER} names the shared edible component for provider lookup
     * and dependency injection during item behavior assembly.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_food_component");
    /**
     * {@code DEFAULT_EATING_TIME} defines the thirty-one game ticks used by
     * constructors that do not specify a separate eating duration.
     */
    public static final int DEFAULT_EATING_TIME = 31;

    /**
     * Consumed food points.
     */
    private final int foodPoints;
    /**
     * Consumed saturation points.
     */
    private final float saturationPoints;
    /**
     * Minimum use ticks.
     */
    private final int eatingTime;
    /**
     * Drink behavior flag.
     */
    private final boolean drink;
    /**
     * {@code canBeAlwaysEaten} controls whether use may start without
     * {@code EntityPlayer.canEat()}. The fluent generated accessor supplies
     * the eligibility override used by {@code onTryUseItem}.
     */
    @Accessors(fluent = true)
    private final boolean canBeAlwaysEaten;

    /**
     * {@code thisItemStack} holds the injected owning stack. {@code onItemUse}
     * passes that instance to {@code PlayerEatEvent} before invoking the
     * consumption callback.
     */
    @ComponentObject
    protected ItemStack thisItemStack;

    /**
     * Creates ordinary food behavior with the supplied nutrition values. This
     * overload uses {@code DEFAULT_EATING_TIME}, a non-drink flag, and no
     * bypass of the ordinary player eating-eligibility check.
     */
    public ItemEdibleComponentImpl(int foodPoints, float saturationPoints) {
        this(foodPoints, saturationPoints, DEFAULT_EATING_TIME, false, false);
    }

    /**
     * Creates ordinary food behavior with explicit nutrition and consumption
     * duration. {@code eatingTime} is measured in game ticks; this overload
     * selects a non-drink flag and keeps the ordinary player
     * eating-eligibility check.
     */
    public ItemEdibleComponentImpl(int foodPoints, float saturationPoints, int eatingTime) {
        this(foodPoints, saturationPoints, eatingTime, false, false);
    }

    /**
     * Creates configurable food or drink behavior with an explicit consumption
     * duration. {@code eatingTime} is measured in game ticks, and this
     * overload retains the ordinary player eating-eligibility check regardless
     * of {@code drink}.
     */
    public ItemEdibleComponentImpl(int foodPoints, float saturationPoints, int eatingTime, boolean drink) {
        this(foodPoints, saturationPoints, eatingTime, drink, false);
    }

    /**
     * Requests the configured food and saturation additions through the
     * player's nutrition API, then emits a burp sound. Player setters retain
     * their bounds and events; the surrounding use handler separately attempts
     * held-item consumption.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        player.saturate(this.foodPoints, this.saturationPoints);
        var pos = player.getLocation();
        player.getDimension().addSound(pos.x(), pos.y(), pos.z(), SimpleSound.BURP);
    }

    /**
     * Sets use eligibility from the configured hunger bypass and the player's
     * current eating eligibility. A bypass permits starting use even when
     * {@code canEat()} is {@code false}; this handler does not consume items.
     */
    @EventHandler
    protected void onTryUseItem(CItemTryUseInAirEvent event) {
        event.setCanBeUsed(canBeAlwaysEaten() || event.getPlayer().canEat());
    }

    /**
     * Processes consumption after the configured duration and a successful
     * eating event. Early use or cancellation marks the event unusable; the
     * accepted path invokes {@code onEaten} before held-item consumption but
     * does not set {@code canBeUsed} to {@code true}.
     */
    @EventHandler
    protected void onItemUse(CItemUseInAirEvent event) {
        if (event.getUsingTime() < eatingTime) {
            event.setCanBeUsed(false);
            return;
        }

        var player = event.getPlayer();
        var playerEatFoodEvent = new PlayerEatEvent(player, thisItemStack);
        if (!playerEatFoodEvent.call()) {
            event.setCanBeUsed(false);
            return;
        }

        // Apply callback effects before consuming the current hand item; callbacks can
        // return containers or change player state that the later consumption observes.
        onEaten(player);
        player.tryConsumeItemInHand();
    }
}
