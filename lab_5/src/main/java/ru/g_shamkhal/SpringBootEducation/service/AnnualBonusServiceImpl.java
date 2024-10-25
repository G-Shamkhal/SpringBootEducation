package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.model.Positions;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int yearDays = getYearDays();
        double result = salary * bonus * yearDays * positions.getPositionCoefficient() / workDays;

        if (positions.isManager()) {
            double quarterlyBonus = salary * bonus;
            result += quarterlyBonus;
        }

        return result;
    }

    public static int getYearDays() {
        int year = LocalDate.now().getYear();
        return ((GregorianCalendar) Calendar.getInstance()).isLeapYear(year) ? 366 : 365;
    }

}