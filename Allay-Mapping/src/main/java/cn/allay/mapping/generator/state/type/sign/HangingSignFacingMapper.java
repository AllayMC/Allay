package cn.allay.mapping.generator.state.type.sign;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "rotation", blockRegex = {".*hanging_sign.?$", "^((?!wall).)*$"})
public class HangingSignFacingMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        // Seems like 'facing_direction' is used if 'attached' is false, in which the sign points
        // in a cardinal direction (rather 16 different possible rotation values)

        int facing = switch (Integer.parseUnsignedInt(value)) {
            case 0 -> 3; // South rotation to south facing
            case 4 -> 4; // West rotation to west facing
            case 8 -> 2; // North rotation to north facing
            case 12 -> 5; // East rotation to east facing
            default -> 2;
            // Any other value is a rotation that does NOT point in a cardinal direction. The mapped value theoretically
            // shouldn't matter because if the rotation is not cardinal, the sign must have 'attached' true, in which
            // 'ground_sign_direction' is used instead of 'facing_direction'
        };

        return Pair.of("facing_direction", facing);
    }
}
