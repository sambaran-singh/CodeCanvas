package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.project.FileContentResponse;
import com.sambaran.codecanvas.dto.project.FileNode;

import java.util.List;

/**
 * @author Sambaran
 */

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
