package com.example.product.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.dto.CommentsDto;
import com.example.product.dto.Likesdto;
import com.example.product.dto.PostsDto;
import com.example.product.dto.PostsDto2;
import com.example.product.entity.Comments;
import com.example.product.entity.Likes;
import com.example.product.entity.Posts;
import com.example.product.entity.Users;
import com.example.product.exception.PostsNotFoundException;
import com.example.product.exception.UserNotFoundException;
import com.example.product.repository.CommentRepository;
import com.example.product.repository.LikesRepository;
import com.example.product.repository.PostRepository;
import com.example.product.repository.UsersRepository;


import jakarta.transaction.Transactional;

@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	UsersRepository userRepo;

	@Autowired
	PostRepository repo;

	@Autowired
	UsersRepository userrepo;
	
	@Autowired
	LikesRepository likesRepo;
	
	@Autowired
	CommentRepository commentRepo;

	@Override
	@Transactional
	public PostsDto2 createPost(Posts post,int userid) throws UserNotFoundException {
		if(userRepo.findById(userid).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		Users user=userRepo.findById(userid).get();
		post.setUsers(user);
		repo.save(post);
		PostsDto2 pd=new PostsDto2();
		pd.setPostId(post.getPostID());
		pd.setContent(post.getContent());
		pd.setTimestamp(post.getTimestamp());
		pd.setProfile_picture(post.getUsers().getProfile_picture());
		pd.setUsername(post.getUsers().getUsername());
		pd.setUserId(post.getUsers().getUserID());
		return pd;
		

	}

	@Override

	public PostsDto2 getPostById(int postId) throws PostsNotFoundException{
		if(repo.findById(postId).isEmpty()) {
			throw new PostsNotFoundException("Post with the id "+postId+" not found");
		}
		Posts p= repo.findById(postId).get();
		PostsDto2 pd=new PostsDto2();
		pd.setPostId(p.getPostID());
		pd.setContent(p.getContent());
		pd.setTimestamp(p.getTimestamp());
		pd.setProfile_picture(p.getUsers().getProfile_picture());
		pd.setUsername(p.getUsers().getUsername());
		pd.setUserId(p.getUsers().getUserID());
		
		return pd;
	}
	



	@Override
	@Transactional
	public PostsDto UpdatePosts(Posts posts, int postid) throws PostsNotFoundException{
		if(repo.findById(postid).isEmpty()) {
			throw new PostsNotFoundException("Post with the id "+postid+" not found");
		}
		Posts Originalposts = repo.findById(postid).get();
		Originalposts.setContent(posts.getContent());
		if(posts.getPost_picture()!=null) {
			Originalposts.setPost_picture(posts.getPost_picture());
		}
		
		repo.save(Originalposts);
		 PostsDto pd=new PostsDto();
		 pd.setPostId(Originalposts.getPostID());
		 pd.setContent(Originalposts.getContent());
		 pd.setTimestamp(Originalposts.getTimestamp());
		
		 return pd;
	}
	@Override
	@Transactional
	public void deletePosts(int postid) {
		
		repo.deletePostNative(postid);

	}

	@Override
	public String addliketoPost(int postId, int userId) throws PostsNotFoundException,UserNotFoundException {
		if(repo.findById(postId).isEmpty()) {
			throw new PostsNotFoundException("Post with the id "+postId+" not found");
		}
		
		if(userRepo.findById(userId).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		
		
		
		Posts post = repo.findById(postId).get();
		Users user = userrepo.findById(userId).get();
         Likes like = new Likes();
         like.setPosts(post);
 		like.setUsers(user);
 		likesRepo.save(like);
         
         
        

		return "Liked";

	}

	@Override
	public List<Likesdto> getallLikesinPost(int postId) throws PostsNotFoundException {
		if(repo.findById(postId).isEmpty()) {
			throw new PostsNotFoundException("Post with the id "+postId+" not found");
		}
		Posts post = repo.findById(postId).get();
	     List<Likes> likedpost= post.getLikes();
	     List<Likesdto> likesdto=new ArrayList<>();
	     
	     for(Likes l:likedpost) {
	    	 Likesdto ld=new Likesdto();
	    	 ld.setLikeid(l.getLikeID());
	    	 ld.setTimestamp(l.getTimestamp());
	    	 ld.setUserid(l.getUsers().getUserID());
	    	 likesdto.add(ld);
	    	 
	     }
	     return likesdto;
		
	}

	@Override
	@Transactional
	public void removeLikeFromPost(int postId, int userId) throws UserNotFoundException,PostsNotFoundException{
		if(repo.findById(postId).isEmpty()) {
			throw new PostsNotFoundException("Post with the id "+postId+" not found");
		}
		
		if(userRepo.findById(userId).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		
		List<Likes> allLikes=likesRepo.findAll();
		
		Posts post = repo.findById(postId).get();
		Users user = userrepo.findById(userId).get();
		
		for(Likes l:allLikes) {
			if(l.getPosts().equals(post) && l.getUsers().equals(user)) {
				likesRepo.deleteLikeNative(l.getLikeID());
				
			}
		}
	}

	@Override
	public String addCommentinPost(int postId, int userId, String comment) throws PostsNotFoundException,UserNotFoundException {
		if(repo.findById(postId).isEmpty()) {
			throw new PostsNotFoundException("Post with the id "+postId+" not found");
		}
		
		if(userRepo.findById(userId).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		
		Posts post = repo.findById(postId).get();
		Users user = userrepo.findById(userId).get();
         Comments com=new Comments();
         com.setPosts(post);
         com.setUsers(user);
         com.setComment_text(comment);
         commentRepo.save(com);
 		
 		return "Commented";
	}

	@Override
	@Transactional
	public String RemoveCommentFromPost(int postId, int userId) throws PostsNotFoundException,UserNotFoundException{
		if(repo.findById(postId).isEmpty()) {
			throw new PostsNotFoundException("Post with the id "+postId+" not found");
		}
		
		if(userRepo.findById(userId).isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		
		List<Comments> allcomments=commentRepo.findAll();
		
		Posts post = repo.findById(postId).get();
		Users user = userrepo.findById(userId).get();
		
		for(Comments l:allcomments) {
			if(l.getPosts().equals(post) && l.getUsers().equals(user)) {
				commentRepo.deleteCommentsNative(l.getCommentID());
				
			}
			
		}
		return "comment deleted";
		
	}

	@Override
	public List<PostsDto2> getEveryPost() {
		List<Posts> getallpost=repo.findAll();
//		System.out.println(getallpost);
		List<PostsDto2> getpost=new ArrayList<>();
		
		for(Posts p:getallpost) {
			PostsDto2 pd=new PostsDto2();
			pd.setPostId(p.getPostID());
			pd.setContent(p.getContent());
			pd.setTimestamp(p.getTimestamp());
			pd.setPost_picture(p.getPost_picture());
			pd.setUserId(p.getUsers().getUserID());
			pd.setUsername(p.getUsers().getUsername());
			pd.setProfile_picture(p.getUsers().getProfile_picture());
			getpost.add(pd);
			
		}
		return getpost;
	}
	
	@Override
	public List<CommentsDto> getallCommentsInPost(int postId) throws PostsNotFoundException {
	      
	       if(repo.findById(postId).isEmpty()) {
				throw new PostsNotFoundException("Post with the id "+postId+" not found");
			}
	       
	       Posts post=repo.findById(postId).get();
	       List<Comments> comments=post.getComments();
	       List<CommentsDto> commentsdto=new ArrayList<>();
	       
	       for(Comments c:comments) {
	    	   CommentsDto cd=new CommentsDto();
	    	   cd.setCommentID(c.getCommentID());
	    	   cd.setComment_text(c.getComment_text());
	    	   cd.setTimestamp(c.getTimestamp());
	    	   cd.setUserid(c.getUsers().getUserID());
	    	   commentsdto.add(cd);
	    	   
	       }
	       return commentsdto;
	       
	}

	@Override
	public List<Posts> getAllPost() {
		List<Posts> getallpost=repo.findAll();
		
		return getallpost;
	}

	
	
}

