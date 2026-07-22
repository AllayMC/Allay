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

/** International v944 encoder. */
public class PacketEncoder_v944 extends PacketEncoder_v924 {
    public PacketEncoder_v944(ProtocolData data) {
        super(data);
    }

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

    @Override
    public ClientboundDataDrivenUIShowScreenPacket encodeDataDrivenUIShowScreen(String screenId, int formId) {
        var packet = new ClientboundDataDrivenUIShowScreenPacket();
        packet.setScreenId(Objects.requireNonNull(screenId, "screenId"));
        packet.setFormId(formId);
        return packet;
    }

    @Override
    public ClientboundDataDrivenUICloseScreenPacket encodeDataDrivenUICloseScreen(Integer formId) {
        var packet = new ClientboundDataDrivenUICloseScreenPacket();
        packet.setFormId(formId);
        return packet;
    }

    private static Object copyDataStoreValue(Object value) {
        if (value == null || value instanceof Boolean || value instanceof Number || value instanceof String) {
            return value;
        }
        if (!(value instanceof Map<?, ?> map)) {
            throw new IllegalArgumentException("Unsupported data store value: " + value.getClass().getName());
        }

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
