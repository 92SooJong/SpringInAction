package newLecture1;

public class InlineExamConsole implements ExamConsole {
	
	private Exam exam;
	
	
	public InlineExamConsole(Exam exam) {
		this.exam = exam;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("�ζ����ֿܼ��� ���  : " + exam.total());
	}
	
}
