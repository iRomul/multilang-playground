package io.github.iromul.playground.new21;

public record User(String firstName, String lastName) {

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
