package com.floatcloud.file.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


/**
 * @author floatcloud
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    @NonNull
    private int status = 1;
    private int errorCode;
    private String msg;

    private T data;
}
