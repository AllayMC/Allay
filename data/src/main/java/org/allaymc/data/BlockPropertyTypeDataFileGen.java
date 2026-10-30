package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * {@code BlockPropertyTypeDataFileGen} derives property-type definitions from
 * the values observed in the unpacked block palette. States are grouped by
 * block name before their property values are collected, allowing the same
 * serialized property name to have different domains on different blocks.
 * Output includes a global definition table and the per-block aliases consumed
 * by the Java property generator.
 *
 * <p>
 * Domain comparison uses the property name, inferred value type, and number of
 * observed values, rather than comparing every value. Definitions that differ
 * by that comparison receive a cardinality suffix and a per-block mapping.
 * Equal-size domains can still overwrite one another under the same output key,
 * so the table does not distinguish arbitrary domains of equal size.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockPropertyTypeDataFileGen {
    /**
     * Reads palette states and replaces the generated property-definition JSON.
     * {@code args} is ignored, and resource paths are relative to the
     * repository root. Property values are first collected per block, then
     * compared to identify names requiring per-block aliases.
     *
     * <p>
     * Boolean output always contains both values, integer values are sorted
     * numerically, and enum values retain discovery order. Unknown input kinds
     * produce diagnostics without aborting collection. Repeated output keys
     * replace earlier definitions, including distinct same-size domains sharing
     * a cardinality suffix.
     *
     * @throws IOException if the compressed palette cannot be opened or read
     */
    public static void main(String[] args) throws IOException {
        File file = new File("data/resources/unpacked/block_palette.nbt");
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
                    // NBT may encode booleans as zero or one bytes; normalize
                    // those observations while retaining unknown kinds as diagnosed property descriptors.
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

        // Compare each block's domain against the first one for that name;
        // differing type or cardinality requires aliases for every block using it.
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
                // Normalize keys for generated Java fields while keeping the
                // original serializationName in the definition used for stored block-state properties.
                String keyName = propertyName.replace(':', '_');
                if (differentSizePropertyTypes.contains(propertyName)) {
                    // Cardinality selects the alias consumed by the block generator;
                    // this key cannot distinguish different value sets of the same size.
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
            Map<String, Object> obj = new TreeMap<>();

            obj.put("serializationName", propertyTypeEntry.getValue().serializationName);
            obj.put("valueType", propertyTypeEntry.getValue().valueType);
            // Emit numeric domains in numeric order, and expose both boolean
            // states even when palette observations included only one of those values.
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
        log.info("Block property type data have been saved to data/resources/unpacked/block_property_types.json");
    }

    /**
     * Serializes the assembled definition object and replaces its resource
     * file. The previous file is deleted before the CRLF writer creates the new
     * one; write failures propagate without restoring the original output.
     */
    @SneakyThrows
    private static void writeJSONObject(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().setNumberToNumberStrategy(JsonReader::nextLong).setObjectToNumberStrategy(JsonReader::nextLong).create();
        String json = gson.toJson(obj);
        Path path = Path.of("data/resources/unpacked/block_property_types.json");
        // Remove the existing resource before writing its replacement; callers
        // must rerun generation if a subsequent failure leaves the output incomplete.
        Files.deleteIfExists(path);
        Utils.writeFileWithCRLF(path, json);
    }

    /**
     * {@code PropertyType} represents one block's observed domain for a
     * serialized property name. Equality combines {@code serializationName},
     * {@code valueType}, and the cardinality of {@code values}; it ignores
     * {@code blockName} and the actual value contents when deciding whether
     * definitions need different aliases.
     */
    static class PropertyType {
        // serializationName and valueType identify the observed property category;
        // values supplies its cardinality, which equality compares instead of individual entries.
        /** Original serialized property name. */
        final String serializationName;
        /** Block supplying these observations. */
        final String blockName;
        /**
         * {@code values} accumulates distinct values in discovery order.
         * Changing this set can change {@code equals} and {@code hashCode}
         * results through its cardinality.
         */
        final Set<String> values = new LinkedHashSet<>();
        /**
         * {@code valueType} records the first inferred value kind. {@code ""}
         * means no observation has classified this property yet.
         */
        String valueType = "";

        /**
         * Creates an unclassified property domain for the supplied block and
         * serialized name. Observations are added afterward to its mutable set,
         * and the first classified input establishes the stored value kind.
         */
        PropertyType(String blockName, String serializationName) {
            this.blockName = blockName;
            this.serializationName = serializationName;
        }

        /**
         * Returns whether another descriptor has the same name, type, and value
         * count. Actual values and source block names are ignored, so different
         * domains of equal cardinality can compare equal.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PropertyType that = (PropertyType) obj;
            return Objects.equals(serializationName, that.serializationName) &&
                   Objects.equals(valueType, that.valueType) &&
                   Objects.equals(values.size(), that.values.size());
        }

        /**
         * Returns a hash of the name, inferred type, and current value count.
         * Adding observations can change this result, so the descriptor must
         * not be mutated while used as a hash-based collection key.
         */
        @Override
        public int hashCode() {
            return Objects.hash(serializationName, valueType, values.size());
        }
    }
}
