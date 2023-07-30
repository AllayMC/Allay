package cn.allay.server.datastruct.aabbtree;

import cn.allay.api.datastruct.aabbtree.Boundable;
import cn.allay.api.datastruct.aabbtree.Identifiable;
import org.joml.primitives.AABBf;

public class TestEntity implements Boundable, Identifiable {

   private final float x;

   private final float y;

   private final float width;

   private final float height;

   private final int id;

   TestEntity(int id, float x, float y, float width, float height) {
      this.id = id;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   @Override
   public AABBf getAABB(AABBf dest) {
      if (dest == null) {
         dest = new AABBf();
      }
      dest.setMin(x, y, 0.0f);
      dest.setMax(x + width, y + height, 0.0f);
      return dest;
   }

   @Override
   public long getID() {
      return id;
   }
}
