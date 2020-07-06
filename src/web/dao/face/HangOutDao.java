package web.dao.face;

import java.util.List;

import web.dto.BookMarkHangOut;
import web.dto.HangOut;
import web.dto.HangOutFile;
import web.dto.UserTB;

public interface HangOutDao {

	public List<HangOut> selectAll();

	public List<HangOut> selectByFilter(String[] filters);

	public List<HangOut> selectByCity1(HangOut hangout);
	
	public List<HangOut> selectByCity(HangOut hangout);
	
	public List<HangOut> selectByCityFilter(HangOut hangout, String[] filters);

	public List<HangOut> selectByCity1(HangOut hangout, String[] filters);

	public void insert(HangOut hangout);

	public int selecthNo();

	public void insertFile(HangOutFile hangoutFile);

	public void delete(HangOut hangout);

	public void deleteFile(HangOutFile hangoutFile);

	public List<HangOutFile> selectFileAll();

	public HangOut selectByhNo(HangOut hangout);

	public HangOutFile selectFileByhNo(HangOut hangout);

	public List<HangOut> selectByNameCity1(HangOut hangout);

	public List<HangOut> selectByName(HangOut hangout);

	public List<BookMarkHangOut> selectBookMarkList();

	public void deleteBookMark(BookMarkHangOut bm);

	public void deleteBookMarkNo(BookMarkHangOut bm);

	public void insertBookMark(BookMarkHangOut bm);

	public void delete(String names);

	public int selectCntAllByUserno(UserTB currUser);


}
