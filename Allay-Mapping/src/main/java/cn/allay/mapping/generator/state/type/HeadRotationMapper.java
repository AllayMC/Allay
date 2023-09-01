package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "rotation", blockRegex = {".*_(head|skull)", "^((?!wall).)*$", "^((?!piston).)*$"})
public class HeadRotationMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("facing_direction", 1); // Handled elsewhere (see getRemapBlock)
    }
}
