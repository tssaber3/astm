package first.demo.service.impl;

import first.demo.dao.UserRepository;
import first.demo.mapper.UserMapper;
import first.demo.pojo.Role;
import first.demo.pojo.User;
import first.demo.service.UserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    @Override
    public User selUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User selUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.selUserByUsernameAndPassword(username,password);
        if(user != null){
            Role role = userMapper.selRoleByUserId(user.getId());
            user.setRole(role);
            return user;
        }else {
            return null;
        }
//        return userRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public boolean saveUser(User user) {
        User user1 = userRepository.save(user);
        if(user1 != null)
        {
            return true;
        }else
        {
            return false;
        }
    }

    //返回除管理员外的用户
    @Override
    public List<User> getAllStu() {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //查询外键时 先join再name
                list.add(criteriaBuilder.equal(root.join("role").get("name"),"老师"));
                list.add(criteriaBuilder.equal(root.join("role").get("name"),"学生"));
                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.or(list.toArray(arr));
            }
        };
        List<User> list = userRepository.findAll(specification);
        if(list != null)
        {
            return list;
        }else
        {
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        User user = userMapper.selUserById(id);
        Role role = userMapper.selRoleByUserId(id);
//        User user = userRepository.findById(id).get();
        //检查查询的值是否为空
        if(user != null && role != null)
        {
            user.setRole(role);
            return user;
        }else
        {
            return null;
        }
    }

    @Override
    public void delUserById(int id) {
        userMapper.delUserById(id);
    }

    @Override
    public void updUser(User user) {
        userMapper.updUser(user.getUsername(),user.getNickname(),user.getDeparment(),user.getScore(),user.getGrade(),user.getId());
    }

    @Override
    public void updUserPassword(String password) {
        userMapper.updUserPassword(password);
    }
}
