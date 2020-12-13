package github.veikkoroc.api;

import github.veikkoroc.bean.User;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 11:27
 */
public interface UserService {

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
}
