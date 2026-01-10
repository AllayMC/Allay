package org.allaymc.server.player;

import lombok.Getter;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.player.OfflinePlayer;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;

/**
 * @author IWareQ
 */
public class AllayOfflinePlayer implements OfflinePlayer {
    private static final String TAG_OFFLINE_DATA = "OfflineData";

    private final PlayerData playerData;
    @Getter
    private final UUID storageUuid;
    @Getter
    private final PersistentDataContainer persistentDataContainer;

    public AllayOfflinePlayer(PlayerData playerData, UUID storageUuid) {
        this.playerData = playerData;
        this.storageUuid = storageUuid;

        this.persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
        playerData.getNbt().listenForCompound("PDC", this.persistentDataContainer::putAll);
    }

    @Override
    public long getXuid() {
        return this.playerData.getXuid();
    }

    @Override
    public UUID getNameUuid() {
        return UUID.nameUUIDFromBytes(this.getName().toLowerCase(Locale.ROOT).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getName() {
        return this.playerData.getName();
    }

    @Override
    public NbtMap getNbtData() {
        return this.playerData.getNbt();
    }

    @Override
    public void setNbtData(NbtMap nbtData) {
        this.playerData.setNbt(nbtData);
    }

    @Override
    public NbtMap getOfflineNbtData() {
        return this.getNbtData().getCompound(TAG_OFFLINE_DATA);
    }

    @Override
    public void setOfflineNbtData(NbtMap nbtData) {
        this.setNbtData(this.getNbtData().toBuilder()
                .putCompound(TAG_OFFLINE_DATA, nbtData)
                .build());
    }

    @Override
    public void save() {
        Server.getInstance().getPlayerManager().getPlayerStorage().savePlayerData(this.getStorageUuid(), this.playerData);
    }

    @Override
    public Player getOnlinePlayer() {
        var players = Server.getInstance().getPlayerManager().getPlayers();
        if (AllayServer.getSettings().networkSettings().xboxAuth()) {
            var xuid = this.getXuid();
            for (var player : players.values()) {
                if (String.valueOf(xuid).equalsIgnoreCase(player.getLoginData().getXuid())) {
                    return player;
                }
            }
        }

        for (var player : players.values()) {
            if (this.getName().equalsIgnoreCase(player.getLoginData().getXname())) {
                return player;
            }
        }

        return null;
    }

    public void updateName(String newName) {
        if (!this.getName().equals(newName)) {
            this.playerData.setName(newName);
        }
    }
}
