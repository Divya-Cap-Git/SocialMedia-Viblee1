package com.example.product.service;


import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.dto.Friendsdto;
import com.example.product.dto.Likesdto;
import com.example.product.dto.MessageDto;
import com.example.product.dto.PostsDto;
import com.example.product.dto.PostsDto2;
import com.example.product.dto.UsersDto;
import com.example.product.dto.groupsDto;
import com.example.product.dto.statustype;
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
import com.example.product.repository.FriendsRepository;
import com.example.product.repository.MessageRepository;
import com.example.product.repository.NotificationRepository;
import com.example.product.repository.UsersRepository;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository repo;

	@Autowired
	FriendsRepository friendsrepo;

	@Autowired
	MessageRepository messagerepo;
	
	@Autowired
	NotificationRepository notificationrepo;

	@Override
	public UsersDto getUserbyId(int userId) throws UserNotFoundException {
		if (repo.findById(userId).isEmpty())
			throw new UserNotFoundException("User not found");

		Users u = repo.findById(userId).get();

		UsersDto users = new UsersDto();
		users.setUserid(u.getUserID());
		users.setUsername(u.getUsername());
		users.setEmail(u.getEmail());
		users.setPassword(u.getPassword());
		users.setProfile_picture(u.getProfile_picture());
		return users;
	}

	@Override
	public List<UsersDto> getAllUsers() {

		List<Users> usersList = repo.findAll();
		List<UsersDto> usersdtoList = new ArrayList<>();

		for (Users u : usersList) {
			UsersDto users = new UsersDto();
			users.setUserid(u.getUserID());
			users.setUsername(u.getUsername());
			users.setEmail(u.getEmail());
			users.setPassword(u.getPassword());
			users.setProfile_picture(u.getProfile_picture());
			usersdtoList.add(users);

		}
		return usersdtoList;
	}

	@Override
	public UsersDto getUserbyName(String name) throws UserNotFoundException {
	    UsersDto usersDto = new UsersDto();
	    
	    // Using the stream to find the first user that matches the username
	    Optional<Users> userOptional = repo.findAll().stream()
	                                         .filter(u -> u.getUsername().equals(name))
	                                         .findFirst();
	    
	    // If user is not found, orElseThrow will throw UserNotFoundException
	 
	    Users user = userOptional.orElseThrow(() -> new UserNotFoundException("User not found with name: " + name));

	    
	    // Set properties from the found user entity to the DTO
	    usersDto.setUserid(user.getUserID());
	    usersDto.setUsername(user.getUsername());
	    usersDto.setEmail(user.getEmail());
	    usersDto.setPassword(user.getPassword());
	    usersDto.setProfile_picture(user.getProfile_picture());
	    
	    return usersDto;
	}

	

	@Override
	public void addUsers(UsersDto user) {
		
		Users u=new Users();
		u.setUserID(user.getUserid());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setProfile_picture(user.getProfile_picture());
		
		repo.save(u);

	}

	@Override
	public void updateUsers(UsersDto newUserDetails, int userId) throws UserNotFoundException{
		Users existingUser = repo.findById(userId)
		        .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
	 
		    // Update the existing user's details with the values from newUserDetails
		    existingUser.setUsername(newUserDetails.getUsername());
		    existingUser.setEmail(newUserDetails.getEmail());
		    existingUser.setPassword(newUserDetails.getPassword());
		  
		    repo.save(existingUser);
        
 
	}



	@Override
	public void deleteUser(int userId) throws UserNotFoundException {
	    Users existingUser = repo.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
	    repo.delete(existingUser);
	}


	@Override
	public List<PostsDto2> getallPostOfUser(int userId) throws UserNotFoundException{
		Users user = repo.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
		List<PostsDto2> postdto=new ArrayList<>();
		
		List<Posts> posts= user.getPosts().stream().collect(Collectors.toList());
	
		for(Posts p:posts) {
			 PostsDto2 d=new PostsDto2();
			d.setContent(p.getContent());
			d.setPost_picture(p.getPost_picture());
			d.setPostId(p.getPostID());
			d.setProfile_picture(p.getUsers().getProfile_picture());
			d.setUsername(p.getUsers().getUsername());
			d.setTimestamp(p.getTimestamp());
			postdto.add(d);
		}
		
		return postdto;

	}

	@Override
	public List<Friendsdto> getAllFriendsOfUser(int userId) throws UserNotFoundException {

		Users user = repo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Friends> allFriends = new ArrayList<>();
		allFriends.addAll(user.getUserID1());
		allFriends.addAll(user.getUserID2());

		List<Friends> acceptedFriends = allFriends.stream().filter(f -> f.getStatus() == statustype.ACCEPTED)
				.collect(Collectors.toList());
       
		List<Friendsdto> friendsdto=new ArrayList<>();
		for(Friends f:acceptedFriends) {
			Friendsdto fd=new Friendsdto();
			fd.setFriendshipid(f.getFriendshipID());
			fd.setUserid1(f.getUserID1().getUserID());
			fd.setUserid2(f.getUserID2().getUserID());
			fd.setStatus(f.getStatus());
			fd.setUser1Profile(f.getUserID1().getProfile_picture());
			fd.setUser2Profile(f.getUserID2().getProfile_picture());
			fd.setUser2name(f.getUserID2().getUsername());
			fd.setUser1name(f.getUserID1().getUsername());
			friendsdto.add(fd);
			
		}
		
		
		return friendsdto;
	}

	@Override
	public List<Comments> getAllCommentsInPostByUser(int userId) throws UserNotFoundException{
		
		Users user = repo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		List<Posts> Post = user.getPosts();
		List<Comments> comments = new ArrayList<>();
		for (Posts p : Post) {
			comments.addAll(p.getComments());
		}
		return comments;

	}

	@Override
	public List<Friendsdto> getAllRequestInPending(int userId) throws UserNotFoundException {
		Users user = repo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Friends> allFriends = new ArrayList<>();
		allFriends.addAll(user.getUserID2());
		List<Friends> Friendrequest = allFriends.stream().filter(f -> f.getStatus() == statustype.PENDING)
				.collect(Collectors.toList());

		List<Friendsdto> friendsdto=new ArrayList<>();
		for(Friends f:Friendrequest) {
			Friendsdto fd=new Friendsdto();
			fd.setFriendshipid(f.getFriendshipID());
			fd.setUserid1(f.getUserID1().getUserID());
			fd.setUserid2(f.getUserID2().getUserID());
			fd.setStatus(f.getStatus());
			fd.setUser1name(f.getUserID1().getUsername());
			fd.setUser2name(f.getUserID2().getUsername());
			fd.setUser1Profile(f.getUserID1().getProfile_picture());
			fd.setUser2Profile(f.getUserID2().getProfile_picture());
			
			friendsdto.add(fd);
			
		}
		
		
		return friendsdto;

	}

	@Override
	public String sendRequest(int from, int to) throws UserNotFoundException {
		Friends newRequest = new Friends();
		if(repo.findById(from).isEmpty() || repo.findById(to).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		
		Users fromUser = repo.findById(from).get();
		Users toUser = repo.findById(to).get();
		

		newRequest.setStatus(statustype.PENDING);
		newRequest.setUserID1(fromUser);
		newRequest.setUserID2(toUser);

		friendsrepo.save(newRequest);

		return "Friend request sent successfully.";
	}

	@Override
	public List<MessageDto> getAllMessageBetweenUser(int send, int received) throws UserNotFoundException {
		if(repo.findById(send).isEmpty()||repo.findById(received).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		
		Users sendUser = repo.findById(send).orElse(null);
		Users receiveUser = repo.findById(received).orElse(null);
		
		

		if (sendUser == null || receiveUser == null) {
			return Collections.emptyList(); // Handle if either user is not found
		}

		List<MessageDto> result = new ArrayList<MessageDto>();

		for (Message message : sendUser.getSenderID()) {
			MessageDto m = new MessageDto();
			if (message.getReceiverID().getUserID() == received) {
				m.setMessage_text(message.getMessage_text());
				m.setMessageID(message.getMessageID());
				m.setReceiverID(message.getReceiverID().getUserID());
				m.setSenderID(message.getSenderID().getUserID());
				m.setTimestamp(message.getTimestamp());
				result.add(m);
				
			}
		}

		for (Message message : receiveUser.getSenderID()) {
			MessageDto m = new MessageDto();
			if (message.getReceiverID().getUserID() == send) {
				m.setMessage_text(message.getMessage_text());
				m.setMessageID(message.getMessageID());
				m.setSenderID(message.getSenderID().getUserID());
				m.setReceiverID(message.getReceiverID().getUserID());
				m.setTimestamp(message.getTimestamp());
				result.add(m);
			}
		}

		return result;
	}

	@Override
	public String sendMessage(int from, int to, String message) throws UserNotFoundException{
		if(repo.findById(from).isEmpty()||repo.findById(to).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		Message newMessage = new Message();

		Users fromUser = repo.findById(from).get();
		Users toUser = repo.findById(to).get();

		newMessage.setMessage_text(message);
		newMessage.setSenderID(fromUser);
		newMessage.setReceiverID(toUser);

		messagerepo.save(newMessage);

		return "Message sent successfully";

	}

	@Override
	public List<Likesdto> getlikesPostbyUser(int userid) throws UserNotFoundException{
		
		Users user = repo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Posts> posts = user.getPosts().stream().collect(Collectors.toList());

		List<Likes> likes = new ArrayList<>();
		for (Posts p : posts) {
			likes.addAll(p.getLikes());
		}
		
		List<Likesdto> likesdto=new ArrayList<>();
		for(Likes l:likes) {
			Likesdto ld=new Likesdto();
			ld.setLikeid(l.getLikeID());
			ld.setTimestamp(l.getTimestamp());
			ld.setUserid(l.getUsers().getUserID());
			likesdto.add(ld);
			
		}
		
		return likesdto;

	
	}

	@Override
	public List<Notifications> getnotification(int userid) throws UserNotFoundException {
		Users user = repo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found"));

		return user.getNotifications();
	}

	@Override
	public List<groupsDto> getGroups(int userid) throws UserNotFoundException{
		Users user = repo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Groups> groups=user.getGroups();
		List<groupsDto> groupdto=new ArrayList<>();
		for(Groups g:groups) {
			groupsDto gd=new groupsDto();
			gd.setGroupid(g.getGroupID());
			gd.setGroupname(g.getGroupName());
			gd.setAdminid(g.getAdmin().getUserID());
			groupdto.add(gd);
		}
		return groupdto;
 
		
	}
 
	@Override
	public String AddFriend(int userid, int friendid) throws UserNotFoundException {
		if(repo.findById(userid).isEmpty()||repo.findById(friendid).isEmpty()) {
			throw new UserNotFoundException("User or friend not found");
		}
		
		Friends friend = new Friends();
		Users fromUser = repo.findById(userid).get();
		Users toUser = repo.findById(friendid).get();

		friend.setStatus(statustype.ACCEPTED);
		friend.setUserID1(fromUser);
		friend.setUserID2(toUser);
		

		friendsrepo.save(friend);

		return "Friend successfully added.";

	}

	@Override
	@Transactional
	public String removeFriend(int userid, int friendid) throws UserNotFoundException{
		if(repo.findById(userid).isEmpty()||repo.findById(friendid).isEmpty()) {
			throw new UserNotFoundException("User or friend not found");
		}
		
		
		Users user = repo.findById(userid).orElse(null);
		Users friend = repo.findById(friendid).orElse(null);

		

		List<Friends> allFriends = new ArrayList<>();
		allFriends.addAll(user.getUserID1());
		allFriends.addAll(user.getUserID2());

		for (Friends f : allFriends) {
			// Check if the friendship matches either combination of user IDs
			if ((f.getUserID1().getUserID() == userid && f.getUserID2().getUserID() == friendid)
					|| (f.getUserID1().getUserID() == friendid && f.getUserID2().getUserID() == userid)) {
				friendsrepo.deleteMessageNative(f.getFriendshipID());
				return "Friend successfully removed";
			}
		}
		return "No friendship found to remove";
	}
	
	@Override
	public List<PostsDto> getAllPostsLikedByUser(int userid) throws UserNotFoundException {
		Users user = repo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found"));
	    List<Likes> likes=user.getLikes();
	    List<PostsDto> allposts=new ArrayList<>();
	    for(Likes l:likes) {
	    	Posts post=new Posts();
	    	post=l.getPosts();
	    	PostsDto pd=new PostsDto();
	    	pd.setPostId(post.getPostID());
	    	pd.setContent(post.getContent());
	    	pd.setTimestamp(post.getTimestamp());
	    	allposts.add(pd);
	    	
	  }
	    
	    return allposts;
	    
		
	}
	
	@Override
	public List<UsersDto> getRandomUsers(int userId) throws UserNotFoundException {
	    // Fetch the current user from the database
	    Users currentUser = repo.findById(userId)
	                            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

	    // Fetch all users from the database
	    List<Users> allUsers = repo.findAll();

	    // Shuffle the list of users to randomize their order
	    Collections.shuffle(allUsers);

	    // Filter out the current user and users who are already friends of the current user
	    List<Users> nonFriendUsers = allUsers.stream()
	            .filter(user -> user.getUserID() != userId && !areFriends(user, currentUser))
	            .limit(4) // Limit the number of random non-friend users
	            .collect(Collectors.toList());

	    // Convert the selected non-friend users to DTOs (UsersDto)
	    List<UsersDto> nonFriendUsersDto = nonFriendUsers.stream()
	            .map(user -> {
	                UsersDto userDto = new UsersDto();
	                userDto.setUserid(user.getUserID());
	                userDto.setUsername(user.getUsername());
	                userDto.setProfile_picture(user.getProfile_picture());
	                return userDto;
	            })
	            .collect(Collectors.toList());

	    return nonFriendUsersDto;
	}

	// Helper method to check if two users are friends
	private boolean areFriends(Users user1, Users user2) {
	    // Check if there is a friendship record between the two users
	    return user1.getUserID1().stream()
	            .anyMatch(friendship -> friendship.getUserID2().getUserID() == user2.getUserID() &&
	                                    friendship.getStatus() == statustype.ACCEPTED) ||
	           user1.getUserID2().stream()
	            .anyMatch(friendship -> friendship.getUserID1().getUserID() == user2.getUserID() &&
	                                    friendship.getStatus() == statustype.ACCEPTED);
	}

	@Override
	public String AcceptFriend(int userId, int friendshipId, int otherUserId) throws UserNotFoundException {
		if(repo.findById(userId).isEmpty()||repo.findById(otherUserId).isEmpty()) {
			throw new UserNotFoundException("User or friend not found");
		}
	
	Friends friend = new Friends();
	Users fromUser = repo.findById(otherUserId).get();
	Users toUser = repo.findById(userId).get();

	friend.setStatus(statustype.ACCEPTED);
	friend.setUserID1(fromUser);
	friend.setUserID2(toUser);
	friend.setFriendshipID(friendshipId);

	friendsrepo.save(friend);

	return "Friend request accepted.";
		
	}

	@Override
	@Transactional
	public String addNotifications(int UserId, String notifications) throws UserNotFoundException {
		Users user = repo.findById(UserId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Notifications> noti=user.getNotifications();
        Notifications n=new Notifications();
         n.setContent(notifications);
         n.setUsers(user);
         noti.add(n);
         notificationrepo.save(n);
         return "notification added";

		
	}

	
   
	
	
	}


 ;