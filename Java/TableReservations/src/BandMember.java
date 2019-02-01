import java.util.ArrayList;

public class BandMember {
    private String name, grade, instrument;
    private ArrayList<String> dislikes;

    /**
     * Band member constructor.
     * @param name
     * @param grade
     * @param instrument
     * @param dislikes
     */
    public BandMember(String name, String grade, String instrument, ArrayList<String> dislikes) {
        this.name = name;
        this.grade = grade;
        this.instrument = instrument;
        this.dislikes = dislikes;
    }

    public BandMember() {
        this.name = null;
        this.grade = null;
        this.instrument = null;
        this.dislikes = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public ArrayList<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(ArrayList<String> dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "BandMember{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", instrument='" + instrument + '\'' +
                ", dislikes=" + dislikes +
                '}';
    }

    /**
     * Method checks if a band member likes new table member.
     *
     * @param newTableMember
     * @return
     */
    public boolean likes(BandMember newTableMember) {
        if (this.getDislikes().size() != 0) {
            for (int i = 0; i < this.getDislikes().size(); i++) {
                if (this.getDislikes().get(i).equalsIgnoreCase(newTableMember.grade) ||
                        this.getDislikes().get(i).equalsIgnoreCase(newTableMember.instrument)) {
                    return false;
                }
            }
        } else {
            return true;
        }
        return true;
    }
}
