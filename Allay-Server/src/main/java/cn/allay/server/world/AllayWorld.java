package cn.allay.server.world;

import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;
import cn.allay.api.world.Difficulty;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.World;
import cn.allay.api.world.storage.WorldStorage;
import lombok.Getter;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayWorld implements World {

    @Getter
    private final WorldStorage worldStorage;
    @Getter
    private final String name;
    @Getter
    private final DimensionInfo dimensionInfo;
    private Loc<Float> spawnLocation;
    private Difficulty difficulty;

    public AllayWorld(WorldStorage worldStorage, String name, DimensionInfo dimensionInfo) {
        this.worldStorage = worldStorage;
        this.name = name;
        this.dimensionInfo = dimensionInfo;
    }

    @Override
    public FixedLoc<Float> getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public void setSpawnLocation(Loc<Float> newSpawn) {
        this.spawnLocation = newSpawn;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    private static class WorldBuilder {
        private String name = "world";
        private DimensionInfo dimensionInfo = DimensionInfo.OVERWORLD;
        private Loc<Float> spawnLocation = Loc.of(0f, 60f, 0f, null);
        private Difficulty difficulty = Difficulty.EASY;
        private WorldStorage worldStorage;

        public WorldBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public WorldBuilder setDimensionInfo(DimensionInfo dimensionInfo) {
            this.dimensionInfo = dimensionInfo;
            return this;
        }

        public WorldBuilder setSpawnLocation(Loc<Float> spawnLocation) {
            this.spawnLocation = spawnLocation;
            return this;
        }

        public WorldBuilder setDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public WorldBuilder setWorldStorage(WorldStorage worldStorage) {
            this.worldStorage = worldStorage;
            return this;
        }

        public World build() {
            var world = new AllayWorld(worldStorage, name, dimensionInfo);
            world.spawnLocation = spawnLocation;
            world.difficulty = difficulty;
            return world;
        }
    }
}
