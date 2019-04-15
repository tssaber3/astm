package first.demo.Service.Impl;

import first.demo.Dao.UserRepository;
import first.demo.Pojo.User;
import first.demo.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Override
    public User selUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User selUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
