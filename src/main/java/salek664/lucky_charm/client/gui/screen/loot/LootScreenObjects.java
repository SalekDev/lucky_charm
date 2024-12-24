package salek664.lucky_charm.client.gui.screen.loot;

import java.util.List;
import java.util.Set;

public class LootScreenObjects {
    public class LootTable {
        Set<LootPool> pools;
    }
    public class LootPool {
        List<PoolEntry> entries;
    }
    public class PoolEntry {
        int weight;
        int quality;
    }
}
