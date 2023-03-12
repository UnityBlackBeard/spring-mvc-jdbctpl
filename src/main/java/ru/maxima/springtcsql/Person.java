package ru.maxima.springtcsql;

public class Person {

    private Long id;
    private String name;
    private int birth;


    public Person() {}

    public Person(String name, int birth) {
        this.name = name;
        this.birth = birth;
    }

    public Person(Long id, String name, int birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return name+", "+birth;
    }
}
