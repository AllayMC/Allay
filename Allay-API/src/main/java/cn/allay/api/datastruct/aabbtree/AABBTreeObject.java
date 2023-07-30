package cn.allay.api.datastruct.aabbtree;

import java.util.Objects;

final class AABBTreeObject<E extends Identifiable> {
   private final E data;

   private AABBTreeObject(E data) {
      this.data = data;
   }

   static <E extends Identifiable> AABBTreeObject<E> create(E data) {
      return new AABBTreeObject<>(data);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      AABBTreeObject<?> that = (AABBTreeObject<?>) o;
      return Objects.equals(data.getID(), that.data.getID());
   }

   @Override
   public int hashCode() {
      return Objects.hash(data.getID());
   }

   E getData() {
      return data;
   }
}
