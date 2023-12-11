import java.util.Scanner; // Scanner 클래스를 가져와 사용자로부터 입력을 받음.

// 숫자 데이터를 저장하고 처리하기 위한 클래스
public class NumberDTO {
    private int num1; // 첫 번째 숫자
    private int num2; // 두 번째 숫자
    private double result; // 연산 결과

    // 첫 번째 숫자에 대한 getter와 setter
    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    // 두 번째 숫자에 대한 getter와 setter
    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    // 연산 결과에 대한 getter와 setter
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

// 계산기 기능을 정의하는 인터페이스
interface ICalcService {
    NumberDTO add(NumberDTO number); // 더하기
    NumberDTO sub(NumberDTO number); // 빼기
    NumberDTO mul(NumberDTO number); // 곱하기
    NumberDTO div(NumberDTO number); // 나누기
    // 추가 기능
    NumberDTO mod(NumberDTO number); // 나머지
    NumberDTO sqrt(NumberDTO number); // 제곱근
}

// ICalcService 인터페이스의 구현체
class CalcServiceBiz implements ICalcService {

    // 두 숫자를 더하는 기능
    @Override
    public NumberDTO add(NumberDTO number) {
        double result = number.getNum1() + number.getNum2();
        number.setResult(result);
        return number;
    }

    // 두 숫자를 빼는 기능
    @Override
    public NumberDTO sub(NumberDTO number) {
        double result = number.getNum1() - number.getNum2();
        number.setResult(result);
        return number;
    }

    // 두 숫자를 곱하는 기능
    @Override
    public NumberDTO mul(NumberDTO number) {
        double result = number.getNum1() * number.getNum2();
        number.setResult(result);
        return number;
    }

    // 두 숫자를 나누는 기능
    @Override
    public NumberDTO div(NumberDTO number) {
        double result = number.getNum1() / number.getNum2();
        number.setResult(result);
        return number;
    }

    // 두 숫자의 나머지를 구하는 기능
    @Override
    public NumberDTO mod(NumberDTO number) {
        double result = number.getNum1() % number.getNum2();
        number.setResult(result);
        return number;
    }

    // 두 숫자 중 첫 번째 숫자의 제곱근을 구하는 기능
    @Override
    public NumberDTO sqrt(NumberDTO number) {
        number.setResult(Math.sqrt(number.getNum1()));
        return number;
    }
}

// 계산기의 뷰를 담당하는 클래스
class CalcController {
    private ICalcService service; // 계산 서비스

    // 생성자, 계산 서비스를 주입받음
    public CalcController(ICalcService service) {
        this.service = service;
    }

    // 애플리케이션을 시작하는 메소드
    public void applicationStart() {
        CalcView view = new CalcView(); // 뷰 객체 생성
        NumberDTO number = new NumberDTO(); // 숫자 DTO 객체 생성
        String op; // 연산자

        // 무한 반복문
        while(true) {
            op = view.inputNumber(number); // 사용자 입력 받음
            switch (op) {
                case "+":
                    view.printResult(service.add(number), "add"); // 더하기 연산
                    break;
                case "-":
                    view.printResult(service.sub(number), "sub"); // 빼기 연산
                    break;
                case "*":
                    view.printResult(service.mul(number), "mul"); // 곱하기 연산
                    break;
                case "/":
                    view.printResult(service.div(number), "div"); // 나누기 연산
                    break;
                case "%":
                    view.printResult(service.mod(number), "mod"); // 나머지 연산
                    break;
                case "^":
                    view.printResult(service.sqrt(number), "sqrt"); // 제곱근 연산
                    break;
                case "E":
                case "e":
                    System.out.println("프로그램을 종료합니다."); // 프로그램 종료
                    return;
                default:
                    System.out.println("올바른 연산자를 입력하세요."); // 잘못된 연산자 입력 시 메시지 출력
            }
        }
    }
}

// 사용자로부터 입력을 받고 결과를 출력하는 뷰 클래스
class CalcView {
    Scanner scan = new Scanner(System.in);

    // 사용자로부터 숫자와 연산자를 입력받는 메소드
    public String inputNumber(NumberDTO number) {
        System.out.print("두 수와 연산자를 입력하세요 (ex: 6 7 +): ");
        number.setNum1(scan.nextInt()); // 첫 번째 숫자 입력
        number.setNum2(scan.nextInt()); // 두 번째 숫자 입력
        String op = scan.next(); // 연산자 입력
        return op; // 연산자 반환
    }

    // 결과를 출력하는 메소드
    public void printResult(NumberDTO number, String op) {
        System.out.printf("두 수 %d와 %d의 %s의 결과는 %.2f입니다.\n", number.getNum1(), number.getNum2(), op, number.getResult());
    }
}

// 메인 클래스, 프로그램의 시작점
class Java2CalcMain {
    public static void main(String[] args) {
        ICalcService service = new CalcServiceBiz(); // 서비스 객체 생성
        CalcController controller = new CalcController(service); // 컨트롤러 객체 생성, 서비스 주입
        controller.applicationStart(); // 애플리케이션 시작
    }
}


//  MVC 패턴을 따라 계산기 애플리케이션을 구현
//  NumberDTO 클래스는 데이터를 저장하고,
//  ICalcService 인터페이스 및 그 구현체인 CalcServiceBiz 클래스는 계산 로직을 함
//  CalcView 클래스는 사용자 인터페이스를 처리하고,
//  CalcController 클래스는 모두 연결.
//  Java2CalcMain 클래스에서는 이 모든 것이 시작되며,
// 사용자가 연산자를 입력할 때까지 계산기가 계속 실행