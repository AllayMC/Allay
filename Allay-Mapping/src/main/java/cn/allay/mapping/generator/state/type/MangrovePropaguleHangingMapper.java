package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "hanging", blockRegex = ".*mangrove_propagule$")
public class MangrovePropaguleHangingMapper extends StateMapper<Boolean> {
    @Override
    public Pair<String, Boolean> translateState(String fullIdentifier, String value) {
        return Pair.of("hanging", Boolean.parseBoolean(value));
    }
}
