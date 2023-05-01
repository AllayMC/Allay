package cn.allay.block.type;

import cn.allay.block.data.VanillaBlockId;
import cn.allay.identifier.Identifier;
import cn.allay.registry.SimpleMappedRegistry;
import cn.allay.utils.StringUtils;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
public final class AllayBlockTypeRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> implements BlockTypeRegistry {
    public AllayBlockTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        //HACK: Load all the vanilla block class
        //TODO: Replace it with auto-registry (or other) and remove this hack
        for (var vanillaBlockId : VanillaBlockId.values()) {
            Class.forName("cn.allay.block.impl.Block" + convertToCamelCase(vanillaBlockId.getNamespaceId().getPath()));
        }
    }

    private static String convertToCamelCase(String str) {
        List<String> parts = StringUtils.fastSplit(str, "_");
        StringBuilder output = new StringBuilder();

        for (String part : parts) {
            output.append(Character.toUpperCase(part.charAt(0)));
            output.append(part.substring(1));
        }

        return output.toString();
    }
}
