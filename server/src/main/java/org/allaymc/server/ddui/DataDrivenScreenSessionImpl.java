package org.allaymc.server.ddui;

import org.allaymc.api.ddui.DataDrivenScreen;
import org.allaymc.api.ddui.internal.DataDrivenProperty;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreAction;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUICloseScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIShowScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Per-player DDUI session implementation responsible for show, flush and input routing.
 *
 * @author Miroshka
 */
final class DataDrivenScreenSessionImpl {

    private final Player player;
    private final DataDrivenScreen screen;
    private final String dataStoreName;
    private final int formId;
    private final Consumer<DataDrivenProperty<?>> dirtyMarker = this::markDirty;
    private final Map<String, Object> dirtyProperties = new LinkedHashMap<>();
    private final Map<String, Integer> pathVersions = new HashMap<>();
    private Object handledInputData;
    private String handledInputPath;
    private boolean fullRefreshRequired;
    private int updateVersion;

    DataDrivenScreenSessionImpl(Player player, DataDrivenScreen screen, int formId) {
        this.player = player;
        this.screen = screen;
        this.formId = formId;
        this.dataStoreName = screen.getIdentifier().split(":")[0];
    }

    void show() {
        player.sendPacket(initialDataPacket());
        player.sendPacket(showScreenPacket());
    }

    void close() {
        var packet = new ClientboundDataDrivenUICloseScreenPacket();
        packet.setFormId(null);
        player.sendPacket(packet);
        detach();
    }

    void detach() {
        dirtyProperties.clear();
        pathVersions.clear();
        fullRefreshRequired = false;
        screen.detachSession(dirtyMarker);
    }

    void flush() {
        if (!fullRefreshRequired && dirtyProperties.isEmpty()) {
            return;
        }

        var packet = new ClientboundDataStorePacket();
        packet.setUpdates(fullRefreshRequired ? java.util.List.of(toFullScreenChange()) : toDirtyUpdates());
        player.sendPacket(packet);
    }

    boolean handleInput(String dataStore, String propertyName, String path, Object data) {
        if (!matchesTarget(dataStore, propertyName)) {
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

    int getFormId() {
        return formId;
    }

    Consumer<DataDrivenProperty<?>> dirtyMarker() {
        return dirtyMarker;
    }

    private void markDirty(DataDrivenProperty<?> property) {
        var path = property.getPath();
        var value = property.toWireValue();
        if (shouldSuppressEcho(path, value)) {
            dirtyProperties.remove(path);
            return;
        }

        if (property.requiresFullRefresh()) {
            fullRefreshRequired = true;
            dirtyProperties.clear();
            return;
        }

        dirtyProperties.put(path, value);
    }

    private ClientboundDataStorePacket initialDataPacket() {
        var change = new DataStoreChange();
        change.setDataStoreName(dataStoreName);
        change.setProperty(screen.getPropertyName());
        change.setUpdateCount(nextUpdateVersion());
        change.setNewValue(screen.toWireValue());

        var packet = new ClientboundDataStorePacket();
        packet.setUpdates(java.util.List.of(change));
        return packet;
    }

    private ClientboundDataDrivenUIShowScreenPacket showScreenPacket() {
        var packet = new ClientboundDataDrivenUIShowScreenPacket();
        packet.setScreenId(screen.getIdentifier());
        packet.setFormId(formId);
        return packet;
    }

    private DataStoreUpdate toDataStoreUpdate(String path, Object value, int updateCount) {
        var update = new DataStoreUpdate();
        update.setDataStoreName(dataStoreName);
        update.setProperty(screen.getPropertyName());
        update.setPath(path);
        update.setData(value);
        update.setUpdateCount(updateCount);
        update.setPathUpdateCount(nextPathVersion(path));
        return update;
    }

    private java.util.List<DataStoreAction> toDirtyUpdates() {
        var updateCount = nextUpdateVersion();
        var updates = dirtyProperties.entrySet().stream()
                .<DataStoreAction>map(entry -> toDataStoreUpdate(entry.getKey(), entry.getValue(), updateCount))
                .toList();
        dirtyProperties.clear();
        return updates;
    }

    private DataStoreChange toFullScreenChange() {
        var change = new DataStoreChange();
        change.setDataStoreName(dataStoreName);
        change.setProperty(screen.getPropertyName());
        change.setUpdateCount(nextUpdateVersion());
        change.setNewValue(screen.toWireValue());
        dirtyProperties.clear();
        fullRefreshRequired = false;
        return change;
    }

    private boolean matchesTarget(String dataStore, String propertyName) {
        return dataStoreName.equals(dataStore) && screen.getPropertyName().equals(propertyName);
    }

    private boolean shouldSuppressEcho(String path, Object value) {
        return path.equals(handledInputPath) && isSameHandledInputValue(value, handledInputData);
    }

    private boolean isSameHandledInputValue(Object left, Object right) {
        if (left instanceof Number leftNumber && right instanceof Number rightNumber) {
            return leftNumber.doubleValue() == rightNumber.doubleValue();
        }

        return Objects.equals(left, right);
    }

    private int nextUpdateVersion() {
        updateVersion = nextVersion(updateVersion);
        return updateVersion;
    }

    private int nextPathVersion(String path) {
        var nextVersion = nextVersion(pathVersions.getOrDefault(path, 0));
        pathVersions.put(path, nextVersion);
        return nextVersion;
    }

    private static int nextVersion(int currentVersion) {
        return currentVersion == Integer.MAX_VALUE ? 1 : currentVersion + 1;
    }
}
