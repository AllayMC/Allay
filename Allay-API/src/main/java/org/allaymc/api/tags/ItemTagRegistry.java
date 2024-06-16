package org.allaymc.api.tags;

import com.google.gson.JsonParser;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ItemTagRegistry extends SimpleMappedRegistry<Identifier, Set<String>, Map<Identifier, Set<String>>> {
    static final Map<String, Set<Identifier>> tag2Ids = new HashMap<>();

    public static volatile ItemTagRegistry REGISTRY;

    public static synchronized ItemTagRegistry getRegistry() {
        if (REGISTRY == null) {
            synchronized (ItemTagRegistry.class) {
                if (REGISTRY == null) {
                    REGISTRY = new ItemTagRegistry();
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
    public Set<Identifier> getItemSet(final String tag) {
        return Collections.unmodifiableSet(tag2Ids.getOrDefault(tag, Set.of()));
    }

    public boolean contain(final String tag) {
        return tag2Ids.containsKey(tag);
    }

    @Override
    public Set<String> register(Identifier identifier, Set<String> tags) {
        for (var tag : tags) {
            Set<Identifier> identifiers = tag2Ids.computeIfAbsent(tag, (t) -> new HashSet<>());
            identifiers.add(identifier);
        }
        return super.register(identifier, tags);
    }

    private ItemTagRegistry() {
        super(ItemTagRegistry.class.getClassLoader().getResourceAsStream("item_tags.json"), new ItemTagRegistryLoader());

        for (var e : getContent().entrySet()) {
            Set<String> value = e.getValue();
            Identifier key = e.getKey();
            for (var tag : value) {
                Set<Identifier> identifiers = tag2Ids.computeIfAbsent(tag, (t) -> new HashSet<>());
                identifiers.add(key);
            }
        }
    }


    static class ItemTagRegistryLoader implements RegistryLoader<InputStream, Map<Identifier, Set<String>>> {
        @Override
        public Map<Identifier, Set<String>> load(InputStream input) {
            Map<Identifier, Set<String>> map = new HashMap<>();
            JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(input)))
                    .getAsJsonObject()
                    .entrySet()
                    .forEach(entry -> entry.getValue().getAsJsonArray().forEach(id -> {
                        var identifier = VanillaItemId.valueOf(AllayStringUtils.fastTwoPartSplit(id.getAsString(), ":", "")[1].toUpperCase());
                        map.computeIfAbsent(identifier.getIdentifier(), unused -> new HashSet<>()).add(entry.getKey());
                    }));
            return map;
        }
    }
}
