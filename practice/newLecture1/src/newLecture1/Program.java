package newLecture1;

public class Program {

	public static void main(String[] args) {
		
		// IOC : 작은 부품을 먼저 만들어 놓고 큰 부붐에 끼운다.
		// IOC가 없는 경우는 큰 부붐을 먼저 만들고 작은 부품을 조립한다.
		
		Exam exam = new NewlecExam();
		// InlineExamConsole이 Exam을 조립하고 있다. ( DI )
		//ExamConsole console = new InlineExamConsole(exam); 
		ExamConsole console = new GridExamConsole(exam);
		console.print();
	}

}
