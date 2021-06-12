package core;

public interface BSTreeInterface<T extends Comparable<? super T>> extends Iterable<T> {

	void insert(T myData);

	void preOrder();

	void postOrder();

	void inOrder();

	NodeTreeInterface<T> getRoot();

	int getHeight();
	void printByLevels();
}