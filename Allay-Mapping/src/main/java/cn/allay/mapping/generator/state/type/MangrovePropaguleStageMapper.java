package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "stage", blockRegex = ".*mangrove_propagule$")
public class MangrovePropaguleStageMapper extends StateMapper<Integer> {
    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("propagule_stage", Integer.parseInt(value));
    }
}
