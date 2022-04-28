package ru.netology;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.netology.Main.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    private static List<Employee> employee;

    @BeforeAll
    public static void setEmployee() {
        employee = new ArrayList<>();
        employee.add(new Employee(1, "Vasya", "Ivanov", "Russia", 25));
    }

    //Hamcrest
    @Test
    public void testingParseCSV_Hamcrest() {
        // given:
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        // when:
        List<Employee> result = parseCSV(columnMapping, fileName);
        // then:
        assertThat(result, not(empty()));
    }

    @Test
    public void testingListToJson_Hamcrest() {
        // given:
        // when:
        String result = listToJson(employee);
        // then:
        assertThat(result, not(isEmptyOrNullString()));
    }

    @Test
    public void testingParseXML_Hamcrest() {
        // given:
        String s = "data.xml";
        Employee emp = new Employee(2, "Ivan", "Petrov", "RU", 23);
        // when:
        List<Employee> result = parseXML(s);
        // then:
        assertThat(result, Matchers.notNullValue());
        assertThat(result.get(0).getClass(), anyOf(typeCompatibleWith(Employee.class), samePropertyValuesAs(employee)));
    }

    @Test
    public void testingReadString_Hamcrest() {
        // given:
        String s = "data.json";
        String r = "RU";
        // when:
        String result = readString(s);
        // then:
        assertThat(result.length(), greaterThan(0));
        assertThat(result, containsString(r));
    }

    @Test
    public void testingJsonTOList_Hamcrest() {
        // given:
        String s = "[{\"id\":1,\"firstName\":\"Vasya\",\"lastName\":\"Ivanov\",\"country\":\"Russia\",\"age\":25}]";
        // when:
        List<Employee> result = jsonToList(s);
        // then:
        assertThat(result.isEmpty(), is(false));
        assertThat(result.get(0).getClass(), anyOf(typeCompatibleWith(Employee.class), samePropertyValuesAs(employee.get(0).getClass())));
    }
}