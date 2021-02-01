package util.StrategyBJ2;

public class Storage {
    Strategy strategy;

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    void applyStrategy() {
        strategy.doRead();
        strategy.doWrite();
    }
}
