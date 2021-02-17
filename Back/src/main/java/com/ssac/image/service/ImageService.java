package com.ssac.image.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssac.image.dto.Image;
import com.ssac.mappers.ImageMapper;
import com.ssac.team.dto.Team;
import com.ssac.team.service.TeamService;

import javax.imageio.ImageIO;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ImageService {
	@Autowired
	private ImageMapper mapper;
	
	public Image addImageIntoNewsFeed(MultipartFile file) throws Exception {
		if(file.isEmpty()) throw new Exception("파일없음");
		// 파일이름에서 확장자부분만 때기
		String originName = file.getOriginalFilename();
		String ext = "";
		int index = originName.lastIndexOf(".");
		if(index != -1) ext = originName.substring(index);
		// 서버에 저장될 사진이름
		String filename = UUID.randomUUID().toString() + ext;
		// 서버에 저장될 폴더
		String folderRoot = "/home/image/newsfeed/";
		// 폴더에 접근
		File pFile = new File(folderRoot);
		// 폴더에 사진 저장
		file.transferTo(new File(pFile,filename));
		// 폴더에 저장된 사진 불러오기
		InputStream imgStream = new FileInputStream(folderRoot+filename);
		// 이미지 blob형태로 변형
		byte[] blob = IOUtils.toByteArray(imgStream);
		// 폴더 닫기
		imgStream.close();
		// 이미지 객체에 저장
		Image image = new Image();
		image.setFilename(filename);
		image.setBlob(blob);
		return image;
	}
	
	public Image addImageIntoProfile(MultipartFile file) throws Exception {
		if(file.isEmpty()) throw new Exception("파일없음");
		// 파일이름에서 확장자부분만 때기
		String originName = file.getOriginalFilename();
		String ext = "";
		int index = originName.lastIndexOf(".");
		if(index != -1) ext = originName.substring(index);
		// 서버에 저장될 사진이름
		String filename = UUID.randomUUID().toString() + ext;
		// 서버에 저장될 폴더
		String folderRoot = "/home/image/profile/";
		// 폴더에 접근
		File pFile = new File(folderRoot);
		// 폴더에 사진 저장
		file.transferTo(new File(pFile,filename));
		// 폴더에 저장된 사진 불러오기
		InputStream imgStream = new FileInputStream(folderRoot+filename);
		// 이미지 blob형태로 변형
		byte[] blob = IOUtils.toByteArray(imgStream);
		// 폴더 닫기
		imgStream.close();
		// 이미지 객체에 저장
		Image image = new Image();
		image.setFilename(filename);
		image.setBlob(blob);
		return image;
	}
	
	public Image feedFilenameToBlob(String filename) throws Exception {
		if(filename == null || filename.equals("")) return null;
		System.out.println(filename);
		String folderRoot = "/home/image/newsfeed/";
		InputStream imgStream = new FileInputStream(folderRoot+filename);
	    byte[] imgByteArray = IOUtils.toByteArray(imgStream);
	    imgStream.close();
	    Image image = new Image();
	    image.setFilename(filename);
	    image.setBlob(imgByteArray);
		return image;
	}
	
	public Image profileFilenameToBlob(String filename) throws Exception {
		if(filename == null || filename.equals("")) return null;
		String folderRoot = "/home/image/profile/";
		InputStream imgStream = new FileInputStream(folderRoot+filename);
	    byte[] imgByteArray = IOUtils.toByteArray(imgStream);
	    imgStream.close();
	    Image image = new Image();
	    image.setFilename(filename);
	    image.setBlob(imgByteArray);
		return image;
	}

//    public Image addFile(MultipartFile file) throws Exception{
//        if(file.isEmpty())  throw new Exception("파일 등록 실패 (파일 객체 비었음)");
//        String originName = file.getOriginalFilename();
//        System.out.println(originName);
//        String ext = "";
//        int index = originName.lastIndexOf(".");
//        if(index!=-1){
//            ext = originName.substring(index);
//        }
//
//
//        //저장할 이름
//        String systemName = UUID.randomUUID().toString() + ext;
//
//        String currentTime = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//        StringTokenizer st = new StringTokenizer(currentTime,"/");
//        //저장될 경로
//        //String fsl = File.pathSeparator;
//        //String fsl = "\\";
//        String fsl="/";
//
//        StringBuilder pathRoot = new StringBuilder();
//        pathRoot.append("/home/upload/image");
//
//        StringBuilder modulePath = new StringBuilder();
//        modulePath.append(st.nextToken())
//                .append(fsl).append(st.nextToken())
//                .append(fsl).append(st.nextToken());
//
//        String totalPath = pathRoot.toString()+fsl+modulePath.toString();
//        File pFile = new File(totalPath);
//        //폴더 있는지 확인하고 폴더 생성
//        if(pFile.exists()==false) {
//            pFile.mkdirs();
//        }
//
//        //서버에 파일 생성하기
//        //pfile의 설정된 경로를 준다
//        file.transferTo(new File(pFile,systemName));
//
//        Image image = new Image();
//        image.setOriginName(originName);
//        image.setSystemName(systemName);
//        image.setType(file.getContentType());
//        image.setSize((int)file.getSize());
//        image.setPath(modulePath.toString());
//
//        Image doneImage = repository.save(image);
//        InputStream imgStream = new FileInputStream(totalPath+fsl+systemName);
//        byte[] imgByteArray = IOUtils.toByteArray(imgStream);
//        imgStream.close();
//
//        doneImage.setImageBytes(imgByteArray);
//
//        return doneImage;
//    }
//
//    public List<Image> filesServe(List<Integer> fileIds) throws IOException {
//        List<Image> images = new ArrayList<>();
//        for (Integer fileId : fileIds) {
//            images.add(fileServe(fileId));
//        }
//        return images;
//    }
//
//    public Image fileServe(int fileId) throws IOException {
//
//        Image image = repository.findById(fileId);
//
//        //String fsl = File.pathSeparator;
//        //String fsl = "\\";
//        String fsl="/";
//
//        StringBuilder path = new StringBuilder();
//        path.append("/home/upload/image");
//        //path.append("C:\\Users\\sumin\\etc\\upload\\eureka");
//
//        path.append(fsl).append(image.getPath())
//                .append(fsl).append(image.getSystemName());
//
//        InputStream imgStream = new FileInputStream(path.toString());
//        byte[] imgByteArray = IOUtils.toByteArray(imgStream);
//        imgStream.close();
//
//        image.setImageBytes(imgByteArray);
//
//        return image;
//    }
//
//    public void delete(int fileId) throws Exception {
//
//        Image image = repository.findById(fileId);
//        repository.deleteAllById(fileId);
//
//        //String fsl = File.pathSeparator;
//        //String fsl = "\\";
//        String fsl="/";
//
//        StringBuilder pathRoot = new StringBuilder();
//        pathRoot.append("/home/upload/image");
//
//        pathRoot.append(fsl).append(image.getPath())
//                .append(fsl).append(image.getSystemName());
//        File file = new File(pathRoot.toString());
//        if(file.exists()){
//            if(!file.delete())  throw new Exception("파일 삭제 실패");
//        }
//    }


}