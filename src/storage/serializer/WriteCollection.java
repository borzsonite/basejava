package storage.serializer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public interface WriteCollection<K, V> {
    void accept(Map<K, V> map, DataOutputStream dos) throws IOException;
}
