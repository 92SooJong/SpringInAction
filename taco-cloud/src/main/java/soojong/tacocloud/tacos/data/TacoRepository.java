package soojong.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;

import soojong.tacocloud.tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco,Long>{
	//Taco save(Taco design);
}
