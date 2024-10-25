package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);

}
