package cn.allay.server.world.storage.nonpersistent;

import cn.allay.api.client.Client;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.world.storage.ClientStorage;
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
public class AllayNonPersistentClientStorage implements ClientStorage {
    private final Map<UUID, List<NbtMap>> clientInventories = new ConcurrentHashMap<>();
    private final Map<UUID, List<NbtMap>> clientOffhandInventories = new ConcurrentHashMap<>();
    private final Map<UUID, List<NbtMap>> clientArmorInventories = new ConcurrentHashMap<>();

    @Override
    public void readClientData(Client client) {
        var uuid = client.getUuid();
        var inv = clientInventories.get(uuid);
        if (inv != null) Objects.requireNonNull(client.getPlayerEntity().getContainer(FullContainerType.PLAYER_INVENTORY)).loadNBT(inv);
        var offhandInv = clientOffhandInventories.get(uuid);
        if (offhandInv != null) Objects.requireNonNull(client.getPlayerEntity().getContainer(FullContainerType.OFFHAND)).loadNBT(offhandInv);
        var armorInv = clientArmorInventories.get(uuid);
        if (armorInv != null) Objects.requireNonNull(client.getPlayerEntity().getContainer(FullContainerType.ARMOR)).loadNBT(armorInv);
    }

    @Override
    public void writeClientData(Client client) {
        var uuid = client.getUuid();
        clientInventories.put(uuid, Objects.requireNonNull(client.getPlayerEntity().getContainer(FullContainerType.PLAYER_INVENTORY)).saveNBT());
        clientOffhandInventories.put(uuid, Objects.requireNonNull(client.getPlayerEntity().getContainer(FullContainerType.OFFHAND)).saveNBT());
        clientArmorInventories.put(uuid, Objects.requireNonNull(client.getPlayerEntity().getContainer(FullContainerType.ARMOR)).saveNBT());
    }
}
