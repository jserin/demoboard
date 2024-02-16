package demo.demoBoard.file.service;

import demo.demoBoard.file.mapper.FileMapper;
import demo.demoBoard.file.model.FileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;

    @Transactional
    public void saveFiles(final int boardId, final List<FileRequest> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequest file : files) {
            file.setBoardId(boardId);
        }
        fileMapper.saveAll(files);
    }
}
