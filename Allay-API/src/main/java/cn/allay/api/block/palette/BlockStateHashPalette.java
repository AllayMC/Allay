package cn.allay.api.block.palette;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/5/29
 *
 * @author Cool_Loong
 */
public interface BlockStateHashPalette extends MappedRegistry<Integer, BlockState, Map<Integer, BlockState>> {
    ApiInstanceHolder<BlockStateHashPalette> REGISTRY = ApiInstanceHolder.of();

    static BlockStateHashPalette getRegistry() {
        return REGISTRY.get();
    }

    BlockState register(Integer hash, BlockState blockState, boolean assignPaletteIndex);

    void rebuildPaletteIndexList();

    class BlockStateHashCollisionException extends RuntimeException {
        public BlockStateHashCollisionException() {
        }

        public BlockStateHashCollisionException(String message) {
            super(message);
        }

        public BlockStateHashCollisionException(String message, Throwable cause) {
            super(message, cause);
        }

        public BlockStateHashCollisionException(Throwable cause) {
            super(cause);
        }

        public BlockStateHashCollisionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
