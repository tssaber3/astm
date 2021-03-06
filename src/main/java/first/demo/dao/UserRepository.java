package first.demo.dao;

import first.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);

    List<User> findByUsernameLike(String username);
}
