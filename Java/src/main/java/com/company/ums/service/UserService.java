package com.company.ums.service;

import com.company.ums.model.User;

public interface UserService {

    User save(User user);

    User findById(Long id);

    void delete(Long id);
}
