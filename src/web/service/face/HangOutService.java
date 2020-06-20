package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.HangOut;
import web.dto.HangOutFile;

public interface HangOutService {

	/**
	 * 놀거리 장소 전체 조회
	 * 
	 * @return List<HangOut> - 조회된 장소 반환
	 */
	List<HangOut> list();

	/**
	 * 놀거리 장소에 대한 이미지 파일 조회
	 * 
	 * @return List<HangOutFile> - 조회된 파일 정보 반환
	 */
	List<HangOutFile> fileList();

	/**
	 * 검색된 장소 리스트 조회하기
	 * 
	 * @param hangout - 장소 객체
	 * @param filters - 검색 필터
	 * @return List<HangOut> - 검색된 장소 결과 리스트 반환
	 */
	List<HangOut> viewPlaceList(HangOut hangout, String[] filters);

	
	/**
	 * 장소 번호 얻기
	 * 
	 * @param req - 요청 객체
	 * @return
	 */
	HangOut gethNo(HttpServletRequest req);

	/**
	 * ?
	 * 
	 * @param hangout
	 * @return
	 */
	HangOut view(HangOut hangout);

	/**
	 * ?
	 * 
	 * @param hangout
	 * @return
	 */
	HangOutFile viewFile(HangOut hangout);

	/**
	 * 사용자가 직접 장소 추가
	 * 
	 * @param req - 요청 객체
	 */
	void addPlace(HttpServletRequest req);

	/**
	 * 사용자가 추가한 장소 삭제
	 * 
	 * @param hangout - 삭제할 장소에 대한 정보를 갖고 있는 HangOut 객체
	 */
	void delete(HangOut hangout);


}
