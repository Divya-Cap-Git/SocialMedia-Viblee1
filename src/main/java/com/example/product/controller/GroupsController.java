package com.example.product.controller;
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
 
import com.example.product.repository.*;
import com.example.product.service.*;
 
import com.example.product.dto.*;
 
import com.example.product.entity.*;
import com.example.product.exception.*;
 
 
@RestController
@RequestMapping("/api/v1/groups/")
@CrossOrigin(origins ="http://localhost:3000")
 
public class GroupsController {
	@Autowired
	GroupService groupsService;
	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<groupsDto>> retrieveListOfGroups(){
		return new ResponseEntity<List<groupsDto>>
		       (groupsService.getAllListOfGroups(),HttpStatus.OK);
	}
	
	@GetMapping("/{groupId}")
	public ResponseEntity<groupsDto> getGroupById(@PathVariable("groupId") int groupId)throws GroupsNotFoundException{
		return new ResponseEntity<groupsDto>
				(groupsService.getAGroupById(groupId),HttpStatus.OK);
	}

	
	@PostMapping("/{groupName}/{adminid}")
	public ResponseEntity<Void> createNewGroup(@PathVariable("groupName")String groupname, @PathVariable("adminid") int adminid) throws UserNotFoundException {

		Groups newgroup=new Groups();
		Users groupadmin=usersRepository.findById(adminid).get();
		newgroup.setGroupName(groupname);
		newgroup.setAdmin(groupadmin);
		 if (newgroup.getAdmin() == null) {
		        
		        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		    }
		    groupsService.createANewGroup(newgroup);
		    return new ResponseEntity<>(HttpStatus.CREATED);
 
	}
	
	@DeleteMapping("/delete/{groupid}")
	public ResponseEntity<String> deleteUsers(@PathVariable("groupid") int groupid) throws GroupsNotFoundException {
		groupsService.deleteGroupByItsId(groupid);
		return new ResponseEntity<String>("Users deleted successfully", HttpStatus.OK);
		

	}

	
	
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<ExceptionMessage> handleUsersNotFoundException(UserNotFoundException ex){
		ExceptionMessage message=new ExceptionMessage(ex.getMessage());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler({FriendsNotFoundException.class})
	public ResponseEntity<ExceptionMessage> handleFriendsNotFoundException(FriendsNotFoundException ex){
		ExceptionMessage message=new ExceptionMessage(ex.getMessage());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler({GroupsNotFoundException.class})
	public ResponseEntity<ExceptionMessage> handleGroupsNotFoundException(GroupsNotFoundException ex){
		ExceptionMessage message=new ExceptionMessage(ex.getMessage());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}
 
	
 
}