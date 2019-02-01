import java.util.ArrayList;

public class Table {
    private ArrayList<BandMember> table;

    /**
     * Table constructor
     * @param bandMember
     */
    public Table(BandMember bandMember) {
        if (this.table.size() == 4) {
            System.out.println("Table is full.");
            return;
        }
        this.table.add(bandMember);
    }

    public Table() {
        this.table = new ArrayList<>();
    }


    public ArrayList<BandMember> getTable() {
        return table;
    }

    public void setTable(ArrayList<BandMember> table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Table" + '\'' +
                "{" +
                "table=" + table +
                '}';
    }
}
