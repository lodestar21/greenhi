package com.greenhi.admin.upload.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.json.JSONObject;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.greenhi.common.Constants;
import com.greenhi.common.util.StringUtil;

@Controller
@RequestMapping( "/Upload" )
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	/**
	 * 사진1 업로드
	 * @param uploadImg
	 * @param res
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping( value = "/photoImg1Upload", method = RequestMethod.POST )
	public void photoImg1Upload( @RequestParam( "photoImg1Upload" ) MultipartFile uploadImg,
			HttpServletResponse res ) throws Exception {

		JSONObject json = new JSONObject();
		
		try {

			if ( uploadImg.getSize() <= 5242880 ) {

				// 중복없이 파일명 생성
				String fileName = uploadImg.getOriginalFilename();
				long currentTime = System.currentTimeMillis();
				int pos = fileName.lastIndexOf( "." );
				String fileExt = fileName.substring( pos + 1 );
				String uuid = UUID.randomUUID().toString().replace( "-", "" );
				String imgSaveName = currentTime + "_" + uuid + "." + fileExt;
				String serverFullPath = Constants.IMG_URL + imgSaveName;
				
				// 파일 저장
				byte[] bytes = uploadImg.getBytes();
				File serverFile = new File( serverFullPath );
				BufferedOutputStream stream = new BufferedOutputStream(  new FileOutputStream(serverFile) );
				stream.write(bytes);
				stream.close();

				Metadata metadata = JpegMetadataReader.readMetadata( serverFile );
				Iterable<Directory> directories = metadata.getDirectories();
				String tagDesc = "";
				
				for ( Directory mataFile : directories ) {
					 Collection<Tag> tags = mataFile.getTags();
					 for ( Tag tag : tags ) {
						 if ( tag.getTagName().equals( "Date/Time" ) ) {
							 tagDesc = tag.getDescription();
						 }
						 if ( StringUtil.isEmpty( tagDesc ) ) {
							 if ( tag.getTagName().equals( "Date/Time Original" ) ) {
								 tagDesc = tag.getDescription();
							 }
						 }
					 }
				}
				
				json.put( "result", 1 );
				json.put( "imgSaveName", imgSaveName );
				json.put( "tagDesc", tagDesc );
				json.put( "message", "파일 업로드 완료" );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
			} else {

				json.put( "result", 2 );
				json.put( "message", "파일 사이즈는 5MB를 넘을 수 없습니다." );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
				
			}
		} catch (Exception e) {
			logger.error( "Thread[" + Thread.currentThread().getId() + "]" + "photoImg1Upload fail", e );
			json.put( "result", 2 );
			json.put( "message", "파일 업로드 오류입니다." );

			res.setContentType( "text/html;charset=utf-8" );
			res.getWriter().print( json.toString() );
		}
		
	}

	/**
	 * 사진2 업로드
	 * @param uploadImg
	 * @param res
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping( value = "/photoImg2Upload", method = RequestMethod.POST )
	public void photoImg2Upload( @RequestParam( "photoImg2Upload" ) MultipartFile uploadImg,
			HttpServletResponse res ) throws Exception {

		JSONObject json = new JSONObject();
		
		try {

			if ( uploadImg.getSize() <= 5242880 ) {

				// 중복없이 파일명 생성
				String fileName = uploadImg.getOriginalFilename();
				long currentTime = System.currentTimeMillis();
				int pos = fileName.lastIndexOf( "." );
				String fileExt = fileName.substring( pos + 1 );
				String uuid = UUID.randomUUID().toString().replace( "-", "" );
				String imgSaveName = currentTime + "_" + uuid + "." + fileExt;
				String serverFullPath = Constants.IMG_URL + imgSaveName;
				
				// 파일 저장
				byte[] bytes = uploadImg.getBytes();
				File serverFile = new File( serverFullPath );
				BufferedOutputStream stream = new BufferedOutputStream(  new FileOutputStream(serverFile) );
				stream.write(bytes);
				stream.close();

				Metadata metadata = JpegMetadataReader.readMetadata( serverFile );
				Iterable<Directory> directories = metadata.getDirectories();
				String tagDesc = "";
				
				for ( Directory mataFile : directories ) {
					 Collection<Tag> tags = mataFile.getTags();
					 for ( Tag tag : tags ) {
						 if ( tag.getTagName().equals( "Date/Time" ) ) {
							 tagDesc = tag.getDescription();
						 }
						 if ( StringUtil.isEmpty( tagDesc ) ) {
							 if ( tag.getTagName().equals( "Date/Time Original" ) ) {
								 tagDesc = tag.getDescription();
							 }
						 }
					 }
				}
				
				json.put( "result", 1 );
				json.put( "imgSaveName", imgSaveName );
				json.put( "tagDesc", tagDesc );
				json.put( "message", "파일 업로드 완료" );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
			} else {

				json.put( "result", 2 );
				json.put( "message", "파일 사이즈는 5MB를 넘을 수 없습니다." );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
				
			}
		} catch (Exception e) {
			logger.error( "Thread[" + Thread.currentThread().getId() + "]" + "photoImg2Upload fail", e );
			json.put( "result", 2 );
			json.put( "message", "파일 업로드 오류입니다." );

			res.setContentType( "text/html;charset=utf-8" );
			res.getWriter().print( json.toString() );
		}
		
	}

	/**
	 * 사진3 업로드
	 * @param uploadImg
	 * @param res
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping( value = "/photoImg3Upload", method = RequestMethod.POST )
	public void photoImg3Upload( @RequestParam( "photoImg3Upload" ) MultipartFile uploadImg,
			HttpServletResponse res ) throws Exception {

		JSONObject json = new JSONObject();
		
		try {

			if ( uploadImg.getSize() <= 5242880 ) {

				// 중복없이 파일명 생성
				String fileName = uploadImg.getOriginalFilename();
				long currentTime = System.currentTimeMillis();
				int pos = fileName.lastIndexOf( "." );
				String fileExt = fileName.substring( pos + 1 );
				String uuid = UUID.randomUUID().toString().replace( "-", "" );
				String imgSaveName = currentTime + "_" + uuid + "." + fileExt;
				String serverFullPath = Constants.IMG_URL + imgSaveName;
				
				// 파일 저장
				byte[] bytes = uploadImg.getBytes();
				File serverFile = new File( serverFullPath );
				BufferedOutputStream stream = new BufferedOutputStream(  new FileOutputStream(serverFile) );
				stream.write(bytes);
				stream.close();

				Metadata metadata = JpegMetadataReader.readMetadata( serverFile );
				Iterable<Directory> directories = metadata.getDirectories();
				String tagDesc = "";
				
				for ( Directory mataFile : directories ) {
					 Collection<Tag> tags = mataFile.getTags();
					 for ( Tag tag : tags ) {
						 if ( tag.getTagName().equals( "Date/Time" ) ) {
							 tagDesc = tag.getDescription();
						 }
						 if ( StringUtil.isEmpty( tagDesc ) ) {
							 if ( tag.getTagName().equals( "Date/Time Original" ) ) {
								 tagDesc = tag.getDescription();
							 }
						 }
					 }
				}
				
				json.put( "result", 1 );
				json.put( "imgSaveName", imgSaveName );
				json.put( "tagDesc", tagDesc );
				json.put( "message", "파일 업로드 완료" );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
			} else {

				json.put( "result", 2 );
				json.put( "message", "파일 사이즈는 5MB를 넘을 수 없습니다." );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
				
			}
		} catch (Exception e) {
			logger.error( "Thread[" + Thread.currentThread().getId() + "]" + "photoImg3Upload fail", e );
			json.put( "result", 2 );
			json.put( "message", "파일 업로드 오류입니다." );

			res.setContentType( "text/html;charset=utf-8" );
			res.getWriter().print( json.toString() );
		}
		
	}

	/**
	 * 사진4 업로드
	 * @param uploadImg
	 * @param res
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping( value = "/photoImg4Upload", method = RequestMethod.POST )
	public void photoImg4Upload( @RequestParam( "photoImg4Upload" ) MultipartFile uploadImg,
			HttpServletResponse res ) throws Exception {

		JSONObject json = new JSONObject();
		
		try {

			if ( uploadImg.getSize() <= 5242880 ) {

				// 중복없이 파일명 생성
				String fileName = uploadImg.getOriginalFilename();
				long currentTime = System.currentTimeMillis();
				int pos = fileName.lastIndexOf( "." );
				String fileExt = fileName.substring( pos + 1 );
				String uuid = UUID.randomUUID().toString().replace( "-", "" );
				String imgSaveName = currentTime + "_" + uuid + "." + fileExt;
				String serverFullPath = Constants.IMG_URL + imgSaveName;
				
				// 파일 저장
				byte[] bytes = uploadImg.getBytes();
				File serverFile = new File( serverFullPath );
				BufferedOutputStream stream = new BufferedOutputStream(  new FileOutputStream(serverFile) );
				stream.write(bytes);
				stream.close();

				Metadata metadata = JpegMetadataReader.readMetadata( serverFile );
				Iterable<Directory> directories = metadata.getDirectories();
				String tagDesc = "";
				
				for ( Directory mataFile : directories ) {
					 Collection<Tag> tags = mataFile.getTags();
					 for ( Tag tag : tags ) {
						 if ( tag.getTagName().equals( "Date/Time" ) ) {
							 tagDesc = tag.getDescription();
						 }
						 if ( StringUtil.isEmpty( tagDesc ) ) {
							 if ( tag.getTagName().equals( "Date/Time Original" ) ) {
								 tagDesc = tag.getDescription();
							 }
						 }
					 }
				}
				
				json.put( "result", 1 );
				json.put( "imgSaveName", imgSaveName );
				json.put( "tagDesc", tagDesc );
				json.put( "message", "파일 업로드 완료" );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
			} else {

				json.put( "result", 2 );
				json.put( "message", "파일 사이즈는 5MB를 넘을 수 없습니다." );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
				
			}
		} catch (Exception e) {
			logger.error( "Thread[" + Thread.currentThread().getId() + "]" + "photoImg4Upload fail", e );
			json.put( "result", 2 );
			json.put( "message", "파일 업로드 오류입니다." );

			res.setContentType( "text/html;charset=utf-8" );
			res.getWriter().print( json.toString() );
		}
		
	}

	/**
	 * 사진5 업로드
	 * @param uploadImg
	 * @param res
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping( value = "/photoImg5Upload", method = RequestMethod.POST )
	public void photoImg5Upload( @RequestParam( "photoImg5Upload" ) MultipartFile uploadImg,
			HttpServletResponse res ) throws Exception {

		JSONObject json = new JSONObject();
		
		try {

			if ( uploadImg.getSize() <= 5242880 ) {

				// 중복없이 파일명 생성
				String fileName = uploadImg.getOriginalFilename();
				long currentTime = System.currentTimeMillis();
				int pos = fileName.lastIndexOf( "." );
				String fileExt = fileName.substring( pos + 1 );
				String uuid = UUID.randomUUID().toString().replace( "-", "" );
				String imgSaveName = currentTime + "_" + uuid + "." + fileExt;
				String serverFullPath = Constants.IMG_URL + imgSaveName;
				
				// 파일 저장
				byte[] bytes = uploadImg.getBytes();
				File serverFile = new File( serverFullPath );
				BufferedOutputStream stream = new BufferedOutputStream(  new FileOutputStream(serverFile) );
				stream.write(bytes);
				stream.close();

				Metadata metadata = JpegMetadataReader.readMetadata( serverFile );
				Iterable<Directory> directories = metadata.getDirectories();
				String tagDesc = "";
				
				for ( Directory mataFile : directories ) {
					 Collection<Tag> tags = mataFile.getTags();
					 for ( Tag tag : tags ) {
						 if ( tag.getTagName().equals( "Date/Time" ) ) {
							 tagDesc = tag.getDescription();
						 }
						 if ( StringUtil.isEmpty( tagDesc ) ) {
							 if ( tag.getTagName().equals( "Date/Time Original" ) ) {
								 tagDesc = tag.getDescription();
							 }
						 }
					 }
				}
				
				json.put( "result", 1 );
				json.put( "imgSaveName", imgSaveName );
				json.put( "tagDesc", tagDesc );
				json.put( "message", "파일 업로드 완료" );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
			} else {

				json.put( "result", 2 );
				json.put( "message", "파일 사이즈는 5MB를 넘을 수 없습니다." );

				res.setContentType( "text/html;charset=utf-8" );
				res.getWriter().print( json.toString() );
				
			}
		} catch (Exception e) {
			logger.error( "Thread[" + Thread.currentThread().getId() + "]" + "photoImg5Upload fail", e );
			json.put( "result", 2 );
			json.put( "message", "파일 업로드 오류입니다." );

			res.setContentType( "text/html;charset=utf-8" );
			res.getWriter().print( json.toString() );
		}
		
	}
}
