package cn.allay.api.datastruct.aabbtree;

import cn.allay.api.datastruct.aabbtree.Boundable;
import cn.allay.api.datastruct.aabbtree.Identifiable;
import org.joml.primitives.AABBf;

public class TestEntity implements Boundable, Identifiable {

   private final float x;

   private final float y;

   private final float z;

   private final float width;

   private final float height;

    private final float length;

   private final int id;

   public TestEntity(int id, float x, float y, float width, float height) {
        this(id, x, y, 0, width, height, 0);
   }

   public TestEntity(int id, float x, float y, float z, float width, float height, float length) {
      this.id = id;
      this.x = x;
      this.y = y;
      this.z = z;
      this.width = width;
      this.height = height;
      this.length = length;
   }

   @Override
   public AABBf getAABB(AABBf dest) {
      if (dest == null) {
         dest = new AABBf();
      }
      dest.setMin(x, y, z);
      dest.setMax(x + width, y + height, z + length);
      return dest;
   }

   @Override
   public long getID() {
      return id;
   }
}
