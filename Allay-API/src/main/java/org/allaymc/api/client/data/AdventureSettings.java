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
    @Setter
    private boolean sendUpdate = true;

    public AdventureSettings(EntityPlayer player) {
        this.player = player;
        var tree = player.getPermTree();
        tree.registerPermListener(PermKeys.PVM, type -> {
            noPVM = type == REMOVE;
            sendToClient();
        });
        tree.registerPermListener(PermKeys.MVP, type -> {
            noMVP = type == REMOVE;
            sendToClient();
        });
    }

    public void applyGameType(GameType gameType) {
        sendUpdate(false);
        player.getPermTree().setPerm(PermKeys.PVM, gameType == SPECTATOR);
        player.getPermTree().setPerm(PermKeys.MVP, gameType == SPECTATOR);
        immutableWorld = gameType == SPECTATOR;
        showNameTags = gameType != SPECTATOR;
        sendUpdate(true);
        sendToClient();
    }

    public void setNoPVM(boolean noPVM) {
        this.noPVM = noPVM;
        sendToClient();
    }

    public void setNoMVP(boolean noMVP) {
        this.noMVP = noMVP;
        sendToClient();
    }

    public void setImmutableWorld(boolean immutableWorld) {
        this.immutableWorld = immutableWorld;
        sendToClient();
    }

    public void setShowNameTags(boolean showNameTags) {
        this.showNameTags = showNameTags;
        sendToClient();
    }

    public void setAutoJump(boolean autoJump) {
        this.autoJump = autoJump;
        sendToClient();
    }

    public void sendToClient() {
        if (!sendUpdate) return;
        UpdateAdventureSettingsPacket updateAdventureSettingsPacket = new UpdateAdventureSettingsPacket();
        updateAdventureSettingsPacket.setAutoJump(autoJump);
        updateAdventureSettingsPacket.setImmutableWorld(immutableWorld);
        updateAdventureSettingsPacket.setNoMvP(noMVP);
        updateAdventureSettingsPacket.setNoPvM(noPVM);
        updateAdventureSettingsPacket.setShowNameTags(showNameTags);

        player.sendPacket(updateAdventureSettingsPacket);
    }
}
