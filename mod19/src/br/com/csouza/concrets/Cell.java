package br.com.csouza.concrets;

public class Cell<T> {
    private T value;
    private int column;
    private int row;

    public Cell(int column, int row, T value) {
        this.column = column;
        this.row = row;
        this.value = value;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
