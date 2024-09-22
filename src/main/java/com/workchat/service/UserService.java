package com.workchat.service;

import com.workchat.dto.request.UserDto;
import com.workchat.entity.User;
import com.workchat.globalResponse.MessageResponse;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<Map<String,Object>> getAllActiveUsers();

    List<User> getAllUsers(String userId);

    MessageResponse  saveUser(UserDto dto);
}
