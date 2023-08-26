package cn.allay.server.world.storage.rocksdb;

import cn.allay.api.world.chunk.UnsafeChunk;
import cn.allay.server.world.chunk.AllayUnsafeChunk;
import com.google.common.base.Preconditions;
import lombok.Getter;
import org.rocksdb.RocksDB;
import org.rocksdb.WriteBatch;

/**
 * Allay Project 8/23/2023
 *
 * @author Cool_Loong
 */
public interface RocksdbChunkSerializer {
    void serialize(WriteBatch writeBatch, UnsafeChunk chunk);

    void deserialize(RocksDB db, AllayUnsafeChunk.Builder builder);

    enum Provider {
        V0(RocksdbChunkSerializerV1.INSTANCE);


        private @Getter
        final RocksdbChunkSerializer serializer;

        Provider(RocksdbChunkSerializer serializer) {
            this.serializer = serializer;
        }

        public static RocksdbChunkSerializer of(int version) {
            Preconditions.checkArgument(version >= 0 && version < values().length);
            return values()[version].getSerializer();
        }
    }
}
