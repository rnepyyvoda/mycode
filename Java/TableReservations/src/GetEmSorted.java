import java.util.ArrayList;
import java.util.Scanner;

public class GetEmSorted {
    public static ArrayList<Table> partialSolution;
    private static int arraySizeFlag, flaggedMemberCounter = 0;
    private static Table currentTable = new Table();


    public static void main(String[] args) {
        ArrayList<BandMember> guestList;
        ArrayList<Table> sortedTables;

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter a name of the guest list file:");
        String fileName = in.nextLine();
        guestList = InputOutput.readFile(fileName);

        sortedTables = createTables(guestList);
        System.out.println("number of tables: " + sortedTables.size());

        for (int i = 0; i < sortedTables.size(); i++) {
            System.out.println("Table " + (i + 1) + ":");
            for (int j = 0; j < sortedTables.get(i).getTable().size(); j++) {
                System.out.println(sortedTables.get(i).getTable().get(j).toString());
            }
            System.out.println();
        }


    }


    /**Method takes array list of attending band members and sots them into tables accorsing to their dislikes.
     * @param noTableMembers
     * @return
     */
    public static ArrayList<Table> createTables(ArrayList<BandMember> noTableMembers) {
        partialSolution = new ArrayList<>();
        createTables(partialSolution, noTableMembers);

        ArrayList<Table> sortedTables = new ArrayList<>(partialSolution);
        return sortedTables;
    }


    /**This is supplementary method that takes array list of attending band members and sots them into tables accorsing to their dislikes.
     * @param noTableMembers
     */
    private static void createTables(ArrayList<Table> partialSolution, ArrayList<BandMember> noTableMembers) {
        ArrayList<BandMember> currentNoTableMembers;
        currentNoTableMembers = noTableMembers;


        //base case
        if (currentNoTableMembers.isEmpty()) {
            return;
        }
        if (currentNoTableMembers.size() == 1) {
            currentTable=new Table();
            currentTable.getTable().add(currentNoTableMembers.get(0));
            partialSolution.add(currentTable);
            return;
        }

        //recursive
        BandMember currentNoTableMember = currentNoTableMembers.get(0);
        if (!currentTable.getTable().isEmpty() && currentTable.getTable().size() < 4) {
            for (int i = 0; i < currentTable.getTable().size(); i++) {
                if ((!currentNoTableMember.likes(currentTable.getTable().get(i))) ||
                        (!currentTable.getTable().get(i).likes(currentNoTableMember))) {

                    arraySizeFlag = currentNoTableMembers.size();

                    if (arraySizeFlag == flaggedMemberCounter) {
                        BandMember evictedTableMember = currentTable.getTable().get(currentTable.getTable().size() - 1);
                        currentTable.getTable().remove(evictedTableMember);
                        currentNoTableMembers.add(evictedTableMember);
                    }

                    currentNoTableMembers.remove(0);
                    currentNoTableMembers.add(currentNoTableMember);
                    flaggedMemberCounter++;
                    createTables(partialSolution, currentNoTableMembers);
                }
            }
            currentTable.getTable().add(currentNoTableMember);
            flaggedMemberCounter = 0;
        }
        if (currentTable.getTable().isEmpty()) {
            currentTable.getTable().add(currentNoTableMember);
        }
        if (currentTable.getTable().size() == 4) {
            partialSolution.add(currentTable);
            currentTable = new Table();
            flaggedMemberCounter = 0;
        }

        currentNoTableMembers.remove(currentNoTableMember);
        if (currentNoTableMembers.isEmpty()) {
            partialSolution.add(currentTable);
            currentNoTableMembers = new ArrayList<>();
        }
        currentNoTableMember = null;
        createTables(partialSolution, currentNoTableMembers);
    }
}
