package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.dto.project.FileContentResponse;
import com.sambaran.codecanvas.dto.project.FileNode;
import com.sambaran.codecanvas.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sambaran
 */
@Service
public class FileServiceimpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return null;
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
