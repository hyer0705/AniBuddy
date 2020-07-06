package web.dao.face;

import java.util.List;

import web.dto.HelpFile;
import web.dto.HelpPost;
import web.dto.Help_calls;
import web.dto.UserID;
import web.util.Paging;

public interface HelpDao {

	public int selectCntAll();

	public List<UserID> selectAll(Paging paging);

	public HelpPost selectBoardByBoardNo(HelpPost help);

	public void updateHit(HelpPost help);

	public HelpFile selectFile(HelpPost result);

	public int selectPostno();

	public void insert(HelpPost post);

	public void insertFile(HelpFile helpFile);

	public void update(HelpPost post);

	public void deleteFile(HelpPost post);

	public void delete(HelpPost post);

	public int selectCntRecommend(Help_calls calls);

	public void deleteRecommend(Help_calls calls);

	public void insertRecommend(Help_calls calls);

	public List<UserID> selectChart();

	public int selectCntHelpSearch(String search);

}
