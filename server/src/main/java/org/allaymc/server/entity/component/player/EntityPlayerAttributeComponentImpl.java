package org.allaymc.server.entity.component.player;

import com.google.common.collect.Lists;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerExperienceLevelChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerExperienceProgressChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerFoodLevelChangeEvent;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityAttributeComponentImpl;
import org.allaymc.server.entity.component.event.*;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAttributesPacket;

import java.util.Arrays;

/**
 * @author daoge_cmd
 */
public class EntityPlayerAttributeComponentImpl extends EntityAttributeComponentImpl implements EntityPlayerAttributeComponent {

    protected static final String TAG_FOOD_TICK_TIMER = "foodTickTimer";

    private static final int FOOD_TICK_THRESHOLD = 80;
    /**
     * To reduce network traffic, we only update food data every 10 blocks of movement
     */
    private static final int MOVEMENT_THRESHOLD = 10;
    private static final int MIN_FOOD_EXHAUSTION = 4;

    @Dependency(optional = true)
    protected EntityPlayerNetworkComponent networkComponent;

    protected float swimDistance = 0;
    protected float sprintDistance = 0;

    @ComponentObject
    private EntityPlayer thisPlayer;

    private int foodTickTimer;

    public EntityPlayerAttributeComponentImpl(AttributeType... attributeTypes) {
        super(attributeTypes);
    }

    public static AttributeType[] basicPlayerAttributes() {
        var list = Lists.newArrayList(
                AttributeType.PLAYER_HUNGER,
                AttributeType.PLAYER_SATURATION,
                AttributeType.PLAYER_EXHAUSTION,
                AttributeType.PLAYER_EXPERIENCE_LEVEL,
                AttributeType.PLAYER_EXPERIENCE_PROGRESS
        );
        list.addAll(Arrays.asList(EntityAttributeComponentImpl.basicEntityAttributes()));
        return list.toArray(AttributeType[]::new);
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (thisPlayer.isDead() ||
            thisPlayer.getGameMode() == GameMode.CREATIVE ||
            thisPlayer.getGameMode() == GameMode.SPECTATOR) {
            return;
        }

        tickFood();
    }

    protected void tickFood() {
        foodTickTimer++;
        if (foodTickTimer >= FOOD_TICK_THRESHOLD) {
            foodTickTimer = 0;
        }

        var currentFoodLevel = getFoodLevel();
        var difficulty = thisPlayer.getWorld().getWorldData().getDifficulty();
        if (difficulty == Difficulty.PEACEFUL && foodTickTimer % 10 == 0) {
            setFoodLevel(currentFoodLevel + 1);
            if (foodTickTimer % 20 == 0) {
                regenerate(false);
            }
        }

        if (foodTickTimer == 0 && difficulty != Difficulty.PEACEFUL) {
            if (currentFoodLevel >= 18) {
                regenerate(true);
            } else if (currentFoodLevel == 0) {
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
        if (thisPlayer.getHealth() == thisPlayer.getMaxHealth()) {
            return;
        }

        thisPlayer.setHealth(thisPlayer.getHealth() + 1);
        if (exhaust) {
            thisPlayer.exhaust(6);
        }
    }

    @Override
    public boolean canEat() {
        return getFoodLevel() < (int) AttributeType.PLAYER_HUNGER.getMaxValue() ||
               thisPlayer.getGameMode() == GameMode.CREATIVE ||
               thisPlayer.getWorld().getWorldData().getDifficulty() == Difficulty.PEACEFUL;
    }

    @Override
    public int getExperienceLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_EXPERIENCE_LEVEL);
    }

    @Override
    public void setExperienceLevel(int value) {
        var event = new PlayerExperienceLevelChangeEvent(thisPlayer, (int) getAttributeValue(AttributeType.PLAYER_EXPERIENCE_LEVEL), value);
        if (!event.call()) {
            return;
        }

        setAttributeValue(AttributeType.PLAYER_EXPERIENCE_LEVEL, event.getNewExperienceLevel());
    }

    @Override
    public float getExperienceProgress() {
        return getAttributeValue(AttributeType.PLAYER_EXPERIENCE_PROGRESS);
    }

    @Override
    public void setExperienceProgress(float value) {
        var event = new PlayerExperienceProgressChangeEvent(thisPlayer, getAttributeValue(AttributeType.PLAYER_EXPERIENCE_PROGRESS), value);
        if (!event.call()) {
            return;
        }

        setAttributeValue(AttributeType.PLAYER_EXPERIENCE_PROGRESS, event.getNewExperienceProgress());
    }

    @Override
    public void setFoodLevel(int value) {
        var maxHunger = (int) AttributeType.PLAYER_HUNGER.getMaxValue();
        value = Math.max(0, Math.min(value, maxHunger));

        var event = new PlayerFoodLevelChangeEvent(thisPlayer, getFoodLevel(), value);
        if (!event.call()) {
            return;
        }

        setAttributeValue(AttributeType.PLAYER_HUNGER, event.getNewFoodLevel());
    }

    @Override
    public void setFoodTickTimer(int foodTickTimer) {
        this.foodTickTimer = Math.max(foodTickTimer, 0);
    }

    @Override
    public void exhaust(float level) {
        if (thisPlayer.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        var exhaustionLevel = getFoodExhaustionLevel() + level;
        var saturationLevel = getFoodSaturationLevel();
        var foodLevel = getFoodLevel();

        while (exhaustionLevel >= MIN_FOOD_EXHAUSTION) {
            exhaustionLevel -= MIN_FOOD_EXHAUSTION;

            if (saturationLevel > 0) {
                saturationLevel = Math.max(saturationLevel - 1, 0);
            } else {
                foodLevel--;
            }
        }

        setFoodExhaustionLevel(exhaustionLevel);
        setFoodSaturationLevel(saturationLevel);
        setFoodLevel(foodLevel);
    }

    @Override
    public void saturate(int food, float saturation) {
        setFoodLevel(getFoodLevel() + food);

        var newSaturation = Math.min(getFoodSaturationLevel() + saturation, getFoodLevel());
        setFoodSaturationLevel(newSaturation);
    }

    @Override
    public void sendAttributesToClient() {
        var packet = new UpdateAttributesPacket();
        packet.setRuntimeEntityId(thisEntity.getRuntimeId());
        attributes.values().forEach(attr -> packet.getAttributes().add(new AttributeData(
                attr.getKey(), attr.getMinValue(), attr.getMaxValue(),
                attr.getCurrentValue(), attr.getDefaultValue()
        )));
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
        event.getNbt().putInt(TAG_FOOD_TICK_TIMER, foodTickTimer);
    }

    @EventHandler
    @Override
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        super.onLoadNBT(event);
        event.getNbt().listenForInt(TAG_FOOD_TICK_TIMER, value -> foodTickTimer = value);
    }

    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        exhaust(0.1f);
    }

    @EventHandler
    protected void onAttack(CEntityAttackEvent event) {
        exhaust(0.1f);
    }

    @EventHandler
    protected void onMove(CPlayerMoveEvent event) {
        var distance = (float) thisPlayer.getLastLocation().distance(thisPlayer.getLocation());

        if (thisPlayer.isSwimming()) {
            swimDistance += distance;
            if (swimDistance >= MOVEMENT_THRESHOLD) {
                exhaust(0.01f * swimDistance);
                swimDistance = 0;
            }
        }

        if (thisPlayer.isSprinting()) {
            sprintDistance += distance;
            if (sprintDistance >= MOVEMENT_THRESHOLD) {
                exhaust(0.1f * sprintDistance);
                sprintDistance = 0;
            }
        }
    }

    @EventHandler
    protected void onJump(CPlayerJumpEvent event) {
        exhaust(thisPlayer.isSprinting() ? 0.2f : 0.05f);
    }
}
