package exam;

import java.util.*;
import java.util.stream.Collectors;

//Class Employee
class Employee {
    private String name;
    private String department;
    private double salary;
    private List<String> skills;

//Constructor for Class Employee
    public Employee(String name, String department, double salary, List<String> skills) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.skills = skills;
    }
  
//getters for Class Employee
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }
}
//Class DepartmentStats
class DepartmentStats {
    private double averageSalary;
    private long employeeCount;
    private Set<String> uniqueSkills;
//constructor
    public DepartmentStats(double averageSalary, long employeeCount, Set<String> uniqueSkills) {
        this.averageSalary = averageSalary;
        this.employeeCount = employeeCount;
        this.uniqueSkills = uniqueSkills;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }
  
    public Set<String> getUniqueSkills() {
        return uniqueSkills;
    }
}

//Main class
public class EmployeeStatitics {
    public static Map<String, DepartmentStats> analyzeDepartments(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,Collectors.filtering(e -> e.getDepartment() != null,
                                Collectors.collectingAndThen(Collectors.toList(),
                                        deptEmployees -> {
                                            double avgSalary = deptEmployees.stream()
                                                    .mapToDouble(Employee::getSalary)
                                                    .average()
                                                    .orElse(0.0);

                                            Set<String> uniqueSkills = deptEmployees.stream()
                                                    .flatMap(e -> e.getSkills().stream())
                                                    .collect(Collectors.toSet());

                                            return new DepartmentStats(avgSalary, deptEmployees.size(), uniqueSkills);
                                        }
                                )
                        )
                ))
                .entrySet().stream()
                .filter(e -> e.getValue().getEmployeeCount() > 2)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "IT", 75000, Arrays.asList("Java", "SQL")),
                new Employee("Alice", "IT", 82000, Arrays.asList("Python", "Java")),
                new Employee("Bob", "HR", 65000, Arrays.asList("Communication")),
                new Employee("Charlie", "IT", 78000, Arrays.asList("Java", "AWS"))
        );

        Map<String, DepartmentStats> departmentStats = analyzeDepartments(employees);

        departmentStats.forEach((department, stats) -> {
            System.out.println("Department: " + department);
            System.out.println("Average Salary: " + stats.getAverageSalary());
            System.out.println("Employee Count: " + stats.getEmployeeCount());
            System.out.println("Unique Skills: " + stats.getUniqueSkills());
            System.out.println();
        });
    }
}

