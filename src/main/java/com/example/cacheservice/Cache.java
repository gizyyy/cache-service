package com.example.cacheservice;

import java.util.Optional;

//Let’s implement a cache library with following interface:
interface Cache {
  public Optional<Integer> getItemWithKey(Integer key);
  public void putItem(Integer key, Integer value);

}
