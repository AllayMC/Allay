package org.allaymc.testutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.exception.MissingImplementationException;
import org.allaymc.server.Allay;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * 运行前需要关闭VanillaItemMetaBlockStateBiMap CreativeItemRegistry RecipeRegistry
 */
public class AuxToBlockStateBimapExtractor {
    public static final BlockPropertyTypeFile BLOCK_PROPERTY_TYPE_INFO_FILE;
    public static final Path BLOCK_PROPERTY_TYPES_FILE = Path.of("Allay-Data/resources/unpacked/block_property_types.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void main(String[] args) throws MissingImplementationException {
        Allay.initI18n();
        Allay.initAllayAPI();

        String url = "https://learn.microsoft.com/en-us/minecraft/creator/reference/content/blockreference/examples/auxvaluetoblockstatemap?view=minecraft-bedrock-stable";

        //                                       name         meta  blockStateHash
        var auxValue2BlockStateMap = new LinkedHashMap<String, Map<Integer, Integer>>();
        try {
            // Fetch the HTML content of the page
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".content > table > tbody");
            Element tbody = elements.getFirst();
            block:
            for (var n : tbody.children()) {
                String name = n.children().get(1).text();
                int aux = Integer.parseInt(n.children().get(2).text());
                String state = n.children().get(3).text();

                String stateName = state.trim();
                List<String> split = new ArrayList<>();
                if (state.contains("[")) {
                    String[] values = stateName.split(" ");
                    stateName = values[0];
                    state = state.replace(stateName, "").trim();
                    if (state.startsWith("[")) {
                        state = state.substring(1);
                    }
                    if (state.endsWith("]")) {
                        state = state.substring(0, state.length() - 1);
                    }
                    split = Arrays.stream(state.split(",")).map(String::trim).toList();
                }

                if (handleDocError(stateName, aux, split)) continue;

                NbtMapBuilder builder = NbtMap.builder().putString("name", stateName);
                BlockType<?> blockType = BlockTypeRegistry.getRegistry().get(new Identifier(stateName));
                if (blockType == null) {
                    System.out.println("Skip blockPropertyType %s, aux %s".formatted(stateName, aux));
                    continue;
                }
                BlockState blockState = blockType.getDefaultState();
                for (var s : split) {
                    String[] propertyAndValue = s.split("=");
                    String pn = propertyAndValue[0].trim();
                    String pvalue = propertyAndValue[1].trim();
                    pn = pn.substring(1, pn.length() - 1);
                    BlockPropertyTypeFile.BlockPropertyTypeInfo blockPropertyTypeInfo = BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.get(pn);
                    if (blockPropertyTypeInfo == null) {
                        String finalPn = pn;
                        Optional<Map.Entry<String, BlockPropertyTypeFile.BlockPropertyTypeInfo>> first = BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.entrySet().stream().filter(e -> e.getValue().serializationName.equals(finalPn)).findFirst();
                        if (first.isPresent()) {
                            blockPropertyTypeInfo = first.get().getValue();
                        }
                    }
                    if (BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(pn)) {
                        pn = BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(name).get(pn);
                        blockPropertyTypeInfo = BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.get(pn);
                    }
                    if (blockPropertyTypeInfo == null) {
                        System.out.println("Skip blockPropertyType %s for %s, aux %s".formatted(pn, name, aux));
                        continue block;
                    }

                    if (pn.contains(":")) {
                        pn = pn.replace(":", "_");
                    }

                    switch (blockPropertyTypeInfo.valueType) {
                        case ENUM ->
                                blockState = blockState.setProperty(VanillaBlockPropertyTypes.from(pn).tryCreateValue(parseString(pvalue)));
                        case BOOLEAN ->
                                blockState = blockState.setProperty(VanillaBlockPropertyTypes.from(pn).tryCreateValue(parseBoolean(pvalue)));
                        case INTEGER ->
                                blockState = blockState.setProperty(VanillaBlockPropertyTypes.from(pn).tryCreateValue(Integer.parseInt(pvalue)));
                    }
                }
                Map<Integer, Integer> integerIntegerMap = auxValue2BlockStateMap.computeIfAbsent(name, (xx) -> new LinkedHashMap<>());
                integerIntegerMap.put(aux, blockState.blockStateHash());
            }
            String json = GSON.toJson(auxValue2BlockStateMap);
            Path path = Path.of("Allay-Data/resources/aux_to_block_state_bimap.json");
            Files.deleteIfExists(path);
            Files.writeString(path, json, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle doc error in microsoft.
     */
    public static boolean handleDocError(String name, int aux, List<String> properties) {
        if (name.equals("minecraft:cake") && aux == 7) {//skip because cake havent 7
            return true;
        }
        if (name.equals("minecraft:chorus_flower") && (aux == 6 || aux == 7)) {//skip because chorus_flower havent 6,7
            return true;
        }
        if (name.equals("minecraft:cocoa") && (aux >= 12)) {
            return true;
        }
        if (name.equals("minecraft:composter") && (aux >= 9)) {
            return true;
        }
        if (name.equals("minecraft:rail") && (aux >= 9)) {
            return true;
        }
        return false;
    }

    static {
        try {
            BLOCK_PROPERTY_TYPE_INFO_FILE = GSON.fromJson(Files.newBufferedReader(BLOCK_PROPERTY_TYPES_FILE), BlockPropertyTypeFile.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean parseBoolean(String v) {
        try {
            int i = Integer.parseInt(v);
            return i > 0;
        } catch (NumberFormatException ignore) {
            return Boolean.parseBoolean(v);
        }
    }

    public static String parseString(String v) {
        if (v.startsWith("\"")) {
            v = v.substring(1);
        }
        if (v.endsWith("\"")) {
            v = v.substring(0, v.length() - 1);
        }
        return v;
    }

    public static class BlockPropertyTypeFile {
        Map<String, BlockPropertyTypeInfo> propertyTypes;
        List<String> differentSizePropertyTypes;
        Map<String, Map<String, String>> specialBlockTypes;

        protected enum BlockPropertyType {
            BOOLEAN,
            INTEGER,
            ENUM
        }

        public static class BlockPropertyTypeInfo {
            String serializationName;
            BlockPropertyType valueType;
            List<String> values;

            public String getConstantValueName() {
                return serializationName.replace(":", "_").toUpperCase();
            }
        }
    }
}
