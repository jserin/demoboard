package demo.demoBoard.file.service;

import demo.demoBoard.file.mapper.FileMapper;
import demo.demoBoard.file.model.FileRequest;
import demo.demoBoard.file.model.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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

    /*
     * 파일 리스트 조회
     * @param boardId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    public List<FileResponse> findAllFileByBoardId(final Integer boardId) {
        return fileMapper.findAllByBoardId(boardId);
    }

    /*
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    public List<FileResponse> findAllFileByIds(final List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return fileMapper.findAllByIds(ids);
    }

    /*
     * 파일 삭제 (from Database)
     * @param ids - PK 리스트
     */
    @Transactional
    public void deleteAllFileByIds(final List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        fileMapper.deleteAllByIds(ids);
    }
}
