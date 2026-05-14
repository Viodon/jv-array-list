package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] data;

    @Override
    public void add(T value) {
        if (data == null || data.length <= size) {
            Object[] newData =
                    new Object[data == null ? DEFAULT_CAPACITY : (int) (data.length * 1.5)];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }

        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't write value");
        }

        if (index == size) {
            add(value);
            return;
        }

        if (data.length == size) {
            Object[] newData = new Object[(int) (size * 1.5)];

            System.arraycopy(data, 0, newData, 0, index);
            System.arraycopy(data, index, newData, index + 1, size - index);
            newData[index] = value;
            data = newData;
            size++;
            return;
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int j = 0;

        if (data == null) {
            data = new Object[DEFAULT_CAPACITY];
        }
        if (list.size() + size >= data.length) {

            Object[] newData = new Object[(int) (Math.max(size + list.size(), data.length * 1.5))];
            System.arraycopy(data, 0, newData, 0, size);
            for (int i = size; i < size + list.size(); i++) {
                newData[i] = list.get(j);
                j++;
            }
            data = newData;
            size += list.size();
            return;
        }

        for (int i = size; i < size + list.size(); i++) {
            data[i] = list.get(j);
            j++;
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't get value, invalid index");
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't set value, invalid index");
        }

        data[index] = value;
    }

    @Override
    public T remove(int index) {
        T value;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove value, invalid index");
        }
        value = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, data[i])) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Can't such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
