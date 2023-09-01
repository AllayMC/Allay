package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


/**
 * calibrated_sculk_sensor and sculk_sensor
 */
@StateRemapper(value = "sculk_sensor_phase", blockRegex = ".*sculk_sensor")
public class SculkSensorPhaseMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("sculk_sensor_phase", switch (value) {
            case "inactive" -> 0;
            case "active" -> 1;
            case "cooldown" -> 2;
            default -> throw new IllegalArgumentException("Unknown sculk sensor phase: " + value);
        });
    }
}
