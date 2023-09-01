package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "can_summon", blockRegex = ".*sculk_shrieker$")
public class SculkShriekerCanSummonMapper extends StateMapper<Boolean> {
    @Override
    public Pair<String, Boolean> translateState(String fullIdentifier, String value) {
        return Pair.of("can_summon", Boolean.parseBoolean(value));
    }
}
