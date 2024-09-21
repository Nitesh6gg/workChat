package com.workchat.service;

import com.workchat.dto.request.LoginDto;
import com.workchat.globalResponse.LoginResponse;

public interface AuthService {

    LoginResponse authenticate(LoginDto dto);

}
