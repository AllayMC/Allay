package org.allaymc.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code BlockStateDataProcessor} converts unpacked block-state metadata into
 * the runtime resource schema. Most physical and rendering properties are
 * copied, while translation keys receive the Minecraft namespace and shape
 * coordinates are clamped only at their lower bound. Conversion mutates the
 * input shape arrays and shares collision arrays with the resulting metadata
 * rather than creating an independent deep copy.
 *
 * <p>
 * States without collision boxes use their outline array as the resulting
 * shape. Other states use an enclosing box accumulated from collision boxes and
 * the origin. Because the accumulator starts at zero, positive minimum
 * coordinates do not produce a tight bounding box; the generated shape extends
 * back to the origin along those axes.
 *
 * @author daoge_cmd
 */
public class BlockStateDataProcessor {

    /**
     * Reads unpacked block states and replaces the processed runtime JSON.
     * {@code args} is ignored, and paths are relative to the repository root.
     * Each entry is converted before output is written; malformed entries or
     * missing required shape arrays abort processing instead of being skipped.
     */
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

    /**
     * {@code RawBlockStateData} represents the unpacked physical, rendering,
     * and identity metadata for one state.
     */
    public static class RawBlockStateData {
        /**
         * Block burn-away odds.
         */
        public int burnOdds = 0;
        /**
         * Liquid-source containment flag.
         */
        public boolean canContainLiquidSource;
        /**
         * Liquid-contact reaction name.
         */
        public String liquidReactionOnTouch;
        /**
         * {@code collisionShape} holds raw collision boxes. {@code fromRaw}
         * clamps their coordinates in place and shares the same arrays with the
         * processed result.
         */
        public float[][] collisionShape;
        /**
         * {@code liquidClipShape} holds liquid-clipping bounds from the
         * unpacked input. {@code fromRaw} omits these bounds from processed
         * metadata.
         */
        public float[] liquidClipShape;
        /**
         * {@code outlineShape} holds outline bounds. When
         * {@code collisionShape} is empty, {@code fromRaw} clamps and retains
         * this same mutable array.
         */
        public float[] outlineShape;
        /**
         * {@code uiShape} holds interface-display bounds from the unpacked
         * input. {@code fromRaw} omits these bounds from processed metadata.
         */
        public float[] uiShape;
        /**
         * {@code visualShape} holds visual bounds from the unpacked input.
         * {@code fromRaw} omits these bounds from processed metadata.
         */
        public float[] visualShape;
        /**
         * Block breaking hardness.
         */
        public float hardness;
        /**
         * Block explosion resistance.
         */
        public float explosionResistance;
        /**
         * Fire spread susceptibility.
         */
        public int flameOdds;
        /**
         * Surface friction coefficient.
         */
        public float friction;
        /**
         * Block light attenuation.
         */
        public int lightDampening;
        /**
         * Emitted light level.
         */
        public int lightEmission;
        /**
         * Base map-rendering color.
         */
        public String mapColor;
        /**
         * Map-rendering tint method.
         */
        public String tintMethod;
        /**
         * Reported block thickness.
         */
        public float thickness;
        /**
         * Correct-tool drop requirement.
         */
        public boolean requiresCorrectToolForDrops;
        /**
         * Solid block classification.
         */
        public boolean isSolid;
        /**
         * Map-rendering translucency value.
         */
        public float translucency;

        /**
         * Namespaced block identifier.
         */
        public String name;
        /**
         * Original block translation key.
         */
        public String translationKey;
        /**
         * Serialized state hash.
         */
        public long blockStateHash;
    }

    /**
     * {@code NewBlockStateData} holds the processed state metadata serialized
     * for the runtime loader.
     */
    public static class NewBlockStateData {
        /**
         * Block burn-away odds.
         */
        public int burnOdds = 0;
        /**
         * Liquid-source containment flag.
         */
        public boolean canContainLiquidSource;
        /**
         * Liquid-contact reaction name.
         */
        public String liquidReactionOnTouch;
        /**
         * {@code collisionShape} holds clamped collision boxes shared with the
         * raw metadata, so later changes through either reference affect these
         * coordinates.
         */
        public float[][] collisionShape;
        /**
         * {@code shape} holds the shared clamped
         * {@code RawBlockStateData.outlineShape} when collision boxes are
         * absent. Otherwise, {@code fromRaw} allocates an origin-inclusive
         * bound from {@code collisionShape}.
         */
        public float[] shape;
        /**
         * Block breaking hardness.
         */
        public float hardness;
        /**
         * Block explosion resistance.
         */
        public float explosionResistance;
        /**
         * Fire spread susceptibility.
         */
        public int flameOdds;
        /**
         * Surface friction coefficient.
         */
        public float friction;
        /**
         * Block light attenuation.
         */
        public int lightDampening;
        /**
         * Emitted light level.
         */
        public int lightEmission;
        /**
         * Base map-rendering color.
         */
        public String mapColor;
        /**
         * Map-rendering tint method.
         */
        public String tintMethod;
        /**
         * Reported block thickness.
         */
        public float thickness;
        /**
         * Correct-tool drop requirement.
         */
        public boolean requiresCorrectToolForDrops;
        /**
         * Solid block classification.
         */
        public boolean isSolid;
        /**
         * Map-rendering translucency value.
         */
        public float translucency;

        /**
         * Namespaced block identifier.
         */
        public String name;
        /**
         * Minecraft-prefixed block translation key.
         */
        public String translationKey;
        /**
         * Serialized state hash.
         */
        public long blockStateHash;

        /**
         * Converts one raw state while retaining its scalar metadata and hash.
         * The collision arrays are clamped in place and shared with the result;
         * callers retaining the input therefore observe those coordinate
         * changes. Translation keys receive a namespace prefix without checking
         * for an existing prefix.
         *
         * <p>
         * Empty collision data selects the raw outline array, also clamped in
         * place. Otherwise a new origin-inclusive bounding box is accumulated
         * from the collision arrays. Required arrays must be nonnull, and boxes
         * passed to the union calculation must contain at least six
         * coordinates.
         */
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

            // Collision-free states still need a shape for selection and inside
            // checks, so preserve the outline when no collision boxes can supply bounds.
            if (raw.collisionShape.length == 0) {
                data.shape = clampShape(raw.outlineShape);
            } else {
                data.shape = unionShapes(data.collisionShape);
            }

            return data;
        }

        /**
         * Clamps every contained coordinate array in place and returns the
         * original outer array. Neither array level is copied, so callers
         * sharing these boxes observe the same changes; values above one are
         * retained.
         */
        public static float[][] clampShape(float[][] shapes) {
            for (var shape : shapes) {
                clampShape(shape);
            }

            return shapes;
        }

        /**
         * Raises negative coordinates to zero in the supplied array. The same
         * array is returned, with no upper limit applied, allowing boxes that
         * extend above one block to retain their height.
         */
        private static float[] clampShape(float[] shape) {
            for (var i = 0; i < shape.length; i++) {
                // Only clamp the lower bound. Values > 1 are allowed because some blocks
                // (fences, walls, fence gates, border_block) have collision shapes with maxY = 1.5
                shape[i] = Math.max(0, shape[i]);
            }

            return shape;
        }

        /**
         * Returns a new six-coordinate box enclosing the supplied boxes and the
         * origin. Positions zero through two are minima and positions three
         * through five are maxima; each input must contain those six values.
         *
         * <p>
         * The accumulator starts at zero rather than the first box, so positive
         * minima remain zero and negative maxima cannot move its upper bound
         * below the origin. Empty input returns an all-zero box. Input arrays
         * are read without being modified by this calculation.
         */
        public static float[] unionShapes(float[][] shapes) {
            // Starting at zero includes the origin even when every supplied box
            // begins at a positive offset, so these bounds are not necessarily tight.
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
