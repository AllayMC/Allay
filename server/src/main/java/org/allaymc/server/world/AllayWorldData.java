package org.allaymc.server.world;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.world.DifficultyChangeEvent;
import org.allaymc.api.eventbus.event.world.GameRuleChangeEvent;
import org.allaymc.api.eventbus.event.world.SpawnPointChangeEvent;
import org.allaymc.api.eventbus.event.world.TimeChangeEvent;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3i;
import org.joml.Vector3ic;

@Getter
@Builder
public final class AllayWorldData implements WorldData {

    private World world;

    @Builder.Default
    private Difficulty difficulty = Server.SETTINGS.genericSettings().defaultDifficulty();
    @Builder.Default
    private GameType gameType = Server.SETTINGS.genericSettings().defaultGameType();
    @Setter
    @Builder.Default
    private String displayName = DEFAULT_WORLD_DISPLAY_NAME;
    @Builder.Default
    private Vector3ic spawnPoint = new Vector3i(0, 64, 0);
    @Builder.Default
    @Setter
    private long totalTime = 0L;
    @Builder.Default
    private int timeOfDay = TIME_SUNRISE;
    @Builder.Default
    private AllayGameRules gameRules = new AllayGameRules();
    @Getter
    @Builder.Default
    private long worldStartCount = 0;

    public void setWorld(World world) {
        this.world = world;
        this.gameRules.setWorld(world);
    }

    @Override
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        var event = new DifficultyChangeEvent(this.world, this.difficulty, difficulty);
        if (event.call()) {
            this.difficulty = event.getNewDifficulty();
        }
    }

    @Override
    public void setTimeOfDay(int timeOfDay) {
        timeOfDay = rollbackTimeOfDay(timeOfDay);

        var event = new TimeChangeEvent(this.world, this.timeOfDay, timeOfDay);
        if (!event.call()) {
            return;
        }

        this.timeOfDay = event.getNewTime();
        sendTimeOfDay(this.world.getPlayers());
    }

    private int rollbackTimeOfDay(int time) {
        if (time < TIME_DAY || time > TIME_FULL) {
            return TIME_DAY;
        }
        return time;
    }

    @Override
    public void setGameRuleValue(GameRule gameRule, Object value) {
        var event = new GameRuleChangeEvent(this.world, gameRule, getGameRuleValue(gameRule), value);
        if (event.call()) {
            this.gameRules.set(gameRule, event.getNewValue());
        }
    }

    @Override
    public <V> V getGameRuleValue(GameRule gameRule) {
        return gameRules.get(gameRule);
    }

    @Override
    public void setSpawnPoint(Vector3ic spawnPoint) {
        var event = new SpawnPointChangeEvent(this.world, this.spawnPoint, spawnPoint);
        if (event.call()) {
            this.spawnPoint = event.getNewPos();
        }
    }

    public void increaseWorldStartCount() {
        this.worldStartCount++;
    }
}
