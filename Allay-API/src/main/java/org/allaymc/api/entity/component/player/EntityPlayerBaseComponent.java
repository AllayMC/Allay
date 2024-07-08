package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.Abilities;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.scoreboard.ScoreboardViewer;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3ic;

import java.util.Map;

public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader, ScoreboardViewer {

    double BLOCK_INTERACT_MAX_DV_DIFF = 4d;

    float DEFAULT_MOVEMENT_SPEED = 0.1f;

    boolean isSprinting();

    void setSprinting(boolean sprinting);

    boolean isSneaking();

    void setSneaking(boolean sneaking);

    boolean isSwimming();

    void setSwimming(boolean swimming);

    boolean isGliding();

    void setGliding(boolean gliding);

    boolean isCrawling();

    void setCrawling(boolean crawling);

    boolean isUsingItemOnBlock();

    void setUsingItemOnBlock(boolean usingItemOnBlock);

    /**
     * 吃食物或者使用弩等会被认为是在使用物品
     * 注意和usingItemOnBlock区分！使用物品和方块无关！
     * @return 玩家是否在使用物品
     */
    boolean isUsingItemInAir();

    void setUsingItemInAir(boolean value, long time);

    default void setUsingItemInAir(boolean value) {
        setUsingItemInAir(value, getWorld().getTick());
    }

    /**
     * @return 返回玩家最近一次开始使用物品的时间
     */
    long getStartUingItemInAirTime();

    /**
     * @return 返回玩家使用了多久物品，单位为gt
     * @param currentTime 当前时间
     */
    long getItemUsingInAirTime(long currentTime);

    default long getItemUsingInAirTime() {
        return getItemUsingInAirTime(getWorld().getTick());
    }

    int getHandSlot();

    void setHandSlot(int handSlot);

    @Override
    default float getBaseOffset() {
        return 1.62f;
    }

    @Override
    default boolean enableHeadYaw() {
        return true;
    }

    String getDisplayName();

    void setDisplayName(String displayName);

    Skin getSkin();

    void setSkin(Skin skin);

    GameType getGameType();

    void setGameType(GameType gameType);

    AdventureSettings getAdventureSettings();

    Abilities getAbilities();

    default void setFlySpeed(float flySpeed) {
        getAbilities().setFlySpeed(flySpeed);
    }

    default void setFlying(boolean flying) {
        getAbilities().setFlying(flying);
    }

    void sendTip(String message);

    void sendPopup(String message);

    PlayerData savePlayerData();

    Location3ic getSpawnPoint();

    void setSpawnPoint(Location3ic spawnPoint);

    void sendLocationToSelf();

    @ApiStatus.Internal
    void sendDimensionChangeSuccess();

    @UnmodifiableView
    Map<Integer, Form> getForms();

    Form getForm(int id);

    Form removeForm(int id);

    @UnmodifiableView
    Map<Integer, Form> getServerSettingForms();

    void addServerSettingForm(CustomForm form);

    CustomForm getServerSettingForm(int id);

    CustomForm removeServerSettingForm(int id);

    void showForm(Form form);

    default boolean canReach(Vector3ic pos) {
        return canReach(pos.x(), pos.y(), pos.z());
    }

    default boolean canReach(float x, float y, float z) {
        var maxDistance = getMaxInteractDistance();
        var location = getLocation();
        if (location.distanceSquared(x, y, z) > maxDistance * maxDistance) return false;

        var dv = MathUtils.JOMLVecToCBVec(MathUtils.getDirectionVector(location.yaw(), location.pitch()));
        var target = org.cloudburstmc.math.vector.Vector3f.from(x - location.x(), y - location.y(), z - location.z()).normalize();
        var delta = dv.sub(target);
        var diff = delta.dot(delta);
        return diff < BLOCK_INTERACT_MAX_DV_DIFF;
    }

    default double getMaxInteractDistance() {
        return getGameType() == GameType.CREATIVE ? 13 : 7;
    }

    float getMovementSpeed();

    void setMovementSpeed(float speed);
}
