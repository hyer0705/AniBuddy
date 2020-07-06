package web.dao.face;

import java.util.List;

import web.dto.ShareFile;
import web.dto.SharePost;
import web.dto.Share_calls;
import web.dto.UserID;
import web.util.Paging;

public interface ShareDao {

	public int selectCntAll();

	public List<UserID> selectAll(Paging paging);

	public UserID selectBoardByBoardNo(UserID share);

	public void updateHit(UserID share);

	public ShareFile selectFile(UserID result);

	public int selectPostno();

	public void insert(SharePost post);

	public void insertFile(ShareFile shareFile);

	public void update(SharePost post);

	public void deleteFile(UserID post);

	public void delete(UserID post);

	public int selectCntRecommend(Share_calls calls);

	public void deleteRecommend(Share_calls calls);

	public void insertRecommend(Share_calls calls);

	public List<UserID> selectChart();

	public int selectCntMailSearch(String search);



}
