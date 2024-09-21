package com.workchat.globalResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {

    private String message;
    private Object httpStatus;
}
