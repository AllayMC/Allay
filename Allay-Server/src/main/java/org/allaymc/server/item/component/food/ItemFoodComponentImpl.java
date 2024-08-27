package org.allaymc.server.item.component.food;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerEatFoodEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.server.component.annotation.ComponentedObject;
import org.allaymc.server.item.component.event.CItemTryUseEvent;
import org.allaymc.server.item.component.event.CItemUsedEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
@Getter
public class ItemFoodComponentImpl implements ItemFoodComponent {

    private static final int DEFAULT_EATING_TIME = 32; // GameTick

    private final int foodPoints;
    private final float saturationPoints;
    private final int eatingTime;

    @ComponentedObject
    protected ItemStack thisItemStack;

    public ItemFoodComponentImpl(int foodPoints, float saturationPoints) {
        this(foodPoints, saturationPoints, DEFAULT_EATING_TIME);
    }

    public ItemFoodComponentImpl(int foodPoints, float saturationPoints, int eatingTime) {
        this.foodPoints = foodPoints;
        this.saturationPoints = saturationPoints;
        this.eatingTime = eatingTime;
    }

    @Override
    public void onEaten(EntityPlayer player) {
        player.saturate(this.foodPoints, this.saturationPoints);
        var pos = player.getLocation();
        player.getDimension().addLevelSoundEvent(pos.x(), pos.y(), pos.z(), SoundEvent.BURP);
    }

    @Override
    public boolean canBeAlwaysEaten() {
        return false;
    }

    @EventHandler
    protected void onTryUseItem(CItemTryUseEvent event) {
        event.setCanBeUsed(canBeAlwaysEaten() || event.getPlayer().canEat());
    }

    @EventHandler
    protected void onItemUsed(CItemUsedEvent event) {
        if (event.getUsedTime() < eatingTime) {
            event.setCanBeUsed(false);
            return;
        }

        var player = event.getPlayer();
        var playerEatFoodEvent = new PlayerEatFoodEvent(player, thisItemStack);
        playerEatFoodEvent.call();
        if (playerEatFoodEvent.isCancelled()) {
            event.setCanBeUsed(false);
            return;
        }

        onEaten(player);
        player.tryConsumeItemInHand();
    }
}
