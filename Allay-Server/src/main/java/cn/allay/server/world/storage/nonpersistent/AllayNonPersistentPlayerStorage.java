package cn.allay.server.world.storage.nonpersistent;

import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.world.storage.PlayerStorage;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class AllayNonPersistentPlayerStorage implements PlayerStorage {
    private final Map<UUID, List<NbtMap>> clientInventories = new ConcurrentHashMap<>();
    private final Map<UUID, List<NbtMap>> clientOffhandInventories = new ConcurrentHashMap<>();
    private final Map<UUID, List<NbtMap>> clientArmorInventories = new ConcurrentHashMap<>();

    @Override
    public void readPlayerData(EntityPlayer player) {
        var uuid = player.getUuid();
        var inv = clientInventories.get(uuid);
        if (inv != null) Objects.requireNonNull(player.getContainer(FullContainerType.PLAYER_INVENTORY)).loadNBT(inv);
        var offhandInv = clientOffhandInventories.get(uuid);
        if (offhandInv != null)
            Objects.requireNonNull(player.getContainer(FullContainerType.OFFHAND)).loadNBT(offhandInv);
        var armorInv = clientArmorInventories.get(uuid);
        if (armorInv != null) Objects.requireNonNull(player.getContainer(FullContainerType.ARMOR)).loadNBT(armorInv);
    }

    @Override
    public void writePlayerData(EntityPlayer player) {
        var uuid = player.getUuid();
        clientInventories.put(uuid, Objects.requireNonNull(player.getContainer(FullContainerType.PLAYER_INVENTORY)).saveNBT());
        clientOffhandInventories.put(uuid, Objects.requireNonNull(player.getContainer(FullContainerType.OFFHAND)).saveNBT());
        clientArmorInventories.put(uuid, Objects.requireNonNull(player.getContainer(FullContainerType.ARMOR)).saveNBT());
    }
}
