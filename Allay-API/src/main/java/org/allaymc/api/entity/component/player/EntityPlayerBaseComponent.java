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
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Map;

public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader, ScoreboardViewer {

    float DEFAULT_MOVEMENT_SPEED = 0.1f;

    default boolean isSprinting() {
        return getMetadata().get(EntityFlag.SPRINTING);
    }

    default void setSprinting(boolean sprinting) {
        if (sprinting == isSprinting()) return;

        var speed = getMovementSpeed();
        if (sprinting) speed *= 1.3f;
        else speed /= 1.3f;

        setMovementSpeed(speed);

        setAndSendEntityFlag(EntityFlag.SPRINTING, sprinting);
    }

    default boolean isSneaking() {
        return getMetadata().get(EntityFlag.SNEAKING);
    }

    default void setSneaking(boolean sneaking) {
        if (sneaking == isSneaking()) return;
        setAndSendEntityFlag(EntityFlag.SNEAKING, sneaking);
    }

    default boolean isSwimming() {
        return getMetadata().get(EntityFlag.SWIMMING);
    }

    default void setSwimming(boolean swimming) {
        if (swimming == isSwimming()) return;
        setAndSendEntityFlag(EntityFlag.SWIMMING, swimming);
    }

    default boolean isGliding() {
        return getMetadata().get(EntityFlag.GLIDING);
    }

    default void setGliding(boolean gliding) {
        if (gliding == isGliding()) return;
        setAndSendEntityFlag(EntityFlag.GLIDING, gliding);
    }

    default boolean isCrawling() {
        return getMetadata().get(EntityFlag.CRAWLING);
    }

    default void setCrawling(boolean crawling) {
        if (crawling == isCrawling()) return;
        setAndSendEntityFlag(EntityFlag.CRAWLING, crawling);
    }

    boolean isUsingItemOnBlock();

    void setUsingItemOnBlock(boolean usingItemOnBlock);

    /**
     * Eating food or using a crossbow is considered using an item.
     * Note the distinction from {@code usingItemOnBlock}! Using an item is unrelated to blocks!
     *
     * @return Whether the player is using an item
     */
    boolean isUsingItemInAir();

    default void setUsingItemInAir(boolean value) {
        setUsingItemInAir(value, getWorld().getTick());
    }

    void setUsingItemInAir(boolean value, long time);

    /**
     * @return Returns the time when the player most recently started using an item
     */
    long getStartUsingItemInAirTime();

    /**
     * @param currentTime The current time
     *
     * @return Returns how long the player has been using the item, in gt
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

    void closeAllForms();

    default boolean canReachBlock(Vector3ic pos) {
        return canReach(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f);
    }

    default boolean canReach(float x, float y, float z) {
        if (isDead()) return false;
        var maxDistance = getMaxInteractDistance();
        // Check whether there is a point that inside of the player's AABB
        // And can reach the provided pos
        var aabb = getOffsetAABB();
        float[] aabbXs = new float[]{aabb.minX(), aabb.maxX()};
        float[] aabbYs = new float[]{aabb.minY(), aabb.maxY()};
        float[] aabbZs = new float[]{aabb.minZ(), aabb.maxZ()};
        for (var aabbX : aabbXs) {
            for (var aabbY : aabbYs) {
                for (var aabbZ : aabbZs) {
                    if (new Vector3f(aabbX, aabbY, aabbZ).distanceSquared(x, y, z) <= maxDistance * maxDistance) {
                        return true;
                    }
                }
            }
        }
        // TODO: check yaw and pitch
        return false;
    }

    default double getMaxInteractDistance() {
        return getGameType() == GameType.CREATIVE ? 13d : 7d;
    }

    float getMovementSpeed();

    void setMovementSpeed(float speed);

    void setMotionValueOnly(Vector3fc motion);
}
