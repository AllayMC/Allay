package org.allaymc.server.block.registry;

import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.material.MaterialType;
import org.allaymc.api.block.registry.MaterialRegistry;
import org.allaymc.api.data.VanillaMaterialTypes;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.server.utils.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Allay Project 2024/7/2
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayMaterialRegistry extends SimpleMappedRegistry<MaterialType, Material, Map<MaterialType, Material>> implements MaterialRegistry {
    public AllayMaterialRegistry(RegistryLoader<Void, Map<MaterialType, Material>> loader) {
        super(null, loader);
    }

    @AllArgsConstructor
    public static class Loader implements RegistryLoader<Void, Map<MaterialType, Material>> {
        protected Supplier<InputStream> streamSupplier;

        public Loader() {
            this(() -> new BufferedInputStream(ResourceUtils.getResource("materials.json")));
        }

        @SneakyThrows
        @Override
        public Map<MaterialType, Material> load(Void input) {
            log.info("Start loading material registry...");
            try (var reader = new InputStreamReader(streamSupplier.get())) {
                var loaded = new HashMap<MaterialType, Material>();
                JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                    var key = entry.getKey();
                    var type = VanillaMaterialTypes.getMaterialTypeByName(key);
                    if (type == null) {
                        log.error("Unknown material type: {}", key);
                        return;
                    }
                    var obj = entry.getValue().getAsJsonObject();
                    var material = new Material(
                            type,
                            obj.get("blocksMotion").getAsBoolean(),
                            obj.get("blocksPrecipitation").getAsBoolean(),
                            obj.get("isAlwaysDestroyable").getAsBoolean(),
                            obj.get("isLiquid").getAsBoolean(),
                            obj.get("isNeverBuildable").getAsBoolean(),
                            obj.get("isReplaceable").getAsBoolean(),
                            obj.get("isSolid").getAsBoolean(),
                            obj.get("isSolidBlocking").getAsBoolean(),
                            obj.get("isSuperHot").getAsBoolean(),
                            obj.get("translucency").getAsFloat()
                    );
                    loaded.put(type, material);
                });
                log.info("Loaded material registry successfully");
                return loaded;
            }
        }
    }
}
