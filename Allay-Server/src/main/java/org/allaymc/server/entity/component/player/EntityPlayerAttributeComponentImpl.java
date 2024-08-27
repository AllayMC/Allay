package org.allaymc.server.entity.component.player;

import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerExperienceLevelChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerExperienceProgressChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerFoodLevelChangeEvent;
import org.allaymc.api.world.Difficulty;
import org.allaymc.server.entity.component.EntityAttributeComponentImpl;
import org.allaymc.server.entity.component.event.*;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAttributesPacket;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class EntityPlayerAttributeComponentImpl extends EntityAttributeComponentImpl implements EntityPlayerAttributeComponent {

    @ComponentedObject
    private EntityPlayer thisPlayer;
    @Dependency(soft = true)
    protected EntityPlayerNetworkComponent networkComponent;
    private int foodTickTimer;

    public EntityPlayerAttributeComponentImpl(AttributeType... attributeTypes) {
        super(attributeTypes);
    }

    @Override
    public void tick() {
        super.tick();

        if (
                thisPlayer.isDead() ||
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
        return getFoodLevel() < (int) AttributeType.PLAYER_HUNGER.getMaxValue() ||
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

        setAttributeValue(AttributeType.PLAYER_EXPERIENCE_LEVEL, event.getNewExperienceLevel());
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

        setAttributeValue(AttributeType.PLAYER_EXPERIENCE_PROGRESS, event.getNewExperienceProgress());
    }

    @Override
    public void setFoodLevel(int value) {
        value = Math.max(0, Math.min(value, (int) AttributeType.PLAYER_HUNGER.getMaxValue()));

        var event = new PlayerFoodLevelChangeEvent(thisPlayer, getFoodLevel(), value);
        event.call();
        if (event.isCancelled()) return;

        setAttributeValue(AttributeType.PLAYER_HUNGER, value);
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

    @Override
    public void sendAttributesToClient() {
        var packet = new UpdateAttributesPacket();
        packet.setRuntimeEntityId(thisEntity.getRuntimeId());
        attributes.values().forEach(attribute -> packet.getAttributes().add(attribute.toNetwork()));
        packet.setTick(thisEntity.getWorld().getTick());
        networkComponent.sendPacket(packet);
    }

    @EventHandler
    protected void onAttributeChange(CEntityAttributeChangeEvent event) {
        sendAttributesToClient();
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

    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        exhaust(0.1f);
    }

    @EventHandler
    protected void onAttack(CEntityAttackEvent event) {
        exhaust(0.1f);
    }

    protected float swimDistance = 0;
    protected float sprintDistance = 0;

    @EventHandler
    protected void onMove(CPlayerMoveEvent event) {
        var distance = thisPlayer.getLocation().distance(event.getNewLoc());

        if (thisPlayer.isSwimming()) swimDistance += distance;
        if (thisPlayer.isSprinting()) sprintDistance += distance;

        // To reduce network traffic, we only update food data
        // every 10 blocks of movement
        if (swimDistance >= 10) {
            exhaust(0.01f * swimDistance);
            swimDistance = 0;
        }
        if (sprintDistance >= 10) {
            exhaust(0.1f * sprintDistance);
            sprintDistance = 0;
        }
    }

    @EventHandler
    protected void onJump(CPlayerJumpEvent event) {
        exhaust(thisPlayer.isSprinting() ? 0.2f : 0.05f);
    }
}
