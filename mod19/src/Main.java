import br.com.csouza.annotations.Tabela;
import br.com.csouza.concrets.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Valor da anotação: " + getTableAnnotation(Table.class));

        Table tabela = new Table();

        tabela.addColumn(new Column("id"));
        tabela.addColumn(new Column("name"));
        tabela.addColumn(new Column("surname"));
        tabela.addColumn(new Column("age"));

        final Row r1 = new Row();
        r1.addCell(new Cell<String>(0, 1, "1"));
        r1.addCell(new Cell<String>(1, 1, "Caique"));
        r1.addCell(new Cell<String>(2, 1, "Souza"));
        r1.addCell(new Cell<String>(3, 1, "21"));

        final Row r2 = new Row();
        r2.addCell(new Cell<String>(0, 1, "2"));
        r2.addCell(new Cell<String>(1, 1, "João"));
        r2.addCell(new Cell<String>(2, 1, "Cardoso"));
        r2.addCell(new Cell<String>(3, 1, "87"));

        final Row r3 = new Row();
        r3.addCell(new Cell<String>(0, 1, "3"));
        r3.addCell(new Cell<String>(1, 1, "Claudia"));
        r3.addCell(new Cell<String>(2, 1, "Viscondi"));
        r3.addCell(new Cell<String>(3, 1, "32"));

        tabela.addRow(r1);
        tabela.addRow(r2);
        tabela.addRow(r3);

        System.out.println("\n" + tabela.getTable());
    }

    public static <T> String getTableAnnotation(Class<T> tableClass) {
        if (tableClass.isAnnotationPresent(Tabela.class)) {
            Tabela tabelaAnnotation = tableClass.getAnnotation(Tabela.class);
            return tabelaAnnotation.value();
        }
        return "";
    }
}
