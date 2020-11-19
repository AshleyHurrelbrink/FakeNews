package model;


public class Editor {
    private String name;

    public Editor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "name='" + name + '\'' +
                '}';
    }
}
