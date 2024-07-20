package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.material.MaterialType;
import org.allaymc.api.data.VanillaMaterialTypes;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2024/7/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class MaterialLoader implements RegistryLoader<Void, Map<MaterialType, Material>> {
    @Override
    @SneakyThrows
    public Map<MaterialType, Material> load(Void $) {
        log.info("Start loading material registry...");
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("materials.json")))) {
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
