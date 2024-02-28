package org.allaymc.api.datastruct.aabbtree;

import java.util.Objects;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
final class AABBTreeObject<E extends HasLongId> {
   private final E data;

   private AABBTreeObject(E data) {
      this.data = data;
   }

   static <E extends HasLongId> AABBTreeObject<E> create(E data) {
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
      return Objects.equals(data.getLongId(), that.data.getLongId());
   }

   @Override
   public int hashCode() {
      return Objects.hash(data.getLongId());
   }

   E getData() {
      return data;
   }
}
