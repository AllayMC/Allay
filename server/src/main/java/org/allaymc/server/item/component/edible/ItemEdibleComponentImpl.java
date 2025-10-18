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
 * @author IWareQ
 */
@Getter
@RequiredArgsConstructor
public class ItemEdibleComponentImpl implements ItemEdibleComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_food_component");
    public static final int DEFAULT_EATING_TIME = 31; // GameTick

    private final int foodPoints;
    private final float saturationPoints;
    private final int eatingTime;
    private final boolean drink;
    @Accessors(fluent = true)
    private final boolean canBeAlwaysEaten;

    @ComponentObject
    protected ItemStack thisItemStack;

    public ItemEdibleComponentImpl(int foodPoints, float saturationPoints) {
        this(foodPoints, saturationPoints, DEFAULT_EATING_TIME, false, false);
    }

    public ItemEdibleComponentImpl(int foodPoints, float saturationPoints, int eatingTime) {
        this(foodPoints, saturationPoints, eatingTime, false, false);
    }

    public ItemEdibleComponentImpl(int foodPoints, float saturationPoints, int eatingTime, boolean drink) {
        this(foodPoints, saturationPoints, eatingTime, drink, false);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        player.saturate(this.foodPoints, this.saturationPoints);
        var pos = player.getLocation();
        player.getDimension().addSound(pos.x(), pos.y(), pos.z(), SimpleSound.BURP);
    }

    @EventHandler
    protected void onTryUseItem(CItemTryUseInAirEvent event) {
        event.setCanBeUsed(canBeAlwaysEaten() || event.getPlayer().canEat());
    }

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

        onEaten(player);
        player.tryConsumeItemInHand();
    }
}
