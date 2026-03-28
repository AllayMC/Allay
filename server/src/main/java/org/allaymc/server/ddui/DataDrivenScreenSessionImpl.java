package org.allaymc.server.ddui;

import org.allaymc.api.ddui.DataDrivenScreen;
import org.allaymc.api.ddui.DataDrivenScreenSession;
import org.allaymc.api.ddui.internal.DataDrivenProperty;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreAction;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUICloseScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIShowScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Per-player DDUI session implementation responsible for show, flush and input routing.
 *
 * @author Miroshka
 */
final class DataDrivenScreenSessionImpl implements DataDrivenScreenSession {

    private final Player player;
    private final DataDrivenScreen screen;
    private final String dataStoreName;
    private final Map<String, Object> dirtyProperties = new LinkedHashMap<>();
    private Object handledInputData;
    private String handledInputPath;

    DataDrivenScreenSessionImpl(Player player, DataDrivenScreen screen) {
        this.player = player;
        this.screen = screen;
        this.dataStoreName = screen.getIdentifier().split(":")[0];
    }

    void show() {
        var change = new DataStoreChange();
        change.setDataStoreName(dataStoreName);
        change.setProperty(screen.getPropertyName());
        change.setUpdateCount(1);
        change.setNewValue(screen.toWireValue());

        var dataPacket = new ClientboundDataStorePacket();
        dataPacket.setUpdates(java.util.List.of(change));

        var showPacket = new ClientboundDataDrivenUIShowScreenPacket();
        showPacket.setScreenId(screen.getIdentifier());
        showPacket.setFormId(0);

        player.sendPacket(dataPacket);
        player.sendPacket(showPacket);
    }

    void close() {
        var packet = new ClientboundDataDrivenUICloseScreenPacket();
        packet.setFormId(0);
        player.sendPacket(packet);
        detach();
    }

    void detach() {
        dirtyProperties.clear();
        screen.detachSession(this);
    }

    void flush() {
        if (dirtyProperties.isEmpty()) {
            return;
        }

        var updates = dirtyProperties.entrySet().stream().<DataStoreAction>map(entry -> {
            var update = new DataStoreUpdate();
            update.setDataStoreName(dataStoreName);
            update.setProperty(screen.getPropertyName());
            update.setPath(entry.getKey());
            update.setData(entry.getValue());
            update.setUpdateCount(1);
            update.setPathUpdateCount(1);
            return update;
        }).toList();

        dirtyProperties.clear();

        var packet = new ClientboundDataStorePacket();
        packet.setUpdates(updates);
        player.sendPacket(packet);
    }

    boolean handleInput(String dataStore, String propertyName, String path, Object data) {
        if (!dataStoreName.equals(dataStore)) {
            return false;
        }

        if (!screen.getPropertyName().equals(propertyName)) {
            return false;
        }

        var property = screen.resolvePath(path);
        if (property == null) {
            return false;
        }

        handledInputPath = path;
        handledInputData = data;
        try {
            property.acceptInput(player, data);
        } finally {
            handledInputPath = null;
            handledInputData = null;
        }
        return true;
    }

    DataDrivenScreen getScreen() {
        return screen;
    }

    @Override
    public void markDirty(DataDrivenProperty<?> property) {
        var path = property.getPath();
        var value = property.toWireValue();
        if (path.equals(handledInputPath) && Objects.equals(value, handledInputData)) {
            dirtyProperties.remove(path);
            return;
        }

        dirtyProperties.put(path, value);
    }
}
