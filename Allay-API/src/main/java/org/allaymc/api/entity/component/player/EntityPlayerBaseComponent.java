package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.Abilities;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader {

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

    default void setWalkSpeed(float walkSpeed) {
        getAbilities().setWalkSpeed(walkSpeed);
    }

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

    default boolean hasAction() {
        return getMetadata().get(EntityFlag.USING_ITEM);
    }

    default void setAction(boolean value) {
        if (value != hasAction()) {
            setAndSendEntityFlag(EntityFlag.USING_ITEM, value);
        }
    }

    @ApiStatus.Internal
    void sendDimensionChangeSuccess();

    @UnmodifiableView
    Map<Integer, Form> getForms();

    Form getForm(int id);

    Form removeForm(int id);

    @UnmodifiableView
    Map<Integer, Form> getServerSettingForms();

    Form getServerSettingForm(int id);

    Form removeServerSettingForm(int id);

    void showForm(Form form);
}
