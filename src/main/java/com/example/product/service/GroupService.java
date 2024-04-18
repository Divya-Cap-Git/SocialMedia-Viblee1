package com.example.product.service;

import java.util.List;

import com.example.product.dto.groupsDto;
import com.example.product.entity.Groups;
import com.example.product.exception.GroupsNotFoundException;
import com.example.product.exception.UserNotFoundException;

public interface GroupService {
	List<groupsDto> getAllListOfGroups();
	groupsDto getAGroupById(int groupId) throws GroupsNotFoundException;
	void createANewGroup(Groups newgroup)throws UserNotFoundException;
	void updateAnExistingGroup(Groups existingGroup, String updatedGroupName)throws GroupsNotFoundException,UserNotFoundException;
	void deleteGroupByItsId(int groupId)throws GroupsNotFoundException;
	

 

}
