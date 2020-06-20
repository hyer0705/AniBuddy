package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.HangOutDao;
import web.dao.face.UserDao;
import web.dao.impl.HangOutDaoImpl;
import web.dao.impl.UserDaoImpl;
import web.dto.HangOut;
import web.dto.HangOutFile;
import web.dto.UserTB;
import web.service.face.HangOutService;

public class HangOutServiceImpl implements HangOutService{

	private HangOutDao hangoutDao = new HangOutDaoImpl();
	
	@Override
	public List<HangOut> list() {
		return hangoutDao.selectAll();
	}
	
	@Override
	public List<HangOutFile> fileList() {
		return hangoutDao.selectFileAll();
	}
	
	@Override
	public List<HangOut> viewPlaceList(HangOut hangout, String[] filters){
		
		String city1 = hangout.gethCity1(); 
		String city2 = hangout.gethCity2(); 
		
		if(filters.length == 0) {
			return hangoutDao.selectByCity(hangout);
			
		} else if(city1.equals("") && city2.equals("")) {
			return hangoutDao.selectByFilter(filters);
			
		} else if(!city1.equals("") && city2.equals("")) {
			return hangoutDao.selectByCity1(hangout, filters);
			
		} else if(!city1.equals("") && !city2.equals("")){
			return hangoutDao.selectByCityFilter(hangout, filters);
		}
		
		return null;
		
	}
	
	@Override
	public HangOut gethNo(HttpServletRequest req) {

		HangOut hangout = new HangOut();
		
		int hNo = Integer.parseInt((String)req.getParameter("hNo"));
		
		hangout.sethNo(hNo);
		
		return hangout;
	}
	
	@Override
	public HangOut view(HangOut hangout) {
		
		return hangoutDao.selectByhNo(hangout);
	}
	
	@Override
	public HangOutFile viewFile(HangOut hangout) {
		
		return hangoutDao.selectFileByhNo(hangout);
	}
	
	@Override
	public void addPlace(HttpServletRequest req) {

		UserTB userTB = null;
		
		HangOut hangout = null;
		
		HangOutFile hangoutFile = null;
		

		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMultipart) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return;
		}
		
		
		userTB = new UserTB();
		hangout = new HangOut();
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(1 * 1024 * 1024);
		
		System.out.println("insert - req.getServletContext().getRealPath: " + req.getServletContext().getRealPath("tmp"));
		System.out.println("insert - req.getServletContext().getRealPath: " + req.getServletContext().getRealPath("upload"));
		File repository = new File(req.getServletContext().getRealPath("tmp")); //임시 저장 폴더
		factory.setRepository(repository);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setFileSizeMax(10 * 1024 * 1024);
		
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Iterator<FileItem> iter = items.iterator();

		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			// 1) 빈 파일 처리
			if(item.getSize() <= 0) continue;
		
			// 2) 일반적인 요청 데이터 처리
			if(item.isFormField()) {
				
				String key1 = item.getFieldName();
				if("name".equals(key1)) { 
						try {
							hangout.sethName(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				}  else if ("filter".equals(key1)) { 
						try {
							hangout.sethFilter(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("startTime".equals(key1)) { 
						try {
							hangout.setStartTime(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("endTime".equals(key1)) { 
						try {
							hangout.setEndTime(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("city1".equals(key1)) { 
						try {
							hangout.sethCity1(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("city2".equals(key1)) { 
						try {
							hangout.sethCity2(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("address".equals(key1)) { 
						try {
							hangout.setAddress(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("tel1".equals(key1)) { 
						try {
							hangout.setTel1(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("tel2".equals(key1)) { 
						try {
							hangout.setTel2(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("tel3".equals(key1)) { 
						try {
							hangout.setTel3(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("email".equals(key1)) { 
						try {
							hangout.sethEmail(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("domain".equals(key1)) { 
						try {
							hangout.sethDomain(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("content".equals(key1)) { 
						try {
							hangout.sethContent(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				} else if ("userId".equals(key1)) { 
						try {
							userTB.setUserId(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				}
				
				
			} //if(item.isFormField()) end
			
			
			
			// 3) 파일 처리
			if(!item.isFormField()) {
			
				UUID uuid = UUID.randomUUID(); 
				
				String u = uuid.toString().split("-")[0];
				
				
//				List<File> files = new ArrayList<>();
//
//				for(int i=0; i<files.size(); i++) {
				
					File up = new File(
							req.getServletContext().getRealPath("upload")
							,item.getName() + "_" + u 
							);

//				}
				
				


				hangoutFile = new HangOutFile();
				hangoutFile.setOriginName(item.getName()); 
				hangoutFile.setStoredName(item.getName()+"_"+u); 
				hangoutFile.setFileSize((int)item.getSize());
				
				
				try {
					item.write(up); //실제 업로드
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		hangout.sethTime(hangout.getStartTime() + " - " + hangout.getEndTime());
		hangout.sethAddress(hangout.gethCity1() + " " + hangout.gethCity2() + " " + hangout.getAddress());
		hangout.sethTel(hangout.getTel1() + "-" + hangout.getTel2() + "-" + hangout.getTel3());
		
		
		UserDao userDao = new UserDaoImpl();
		
		userTB = userDao.selectUserByUserid(userTB);
		
		
		int hNo = hangoutDao.selecthNo();
		
		if(hangout != null) {
			hangout.setUserNo(userTB.getUserNo());
			hangout.sethNo(hNo);
			
			hangoutDao.insert(hangout);
		}
		
		if(hangoutFile != null ) {
			hangoutFile.sethNo(hNo);
			hangoutFile.setUserNo(userTB.getUserNo());
			
			
			hangoutDao.insertFile(hangoutFile);
		}
		
	}
	
	@Override
	public void delete(HangOut hangout) {
		
		HangOutFile hangoutFile = new HangOutFile();
		hangoutFile.sethNo(hangout.gethNo());
		
		hangoutDao.deleteFile(hangoutFile);
		hangoutDao.delete(hangout);
		
	}
	
}
