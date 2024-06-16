package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.allaymc.data.chore.HashUtils;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuxToBlockStateBimapExtractor {
    public static final BlockPropertyTypeFile BLOCK_PROPERTY_TYPE_INFO_FILE;
    public static final Path BLOCK_PROPERTY_TYPES_FILE = Path.of("Allay-Data/resources/unpacked/block_property_types.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void main(String[] args) {
        String url = "https://learn.microsoft.com/en-us/minecraft/creator/reference/content/blockreference/examples/auxvaluetoblockstatemap?view=minecraft-bedrock-stable";

        //                                       name         meta  blockStateHash
        var auxValue2BlockStateMap = new LinkedHashMap<String, Map<Integer, Integer>>();
        try {
            // Fetch the HTML content of the page
            Document doc = Jsoup.connect(url).get();

            Elements elements = doc.select(".content > table > tbody");
            Element tbody = elements.getFirst();
            for (var n : tbody.children()) {
                String name = n.children().get(1).text();
                int aux = Integer.parseInt(n.children().get(2).text());
                String state = n.children().get(3).text();
                state = state.replace(name, "").trim();
                String[] split = new String[0];
                if (state.equals("[")) {
                    state = state.substring(1, state.length() - 1);
                    split = state.split(",");
                }
                NbtMapBuilder builder = NbtMap.builder().putString("name", name);
                NbtMapBuilder states = NbtMap.builder();
                for (var s : split) {
                    String property = s.trim();
                    String[] propertyAndValue = property.split("=");
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
                    if (blockPropertyTypeInfo == null && BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(pn)) {
                        String s1 = BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(name).get(pn);
                        blockPropertyTypeInfo = BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.get(s1);
                    }
                    if (blockPropertyTypeInfo == null) {
                        System.out.println("Skip blockPropertyType %s for %s, aux %s".formatted(pn, name, aux));
                        continue;
                    }
                    switch (blockPropertyTypeInfo.valueType) {
                        case ENUM -> states.put(pn, pvalue);
                        case BOOLEAN -> states.put(pn, parseBoolean(pvalue));
                        case INTEGER -> states.put(pn, Integer.parseInt(pvalue));
                    }
                }
                builder.put("states", states.build());
                NbtMap nbt = builder.build();
                int i = HashUtils.fnv1a_32_nbt(nbt);
                Map<Integer, Integer> integerIntegerMap = auxValue2BlockStateMap.computeIfAbsent(name, (xx) -> new LinkedHashMap<>());
                integerIntegerMap.put(aux, i);
            }
            String json = GSON.toJson(auxValue2BlockStateMap);
            Path path = Path.of("Allay-Data/resources/aux_to_block_state_bimap.json");
            Files.deleteIfExists(path);
            Files.writeString(path, json, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
