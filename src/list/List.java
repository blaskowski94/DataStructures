package list;

public interface List<X> {
    void add(X item);
    X remove();
    void insert(X item, int position);
    X removeAt(int position);
    int find(X item);
    X get(int position);
    int size();
}
