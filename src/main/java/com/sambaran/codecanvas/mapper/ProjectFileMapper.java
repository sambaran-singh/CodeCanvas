package com.sambaran.codecanvas.mapper;

import com.sambaran.codecanvas.dto.project.FileNode;
import com.sambaran.codecanvas.entity.ProjectFile;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Sambaran
 */
@Mapper(componentModel = "spring")
public interface ProjectFileMapper {

    List<FileNode> toListOfFileNode(List<ProjectFile> projectFileList);
}
