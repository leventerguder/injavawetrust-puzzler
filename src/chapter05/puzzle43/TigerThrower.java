package chapter05.puzzle43;

// Don't do this either - circumvents exception checking!
class TigerThrower<T extends Throwable> {
    public static void sneakyThrow(Throwable t) {
        new TigerThrower<Error>().sneakyThrow2(t);
    }

    private void sneakyThrow2(Throwable t) throws T {
        throw (T) t;
    }
}

