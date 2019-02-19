package com.lunzi.camry.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;



/**
 * Created by lunzi on 2018/10/20 下午9:49
 */
@Data
public class UserForm {
    @NotBlank(message = "用户名不能为空")
    private String userName;
}
