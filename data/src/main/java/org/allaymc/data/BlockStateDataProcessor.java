package org.allaymc.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockStateDataProcessor {

    @SneakyThrows
    public static void main(String[] args) {
        var gson = new GsonBuilder().create();
        List<NewBlockStateData> out = new ArrayList<>();

        try (var reader = Files.newBufferedReader(Path.of("data/resources/unpacked/block_states_raw.json"))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var obj = entry.getAsJsonObject();
                var data = gson.fromJson(obj.toString(), RawBlockStateData.class);
                out.add(NewBlockStateData.fromRaw(data));
            });
        }

        Files.writeString(Path.of("data/resources/block_states.json"), gson.toJson(out));
    }

    public static class RawBlockStateData {
        public int burnOdds = 0;
        public boolean canContainLiquid = false;
        public String liquidReactionOnTouch;
        public float[] collisionShape;
        public float[] liquidClipShape;
        public float[] outlineShape;
        public float[] uiShape;
        public float[] visualShape;
        public float hardness;
        public float explosionResistance;
        public int flameOdds;
        public float friction;
        public int lightDampening;
        public int lightEmission;
        public String mapColor;
        public float thickness;
        public boolean requiresCorrectToolForDrops;
        public boolean isSolid;
        public float translucency;

        public String name;
        public long blockStateHash;
    }

    public static class NewBlockStateData {
        public int burnOdds = 0;
        public boolean canContainLiquid = false;
        public String liquidReactionOnTouch;
        public float[] collisionShape;
        public float[] shape;
        public float hardness;
        public float explosionResistance;
        public int flameOdds;
        public float friction;
        public int lightDampening;
        public int lightEmission;
        public String mapColor;
        public float thickness;
        public boolean requiresCorrectToolForDrops;
        public boolean isSolid;
        public float translucency;

        public String name;
        public long blockStateHash;

        public static NewBlockStateData fromRaw(RawBlockStateData raw) {
            var data = new NewBlockStateData();
            data.burnOdds = raw.burnOdds;
            data.canContainLiquid = raw.canContainLiquid;
            data.liquidReactionOnTouch = raw.liquidReactionOnTouch;
            data.collisionShape = clampShape(raw.collisionShape);
            data.hardness = raw.hardness;
            data.explosionResistance = raw.explosionResistance;
            data.flameOdds = raw.flameOdds;
            data.friction = raw.friction;
            data.lightDampening = raw.lightDampening;
            data.lightEmission = raw.lightEmission;
            data.mapColor = raw.mapColor;
            data.thickness = raw.thickness;
            data.requiresCorrectToolForDrops = raw.requiresCorrectToolForDrops;
            data.isSolid = raw.isSolid;
            data.translucency = raw.translucency;

            data.name = raw.name;
            data.blockStateHash = raw.blockStateHash;

            if (isEmptyShape(raw.collisionShape)) {
                data.shape = clampShape(raw.outlineShape);
            } else {
                data.shape = data.collisionShape;
            }

            return data;
        }

        public static boolean isEmptyShape(float[] shape) {
            for (var f : shape) {
                if (f != 0) {
                    return false;
                }
            }
            return true;
        }

        public static float[] clampShape(float[] shape) {
            for (var i = 0; i < shape.length; i++) {
                // Clamp the value between 0 and 1
                // because bigger than 1 is not allowed
                // in allay
                shape[i] = Math.max(0, Math.min(1, shape[i]));
            }
            return shape;
        }
    }
}