package org.allaymc.api.scoreboard.storage;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.utils.config.Config;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Allay Project 2024/2/28
 *
 * @author daoge_cmd
 */
@Slf4j
public class JsonScoreboardStorage implements ScoreboardStorage {

    protected Path filePath;
    protected Config json;

    public JsonScoreboardStorage(Path path) {
        this.filePath = path;
        try {
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
            json = new Config(this.filePath.toFile(), Config.JSON);
        } catch (IOException e) {
            log.error("Failed to create file", e);
        }
    }

    @Override
    public void save(Scoreboard scoreboard) {
        json.set("scoreboard." + scoreboard.getObjectiveName(), serializeToMap(scoreboard));
        json.save();
    }

    @Override
    public void save(Collection<Scoreboard> scoreboards) {
        scoreboards.forEach(this::save);
    }

    @Override
    public void saveDisplay(Map<DisplaySlot, Scoreboard> display) {
        for (var entry : display.entrySet()) {
            json.set("display." + entry.getKey().name(), entry.getValue() != null ? entry.getValue().getObjectiveName() : null);
        }
        json.save();
    }

    @Override
    public Map<String, Scoreboard> readAll() {
        Map<String, Object> scoreboards = (Map<String, Object>) json.get("scoreboard");
        Map<String, Scoreboard> result = new HashMap<>();
        if (scoreboards == null) return result;
        for (Map.Entry<String, Object> entry : scoreboards.entrySet())
            result.put(entry.getKey(), deserializeFromMap((Map<String, Object>) entry.getValue()));
        return result;
    }

    @Override
    public Scoreboard read(String name) {
        return json.get("scoreboard." + name) == null ? null : deserializeFromMap((Map<String, Object>) json.get("scoreboard." + name));
    }

    @Override
    public Map<DisplaySlot, String> readDisplay() {
        Map<DisplaySlot, String> result = new HashMap<>();
        if (json.get("display") == null) return result;
        for (Map.Entry<String, String> e : ((Map<String, String>) json.get("display")).entrySet()) {
            DisplaySlot slot = DisplaySlot.valueOf(e.getKey());
            result.put(slot, e.getValue());
        }
        return result;
    }

    @Override
    public void remove(String name) {
        json.remove("scoreboard." + name);
        json.save();
    }

    @Override
    public void removeAll() {
        json.remove("scoreboard");
        json.save();
    }

    @Override
    public boolean contain(String name) {
        return json.exists("scoreboard." + name);
    }

    private Map<String, Object> serializeToMap(Scoreboard scoreboard) {
        Map<String, Object> map = new HashMap<>();
        map.put("objectiveName", scoreboard.getObjectiveName());
        map.put("displayName", scoreboard.getDisplayName());
        map.put("criteriaName", scoreboard.getCriteriaName());
        map.put("sortOrder", scoreboard.getSortOrder().name());

        List<Map<String, Object>> lines = new ArrayList<>();
        for (var line : scoreboard.getLines().values()) {
            Map<String, Object> data = new HashMap<>();
            data.put("score", line.getScore());
            data.put("scorerType", line.getScorer().getScorerType().name());
            data.put("name", switch (line.getScorer()) {
                case PlayerScorer player -> player.getUuid().toString();
                case EntityScorer entity -> "" + entity.getUniqueId();
                case FakeScorer fake -> fake.getFakeName();
            });
            lines.add(data);
        }

        map.put("lines", lines);
        return map;
    }

    private Scoreboard deserializeFromMap(Map<String, Object> map) {
        var objectiveName = map.get("objectiveName").toString();
        var displayName = map.get("displayName").toString();
        var criteriaName = map.get("criteriaName").toString();
        var sortOrder = SortOrder.valueOf(map.get("sortOrder").toString());

        var scoreboard = new Scoreboard(objectiveName, displayName, criteriaName, sortOrder);
        for (var line : (List<Map<String, Object>>) map.get("lines")) {
            var score = ((Double) line.get("score")).intValue();
            var name = (String) line.get("name");
            var scorer = switch (ScoreInfo.ScorerType.valueOf(line.get("scorerType").toString())) {
                case PLAYER -> new PlayerScorer(UUID.fromString(name));
                case ENTITY -> new EntityScorer(Long.parseLong(name));
                case FAKE -> new FakeScorer(name);
                default -> throw new IllegalStateException("Unexpected value: " + line.get("scorerType"));
            };
            scoreboard.addLine(new ScoreboardLine(scoreboard, scorer, score));
        }

        return scoreboard;
    }
}
