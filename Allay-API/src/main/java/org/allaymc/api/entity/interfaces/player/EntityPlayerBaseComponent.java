package org.allaymc.api.entity.interfaces.player;

import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.component.base.EntityBaseComponent;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;
import org.jetbrains.annotations.Range;

import java.util.function.Function;

public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader {

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

    Function<SubChunkRequestPacket, SubChunkPacket> getSubChunkRequestHandler();

    void setSubChunkRequestHandler(Function<SubChunkRequestPacket, SubChunkPacket> subChunkRequestHandler);

    void sendChat(EntityPlayer sender, String message);

    void sendRawMessage(String message);

    void sendTip(String message);

    void sendPopup(String message);
}
