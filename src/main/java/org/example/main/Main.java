package org.example.main;

import org.example.model.FamilyTree;
import org.example.model.Person;
import org.example.service.FileOperations;
import org.example.service.FileOperationsImpl;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final FamilyTree familyTree = getPeople();

        // Сортируем по имени
        System.out.println("Сортировка по имени:");
        familyTree.sortByName();
        for (Person person : familyTree) {
            System.out.println(person.getName() + " - " + person.getBirthYear());
        }

        // Сортируем по дате рождения
        System.out.println("\nСортировка по дате рождения:");
        familyTree.sortByBirthYear();
        for (Person person : familyTree) {
            System.out.println(person.getName() + " - " + person.getBirthYear());
        }

        // Создаем объект для работы с файлами
        FileOperations fileOps = new FileOperationsImpl();

        // Сохраняем генеалогическое древо в файл
        try {
            fileOps.saveToFile(familyTree, "familyTree.dat");
            System.out.println("Family tree saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Загружаем генеалогическое древо из файла
        FamilyTree loadedFamilyTree = null;
        try {
            loadedFamilyTree = fileOps.loadFromFile("familyTree.dat");
            System.out.println("Family tree loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Проверяем, что древо загрузилось правильно
        if (loadedFamilyTree != null) {
            System.out.println("\nLoaded people:");
            for (Person person : loadedFamilyTree) {
                System.out.println(person.getName() + ", born in " + person.getBirthYear());
            }
        }

    }

    private static FamilyTree getPeople() {
        FamilyTree familyTree = new FamilyTree();

        // Создаем людей
        Person john = new Person("John", 1950);
        Person mary = new Person("Mary", 1955);
        Person susan = new Person("Susan", 1980);

        // Устанавливаем родительские связи
        susan.setMother(mary);
        susan.setFather(john);
        john.addChild(susan);
        mary.addChild(susan);

        // Добавляем людей в древо
        familyTree.addPerson(john);
        familyTree.addPerson(mary);
        familyTree.addPerson(susan);
        return familyTree;
    }
}
