package newLecture1;

public class Program {

	public static void main(String[] args) {
		
		// IOC : ���� ��ǰ�� ���� ����� ���� ū �κտ� �����.
		// IOC�� ���� ���� ū �κ��� ���� ����� ���� ��ǰ�� �����Ѵ�.
		
		Exam exam = new NewlecExam();
		// InlineExamConsole�� Exam�� �����ϰ� �ִ�. ( DI )
		//ExamConsole console = new InlineExamConsole(exam); 
		ExamConsole console = new GridExamConsole(exam);
		console.print();
	}

}
