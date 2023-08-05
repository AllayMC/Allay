package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBd;

public class TestEntity implements HasAABB, HasLongId {

   private final double x;

   private final double y;

   private final double z;

   private final double width;

   private final double height;

    private final double length;

   private final int id;

   public TestEntity(int id, double x, double y, double width, double height) {
        this(id, x, y, 0, width, height, 0);
   }

   public TestEntity(int id, double x, double y, double z, double width, double height, double length) {
      this.id = id;
      this.x = x;
      this.y = y;
      this.z = z;
      this.width = width;
      this.height = height;
      this.length = length;
   }

   @Override
   public AABBd copyAABBTo(AABBd dest) {
      if (dest == null) {
         dest = new AABBd();
      }
      dest.setMin(x, y, z);
      dest.setMax(x + width, y + height, z + length);
      return dest;
   }

   @Override
   public long getUniqueId() {
      return id;
   }
}
