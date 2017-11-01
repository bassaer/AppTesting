package com.github.bassaer.lib.util;

import com.github.bassaer.lib.model.User;

/**
 * User validator
 * Created by nakayama on 2017/11/01.
 */

public class UserValidator {
    public static boolean validate(User user) {
        return user.getChecksum() == user.getId() * user.getName().length();
    }
}
