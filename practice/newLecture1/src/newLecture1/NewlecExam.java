package newLecture1;

public class NewlecExam implements Exam {
	
	private int kor;
	private int eng;
	private int com;
	
	@Override
	public int total() {
		
		return kor+eng+com;
	}

	@Override
	public float avg() {

		return total() / 4.0f;
	}
	
}
