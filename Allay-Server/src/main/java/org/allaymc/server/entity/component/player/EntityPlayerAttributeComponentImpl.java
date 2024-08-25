package org.allaymc.server.entity.component.player;

import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.api.entity.component.event.CEntitySaveNBTEvent;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerExperienceLevelChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerExperienceProgressChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerFoodLevelChangeEvent;
import org.allaymc.api.world.Difficulty;
import org.allaymc.server.entity.component.EntityAttributeComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class EntityPlayerAttributeComponentImpl extends EntityAttributeComponentImpl implements EntityPlayerAttributeComponent {

    @ComponentedObject
    private EntityPlayer thisPlayer;
    private int foodTickTimer;

    public EntityPlayerAttributeComponentImpl(AttributeType... attributeTypes) {
        super(attributeTypes);
    }

    @Override
    public void tick() {
        super.tick();

        if (
                !thisPlayer.isSpawned() || thisPlayer.isDead() ||
                thisPlayer.getGameType() == GameType.CREATIVE ||
                thisPlayer.getGameType() == GameType.SPECTATOR
        ) return;

        foodTickTimer++;
        if (foodTickTimer >= 80) foodTickTimer = 0;

        var currentFoodLevel = getFoodLevel();
        var difficulty = thisPlayer.getWorld().getWorldData().getDifficulty();
        if (difficulty == Difficulty.PEACEFUL && foodTickTimer % 10 == 0) {
            setFoodLevel(currentFoodLevel + 1);

            if (foodTickTimer % 20 == 0) regenerate(false);
        }

        if (foodTickTimer == 0 && difficulty != Difficulty.PEACEFUL) {
            if (currentFoodLevel >= 18) regenerate(true);
            else if (currentFoodLevel == 0) {
                if (
                        (difficulty == Difficulty.EASY && thisPlayer.getHealth() > 10) ||
                        (difficulty == Difficulty.NORMAL && thisPlayer.getHealth() > 1) ||
                        difficulty == Difficulty.HARD
                ) {
                    thisPlayer.attack(DamageContainer.starve(1));
                }
            }
        }

        if (currentFoodLevel <= 6 && thisPlayer.isSprinting()) {
            thisPlayer.setSprinting(false);
        }
    }

    protected void regenerate(boolean exhaust) {
        if (thisPlayer.getHealth() == thisPlayer.getMaxHealth()) return;

        thisPlayer.setHealth(thisPlayer.getHealth() + 1);
        if (exhaust) thisPlayer.exhaust(6);
    }

    @Override
    public boolean canEat() {
        return getFoodLevel() < MAX_FOOD_LEVEL ||
               thisPlayer.getGameType() == GameType.CREATIVE ||
               thisPlayer.getWorld().getWorldData().getDifficulty() == Difficulty.PEACEFUL;
    }

    @Override
    public int getExperienceLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_EXPERIENCE_LEVEL);
    }

    @Override
    public void setExperienceLevel(int value) {
        var event = new PlayerExperienceLevelChangeEvent(thisPlayer, (int) getAttributeValue(AttributeType.PLAYER_EXPERIENCE_LEVEL), value);
        event.call();
        if (event.isCancelled()) return;

        setAttribute(AttributeType.PLAYER_EXPERIENCE_LEVEL, event.getNewExperienceLevel());
    }

    @Override
    public float getExperienceProgress() {
        return getAttributeValue(AttributeType.PLAYER_EXPERIENCE_PROGRESS);
    }

    @Override
    public void setExperienceProgress(float value) {
        var event = new PlayerExperienceProgressChangeEvent(thisPlayer, getAttributeValue(AttributeType.PLAYER_EXPERIENCE_PROGRESS), value);
        event.call();
        if (event.isCancelled()) return;

        setAttribute(AttributeType.PLAYER_EXPERIENCE_PROGRESS, event.getNewExperienceProgress());
    }

    @Override
    public void setFoodLevel(int value) {
        value = Math.max(0, Math.min(value, MAX_FOOD_LEVEL));

        var event = new PlayerFoodLevelChangeEvent(thisPlayer, getFoodLevel(), value);
        event.call();
        if (event.isCancelled()) return;

        setAttribute(AttributeType.PLAYER_HUNGER, value);
    }

    @Override
    public void setFoodTickTimer(int foodTickTimer) {
        this.foodTickTimer = Math.max(foodTickTimer, 0);
    }

    @Override
    public void exhaust(float level) {
        if (thisPlayer.getGameType() == GameType.CREATIVE) return;
        var currentFoodExhaustionLevel = getFoodExhaustionLevel();
        var currentFoodSaturationLevel = getFoodSaturationLevel();
        var currentFoodLevel = getFoodLevel();

        currentFoodExhaustionLevel += level;
        while (currentFoodExhaustionLevel >= 4) {
            currentFoodExhaustionLevel -= 4;

            if (currentFoodSaturationLevel > 0) {
                currentFoodSaturationLevel = Math.max(currentFoodSaturationLevel - 1, 0);
            } else {
                currentFoodLevel -= 1;
            }
        }

        setFoodExhaustionLevel(currentFoodExhaustionLevel);
        setFoodSaturationLevel(currentFoodSaturationLevel);
        setFoodLevel(currentFoodLevel);
    }

    @Override
    public void saturate(int food, float saturation) {
        setFoodLevel(getFoodLevel() + food);

        var currentFoodSaturationLevel = getFoodSaturationLevel();
        currentFoodSaturationLevel += saturation;
        if (currentFoodSaturationLevel > getFoodLevel()) {
            currentFoodSaturationLevel = getFoodLevel();
        }

        setFoodSaturationLevel(currentFoodSaturationLevel);
    }

    @EventHandler
    @Override
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        super.onSaveNBT(event);
        event.getNbt().putInt("foodTickTimer", foodTickTimer);
    }

    @EventHandler
    @Override
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        super.onLoadNBT(event);
        event.getNbt().listenForInt("foodTickTimer", value -> foodTickTimer = value);
    }
}
