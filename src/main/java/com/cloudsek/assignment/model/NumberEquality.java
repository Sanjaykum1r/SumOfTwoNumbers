package com.cloudsek.assignment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
public class NumberEquality {

    @Id
    private int  id;
    private int  number1;
    private int  number2;
    private Integer  sum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberEquality that = (NumberEquality) o;
        return number1 == that.number1 && number2 == that.number2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number1, number2);
    }
}
