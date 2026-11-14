package org.allaymc.server.network.protocol;

import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The block palette the clients of a protocol know. Block states are sent by their hash in that palette.
 * <p>
 * The current palette sends every state as it is. An older palette maps each current state to the state of that
 * palette the client should see instead, in this order:
 * <ol>
 *     <li>the old state that the block state updater upgrades to it, which also covers renamed blocks;</li>
 *     <li>the old block with the properties it has, e.g. stairs without {@code minecraft:corner};</li>
 *     <li>a fallback block of the same shape from {@code block_fallbacks.json} for blocks the version lacks, with the
 *     properties both share;</li>
 *     <li>{@code minecraft:unknown}.</li>
 * </ol>
 * Custom blocks are described to every client in the start game packet and are sent as they are.
 * <p>
 * The old palettes in {@code block_palettes/} come from CloudburstMC/Data (Apache-2.0): 818 (a899625),
 * 827 (6b0c502), 844 (10d55f7), 944 (0fb7745), 975 (eb77f9f), 1001 (4634cd1) and 2168 (619483e).
 */
@Slf4j
public final class NetworkBlockPalette {
    private static final NetworkBlockPalette CURRENT = new NetworkBlockPalette("current", null, null);
    private static final Map<String, NetworkBlockPalette> OLD_PALETTES = new ConcurrentHashMap<>();
    private static final int MAX_FALLBACK_DEPTH = 8;
    private static final Identifier UNKNOWN_ID = new Identifier("minecraft:unknown");

    private final String name;
    private final Int2IntMap currentToNetwork;
    private final List<BlockDefinition> definitions;

    private NetworkBlockPalette(String name, Int2IntMap currentToNetwork, List<BlockDefinition> definitions) {
        this.name = name;
        this.currentToNetwork = currentToNetwork;
        this.definitions = definitions;
    }

    /**
     * Returns the current block palette, which sends every block state as it is.
     *
     * @return the current palette
     */
    public static NetworkBlockPalette current() {
        return CURRENT;
    }

    /**
     * Returns an older block palette from {@code block_palettes/<name>.nbt}, loading it on first use.
     *
     * @param name the palette name, e.g. {@code 2168}
     * @return the palette
     */
    public static NetworkBlockPalette of(String name) {
        return OLD_PALETTES.computeIfAbsent(name, NetworkBlockPalette::load);
    }

    /**
     * Returns the name of this palette, {@code current} for the current one.
     *
     * @return the name
     */
    public String name() {
        return name;
    }

    /**
     * Returns whether this is the current palette.
     *
     * @return {@code true} for the current palette
     */
    public boolean isCurrent() {
        return currentToNetwork == null;
    }

    /**
     * Returns the hash a client of this palette knows the given block state by.
     *
     * @param blockState the block state
     * @return the network block state hash
     */
    public int toNetwork(BlockState blockState) {
        return toNetwork(blockState.blockStateHash());
    }

    /**
     * Returns the hash a client of this palette knows the given block state by.
     *
     * @param blockStateHash the hash of a current block state
     * @return the network block state hash
     */
    public int toNetwork(int blockStateHash) {
        return currentToNetwork == null ? blockStateHash : currentToNetwork.get(blockStateHash);
    }

    /**
     * Returns the block definitions a client of this palette decodes.
     *
     * @return the block definitions
     */
    public List<BlockDefinition> definitions() {
        if (definitions != null) {
            return definitions;
        }
        return Registries.BLOCKS.getContent().values().stream()
                .flatMap(blockType -> blockType.getAllStates().stream())
                .map(blockState -> (BlockDefinition) blockState::blockStateHash)
                .toList();
    }

    @Override
    public String toString() {
        return "NetworkBlockPalette[" + name + "]";
    }

    private static NetworkBlockPalette load(String name) {
        var oldStates = readPalette("block_palettes/" + name + ".nbt");
        var builder = new Builder(oldStates, readFallbacks());
        var palette = new NetworkBlockPalette(name, builder.build(), builder.definitions());
        if (builder.unknownStates > 0) {
            log.warn("{} block states have no counterpart in block palette {} and are sent as unknown", builder.unknownStates, name);
        }
        return palette;
    }

    private static List<NbtMap> readPalette(String resource) {
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource(resource)))) {
            return ((NbtMap) reader.readTag()).getList("blocks", NbtType.COMPOUND);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read block palette " + resource, e);
        }
    }

    private static Map<Identifier, Identifier> readFallbacks() {
        var fallbacks = new HashMap<Identifier, Identifier>();
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_fallbacks.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry ->
                    fallbacks.put(new Identifier(entry.getKey()), new Identifier(entry.getValue().getAsString())));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read block_fallbacks.json", e);
        }
        return fallbacks;
    }

    private static final class Builder {
        private final IntSet networkHashes = new IntOpenHashSet();
        private final Map<String, Set<String>> propertyNames = new HashMap<>();
        private final Map<BlockType<?>, String> oldNames = new HashMap<>();
        private final Int2IntOpenHashMap currentToNetwork = new Int2IntOpenHashMap();
        private final Map<Identifier, Identifier> fallbacks;
        private final List<BlockDefinition> definitions = new ArrayList<>();
        private int unknownHash = HashUtils.computeBlockStateHash(UNKNOWN_ID, List.of());
        private int unknownStates;

        private Builder(List<NbtMap> oldStates, Map<Identifier, Identifier> fallbacks) {
            this.fallbacks = fallbacks;
            for (var oldState : oldStates) {
                var name = oldState.getString("name");
                int hash = oldState.getInt("network_id");
                networkHashes.add(hash);
                definitions.add(() -> hash);
                propertyNames.computeIfAbsent(name, k -> new HashSet<>()).addAll(oldState.getCompound("states").keySet());
                if (name.equals(UNKNOWN_ID.toString())) {
                    unknownHash = hash;
                }

                var current = upgrade(oldState);
                if (current == null) {
                    continue;
                }
                oldNames.putIfAbsent(current.getBlockType(), name);
                if (current.blockStateHash() == hash) {
                    currentToNetwork.put(hash, hash);
                } else {
                    currentToNetwork.putIfAbsent(current.blockStateHash(), hash);
                }
            }
        }

        private Int2IntMap build() {
            for (var blockType : Registries.BLOCKS.getContent().values()) {
                boolean custom = blockType instanceof AllayBlockType<?> allayBlockType && allayBlockType.isCustomBlock();
                for (var blockState : blockType.getAllStates()) {
                    int hash = blockState.blockStateHash();
                    if (custom) {
                        currentToNetwork.put(hash, hash);
                        definitions.add(blockState::blockStateHash);
                    } else if (!currentToNetwork.containsKey(hash)) {
                        currentToNetwork.put(hash, resolve(blockState, 0));
                    }
                }
            }
            currentToNetwork.defaultReturnValue(unknownHash);
            return currentToNetwork;
        }

        private List<BlockDefinition> definitions() {
            return List.copyOf(definitions);
        }

        private int resolve(BlockState blockState, int depth) {
            var blockType = blockState.getBlockType();
            var oldName = oldNames.get(blockType);
            if (oldName != null) {
                // The version knows the block but not this state, e.g. stairs before minecraft:corner: keep the
                // properties it has.
                int hash = hash(oldName, blockState, propertyNames.get(oldName));
                if (networkHashes.contains(hash)) {
                    return hash;
                }
                var defaultHash = blockType.getDefaultState().blockStateHash();
                if (currentToNetwork.containsKey(defaultHash)) {
                    return currentToNetwork.get(defaultHash);
                }
            }

            var fallback = fallbacks.get(blockType.getIdentifier());
            var fallbackType = fallback == null ? null : Registries.BLOCKS.get(fallback);
            if (fallbackType != null && depth < MAX_FALLBACK_DEPTH) {
                var target = withSharedProperties(fallbackType.getDefaultState(), blockState);
                int targetHash = target.blockStateHash();
                return currentToNetwork.containsKey(targetHash) ? currentToNetwork.get(targetHash) : resolve(target, depth + 1);
            }

            unknownStates++;
            return unknownHash;
        }

        private static BlockState upgrade(NbtMap oldState) {
            var tag = BlockStateUpdaters.updateBlockState(oldState, ProtocolInfo.BLOCK_STATE_UPDATER.getVersion());
            var blockType = Registries.BLOCKS.get(new Identifier(tag.getString("name")));
            if (blockType == null) {
                return null;
            }
            var values = new ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
            var states = tag.getCompound("states");
            for (var property : blockType.getProperties().values()) {
                var value = states.get(property.getName());
                try {
                    values.add(value == null ? property.createDefaultValue() : property.tryCreateValue(value));
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }
            return blockType.ofState(values);
        }

        private static int hash(String name, BlockState blockState, Set<String> keptProperties) {
            var states = new TreeMap<String, Object>();
            for (var value : blockState.getPropertyValues().values()) {
                var propertyName = value.getPropertyType().getName();
                if (keptProperties.contains(propertyName)) {
                    states.put(propertyName, value.getSerializedValue());
                }
            }
            return HashUtils.fnv1a_32_nbt(NbtMap.builder()
                    .putString("name", name)
                    .putCompound("states", NbtMap.fromMap(states))
                    .build());
        }

        private static BlockState withSharedProperties(BlockState target, BlockState source) {
            var properties = target.getBlockType().getProperties();
            for (var value : source.getPropertyValues().values()) {
                var property = properties.get(value.getPropertyType().getName());
                if (property == null) {
                    continue;
                }
                try {
                    target = target.setPropertyValue(property.tryCreateValue(value.getSerializedValue()));
                } catch (IllegalArgumentException ignored) {
                    // The fallback block does not accept this value, keep its default.
                }
            }
            return target;
        }
    }
}
