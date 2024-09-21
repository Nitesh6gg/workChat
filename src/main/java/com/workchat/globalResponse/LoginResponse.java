package com.workchat.globalResponse;

import com.workchat.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String Message;
    private Object httpStatus;
    private User user;

}
