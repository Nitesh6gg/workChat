package com.workchat.service;

import com.workchat.dto.request.LoginDto;
import com.workchat.globalResponse.MessageResponse;

public interface AuthService {

    MessageResponse authenticate(LoginDto dto);

}
