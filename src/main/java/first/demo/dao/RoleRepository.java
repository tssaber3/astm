package first.demo.dao;

import first.demo.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role,Integer>, JpaSpecificationExecutor<Role> {
}
