import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {

    /**Method takes txt file with list people who are to attend function and returns array list of band members.
     * To be properly interpreted txt file supposed to be formatted in the following way:
     * For each new person:
     * row 1: name;
     * row 2: grade;
     * row 3: instrument;
     * row 4: list of dislikes, if there is more than one dislike - must be separated by space.
     * If any portion of information regarding a person is missing it must be represented with empty row.
     *
     * @param fileName
     * @return
     */
    public static ArrayList<BandMember> readFile(String fileName) {
        Scanner guestListScanner;
        ArrayList<BandMember> noTableMembers = new ArrayList<>();

        try {
            File input = new File(fileName);
            guestListScanner = new Scanner(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("No such file file: " + fileName);
        }

        while (guestListScanner.hasNext()) {
            noTableMembers.add(getNextMember(guestListScanner));
        }
        guestListScanner.close();
        System.out.println();
        return noTableMembers;
    }

    /**Method processes text file band member by band member.
     * @param guestListScanner
     * @return
     */
    public static BandMember getNextMember(Scanner guestListScanner) {
        BandMember newMember;
        String name = null, grade = null, instrument = null, dislikesString = null;
        ArrayList<String> dislikes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                name = guestListScanner.nextLine();
            }
            if (i == 1) {
                grade = guestListScanner.nextLine();
            }
            if (i == 2) {
                instrument = guestListScanner.nextLine();
            }
            if (i == 3) {
                dislikesString = guestListScanner.nextLine();
                Scanner dislikeScanner = new Scanner(dislikesString);
                while (dislikeScanner.hasNext()) {
                    dislikes.add(dislikeScanner.next());
                }
            }
        }
        newMember = new BandMember(name, grade, instrument, dislikes);
        return newMember;
    }
}
