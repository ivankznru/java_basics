public class Line implements Comparable<Line> {
    private String id;
    private String name;


    public Line(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.id + ". " + this.name;
    }



    @Override
    public int compareTo(Line line) {
        return this.getID().compareTo(line.getID());
    }
}

