package org.allaymc.api.client.data;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.PermissionKeys;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAdventureSettingsPacket;

import static org.allaymc.api.permission.tree.PermissionTree.PermissionChangeType.REMOVE;
import static org.cloudburstmc.protocol.bedrock.data.GameType.SPECTATOR;

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
        var tree = player.getPermissionTree();
        tree.registerPermissionListener(PermissionKeys.PVM, type -> this.setNoPVM(type == REMOVE));
        tree.registerPermissionListener(PermissionKeys.MVP, type -> this.setNoMVP(type == REMOVE));
    }

    public void applyGameType(GameType gameType) {
        player.getPermissionTree().setPermission(PermissionKeys.PVM, gameType != SPECTATOR);
        player.getPermissionTree().setPermission(PermissionKeys.MVP, gameType != SPECTATOR);
        this.setImmutableWorld(gameType == SPECTATOR);
        this.setShowNameTags(gameType != SPECTATOR);
        dirty = true;
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
