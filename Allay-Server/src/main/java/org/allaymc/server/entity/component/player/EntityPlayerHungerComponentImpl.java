package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerHungerComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Difficulty;
import org.cloudburstmc.protocol.bedrock.data.GameType;

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

    private static final int MAX_FOOD_LEVEL = 20;

    @ComponentedObject
    protected EntityPlayer player;

    private int foodLevel = MAX_FOOD_LEVEL;
    private float foodSaturationLevel = 5f;
    private float foodExhaustionLevel;

    private int foodTickTimer;

    @Override
    public void tickHunger() {
        if (
                !player.isSpawned() || player.isDead() ||
                player.getGameType() == GameType.CREATIVE ||
                player.getGameType() == GameType.SPECTATOR
        ) return;

        foodTickTimer++;
        if (foodTickTimer >= 80) foodTickTimer = 0;

        var difficulty = player.getWorld().getWorldData().getDifficulty();
        if (difficulty == Difficulty.PEACEFUL && foodTickTimer % 10 == 0) {
            setFoodLevel(foodLevel + 1);

            if (foodTickTimer % 20 == 0) regenerate(false);
        }

        if (foodTickTimer == 0 && difficulty != Difficulty.PEACEFUL) {
            if (foodLevel >= 18) regenerate(true);
            else if (foodLevel == 0) {
                if (
                        (difficulty == Difficulty.EASY && player.getHealth() > 10) ||
                        (difficulty == Difficulty.NORMAL && player.getHealth() > 1) ||
                        difficulty == Difficulty.HARD
                ) {
                    player.attack(DamageContainer.starve(1));
                }
            }
        }

        if (foodLevel <= 6 && player.isSprinting()) {
            player.setSprinting(false);
        }

        syncFoodData();
    }

    private void syncFoodData() {
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

    @Override
    public void exhaust(float level) {
        if (player.getGameType() == GameType.CREATIVE) return;
        this.foodExhaustionLevel += level;
        while (this.foodExhaustionLevel >= 4) {
            this.foodExhaustionLevel -= 4;

            if (this.foodSaturationLevel > 0) {
                this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1, 0);
            } else {
                this.setFoodLevel(this.foodLevel - 1);
            }
        }
    }

    @Override
    public void saturate(int food, float saturation) {
        this.setFoodLevel(this.foodLevel + food);

        this.foodSaturationLevel += saturation;
        if (this.foodSaturationLevel > this.foodLevel)
            this.foodSaturationLevel = this.foodLevel;
    }

    private void regenerate(boolean exhaust) {
        if (player.getHealth() == player.getMaxHealth()) return;

        // TODO: normal heal method with event
        player.setHealth(player.getHealth() + 1);
        if (exhaust) player.exhaust(6);
    }

    @Override
    public boolean canEat() {
        return getFoodLevel() < MAX_FOOD_LEVEL ||
               player.getGameType() == GameType.CREATIVE ||
               player.getWorld().getWorldData().getDifficulty() == Difficulty.PEACEFUL;
    }

    @Override
    public void setFoodLevel(int foodLevel) {
        // TODO: event
        this.foodLevel = Math.max(Math.min(foodLevel, MAX_FOOD_LEVEL), 0);
    }

    @Override
    public void setFoodTickTimer(int foodTickTimer) {
        this.foodTickTimer = Math.max(foodTickTimer, 0);
    }
}
