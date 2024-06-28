package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerHungerComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 28/06/2024
 *
 * @author IWareQ
 * @see <a href="https://minecraft.wiki/w/Hunger">Hunger</a>
 */
@Getter
@Setter
public class EntityPlayerHungerComponentImpl implements EntityPlayerHungerComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:player_hunger_component");

    @ComponentedObject
    protected EntityPlayer player;

    private int foodLevel = 20;
    private int foodSaturationLevel = 20;

    private float foodTickTimer;
    private float foodExhaustionLevel = 5;

    @Override
    public void tick() {
        if (player.isSpawned()) {
            var needSend = false;

            var hunger = player.getAttribute(AttributeType.PLAYER_HUNGER);
            if (hunger.getCurrentValue() != foodLevel) {
                hunger.setCurrentValue(foodLevel);
                needSend = true;
            }

            var saturation = player.getAttribute(AttributeType.PLAYER_SATURATION);
            if (saturation.getCurrentValue() != foodSaturationLevel) {
                saturation.setCurrentValue(foodSaturationLevel);
                needSend = true;
            }

            var exhaustion = player.getAttribute(AttributeType.PLAYER_EXHAUSTION);
            if (exhaustion.getCurrentValue() != foodExhaustionLevel) {
                exhaustion.setCurrentValue(foodExhaustionLevel);
                needSend = true;
            }

            if (needSend) player.sendAttributesToClient();
        }
    }

    @Override
    public void setFoodTickTimer(float foodTickTimer) {
        this.foodTickTimer = Math.max(foodTickTimer, 0);
    }

    @Override
    public void setFoodExhaustionLevel(float foodExhaustionLevel) {
        while (foodExhaustionLevel >= 4) {
            foodExhaustionLevel -= 4;

            if (this.foodSaturationLevel > 0) {
                this.foodSaturationLevel--;
            } else {
                this.foodLevel = Math.max(--this.foodLevel, 0);
            }
        }

        this.foodExhaustionLevel = foodExhaustionLevel;
    }
}
