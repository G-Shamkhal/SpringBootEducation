package ru.g_shamkhal.SpringBootEducation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.g_shamkhal.SpringBootEducation.model.Positions;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnualBonusServiceImplTest {

    private AnnualBonusService calculator;

    @BeforeEach
    public void setUp() {
        calculator = new AnnualBonusServiceImpl();
    }
    @ParameterizedTest
    @CsvSource({
            "TL, 80000, 2.6, 250, true",
            "DEV, 90000, 2.2, 250, false",
            "HR, 70000, 1.2, 250, false",
            "CEO,120000, 5.0, 250, true)",
            "SE, 100000, 1.9, 250, false)"
    })
    public void testCalculate(Positions position, double salary, double bonus, int workDays) {
        int year = LocalDate.now().getYear();
        double daysInYear = ((GregorianCalendar) Calendar.getInstance()).isLeapYear(year) ? 366 : 365;
        double total = salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
        if (position.isManager())
            total = total + salary * bonus;

        double result = calculator.calculate(position, salary, bonus, workDays);

        assertEquals(total, result, 0.001);
    }
}