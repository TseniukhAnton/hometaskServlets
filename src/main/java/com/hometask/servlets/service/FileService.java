package com.hometask.servlets.service;

import com.hometask.servlets.model.File;
import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.FileRepository;

import java.util.List;

public class FileService {
    FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File getById(Long id) {
        return fileRepository.getById(id);
    }

    public boolean deleteById(Long id) {
        return fileRepository.deleteById(id);
    }

    public File getFileByName(String fileName, User user) {
        return fileRepository.getFileByName(fileName, user);
    }

    public List<File> getUserFiles(User user) {
        return fileRepository.getUserFiles(user);
    }

    public List<File> getAll() {
        return fileRepository.getAll();
    }

    public File save(String name, User user) {
        File file = new File();
        file.setName(name);
        file.setUser(user);
        fileRepository.save(file);
        return file;
    }

    public File update(File file) {
        return fileRepository.update(file);
    }

}
