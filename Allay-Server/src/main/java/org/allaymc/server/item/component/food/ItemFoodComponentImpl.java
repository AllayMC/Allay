package org.allaymc.server.item.component.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.component.food.ItemFoodComponent;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class ItemFoodComponentImpl implements ItemFoodComponent {
    private final int foodPoints;
    private final float saturationPoints;

    @Override
    public void onEaten(EntityPlayer player) {
        player.saturate(this.foodPoints, this.saturationPoints);
    }
}
