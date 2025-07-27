package org.allaymc.server.item.component.food;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerEatFoodEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.component.event.CItemTryUseInAirEvent;
import org.allaymc.server.item.component.event.CItemUsedInAirEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
@Getter
@RequiredArgsConstructor
public class ItemFoodComponentImpl implements ItemFoodComponent {
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

    public ItemFoodComponentImpl(int foodPoints, float saturationPoints) {
        this(foodPoints, saturationPoints, DEFAULT_EATING_TIME, false, false);
    }

    public ItemFoodComponentImpl(int foodPoints, float saturationPoints, int eatingTime) {
        this(foodPoints, saturationPoints, eatingTime, false, false);
    }

    public ItemFoodComponentImpl(int foodPoints, float saturationPoints, int eatingTime, boolean drink) {
        this(foodPoints, saturationPoints, eatingTime, drink, false);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        player.saturate(this.foodPoints, this.saturationPoints);
        var pos = player.getLocation();
        player.getDimension().addLevelSoundEvent(pos.x(), pos.y(), pos.z(), SoundEvent.BURP);
    }

    @EventHandler
    protected void onTryUseItem(CItemTryUseInAirEvent event) {
        event.setCanBeUsed(canBeAlwaysEaten() || event.getPlayer().canEat());
    }

    @EventHandler
    protected void onItemUsed(CItemUsedInAirEvent event) {
        if (event.getUsedTime() < eatingTime) {
            event.setCanBeUsed(false);
            return;
        }

        var player = event.getPlayer();
        var playerEatFoodEvent = new PlayerEatFoodEvent(player, thisItemStack);
        if (!playerEatFoodEvent.call()) {
            event.setCanBeUsed(false);
            return;
        }

        onEaten(player);
        player.tryConsumeItemInHand();
    }
}
