package org.allaymc.api.client.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.perm.PermKeys;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAdventureSettingsPacket;

import static org.allaymc.api.perm.tree.PermTree.PermChangeType.REMOVE;
import static org.cloudburstmc.protocol.bedrock.data.GameType.SPECTATOR;

/**
 * Allay Project 2023/7/9
 *
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
    @Getter
    private boolean dirty = false;

    public AdventureSettings(EntityPlayer player) {
        this.player = player;
        var tree = player.getPermTree();
        tree.registerPermListener(PermKeys.PVM, type -> {
            noPVM = type == REMOVE;
        });
        tree.registerPermListener(PermKeys.MVP, type -> {
            noMVP = type == REMOVE;
        });
    }

    public void applyGameType(GameType gameType) {
        player.getPermTree().setPerm(PermKeys.PVM, gameType == SPECTATOR);
        player.getPermTree().setPerm(PermKeys.MVP, gameType == SPECTATOR);
        immutableWorld = gameType == SPECTATOR;
        showNameTags = gameType != SPECTATOR;
        sync();
    }

    public void setNoPVM(boolean noPVM) {
        this.noPVM = noPVM;
        dirty = true;
    }

    public void setNoMVP(boolean noMVP) {
        this.noMVP = noMVP;
        dirty = true;
    }

    public void setImmutableWorld(boolean immutableWorld) {
        this.immutableWorld = immutableWorld;
        dirty = true;
    }

    public void setShowNameTags(boolean showNameTags) {
        this.showNameTags = showNameTags;
        dirty = true;
    }

    public void setAutoJump(boolean autoJump) {
        this.autoJump = autoJump;
        dirty = true;
    }

    public void sync() {
        if (!dirty) return;
        UpdateAdventureSettingsPacket updateAdventureSettingsPacket = new UpdateAdventureSettingsPacket();
        updateAdventureSettingsPacket.setAutoJump(autoJump);
        updateAdventureSettingsPacket.setImmutableWorld(immutableWorld);
        updateAdventureSettingsPacket.setNoMvP(noMVP);
        updateAdventureSettingsPacket.setNoPvM(noPVM);
        updateAdventureSettingsPacket.setShowNameTags(showNameTags);

        player.sendPacket(updateAdventureSettingsPacket);
        dirty = false;
    }
}
