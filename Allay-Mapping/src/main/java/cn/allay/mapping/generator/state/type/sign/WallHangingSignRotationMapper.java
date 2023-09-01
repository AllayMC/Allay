package cn.allay.mapping.generator.state.type.sign;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


/**
 * Covers ground_sign_direction for wall_hanging signs. Theoretically, this state is not used for wall_hanging_signs,
 * which face a cardinal direction (and use the "facing_direction" state), and have "hanging" false
 */
@StateRemapper(value = "facing", blockRegex = ".*wall_hanging_sign.?$")
public class WallHangingSignRotationMapper extends StateMapper<Integer> {
    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        int rotationDirection = switch (value) {
            case "south" -> 0;
            case "west" -> 4;
            case "north" -> 8;
            case "east" -> 12;
            default -> throw new IllegalArgumentException("Got " + value + " instead of a cardinal direction");
        };

        return Pair.of("ground_sign_direction", rotationDirection);
    }
}
