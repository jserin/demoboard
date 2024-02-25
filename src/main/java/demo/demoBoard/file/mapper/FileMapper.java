package demo.demoBoard.file.mapper;

import demo.demoBoard.file.model.FileRequest;
import demo.demoBoard.file.model.FileResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    /*
     * 파일 정보 저장
     * @param files - 파일 정보 리스트
     */
    void saveAll(List<FileRequest> files);

    /*
     * 파일 리스트 조회
     * @param boardId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    List<FileResponse> findAllByBoardId(int boardId);

    /*
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    List<FileResponse> findAllByIds(List<Integer> ids);

    /*
     * 파일 삭제
     * @param ids - PK 리스트
     */
    void deleteAllByIds(List<Integer> ids);

}
