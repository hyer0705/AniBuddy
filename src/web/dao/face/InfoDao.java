package web.dao.face;

import java.util.List;

import web.dto.BookMarkInfo;
import web.dto.Info;
import web.dto.UserTB;

public interface InfoDao {

	List<Info> selectAll();

	List<Info> selectByCity1(Info info);

	List<Info> selectByCity(Info info);

	List<Info> selectByFilter(String[] filters);

	List<Info> selectByCity1(Info info, String[] filters);

	List<Info> selectByCityFilter(Info info, String[] filters);

	void insert(Info info);

	List<Info> selectByName(Info info);

	Info selectByNo(Info info);

	void delete(Info info);

	List<Info> selectByNameCity1(Info info);

	void insertBookMark(BookMarkInfo bm);

	void deleteBookMark(BookMarkInfo bm);

	List<BookMarkInfo> selectBookMarkList();

	void delete(String names);

	void deleteBookMarkNo(BookMarkInfo bm);

	int selectCntAllByUserno(UserTB currUser);

}
