package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.project.FileContentResponse;
import com.sambaran.codecanvas.dto.project.FileNode;

import java.util.List;

/**
 * @author Sambaran
 */

public interface ProjectFileService {
    List<FileNode> getFileTree(Long projectId);

    FileContentResponse getFileContent(Long projectId, String path);

    void save(Long projectId, String filePath, String fileContent);
}
