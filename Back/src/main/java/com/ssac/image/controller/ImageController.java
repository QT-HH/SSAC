package com.ssac.image.controller;

import com.ssac.image.dto.Image;
import com.ssac.image.service.ImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/file")
public class ImageController {
	@Autowired
    private ImageService imageService;
	
	@ApiOperation(value = "뉴스피드에 이미지 넣기", notes = "입력 : file(MultipartFile형식의 이미지)")
	@PostMapping("/newsfeedImageAdd")
	public ResponseEntity<?> addImageNewsFeed(MultipartFile file) throws Exception {
		try {
			System.out.println("뉴스피드 이미지 넣기");
			return new ResponseEntity<>(imageService.addImageIntoNewsFeed(file), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail", HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로필에 이미지 넣기", notes = "입력 : file(MultipartFile형식의 이미지)")
	@PostMapping("/profileImageAdd")
	public ResponseEntity<?> addImageProfile(MultipartFile file) throws Exception {
		try {
			System.out.println("프로필 이미지 넣기");
			return new ResponseEntity<>(imageService.addImageIntoProfile(file), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail", HttpStatus.OK);
	}
//    @ApiOperation(value="이미지 서빙(n개)", notes = "여러개의 이미지 불러오기", httpMethod = "GET")
//    @GetMapping(value = "/fileServe")
//    public Response fileServe(@ApiParam(value="파일 고유값 리스트") @RequestParam(value="fileIds",required = true) List<Integer> fileIds) throws IllegalStateException {
//        Response response;
//
//        try {
//            response = new Response("success", "파일 서빙 성공", service.filesServe(fileIds));
//        } catch(Exception e){
//            System.out.println("sad");
//            response = new Response("error","파일 서빙 실패",e.getMessage());
//        }
//
//        return response;
//    }
//
//    @ApiOperation(value="이미지 서빙(1개)", notes = "이미지 불러오기", httpMethod = "GET")
//    @GetMapping(value = "/fileServe/{fileId}")
//    public Response fileServe(@ApiParam(value="파일 고유값") @PathVariable int fileId) throws IllegalStateException {
//        Response response;
//
//        try {
//            response = new Response("success", "파일 서빙 성공", service.fileServe(fileId));
//        } catch(Exception e){
//            System.out.println("sad");
//            response = new Response("error","파일 서빙 실패",e.getMessage());
//        }
//
//        return response;
//    }
//
//    @ApiOperation(value="이미지 삭제", notes = "이미지 삭제하기", httpMethod = "DELETE")
//    @DeleteMapping(value = "/delete/{fileId}")
//    public Response delete(@ApiParam(value="파일 고유값") @PathVariable int fileId) throws IllegalStateException {
//        Response response;
//
//        try {
//            service.delete(fileId);
//            response = new Response("success", "파일 삭제 성공", null);
//        } catch(Exception e){
//            System.out.println("sad");
//            response = new Response("error","파일 삭제 실패",e.getMessage());
//        }
//
//        return response;
//    }
}