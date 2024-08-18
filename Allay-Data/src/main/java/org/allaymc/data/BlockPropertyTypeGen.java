package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Allay Project 2024/7/5
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockPropertyTypeGen {
    public static void main(String[] args) throws IOException {
        File file = new File("Allay-Data/resources/unpacked/block_palette.nbt");
        InputStream stream = new FileInputStream(file);
        List<NbtMap> blocks;
        int blockStateCounter = 0;
        try (var reader = NbtUtils.createGZIPReader(stream)) {
            NbtMap map = (NbtMap) reader.readTag();
            blocks = map.getList("blocks", NbtType.COMPOUND);
            for (var ignored : blocks) {
                blockStateCounter++;
            }
        }
        log.info("Generating property type data...");

        Map<String, List<NbtMap>> blockToBlockStateData = new LinkedHashMap<>();
        for (int i = 0; i < blockStateCounter; ++i) {
            NbtMap nbtMap = blocks.get(i);
            String name = nbtMap.getString("name");
            List<NbtMap> blockStates = blockToBlockStateData.computeIfAbsent(name, (s) -> new ArrayList<>());
            if (nbtMap.containsKey("states") && !nbtMap.getCompound("states").isEmpty()) {
                blockStates.add(NbtMapBuilder.from(nbtMap.getCompound("states")).build());
            }
        }

        Map<String, Map<String, PropertyType>> blockToPropertyTypeMap = new TreeMap<>();
        for (Map.Entry<String, List<NbtMap>> entry : blockToBlockStateData.entrySet()) {
            List<NbtMap> states = entry.getValue();
            Map<String, PropertyType> propertyTypeMap = new TreeMap<>();

            for (NbtMap state : states) {
                for (Map.Entry<String, Object> valueEntry : state.entrySet()) {
                    PropertyType propertyType = propertyTypeMap.computeIfAbsent(valueEntry.getKey(), (k) -> new PropertyType(entry.getKey(), valueEntry.getKey()));
                    Object value = valueEntry.getValue();
                    switch (value) {
                        case Byte b -> {
                            if (b == 1 || b == 0) {
                                if (propertyType.valueType.isEmpty()) {
                                    propertyType.valueType = "BOOLEAN";
                                }
                                propertyType.values.add(String.valueOf(b == 1));
                            } else {
                                if (propertyType.valueType.isEmpty()) {
                                    propertyType.valueType = "UNKNOWN";
                                }
                                System.out.println("Unknown tag type when dumping property type data: " + valueEntry.getKey());
                            }
                        }
                        case Boolean bool -> {
                            if (propertyType.valueType.isEmpty()) {
                                propertyType.valueType = "BOOLEAN";
                            }
                            propertyType.values.add(String.valueOf(bool));
                        }
                        case Integer i -> {
                            if (propertyType.valueType.isEmpty()) {
                                propertyType.valueType = "INTEGER";
                            }
                            propertyType.values.add(String.valueOf(i));
                        }
                        case String s -> {
                            if (propertyType.valueType.isEmpty()) {
                                propertyType.valueType = "ENUM";
                            }
                            propertyType.values.add(s);
                        }
                        default -> {
                            if (propertyType.valueType.isEmpty()) {
                                propertyType.valueType = "UNKNOWN";
                            }
                            System.out.println("Unknown tag type when dumping property type data: " + valueEntry.getKey());
                        }
                    }
                }
            }
            blockToPropertyTypeMap.put(entry.getKey(), propertyTypeMap);
        }

        Set<String> differentSizePropertyTypes = new TreeSet<>();
        Map<String, Map<String, String>> specialBlockTypes = new TreeMap<>();
        Map<String, PropertyType> tmpLookUp = new TreeMap<>();

        for (Map.Entry<String, Map<String, PropertyType>> entry : blockToPropertyTypeMap.entrySet()) {
            for (Map.Entry<String, PropertyType> entryInside : entry.getValue().entrySet()) {
                String propertyName = entryInside.getKey();
                PropertyType propertyType = entryInside.getValue();

                tmpLookUp.putIfAbsent(propertyName, propertyType);

                if (!tmpLookUp.get(propertyName).equals(propertyType) && !differentSizePropertyTypes.contains(propertyName)) {
                    System.out.println("Property type \"" + propertyName + "\" has different size in different blocks!");
                    differentSizePropertyTypes.add(propertyName);
                    for (var entryInsideInside : blockToPropertyTypeMap.entrySet()) {
                        if (entryInsideInside.getValue().containsKey(propertyName)) {
                            specialBlockTypes.putIfAbsent(entryInsideInside.getKey(), new TreeMap<>());
                        }
                    }
                }
            }
        }

        Map<String, PropertyType> globalPropertyTypeMap = new TreeMap<>();

        for (Map.Entry<String, Map<String, PropertyType>> entry : blockToPropertyTypeMap.entrySet()) {
            for (Map.Entry<String, PropertyType> entryInside : entry.getValue().entrySet()) {
                String propertyName = entryInside.getKey();
                PropertyType propertyType = entryInside.getValue();
                String keyName = propertyName.replace(':', '_');
                if (differentSizePropertyTypes.contains(propertyName)) {
                    String newKey = keyName + "_" + propertyType.values.size();
                    String fullBlockName = entry.getKey();
                    specialBlockTypes.get(fullBlockName).put(keyName, newKey);
                    globalPropertyTypeMap.put(newKey, propertyType);
                } else {
                    globalPropertyTypeMap.put(keyName, propertyType);
                }
            }
        }


        Map<String, Object> globalJson = new TreeMap<>();
        Map<String, Object> propertyTypes = new TreeMap<>();

        for (Map.Entry<String, PropertyType> propertyTypeEntry : globalPropertyTypeMap.entrySet()) {
            if (propertyTypeEntry.getValue().serializationName.isEmpty()) {
                continue;
            }
            Map<String, Object> obj = new TreeMap();

            obj.put("serializationName", propertyTypeEntry.getValue().serializationName);
            obj.put("valueType", propertyTypeEntry.getValue().valueType);
            if (propertyTypeEntry.getValue().valueType.equals("INTEGER")) {
                List<Integer> values = new ArrayList<>();
                for (String value : propertyTypeEntry.getValue().values) {
                    values.add(Integer.parseInt(value));
                }
                values.sort(Integer::compare);
                obj.put("values", values);
            } else if (propertyTypeEntry.getValue().valueType.equals("BOOLEAN")) {
                List<Boolean> values = new ArrayList<>();
                values.add(false);
                values.add(true);
                obj.put("values", values);
            } else {
                obj.put("values", new ArrayList<>(propertyTypeEntry.getValue().values));
            }

            propertyTypes.put(propertyTypeEntry.getKey(), obj);
        }
        globalJson.put("propertyTypes", propertyTypes);
        globalJson.put("differentSizePropertyTypes", new ArrayList<>(differentSizePropertyTypes));
        globalJson.put("specialBlockTypes", specialBlockTypes);

        writeJSONObject(globalJson);
        log.info("Block property type data have been saved to Allay-Data/resources/unpacked/block_property_types.json");
    }

    private static void writeJSONObject(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().setNumberToNumberStrategy(JsonReader::nextLong).setObjectToNumberStrategy(JsonReader::nextLong).create();
        String json = gson.toJson(obj);
        try {
            Path path = Path.of("Allay-Data/resources/unpacked/block_property_types.json");
            Files.deleteIfExists(path);
            Files.writeString(path, json, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class PropertyType {
        final String serializationName;
        final String blockName;
        final Set<String> values = new LinkedHashSet<>();
        String valueType = "";

        PropertyType(String blockName, String serializationName) {
            this.blockName = blockName;
            this.serializationName = serializationName;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PropertyType that = (PropertyType) obj;
            return Objects.equals(serializationName, that.serializationName) &&
                   Objects.equals(valueType, that.valueType) &&
                   Objects.equals(values.size(), that.values.size());
        }

        @Override
        public int hashCode() {
            return Objects.hash(serializationName, valueType, values.size());
        }
    }
}
