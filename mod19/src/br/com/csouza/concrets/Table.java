package br.com.csouza.concrets;

import br.com.csouza.annotations.Tabela;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

@Tabela("Essa classe é a representação de uma tabela, ou a tentativa de ser uma...")
public class Table {
    private List<Column> columns;
    private List<Row> rows;

    public Table() {
        this.rows = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    public Table(List<Column> columns) {
        this();
        this.columns = columns;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    public void addRow(Row row) {
        this.rows.add(row);
    }

    public Collection<Column> getColumns() {
        return this.columns;
    }

    public Collection<Row> getRows() {
        return this.rows;
    }

    public String getTable() {
        StringBuilder table = new StringBuilder();
        table.append("| ");

        for (Column column : this.columns) {
            table.append(column.getName()).append(" | ");
        }
        table.append("\n");

        for (Row row : this.rows) {
            table.append(row).append('\n');
        }

        return table.toString();
    }
}
