package org.allaymc.server.network.protocol.v944;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v924.PacketEncoder_v924;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUICloseScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIShowScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * {@code PacketEncoder_v944} encodes data-store updates and data-driven screen
 * control for protocol v944. Property replacements and per-path updates copy
 * supported map structures before attaching them to packets, while screen
 * commands carry the requested screen or form identity.
 *
 * <p> The copy operation accepts {@code null}, booleans, numbers, strings, and
 * recursively nested maps with string keys. {@code Map} containers are rebuilt
 * in iteration order, but scalar objects, including arbitrary {@code Number}
 * implementations, retain their references. This separates later map edits from
 * the encoded update without claiming a deep copy of every possible numeric
 * object. Unsupported values fail conversion, and cycles are not detected
 * before recursive traversal.
 */
public class PacketEncoder_v944 extends PacketEncoder_v924 {
    /**
     * Creates the v944 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v944(ProtocolData data) {
        super(data);
    }

    /**
     * Creates one property replacement with nonnull store and property names.
     * The supported value is copied through the data-store conversion helper
     * before attachment, and the change count is one; unsupported values fail
     * conversion.
     */
    @Override
    public ClientboundDataStorePacket encodeDataStoreChange(
            String dataStoreName,
            String propertyName,
            Object value
    ) {
        var change = new DataStoreChange();
        change.setDataStoreName(Objects.requireNonNull(dataStoreName, "dataStoreName"));
        change.setProperty(Objects.requireNonNull(propertyName, "propertyName"));
        change.setNewValue(copyDataStoreValue(value));
        change.setUpdateCount(1);

        var packet = new ClientboundDataStorePacket();
        packet.getUpdates().add(change);
        return packet;
    }

    /**
     * Creates one update for each supplied nonnull property path in iteration
     * order. Store and property names and the path collection must be nonnull;
     * each update receives its own copied map structure, while empty paths
     * produce an empty packet.
     */
    @Override
    public ClientboundDataStorePacket encodeDataStoreUpdates(
            String dataStoreName,
            String propertyName,
            Collection<String> paths,
            Object value
    ) {
        Objects.requireNonNull(dataStoreName, "dataStoreName");
        Objects.requireNonNull(propertyName, "propertyName");
        Objects.requireNonNull(paths, "paths");

        var packet = new ClientboundDataStorePacket();
        for (var path : paths) {
            var update = new DataStoreUpdate();
            update.setDataStoreName(dataStoreName);
            update.setProperty(propertyName);
            update.setPath(Objects.requireNonNull(path, "path"));
            update.setData(copyDataStoreValue(value));
            update.setUpdateCount(1);
            update.setPathUpdateCount(1);
            packet.getUpdates().add(update);
        }
        return packet;
    }

    /**
     * Creates a screen-open request using a nonnull screen identifier and the
     * supplied form ID. The encoder preserves the form value without allocating
     * or registering a corresponding server-side form.
     */
    @Override
    public ClientboundDataDrivenUIShowScreenPacket encodeDataDrivenUIShowScreen(String screenId, int formId) {
        var packet = new ClientboundDataDrivenUIShowScreenPacket();
        packet.setScreenId(Objects.requireNonNull(screenId, "screenId"));
        packet.setFormId(formId);
        return packet;
    }

    /**
     * Creates a screen-close request carrying the supplied optional form ID.
     * {@code null} is preserved to represent the packet form without a specific
     * form identity; no server-side form state is changed here.
     */
    @Override
    public ClientboundDataDrivenUICloseScreenPacket encodeDataDrivenUICloseScreen(Integer formId) {
        var packet = new ClientboundDataDrivenUICloseScreenPacket();
        packet.setFormId(formId);
        return packet;
    }

    /**
     * Copies supported map values while retaining scalar values for data-store
     * packets. {@code null}, {@code Boolean}, {@code Number}, and
     * {@code String} values are returned directly; other non-map objects fail
     * with {@code IllegalArgumentException}.
     *
     * <p> Each map is rebuilt as a {@code LinkedHashMap} with string keys and
     * recursively converted values, preserving its iteration order while
     * isolating the new container from later source-map edits. Non-string keys
     * fail rather than being coerced into possibly conflicting names.
     * {@code Number} subclasses remain shared, and recursive cycles are not
     * detected, so callers must supply a suitable acyclic value tree.
     */
    private static Object copyDataStoreValue(Object value) {
        if (value == null || value instanceof Boolean || value instanceof Number || value instanceof String) {
            return value;
        }
        if (!(value instanceof Map<?, ?> map)) {
            throw new IllegalArgumentException("Unsupported data store value: " + value.getClass().getName());
        }

        // Rebuild map containers recursively without coercing keys, preserving
        // property paths while preventing later source-map mutations from
        // changing packet structure.
        var copy = new LinkedHashMap<String, Object>();
        for (var entry : map.entrySet()) {
            if (!(entry.getKey() instanceof String key)) {
                throw new IllegalArgumentException("Data store map keys must be strings");
            }
            copy.put(key, copyDataStoreValue(entry.getValue()));
        }
        return copy;
    }
}
