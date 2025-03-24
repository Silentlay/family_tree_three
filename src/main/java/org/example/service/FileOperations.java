package org.example.service;

import org.example.model.FamilyTree;

import java.io.IOException;

public interface FileOperations {
    void saveToFile(FamilyTree familyTree, String fileName) throws IOException;
    FamilyTree loadFromFile(String fileName) throws IOException, ClassNotFoundException;
}