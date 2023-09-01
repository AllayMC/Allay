package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "slot_0_occupied", blockRegex = ".*chiseled_bookshelf$")
public class ChiseledBookshelfBooksMapper extends StateMapper<Integer> {
    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        // bedrock stores the book occupancy list as a bitmask.
        int mask = 0;
        for (int i = 0; i < 6; i++) {
            String property = "slot_" + i + "_occupied";
            if ("true".equals(getStateValue(fullIdentifier, property))) {
                mask |= (1 << i);
            }
        }

        return Pair.of("books_stored", mask);
    }
}
