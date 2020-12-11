package newLecture1;

public class GridExamConsole implements ExamConsole {
	
	
	private Exam exam;
	
	public GridExamConsole(Exam exam) {
		this.exam = exam;
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("그리드에서 출력 : " + exam.total());
	}

}
