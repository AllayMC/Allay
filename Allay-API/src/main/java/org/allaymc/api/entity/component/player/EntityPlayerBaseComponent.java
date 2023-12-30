package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TextReceiver;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader, CommandSender {

    void setSprinting(boolean sprinting);

    boolean isSprinting();

    void setSneaking(boolean sneaking);

    boolean isSneaking();

    void setSwimming(boolean swimming);

    boolean isSwimming();

    void setGliding(boolean gliding);

    boolean isGliding();

    void setCrawling(boolean crawling);

    boolean isCrawling();

    int getHandSlot();

    void setHandSlot(@Range(from = 0, to = 8) int handSlot);

    @Override
    default float getBaseOffset() {
        return 1.62f;
    }

    @Override
    default boolean enableHeadYaw() {
        return true;
    }

    boolean computeMovementServerSide();

    String getDisplayName();

    void setDisplayName(String displayName);

    Skin getSkin();

    void setSkin(Skin skin);

    boolean isOp();

    void setOp(boolean op);

    GameType getGameType();

    void setGameType(GameType gameType);

    AdventureSettings getAdventureSettings();

    void sendTip(String message);

    void sendPopup(String message);

    @Override
    default boolean isPlayer() {
        return true;
    }

    @Override
    default boolean isEntity() {
        return true;
    }
}
