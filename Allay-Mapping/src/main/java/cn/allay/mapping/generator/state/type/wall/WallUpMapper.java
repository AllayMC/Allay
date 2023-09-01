package cn.allay.mapping.generator.state.type.wall;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "up", blockRegex = ".*_wall.?$")
public class WallUpMapper extends StateMapper<Boolean> {

    @Override
    public Pair<String, Boolean> translateState(String fullIdentifier, String value) {
        return Pair.of("wall_post_bit", Boolean.parseBoolean(value));
    }
}