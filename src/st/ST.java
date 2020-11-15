package st;

public interface ST<Key, Value>
{
    void put(Key key, Value value);
    Value get(Key key);
    void delete(Key key);
    Iterable<Key> keys();
    int size();
}
