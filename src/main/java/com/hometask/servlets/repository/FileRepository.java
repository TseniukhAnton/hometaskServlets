package com.hometask.servlets.repository;

import com.hometask.servlets.model.File;
import com.hometask.servlets.model.User;

import java.util.List;

public interface FileRepository extends GenericRepository<File, Long> {
    List<File> getUserFiles(User user);
    File getFileByName(String fileName, User user);
}
