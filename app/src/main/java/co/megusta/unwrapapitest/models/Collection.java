package co.megusta.unwrapapitest.models;

public class Collection {
    public int id;
    public String name;
    public Counts counts;

    public Collection(int id, String name, Counts counts) {
        this.id = id;
        this.name = name;
        this.counts = counts;
    }

    public class Counts {
        public int books;

        public Counts(int books) {
            this.books = books;
        }
    }
}


