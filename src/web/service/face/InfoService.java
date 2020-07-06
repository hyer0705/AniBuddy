package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.BookMarkInfo;
import web.dto.Info;
import web.util.Paging;

public interface InfoService {

	List<Info> list();

	List<Info> viewPlaceList(Info info, String[] filters);

	void addPlace(Info info);

	List<Info> viewAddressByName(Info info);

	Info viewAddressByNo(Info info);

	void delete(Info info);

	Info getiNo(HttpServletRequest req);

	Info view(Info info);

	List<Info> viewLocation(Info info);

	List<Info> viewCity(Info info);

	void insertBookMark(BookMarkInfo bm);

	void deleteBookMark(BookMarkInfo bm);

	void deleteBookMarkNo(BookMarkInfo bm);

	List<BookMarkInfo> bmList();

	void delete(String names);

	BookMarkInfo getParamBmNo(HttpServletRequest req);

	Paging getPaging(HttpServletRequest req);
	
}
