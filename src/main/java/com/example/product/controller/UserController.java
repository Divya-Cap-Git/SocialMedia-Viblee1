package com.example.product.controller;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.product.dto.ExceptionMessage;
import com.example.product.dto.Friendsdto;
import com.example.product.dto.Likesdto;
import com.example.product.dto.MessageDto;
import com.example.product.dto.PostsDto;
import com.example.product.dto.PostsDto2;
import com.example.product.dto.UsersDto;
import com.example.product.dto.groupsDto;
import com.example.product.entity.Comments;
import com.example.product.entity.Friends;
import com.example.product.entity.Groups;
import com.example.product.entity.Likes;
import com.example.product.entity.Message;
import com.example.product.entity.Notifications;
import com.example.product.entity.Posts;
import com.example.product.entity.Users;
import com.example.product.exception.PostsNotFoundException;
import com.example.product.exception.UserNotFoundException;

import com.example.product.model.Response;
import com.example.product.service.FileStorageService;
import com.example.product.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins ="http://localhost:3000")
public class UserController {

	@Autowired
	UsersService service;
	

	@Autowired
    private FileStorageService fileStorageService;
	
	//added for images'''''''
		public static String uploadDirectory= System.getProperty("user.dir") + "/src/main/resources/static/Profile_Photoes";
		


		@GetMapping("/all")
		public ResponseEntity<List<UsersDto>> getAllUsers() {
			return new ResponseEntity<List<UsersDto>>(service.getAllUsers(), HttpStatus.OK);
		}

		@GetMapping("/{userid}")
		public ResponseEntity<UsersDto> getUsersById(@PathVariable("userid") int UserId) throws UserNotFoundException {
			return new ResponseEntity<UsersDto>(service.getUserbyId(UserId), HttpStatus.OK);
		}

		@GetMapping("/search/{username}")
		public ResponseEntity<UsersDto> getUsersByname(@PathVariable("username") String name) throws UserNotFoundException {
		    String decodedName = UriComponentsBuilder.fromPath("").queryParam("username", name).build().getQueryParams().getFirst("username");
		    return new ResponseEntity<>(service.getUserbyName(decodedName), HttpStatus.OK);
		}
		@PostMapping("/uploadimage/{userid}")
	    public Response uploadFile(@RequestParam("profile_picture") MultipartFile file, @PathVariable("userid") int UserId) throws IOException, UserNotFoundException{
	        String fileName = fileStorageService.storeFile(file);
	        Path fileNameAndPath=(Path) Paths.get(uploadDirectory, fileName);
	        Files.write(fileNameAndPath, file.getBytes());

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/downloadFile/")
	            .path(fileName)
	            .toUriString();
	        
	        UsersDto user=
	        		service.getUserbyId(UserId);
	        Users user1= new Users();
	        user1.setEmail(user.getEmail());
	        user1.setPassword(user.getPassword());
	        user1.setUsername(user.getUsername());
	        user1.setProfile_picture(fileDownloadUri);
	        
	        service.updateUsers(user, UserId);

	        return new Response(fileName, fileDownloadUri,
	            file.getContentType(), file.getSize());
	    }
	 
	   @PostMapping("/")
		public ResponseEntity<String> createUser(@Valid @RequestBody UsersDto users){
			service.addUsers(users);
			return new ResponseEntity<String>("Users added successfully",HttpStatus.OK);
		}


	  
		@PutMapping("/update/{userid}")
		public ResponseEntity<String> UpdateUser(@Valid @RequestBody UsersDto user,@PathVariable("userid") int userid) throws UserNotFoundException{
			
			service.updateUsers(user, userid);
			return new ResponseEntity<String>("Users updated successfully", HttpStatus.OK);
		}
		

		@DeleteMapping("/delete/{userid}")
		public ResponseEntity<String> deleteUsers(@PathVariable("userid") int userid) throws UserNotFoundException {
			service.deleteUser(userid);
			return new ResponseEntity<String>("Users deleted successfully", HttpStatus.OK);
			

		}


		@GetMapping("/posts/{userid}")
		public ResponseEntity<List<PostsDto2>> getallPosts(@PathVariable("userid") int UserId)
				throws UserNotFoundException, PostsNotFoundException {
			return new ResponseEntity<List<PostsDto2>>(service.getallPostOfUser(UserId), HttpStatus.OK);
		}
		
		@GetMapping("/{userid}/posts/comments")
		public ResponseEntity<List<Comments>> getallCommentonPosts(@PathVariable("userid") int UserId) throws UserNotFoundException{
			return new ResponseEntity<List<Comments>>(service.getAllCommentsInPostByUser(UserId), HttpStatus.OK);
		}


		@GetMapping("/{userid}/friends")
		public ResponseEntity<List<Friendsdto>> getallFriends(@PathVariable("userid") int UserId) throws UserNotFoundException {
			return new ResponseEntity<List<Friendsdto>>(service.getAllFriendsOfUser(UserId), HttpStatus.OK);
		}

		@GetMapping("/{userid}/friendrequest/pending")
		public ResponseEntity<List<Friendsdto>> getallFriendRequest(@PathVariable("userid") int UserId) throws UserNotFoundException{
			return new ResponseEntity<List<Friendsdto>>(service.getAllRequestInPending(UserId), HttpStatus.OK);
		}
		@PostMapping("/{userId}/friendrequest/send/{otherUserId}")
		public ResponseEntity<String> sendRequest(@PathVariable int userId, @PathVariable int otherUserId) throws UserNotFoundException {
			String result = service.sendRequest(userId, otherUserId);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}

		
		@GetMapping("/{userId}/message/{otherUserId}")
		public ResponseEntity<List<MessageDto>> getallMessages(@PathVariable int userId, @PathVariable int otherUserId) throws UserNotFoundException{
			return new ResponseEntity<List<MessageDto>>(service.getAllMessageBetweenUser(userId, otherUserId), HttpStatus.OK);
		}

		@PostMapping("/{userId}/message/send/{otherUserId}")
		public ResponseEntity<String> sendMessage(@PathVariable int userId, @PathVariable int otherUserId,
				@RequestBody String message)throws UserNotFoundException {
			 String pattern = "[^\\w\\s]"; 
			 String sanitizedMessage = message.replaceAll(pattern, " ");
			System.out.println(message);
//			String decodedMessage = new String(Base64.getDecoder().decode(message), StandardCharsets.UTF_8);
//			System.out.println(decodedMessage);
			String result = service.sendMessage(userId, otherUserId,  sanitizedMessage);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		@GetMapping("/{userid}/posts/likes")
		public ResponseEntity<List<Likesdto>> getallLikesOnPost(@PathVariable("userid") int UserId) throws UserNotFoundException{
			return new ResponseEntity<List<Likesdto>>(service.getlikesPostbyUser(UserId), HttpStatus.OK);
		}
		
		@GetMapping("/{userid}/likes")
		public ResponseEntity<List<PostsDto>> getallPostsLikedByUser(@PathVariable("userid") int UserId) throws UserNotFoundException{
			return new ResponseEntity<List<PostsDto>>(service.getAllPostsLikedByUser(UserId), HttpStatus.OK);
		}
		@GetMapping("/{userid}/notifications")
		public ResponseEntity<List<Notifications>> getallNotifications(@PathVariable("userid") int UserId) throws UserNotFoundException{
			return new ResponseEntity<List<Notifications>>(service.getnotification(UserId), HttpStatus.OK);
		}
		
		@PostMapping("/{userid}/notifications")
		public ResponseEntity<String> AddNotifications(@PathVariable("userid") int UserId ,@RequestBody String notifications) throws UserNotFoundException{
			 String pattern = "[^\\w\\s]"; 
			 String sanitizedNotifications = notifications.replaceAll(pattern, " ");
			String newNotification=service.addNotifications(UserId, sanitizedNotifications);
			return new ResponseEntity<>(newNotification, HttpStatus.CREATED);
			
			
		}
		
		

		@GetMapping("/{userid}/groups")
		public ResponseEntity<List<groupsDto>> getgroups(@PathVariable("userid") int UserId) throws UserNotFoundException{
			return new ResponseEntity<List<groupsDto>>(service.getGroups(UserId), HttpStatus.OK);
		}
		
		

		@PostMapping("/{userId}/friends/{otherUserId}")
		public ResponseEntity<String> AddFriend(@PathVariable int userId, @PathVariable int otherUserId) throws UserNotFoundException {
			String result = service.AddFriend(userId, otherUserId);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}

		
		@PutMapping("/{userId}/friends/{friendshipId}/{otherUserId}")
		public ResponseEntity<String> AcceptFriend(@PathVariable int userId,@PathVariable int friendshipId, @PathVariable int otherUserId) throws UserNotFoundException {
			String result = service.AcceptFriend(userId,friendshipId, otherUserId);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}
	

		@DeleteMapping("/{userId}/friends/{otherUserId}")
		public ResponseEntity<String> RemoveFriends(@PathVariable int userId, @PathVariable int otherUserId) throws UserNotFoundException {
			String result = service.removeFriend(userId, otherUserId);
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		}
		
		@GetMapping("/random/{userId}")
		public ResponseEntity<List<UsersDto>> getRandomUsers(@PathVariable int userId) throws UserNotFoundException{
			return new ResponseEntity<List<UsersDto>>(service.getRandomUsers(userId), HttpStatus.OK);
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
