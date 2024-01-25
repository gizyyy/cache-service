package com.example.cacheservice;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import org.apache.commons.lang3.tuple.Pair;

public class ItemCache implements Cache {

  private static HashMap<Integer, Integer> itemCacheMap;

  private Integer capacity;

  private static PriorityQueue<Pair<Integer, Integer>> itemCacheQueue;

  private ItemCache(HashMap<Integer, Integer> itemCacheMap, PriorityQueue<Pair<Integer, Integer>> itemCacheQueue, Integer capacity) {
    this.itemCacheMap = itemCacheMap;
    this.itemCacheQueue = itemCacheQueue;
    this.capacity = capacity;
  }


  public static ItemCache fromCapacity(Integer capacity) {
    itemCacheMap = new HashMap<>(capacity);
    itemCacheQueue = new PriorityQueue<>(capacity);
    return new ItemCache(itemCacheMap,itemCacheQueue, capacity);
  }

  @Override
  public Optional<Integer> getItemWithKey(Integer key) {
    Integer item = itemCacheMap.get(key);
    if (Objects.isNull(item)) {
      return Optional.empty();
    }
    return Optional.ofNullable(item);
  }


  @Override
  public void putItem(Integer key, Integer value) {
    if (itemCacheQueue.size() == this.capacity) {
      Pair<Integer, Integer> removed = itemCacheQueue.remove();
      itemCacheMap.remove(removed.getKey());
    }

    itemCacheQueue.add(Pair.of(key, value));
    itemCacheMap.put(key, value);
  }
}
