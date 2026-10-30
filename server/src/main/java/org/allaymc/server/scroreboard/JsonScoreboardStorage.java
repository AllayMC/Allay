package org.allaymc.server.scroreboard;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.ScoreboardStorage;
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
 * {@code JsonScoreboardStorage} persists objective definitions, scorer identities, scores, and
 * display references in a JSON configuration file. Scorer variants use a UUID string, decimal
 * entity identifier, or fake name so reloading reconstructs the appropriate scorer category. Client
 * line identifiers are newly allocated on reload.
 *
 * <p>
 * Each save mutates the configuration before writing the file, and collection saves write once per
 * objective. Dotted path updates retain section-valued parents but replace raw map parents, which
 * can discard sibling entries. Nested removal also requires a section-valued parent. Objective
 * removal leaves display references intact, and write failure restores neither memory nor disk
 * changes. Initialization logs I/O failures and can leave {@code json} unset, causing later
 * operations to fail.
 *
 * @author daoge_cmd
 */
@Slf4j
public class JsonScoreboardStorage implements ScoreboardStorage {

    /**
     * Backing JSON configuration file path.
     */
    protected Path filePath;
    /**
     * {@code json} holds the mutable loaded configuration after successful initialization. A caught
     * file-creation failure can leave this reference {@code null}.
     */
    protected Config json;

    /**
     * Creates missing parent directories and the backing file before loading its JSON
     * configuration. The supplied path must have a parent; I/O failures are logged and suppressed,
     * potentially leaving {@code json} unset.
     */
    public JsonScoreboardStorage(Path path) {
        this.filePath = path;
        try {
            if (!Files.exists(this.filePath.getParent())) {
                Files.createDirectories(this.filePath.getParent());
            }
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
            json = new Config(this.filePath.toFile(), Config.JSON);
        } catch (IOException e) {
            // Initialization reports file-creation failure without a replacement
            // configuration; later storage calls still depend on json being initialized.
            log.error("Failed to create file", e);
        }
    }

    /**
     * Serializes the objective into a dotted configuration path before synchronously saving the
     * file. Section-valued parents retain sibling objectives, but a raw map parent can be replaced;
     * write failure leaves the resulting in-memory mutation installed.
     */
    @Override
    public void save(Scoreboard scoreboard) {
        json.set("scoreboard." + scoreboard.getObjectiveName(), serializeToMap(scoreboard));
        json.save();
    }

    /**
     * Saves each supplied objective separately through the single-objective operation. Earlier
     * entries may already be written if a later save fails, and objectives absent from the
     * collection are not removed.
     */
    @Override
    public void save(Collection<Scoreboard> scoreboards) {
        scoreboards.forEach(this::save);
    }

    /**
     * Writes supplied slot names and objective names through dotted paths, then saves the
     * configuration. Section-valued parents retain omitted slots, while raw map parents can be
     * replaced; {@code null} objectives become stored {@code null} values.
     */
    @Override
    public void saveDisplay(Map<DisplaySlot, Scoreboard> display) {
        for (var entry : display.entrySet()) {
            json.set("display." + entry.getKey().name(), entry.getValue() != null ? entry.getValue().getObjectiveName() : null);
        }
        json.save();
    }

    /**
     * Reconstructs all stored objectives into a new mutable map keyed by stored names. A missing
     * objective section returns an empty map; malformed entries propagate failures rather than
     * skipping only those objectives.
     */
    @Override
    public Map<String, Scoreboard> readAll() {
        Map<String, Object> scoreboards = (Map<String, Object>) json.get("scoreboard");
        Map<String, Scoreboard> result = new HashMap<>();
        if (scoreboards == null) return result;
        for (Map.Entry<String, Object> entry : scoreboards.entrySet())
            result.put(entry.getKey(), deserializeFromMap((Map<String, Object>) entry.getValue()));
        return result;
    }

    /**
     * Reconstructs the named stored objective, or returns {@code null} when its configuration entry
     * is absent. Malformed fields propagate conversion failures, and newly constructed lines
     * receive fresh client identifiers.
     */
    @Override
    public Scoreboard read(String name) {
        return json.get("scoreboard." + name) == null ? null : deserializeFromMap((Map<String, Object>) json.get("scoreboard." + name));
    }

    /**
     * Reads recorded slots into a new mutable map of enum slots to objective names. A missing
     * section returns an empty map, while invalid enum identifiers or incompatible stored shapes
     * propagate failures.
     */
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

    /**
     * Attempts dotted-path objective removal before saving the configuration file. Removal descends
     * only through section-valued parents, so a raw map parent can leave the objective present;
     * display references remain and write failure does not restore mutations.
     */
    @Override
    public void remove(String name) {
        json.remove("scoreboard." + name);
        json.save();
    }

    /**
     * Removes the entire objective section from the in-memory configuration and then saves the
     * file. Stored display references remain, and a write failure does not restore the removed
     * objective data.
     */
    @Override
    public void removeAll() {
        json.remove("scoreboard");
        json.save();
    }

    /**
     * Returns whether the current configuration contains the named objective path.
     */
    @Override
    public boolean contain(String name) {
        return json.exists("scoreboard." + name);
    }

    /**
     * Serializes objective metadata and scorer lines into a fresh map without viewer subscriptions.
     * The map retains scalar identity values rather than client line identifiers.
     *
     * <p>
     * Player scorers use complete UUID strings, entity scorers use decimal unique identifiers, and
     * fake scorers use their text identity. The stored category selects the inverse conversion on
     * reload, preventing online display names from replacing persistent player identity. The live
     * line map is traversed without a combined snapshot, so concurrent score or membership changes
     * can affect the serialized result.
     */
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
            // Persist the scorer's identity representation rather than its current
            // display name, so loading can reconstruct the same scorer category and key.
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

    /**
     * Reconstructs an objective and its scorer lines from the expected JSON-derived map shape.
     * Numeric scores are expected as {@code Double} values and converted to integers, while
     * category names select UUID, entity-long, or fake-name parsing.
     *
     * <p>
     * Each line is created with a fresh client identifier and added through the scoreboard API. If
     * an equal objective name is already globally managed, those additions can dispatch cancellable
     * events and omit rejected lines. Malformed fields or unsupported categories fail without
     * returning the partially built objective.
     */
    private Scoreboard deserializeFromMap(Map<String, Object> map) {
        var objectiveName = map.get("objectiveName").toString();
        var displayName = map.get("displayName").toString();
        var criteriaName = map.get("criteriaName").toString();
        var sortOrder = SortOrder.valueOf(map.get("sortOrder").toString());

        var scoreboard = new Scoreboard(objectiveName, displayName, criteriaName, sortOrder);
        for (var line : (List<Map<String, Object>>) map.get("lines")) {
            // JSON numeric values arrive as doubles; convert the stored score
            // back to the integer representation expected by scoreboard line updates.
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
