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

        Utils.writeFileWithCRLF(Path.of("data/resources/block_states.json"), gson.toJson(out));
    }

    public static class RawBlockStateData {
        public int burnOdds = 0;
        public boolean canContainLiquidSource;
        public String liquidReactionOnTouch;
        public float[][] collisionShape;
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
        public String tintMethod;
        public float thickness;
        public boolean requiresCorrectToolForDrops;
        public boolean isSolid;
        public float translucency;

        public String name;
        public String translationKey;
        public long blockStateHash;
    }

    public static class NewBlockStateData {
        public int burnOdds = 0;
        public boolean canContainLiquidSource;
        public String liquidReactionOnTouch;
        public float[][] collisionShape;
        public float[] shape;
        public float hardness;
        public float explosionResistance;
        public int flameOdds;
        public float friction;
        public int lightDampening;
        public int lightEmission;
        public String mapColor;
        public String tintMethod;
        public float thickness;
        public boolean requiresCorrectToolForDrops;
        public boolean isSolid;
        public float translucency;

        public String name;
        public String translationKey;
        public long blockStateHash;

        public static NewBlockStateData fromRaw(RawBlockStateData raw) {
            var data = new NewBlockStateData();
            data.burnOdds = raw.burnOdds;
            data.canContainLiquidSource = raw.canContainLiquidSource;
            data.liquidReactionOnTouch = raw.liquidReactionOnTouch;
            data.collisionShape = clampShape(raw.collisionShape);
            data.hardness = raw.hardness;
            data.explosionResistance = raw.explosionResistance;
            data.flameOdds = raw.flameOdds;
            data.friction = raw.friction;
            data.lightDampening = raw.lightDampening;
            data.lightEmission = raw.lightEmission;
            data.mapColor = raw.mapColor;
            data.tintMethod = raw.tintMethod;
            data.thickness = raw.thickness;
            data.requiresCorrectToolForDrops = raw.requiresCorrectToolForDrops;
            data.isSolid = raw.isSolid;
            data.translucency = raw.translucency;

            data.name = raw.name;
            data.translationKey = "minecraft:" + raw.translationKey;
            data.blockStateHash = raw.blockStateHash;

            if (raw.collisionShape.length == 0) {
                data.shape = clampShape(raw.outlineShape);
            } else {
                data.shape = unionShapes(data.collisionShape);
            }

            return data;
        }

        public static float[][] clampShape(float[][] shapes) {
            for (var shape : shapes) {
                clampShape(shape);
            }

            return shapes;
        }

        private static float[] clampShape(float[] shape) {
            for (var i = 0; i < shape.length; i++) {
                // Only clamp the lower bound. Values > 1 are allowed because some blocks
                // (fences, walls, fence gates, border_block) have collision shapes with maxY = 1.5
                shape[i] = Math.max(0, shape[i]);
            }

            return shape;
        }

        public static float[] unionShapes(float[][] shapes) {
            var unionShape = new float[6];
            for (var shape : shapes) {
                for (var i = 0; i < 3; i++) {
                    unionShape[i] = Math.min(unionShape[i], shape[i]);
                }
                for (var i = 3; i < 6; i++) {
                    unionShape[i] = Math.max(unionShape[i], shape[i]);
                }
            }

            return unionShape;
        }
    }
}