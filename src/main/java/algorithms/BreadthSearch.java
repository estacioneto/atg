package algorithms;

import model.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.io.Serializable;

public class BreadthSearch {
    public static String BFS(Graph graph, int init) {
        var sb = new StringBuilder();
        var visited = new HashSet<Integer>();
        var queue = new LinkedList<Pair<Integer, Integer>>();
        queue.addLast(new Pair<>(init, 1));
        visited.add(init);

        final var template = "%s - %s %s" + System.lineSeparator();
        sb.append(String.format(template, init, "-", 0));
        while (!queue.isEmpty()) {
            var next = queue.pollFirst();
            for (Integer child : graph.getConnected(next.getKey())) {
                if (visited.contains(child)) {
                    continue;
                }
                visited.add(child);
                queue.add(new Pair<>(child, next.getValue() + 1));
                sb.append(String.format(template, child, next.getKey(), next.getValue()));
            }
        }

        return sb.toString();
    }

    static class Pair<K,V> implements Serializable{

        private K key;

        public K getKey() { return key; }

        private V value;

        public V getValue() { return value; }

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Pair) {
                Pair pair = (Pair) o;
                if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
                if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
                return true;
            }
            return false;
        }
    }
}
