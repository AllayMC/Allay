package org.allaymc.api.tags;

import com.google.gson.JsonParser;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BlockTagRegistry extends SimpleMappedRegistry<Identifier, Set<String>, Map<Identifier, Set<String>>> {
    static final Map<String, Set<Identifier>> tag2Ids = new HashMap<>();

    public static volatile BlockTagRegistry REGISTRY;

    public static synchronized BlockTagRegistry getRegistry() {
        if (REGISTRY == null) {
            synchronized (BlockTagRegistry.class) {
                if (REGISTRY == null) {
                    REGISTRY = new BlockTagRegistry();
                }
            }
        }
        return REGISTRY;
    }

    @UnmodifiableView
    @NotNull
    public Set<String> getTagSet(final Identifier identifier) {
        return Collections.unmodifiableSet(this.getOrDefault(identifier, Set.of()));
    }

    @UnmodifiableView
    @NotNull
    public Set<Identifier> getBlockSet(final String tag) {
        return Collections.unmodifiableSet(tag2Ids.getOrDefault(tag, Set.of()));
    }

    private BlockTagRegistry() {
        super(BlockTagRegistry.class.getClassLoader().getResourceAsStream("block_tags.json"), new BlockTagRegistryLoader());

        for (var e : getContent().entrySet()) {
            Set<String> value = e.getValue();
            Identifier key = e.getKey();
            for (var tag : value) {
                Set<Identifier> identifiers = tag2Ids.computeIfAbsent(tag, (t) -> new HashSet<>());
                identifiers.add(key);
            }
        }
    }


    static class BlockTagRegistryLoader implements RegistryLoader<InputStream, Map<Identifier, Set<String>>> {
        @Override
        public Map<Identifier, Set<String>> load(InputStream input) {
            Map<Identifier, Set<String>> blockId2tags = new HashMap<>();
            JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(input)))
                    .getAsJsonObject()
                    .entrySet()
                    .forEach(entry -> entry.getValue().getAsJsonArray().forEach(blockId -> {
                        var id = VanillaBlockId.valueOf(AllayStringUtils.fastTwoPartSplit(blockId.getAsString(), ":", "")[1].toUpperCase());
                        blockId2tags.computeIfAbsent(id.getIdentifier(), unused -> new HashSet<>()).add(entry.getKey());
                    }));
            return blockId2tags;
        }
    }
}
