package com.example.product.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.product.dto.CommentsDto;
import com.example.product.dto.ExceptionMessage;
import com.example.product.dto.Likesdto;
import com.example.product.dto.PostsDto;
import com.example.product.dto.PostsDto2;
import com.example.product.dto.UsersDto;
import com.example.product.entity.Likes;
import com.example.product.entity.Posts;
import com.example.product.entity.Users;
import com.example.product.exception.PostsNotFoundException;
import com.example.product.exception.UserNotFoundException;
import com.example.product.model.Response;
import com.example.product.service.FileStorageService;
import com.example.product.service.PostsService;
import com.example.product.service.UsersService;

@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin(origins ="http://localhost:3000")
public class PostsController {
	
    @Autowired
	PostsService postsService;
    @Autowired
    UsersService service;
    
    @Autowired
    private FileStorageService fileStorageService;
	
	//added for images'''''''
		public static String uploadDirectory= System.getProperty("user.dir") + "/src/main/resources/static/Post_Photos";
		
		@PostMapping("/uploadimage/{postid}")
	    public Response uploadFile(@RequestParam("post_picture") MultipartFile file, @PathVariable("postid") int PostId) throws IOException, UserNotFoundException, PostsNotFoundException{
	        String fileName = fileStorageService.storeFile(file);
	        Path fileNameAndPath=(Path) Paths.get(uploadDirectory, fileName);
	        Files.write(fileNameAndPath, file.getBytes());

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/downloadFile/")
	            .path(fileName)
	            .toUriString();
	        
	     

	        PostsDto2 post = postsService.getPostById(PostId);
	        post.setPost_picture(fileDownloadUri);
	       
	       
	        Posts post1=new Posts();
	        post1.setContent(post.getContent());
	        post1.setPostID(post.getPostId());
	        post1.setTimestamp(post.getTimestamp());
	        UsersDto user=service.getUserbyId(post.getUserId());
	        
	        Users user1= new Users();
	        user1.setEmail(user.getEmail());
	        user1.setPassword(user.getPassword());
	        user1.setUsername(user.getUsername());
	        user1.setProfile_picture(user.getProfile_picture());
	        user1.setUserID(user.getUserid());
	        
	      post1.setUsers(user1);
	        
	        post1.setPost_picture(fileDownloadUri);
	    
	        postsService.UpdatePosts(post1, PostId);

	        return new Response(fileName, fileDownloadUri,
	            file.getContentType(), file.getSize());
	    }
		
		
		//getall posts............
		
		@GetMapping("/allposts")
		public ResponseEntity<List<PostsDto2>> getEveryPosts(){
			return new ResponseEntity<>(postsService.getEveryPost(),HttpStatus.OK);
		}
		@GetMapping("/allpost")
		public ResponseEntity<List<Posts>> getAllPost(){
			return new ResponseEntity<>(postsService.getAllPost(),HttpStatus.OK);
		}
    
    @PostMapping("/{userid}")
    public ResponseEntity<PostsDto2> createPost(@RequestBody Posts post,@PathVariable int userid) throws UserNotFoundException{
        
        return new ResponseEntity<>(postsService.createPost(post,userid), HttpStatus.CREATED);
    }
    
    @GetMapping("/{postid}")
   	public ResponseEntity<PostsDto2> getPostById(@PathVariable("postid") int PostId) throws PostsNotFoundException{
   		return new ResponseEntity<PostsDto2>(postsService.getPostById(PostId),HttpStatus.OK);
   	}
    @PutMapping("/update/{postid}")
   	public ResponseEntity<String> UpdatePosts(@RequestBody Posts posts,@PathVariable("postid") int postid) throws PostsNotFoundException{
    	postsService.UpdatePosts(posts, postid);
    	return new ResponseEntity<String>( "Post is updated successfully",HttpStatus.OK);
   		 
   }
    @DeleteMapping("/delete/{postid}")
	public ResponseEntity<String> deletePosts(@PathVariable("postid") int postid) throws PostsNotFoundException{
		postsService.deletePosts(postid);
		return new ResponseEntity<String>( "Post is deleted successfully",HttpStatus.OK);
		
	}
    @PostMapping("/{postId}/likes/add/{UserId}")
    public ResponseEntity<String> addLikeToPost(@PathVariable int postId, @PathVariable int UserId) throws UserNotFoundException,PostsNotFoundException{
        String result = postsService.addliketoPost(postId, UserId);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
    @GetMapping("/likes/{postid}")
	public ResponseEntity<List<Likesdto>> getLikesonPost(@PathVariable("postid") int PostId) throws PostsNotFoundException{
		return new ResponseEntity<List<Likesdto>>(postsService.getallLikesinPost(PostId),HttpStatus.OK);
	}
    @DeleteMapping("/{postId}/likes/remove/{UserId}")
    public ResponseEntity<Void> RemoveLike(@PathVariable int postId, @PathVariable int UserId) throws UserNotFoundException,PostsNotFoundException {
        postsService.removeLikeFromPost(postId, UserId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
       
    }
    
   
  
    
    //checked till here..............
    
    @PostMapping("/{postId}/comment/add/{UserId}")
    public ResponseEntity<String> addCommentToPost(@PathVariable int postId, @PathVariable int UserId,@RequestBody String Comment) throws PostsNotFoundException,UserNotFoundException{
        String result = postsService.addCommentinPost(postId, UserId,Comment);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
    
   
    @GetMapping("/comments/{postid}")
   	public ResponseEntity<List<CommentsDto>> getCommentsonPost(@PathVariable("postid") int PostId) throws PostsNotFoundException{
   		return new ResponseEntity<List<CommentsDto>>(postsService.getallCommentsInPost(PostId),HttpStatus.OK);
   	}
    
    
   
    
	
	
	
	
	@DeleteMapping("/{postId}/comments/remove/{UserId}")
    public ResponseEntity<Void> RemoveComment(@PathVariable int postId, @PathVariable int UserId) throws UserNotFoundException,PostsNotFoundException {
        postsService.RemoveCommentFromPost(postId, UserId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
     }
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> UserNotFoundException(UserNotFoundException ex) {
		ExceptionMessage message = new ExceptionMessage(ex.getMessage());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> PostsNotFoundException(PostsNotFoundException ex) {
		ExceptionMessage message = new ExceptionMessage(ex.getMessage());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);

	}
	
}

