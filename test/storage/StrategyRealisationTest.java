package storage;

import storage.strategy.StrategyRealisation;

public class StrategyRealisationTest extends AbstractStorageTest{

    public StrategyRealisationTest() {
        super(new FileStorage(STORAGE_DIR, new StrategyRealisation()));
    }
}