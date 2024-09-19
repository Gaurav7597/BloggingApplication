package org.BloggingApplication.blog.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.BloggingApplication.blog.Config.AppConstants;
import org.BloggingApplication.blog.entities.Post;
import org.BloggingApplication.blog.payloads.ApiResponse;
import org.BloggingApplication.blog.payloads.CategoryDto;
import org.BloggingApplication.blog.payloads.PostDto;
import org.BloggingApplication.blog.payloads.PostResponse;
import org.BloggingApplication.blog.services.FileService;
import org.BloggingApplication.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;


        //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable Integer userId , @PathVariable Integer categoryId)
    {
            PostDto createdPost=this.postService.createPost(postDto , userId , categoryId);
            return new ResponseEntity<PostDto>(createdPost , HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts , HttpStatus.OK);

    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts , HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        this.postService.getPostById(postId);
        return  ResponseEntity.ok(this.postService.getPostById(postId));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNo" , defaultValue = AppConstants.PAGE_NO, required = false) Integer pageNo ,
            @RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
            @RequestParam(value = "sortBy" , defaultValue = AppConstants.SORT_BY , required = false) String sortBy ,
            @RequestParam(value = "sortDir" , defaultValue = AppConstants.SORT_DIR , required = false) String sortDir
    )
    {
        return  ResponseEntity.ok(this.postService.getAllPost(pageNo , pageSize , sortBy , sortDir));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully" , true) , HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId)
    {
        PostDto updatePostDto = this.postService.updatePost(postDto , postId);
        return  ResponseEntity.ok(updatePostDto);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
        List<PostDto> result = this.postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImageResponse(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer postId) throws IOException
    {
           PostDto postDto = this.postService.getPostById(postId);
           String fileName =  this.fileService.uploadImage(path, image );
           postDto.setImageName(fileName);
           PostDto updatePost =this.postService.updatePost(postDto , postId);
           return new ResponseEntity<PostDto>(updatePost , HttpStatus.OK);
    }

    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage
            (@PathVariable("imageName") String imageName ,
             HttpServletResponse response ) throws IOException
    {
        InputStream resource = this.fileService.getResource(path , imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource , response.getOutputStream());
    }




}
