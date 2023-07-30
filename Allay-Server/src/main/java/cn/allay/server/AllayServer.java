package cn.allay.server;

import cn.allay.api.math.vector.Vec3i;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.player.Client;
import cn.allay.api.player.info.DeviceInfo;
import cn.allay.api.player.skin.Skin;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.WorldPool;
import cn.allay.api.world.WorldType;
import cn.allay.server.network.AllayNetworkServer;
import cn.allay.server.player.AllayClient;
import cn.allay.server.terminal.AllayTerminalConsole;
import cn.allay.server.utils.GameLoop;
import cn.allay.server.world.AllayWorld;
import cn.allay.server.world.AllayWorldPool;
import cn.allay.server.world.generator.flat.FlatWorldGenerator;
import cn.allay.server.world.storage.nonpersistent.AllayNonPersistentWorldStorage;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Slf4j
public final class AllayServer implements Server {

    private final Map<String, Client> clients = new ConcurrentHashMap<>();
    @Getter
    private final WorldPool worldPool = new AllayWorldPool();
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final Object2ObjectMap<UUID, PlayerListPacket.Entry> playerListEntryMap = new Object2ObjectOpenHashMap<>();
    @Getter
    private ServerSettings serverSettings;
    @Getter
    private NetworkServer networkServer;
    private Thread terminalConsoleThread;
    private AllayTerminalConsole terminalConsole;
    @Getter
    private long ticks = 0;

    @Override
    public void start() {
        initTerminalConsole();
        this.serverSettings = readServerSettings();
        this.networkServer = initNetwork();
        log.info("Starting up network server...");
        this.networkServer.start();
        log.info("Network server started.");
        loadWorlds();
        loop();
    }

    private void loop() {
        GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (isRunning.get())
                        onTick();
                    else loop.stop();
                })
                .onStop(() -> isRunning.set(false))
                .build()
                .startLoop();
    }

    private void initTerminalConsole() {
        terminalConsole = new AllayTerminalConsole(this);
        terminalConsoleThread = new AllayTerminalConsoleThread();
        terminalConsoleThread.start();
    }

    private void loadWorlds() {
        WorldData worldData = WorldData.DEFAULT;
        worldData.setLevelName("Test Flat World");
        worldData.setSpawnPoint(Vec3i.of(0, 6, 0));
        worldPool.setDefaultWorld(AllayWorld
                .builder()
                .worldData(worldData)
                .setWorldGenerator(new FlatWorldGenerator())
                .setWorldStorage(new AllayNonPersistentWorldStorage())
                .setWorldType(WorldType.FLAT)
                .build());
    }

    @Override
    public void shutdown() {
        isRunning.compareAndSet(true, false);
    }

    private void onTick() {
        ticks++;
    }

    @Override
    public int getOnlineClientCount() {
        return clients.size();
    }

    @Override
    @UnmodifiableView
    public Map<String, Client> getOnlineClients() {
        return Collections.unmodifiableMap(clients);
    }

    private ServerSettings readServerSettings() {
        //TODO
        return ServerSettings
                .builder()
                .ip("0.0.0.0")
                .port(19132)
                .motd("Allay Server")
                .subMotd("Powered by Allay")
                .maxClientCount(20)
                .gameType(GameType.CREATIVE)
                .xboxAuth(false)
                .defaultTickingRadius(8)
                .defaultViewDistance(8)
                .enableNetworkEncryption(true)
                .build();
    }

    private NetworkServer initNetwork() {
        return new AllayNetworkServer(this);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void onClientConnect(BedrockServerSession session) {
        AllayClient.hold(session, this);
    }

    @Override
    public void onLoginFinish(Client client) {
        clients.put(client.getName(), client);
    }

    @Override
    public void onClientDisconnect(Client client) {
        clients.remove(client.getName());
    }

    @Override
    public void addToPlayerList(Client client) {
        addToPlayerList(
                client.getLoginData().getUuid(),
                client.getPlayerEntity().getUniqueId(),
                client.getName(),
                client.getLoginData().getDeviceInfo(),
                client.getLoginData().getXuid(),
                client.getLoginData().getSkin()
        );
    }

    @Override
    public void addToPlayerList(UUID uuid, long entityId,
                                String name, DeviceInfo deviceInfo,
                                String xuid, Skin skin) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);
        PlayerListPacket.Entry entry = new PlayerListPacket.Entry(uuid);
        entry.setEntityId(entityId);
        entry.setName(name);
        entry.setXuid(xuid);
        entry.setPlatformChatId(deviceInfo.getDeviceName());
        entry.setBuildPlatform(deviceInfo.getDevice().getId());
        entry.setSkin(skin.toNetwork());
        entry.setTrustedSkin(skin.isTrusted());
        playerListPacket.getEntries().add(entry);
        playerListEntryMap.put(uuid, entry);
        broadcastPacket(playerListPacket);
    }

    public void removeFromPlayerList(Client client) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(new PlayerListPacket.Entry(client.getLoginData().getUuid()));
        broadcastPacket(playerListPacket);
        playerListEntryMap.remove(client.getLoginData().getUuid());
    }

    public void removeFromPlayerList(UUID uuid) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(new PlayerListPacket.Entry(uuid));
        broadcastPacket(playerListPacket);
        playerListEntryMap.remove(uuid);
    }

    public Map<UUID, PlayerListPacket.Entry> getPlayerListEntryMap() {
        return this.playerListEntryMap;
    }

    @Override
    public void broadcastPacket(BedrockPacket packet) {
        for (var client : clients.values()) {
            client.sendPacket(packet);
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    private class AllayTerminalConsoleThread extends Thread {
        public AllayTerminalConsoleThread() {
            super("Console Thread");
        }

        @Override
        public void run() {
            terminalConsole.start();
        }
    }
}
