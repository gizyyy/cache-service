package com.example.cacheservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheServiceApplicationTests {

	@Test
	void shouldReturnValueWhenAnItemIsPlacedInCache() {

		ItemCache itemCache = ItemCache.fromCapacity(2);
		itemCache.putItem(0, 1);
		assertEquals(itemCache.getItemWithKey(0), Optional.of(1));
	}

	@Test
	void shouldEvictOldestItemWhenCapacityIsFullInCache() {

		ItemCache itemCache = ItemCache.fromCapacity(2);
		itemCache.putItem(0, 1);
		itemCache.putItem(1, 3);
		itemCache.putItem(2, 1);

		assertEquals(itemCache.getItemWithKey(0), Optional.empty());
		assertEquals(itemCache.getItemWithKey(2), Optional.of(1));
	}

}
