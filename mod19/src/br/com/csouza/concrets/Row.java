package br.com.csouza.concrets;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Cell<?>> row;

    public Row() {
        this.row = new ArrayList<>();
    }

    public Row(List<Cell<?>> row) {
        this();
        this.row = row;
    }

    public void addCell(Cell<?> cell) {
        this.row.add(cell);
    }

    @Override
    public String toString() {
        final String line = this.row.toString();


        return line.replaceAll("\\[", "| ").replaceAll(", ", " | ").replaceAll("]", " |");
    }
}
