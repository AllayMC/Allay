package org.allaymc.server.network.processor.login;

import org.allaymc.api.pack.Pack;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.packsync.ProxyPackSyncProtocol;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.PyRpcPacket;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class ProxyPackSyncPacketProcessor extends ILoginPacketProcessor<PyRpcPacket> {

    @Override
    public void handle(Player player, PyRpcPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        if (!allayPlayer.isProxySyncSession()) {
            return;
        }

        try (var input = new DataInputStream(new ByteArrayInputStream(packet.getData()))) {
            byte packetType = ProxyPackSyncProtocol.readAndValidateVersion(input);
            switch (packetType) {
                case ProxyPackSyncProtocol.PACK_LIST_REQUEST -> sendPackList(allayPlayer, packet.getMsgId());
                case ProxyPackSyncProtocol.PACK_CHUNK_REQUEST -> sendPackChunk(allayPlayer, packet.getMsgId(), input);
                default -> sendError(allayPlayer, packet.getMsgId(), "Unsupported proxy pack sync packet type: " + packetType);
            }
        } catch (Exception exception) {
            sendError(allayPlayer, packet.getMsgId(), exception.getMessage());
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PY_RPC;
    }

    private void sendPackList(AllayPlayer player, long msgId) throws IOException {
        int chunkSize = AllayServer.getSettings().resourcePackSettings().maxChunkSize() * 1024;
        List<Pack> packs = Registries.PACKS.getContent().values().stream()
                .filter(this::isSupportedPack)
                .sorted(Comparator.comparing(pack -> pack.getId().toString()))
                .toList();

        try (var outputBuffer = new ByteArrayOutputStream();
             var output = new DataOutputStream(outputBuffer)) {
            ProxyPackSyncProtocol.writeHeader(output, ProxyPackSyncProtocol.PACK_LIST_RESPONSE);
            output.writeInt(packs.size());
            for (var pack : packs) {
                ProxyPackSyncProtocol.writeUuid(output, pack.getId());
                ProxyPackSyncProtocol.writeString(output, pack.getStringVersion());
                ProxyPackSyncProtocol.writeString(output, pack.getType().name().toLowerCase(Locale.ROOT));
                output.writeLong(pack.getSize());
                output.writeInt(chunkSize);
                output.writeInt((int) Math.ceil(pack.getSize() / (double) chunkSize));
                ProxyPackSyncProtocol.writeBytes(output, pack.getHash());
                ProxyPackSyncProtocol.writeString(output, pack.getContentKey());
            }
            player.sendPacketImmediately(createPacket(msgId, outputBuffer.toByteArray()));
        }
    }

    private void sendPackChunk(AllayPlayer player, long msgId, DataInputStream input) throws IOException {
        UUID packId = ProxyPackSyncProtocol.readUuid(input);
        String version = ProxyPackSyncProtocol.readString(input);
        int chunkIndex = input.readInt();

        Pack pack = Registries.PACKS.get(packId);
        if (pack == null || !pack.getStringVersion().equals(version) || !isSupportedPack(pack)) {
            sendError(player, msgId, "Unknown resource pack " + packId + "_" + version);
            return;
        }

        int chunkSize = AllayServer.getSettings().resourcePackSettings().maxChunkSize() * 1024;
        byte[] chunk = pack.getChunk(chunkSize * chunkIndex, chunkSize);

        try (var outputBuffer = new ByteArrayOutputStream();
             var output = new DataOutputStream(outputBuffer)) {
            ProxyPackSyncProtocol.writeHeader(output, ProxyPackSyncProtocol.PACK_CHUNK_RESPONSE);
            ProxyPackSyncProtocol.writeUuid(output, packId);
            ProxyPackSyncProtocol.writeString(output, version);
            output.writeInt(chunkIndex);
            ProxyPackSyncProtocol.writeBytes(output, chunk);
            player.sendPacketImmediately(createPacket(msgId, outputBuffer.toByteArray()));
        }
    }

    private void sendError(AllayPlayer player, long msgId, String message) {
        try (var outputBuffer = new ByteArrayOutputStream();
             var output = new DataOutputStream(outputBuffer)) {
            ProxyPackSyncProtocol.writeHeader(output, ProxyPackSyncProtocol.ERROR_RESPONSE);
            ProxyPackSyncProtocol.writeString(output, message == null ? "Unknown proxy pack sync error" : message);
            player.sendPacketImmediately(createPacket(msgId, outputBuffer.toByteArray()));
        } catch (IOException exception) {
            player.disconnect("Waterdog pack sync error");
        }
    }

    private PyRpcPacket createPacket(long msgId, byte[] payload) {
        var packet = new PyRpcPacket();
        packet.setMsgId(msgId);
        packet.setData(payload);
        return packet;
    }

    private boolean isSupportedPack(Pack pack) {
        return pack.getType() == Pack.Type.RESOURCES || pack.getType() == Pack.Type.DATA;
    }
}
