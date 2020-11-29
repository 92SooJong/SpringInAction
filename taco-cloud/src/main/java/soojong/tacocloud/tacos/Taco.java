package soojong.tacocloud.tacos;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class Taco {
	
	@NotNull
	@Size(min=5,message="Name must be at least 5 characters long")
	private String name;
	
	@Size(min=1, message="적어도 1개의 재료는 선택해야합니다.")
	private List<String> ingredients;
}
