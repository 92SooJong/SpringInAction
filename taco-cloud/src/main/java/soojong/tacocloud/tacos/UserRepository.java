package soojong.tacocloud.tacos;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
	User findByUserName(String username);
}
