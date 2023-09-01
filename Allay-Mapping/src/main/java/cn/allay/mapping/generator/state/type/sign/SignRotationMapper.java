package cn.allay.mapping.generator.state.type.sign;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


/**
 * Covers "ground_sign_direction" for both standing signs, and hanging signs (non-wall variants)
 */
@StateRemapper(value = "rotation", blockRegex = {".*sign.?$", "^((?!wall).)*$"})
public class SignRotationMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("ground_sign_direction", Integer.parseInt(value));
    }
}
