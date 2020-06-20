package web.dao.face;

import java.util.List;

import web.dto.HangOut;
import web.dto.HangOutFile;

public interface HangOutDao {

	/**
	 * 놀거리 장소 전체 조회
	 * 
	 * @return List<HangOut> - 조회된 장소 반환
	 */
	List<HangOut> selectAll();

	
	/**
	 * 놀거리 장소에 대한 이미지 파일 조회
	 * 
	 * @return List<HangOutFile> - 조회된 이미지 파일 정보 반환
	 */
	List<HangOutFile> selectFileAll();


	/**
	 * 검색 조건이 도시(위치)만 있을 때
	 * 
	 * @param hangout
	 * @return
	 */
	List<HangOut> selectByCity(HangOut hangout);


	/**
	 * 필터로 검색할 때
	 * 
	 * @param filters
	 * @return
	 */
	List<HangOut> selectByFilter(String[] filters);


	/**
	 * 위치 1로만 검색할 때
	 * 
	 * @param hangout
	 * @param filters
	 * @return
	 */
	List<HangOut> selectByCity1(HangOut hangout, String[] filters);


	/**
	 * 위치와 필터로 검색할 때
	 * 
	 * @param hangout
	 * @param filters
	 * @return
	 */
	List<HangOut> selectByCityFilter(HangOut hangout, String[] filters);


	/**
	 * 장소번호로 놀거리 장소 조회하기
	 * 
	 * @param hangout - 장소 번호를 저장하고 있는 HangOut 객체
	 * @return HangOut - 조회된 객체 반환
	 */
	HangOut selectByhNo(HangOut hangout);


	/**
	 * 장소 번호로 해당 장소 글의 파일 조회하기
	 * 
	 * @param hangout - 장소 번호를 저장하고 있는 HangOut 객체
	 * @return - 조회된 HangOutFile 객체 반환 
	 */
	HangOutFile selectFileByhNo(HangOut hangout);


	/**
	 * 장소 번호 조회
	 * 
	 * @return int - 조회된 장소 번호 반환
	 */
	int selecthNo();


	/** 
	 * 장소 추가
	 * 
	 * @param hangout - 추가할 장소에 대한 정보를 갖고 있는 HangOut 객체
	 */
	void insert(HangOut hangout);


	/** 
	 * 장소 추가할 때 이미지 파일이 있다면 이미지 파일도 삽입
	 * 
	 * @param hangoutFile - 해당 장소에 대한 이미지 파일에 대한 정보를 갖고 있는 HangOutFile 객체
	 */
	void insertFile(HangOutFile hangoutFile);


	/**
	 * 삭제할 장소에 대한 이미지 지우기
	 * 
	 * @param hangoutFile
	 */
	void deleteFile(HangOutFile hangoutFile);


	/**
	 * 장소 삭제
	 * 
	 * @param hangout
	 */
	void delete(HangOut hangout);

}
