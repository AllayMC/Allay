package org.allaymc.api.player.data;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.Permissions;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAdventureSettingsPacket;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
public final class AdventureSettings {

    private final EntityPlayer player;

    private boolean noPVM = false;
    private boolean noMVP = false;
    private boolean immutableWorld = false;
    private boolean showNameTags = true;
    private boolean autoJump = true;

    private boolean dirty = false;

    public AdventureSettings(EntityPlayer player) {
        this.player = player;
    }

    public void applyGameType(GameType gameType) {
        player.setPermission(Permissions.ADVENTURE_SETTING_PVM, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ADVENTURE_SETTING_MVP, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ADVENTURE_SETTING_SHOW_NAME_TAGS, gameType != GameType.SPECTATOR);
        this.setImmutableWorld(gameType == GameType.SPECTATOR);
        dirty = true;
        sync();
    }

    @ApiStatus.Internal
    public void setNoPVM(boolean noPVM) {
        this.noPVM = noPVM;
        dirty = true;
    }

    @ApiStatus.Internal
    public void setNoMVP(boolean noMVP) {
        this.noMVP = noMVP;
        dirty = true;
    }

    @ApiStatus.Internal
    public void setImmutableWorld(boolean immutableWorld) {
        this.immutableWorld = immutableWorld;
        dirty = true;
    }

    @ApiStatus.Internal
    public void setShowNameTags(boolean showNameTags) {
        this.showNameTags = showNameTags;
        dirty = true;
    }

    @ApiStatus.Internal
    public void setAutoJump(boolean autoJump) {
        this.autoJump = autoJump;
        dirty = true;
    }

    @ApiStatus.Internal
    public void sync() {
        if (!dirty) return;
        var packet = new UpdateAdventureSettingsPacket();
        packet.setAutoJump(autoJump);
        packet.setImmutableWorld(immutableWorld);
        packet.setNoMvP(noMVP);
        packet.setNoPvM(noPVM);
        packet.setShowNameTags(showNameTags);

        player.sendPacket(packet);
        dirty = false;
    }
}
