# 안상하 202130311

<!-- 4월 19일 -->

## 4월 12일  
**접근 지정자**  
* 자바의 접근 지정자  
    * private, protected, public, 디폴트(접근지정자 생략)  
* 접근 지정자의 목적  
    * 클래스나 일부 멤버를 공개하여 다른 클래스에서 접근하도록 허용  

**클래스 접근 지정**  
* 클래스 접근 지정  
    * 다른 클래스에서 사용하도록 허용할 지 지정  
    * public 클래스  
        * 다른 모든 클래스에게 접근 허용  
    * 디폴트 클래스(접근지정자 생략)  
        * package-private라고도 함  
        * 같은 패키지의 클래스에만 접근 허용  
    
**멤버 접근 지정**  
* public 멤버  
    * 패키지에 관계없이 모든 클래스에게 접근 허용  
* private 멤버  
    * 동일 클래스 내에만 접근 허용  
    * 상속 받은 서브 클래스에서 접근 불가  
* protected 멤버  
    * 같은 패키지 내의 다른 모든 클래스 접근 허용
* 디폴트(default)멤버      
    * 패키지 내의 다른 클래스에게 접근 허용

**static 멤버**  
* static 멤버 선언  
~~~ java
class StaticSample {
    int n;   // non-static 필드  
    void g() // non-static 메소드

    static int m;     // static 필드  
    static void f();  // static 메소드
} 
~~~  
* 객체 생성과 non-static 멤버의 생성  
    * non-static 멤버는 객체가 생성될 때, 객체마다 생긴다.  

**static 멤버의 생성**  
* static 멤버는 클래스당 하나만 생성  
* 객체들에 의해 공유됨

**static 멤버 사용**  
* 클래스 이름으로 접근 가능  
~~~ java
StaticSample.m = 3; // 클래스 이름으로 static 필드 접근
StaticSample.f(); // 클래스 이름으로 static 메소드 호출
~~~
* 객체의 멤버로 접근 가능  
~~~ java
StaticSample b1 = new StaticSample();

b1.m = 3; //객체 이름으로 static 필드 접근
b1.f();   //객체 이름으로 static 메소드 호출
~~~
* non-static 멤버는 클래스 이름으로 접근 안 됨  
~~~ java
StaticSample.n  = 5;  //n은 non-static이므로 컴파일 오류
StaticSample.g();     //g()는 non-static이므로 컴파일 오류
~~~

**static 메소드의 제약 조건 **  
* static 메소드는 오직 static 멤버만 접근 가능  
    * 객체가 생성되지 않은 상황에서도 static 메소드는 실행될 수 있기 때문에 non-static 멤버는 접근 불가능  
* static 메소드는 this 사용불가  
    * static 메소드는 객체 없이도 사용 가능하므로, this 레퍼런스 사용할 수 없음  

**static 메소드 예시**
~~~ java
public class Ex4_11 {

class Calc {
    public static int abs(int a) {return a>0?a:-a;}
    public static int max(int a, int b) {return (a>b)?a:b;}
    public static int min(int a, int b) {return (a>b)?b:a;}
    }

public class CalcEx {
    public static void main(String[] args) {
        System.out.println(Calc.abs(-5));
        System.out.println(Calc.max(10,8));
        System.out.println(Calc.min(-3,-8));
        }
    }
}

~~~

**final 클래스와 메소드**  
* final 클래스 - 더 이상 클래스 상속 불가능  
~~~ java
final class FinalClass {
}
class DerivedClass extends FinalClass { //컴파일 오류

}
~~~
* final 메소드 - 더 이상 오버라이딩 불가능  
~~~ java
public class SuperClass {
    protected final int finalMetohd()(...)
}
class SubClass extends SuperClass {
    protected int finalMethod()(...) //컴파일 오류
}
~~~

* final 필드, 상수 선언  
    * 상수를 선언할 때 사용
    ~~~ java
    class SharedClass {
        public static final double Pi = 3.14;
    }
    ~~~
    * 상수 필드는 선언 시에 초기 값을 지정하여야 한다  
    * 상수 필드는 실행 중에 값을 변경할 수 없다
    ~~~ java
    public class FinalFieldClass {
        final int ROWS = 10; //상수 정의, 이때 초기 값(10)을 반드시 실행
        void f() {
            int[]intArray = new int [ROWS];//상수 활용
            ROWS = 30; //컴파일 오류 발생. final 필드 값을 변경할 수 없다.
        }
    }
    ~~~

**자바 상속의 특징**  
* 클래스 다중 상속 불허  
    * c++는 다중 상속 가능  
        * c++는 다중 상속으로 멤버가 중복 생성되는 문제 있음  

**자바 상속 extends 예시**  
~~~ java
class Point {
    private int x,y;
    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void showPoint(){
        System.out.println("(" + x + ","+ y +")");
    }
}

class ColorPoint extends Point {
    private String color;
    public void setColor(String color){
        this.color = color;
    }
    public void showColorPoint() {
        System.out.print(color);
        showPoint();
    }
}

public class Ex5_01 {
    public static void main(String[] args) {
        Point p = new Point();
        p.set(1, 2);
        p.showPoint();

        ColorPoint cp = new ColorPoint();
        cp.set(3, 4);
        cp.setColor("red");
        cp.showColorPoint();
    }
}

~~~

**서브 클래스와 슈퍼 클래스의 생성자 선택**  
* 슈퍼 클래스와 서브 클래스  
    * 각각 여러 개의 생성자 작성 가능  
* 서브 클래스의 객체가 생성될 때  
    * 슈퍼 클래스 생성자 1개와 서브 클래스 생성자 1개가 실행  
* 서브 클래스의 생성자와 슈퍼 클래스의 생성자가 결정되는 방식  
    1. 개발자의 명시적 선택  
        * 서브 클래스 개발자가 슈퍼 클래스의 생성자를 선택하지 않는 경우  
        * super()키워드를 이용하여 선택
    2. 컴파일러가 기본생성자 선택  
        * 서브 클래스 개발자가 슈퍼 클래스의 생성자를 선택하지 않는 경우  
        * 컴파일러가 자동으로 슈퍼 클래스의 기본 생성자 선택

**업캐스팅**  
* 서브 클래스 레퍼런스를 슈퍼 클래스 레퍼런스에 대입  

**다운캐스팅**  
* 슈퍼 클래스 레퍼런스를 서브 클래스 레퍼런스에 대입  
* 업캐스팅 된 것을 다시 원래대로 되돌리는 것  
* 반드시 명시적 타입 변환 지정  
~~~ java
class Person()
class Student extends Person()

Person p = new Student("ㄱㄱㄱ") //업캐스팅
Student = (Student)p;//다운캐스팅
~~~


## 4월 5일  
**2차원 배열**  
* 2차원 배열 선언  
    int intArray[][]; 또는 int[][] intArray;  

**메소드의 배열 리턴**  
* 배열 리턴  
    * 배열의 레퍼런스만 리턴(배열 전체가 리턴되는 것이 아님)  
* 메소드의 리턴 타입  
    * 리턴하는 배열 타입과 리턴 받는 배열 타입 일치  
    * 리턴 타입에 배열의 크기를 지정하지 않음  

**자바의 예외 처리**  
* 예외  
    * 실행 중 오동작이나 결과에 악영향을 미치는 변수 발생  
**try-catch-finally문**  
* 예외 처리  
    * 발생한 예외에 대해 개발자가 작성한 프로그램 코드에서 대응하는 것  
    * try-catch-finally문 사용(finally 블록은 생략 가능)  
~~~ java    
    try {
        (실행문)
    }
    catch(처리할 예외 타입선언){
        예외 처리문
    }
    finally
        finally 블록문
~~~

**나눗셈을 할때의 예외 처리문** 
~~~ java
import java.util.Scanner;

public class Ex3_13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dividend;
        int divisor;

        System.out.print("나뉨수를 입력하시오");
        dividend  = scanner.nextInt();
        System.out.print("나눗수를 입력하시오");
        divisor = scanner.nextInt();
        try {
            System.out.println(dividend+"를 " +divisor + "로 나누면 몫은 " + dividend/divisor + "입니다.");
        }
        catch(ArithmeticException e) {
                System.out.println("0으로 나눌 수 없습니다.");
        }
        finally {
            scanner.close();
        }
    }
}
~~~  
**자바 상속**  
* 자바 상속  
    * 상위 클래스의 멤버를 하위 클래스가 물려받음  
        * 상위 클래스: 슈퍼 클래스  
        * 하위 클래스: 서브 클래스, 슈퍼 클래스의 재사용, 새로운 특성 추가 기능  

**다형성**  
* 다형성  
    * 같은 이름의 메소드가 클래스 혹은 객체에 따라 다르게 구현되는 것  

**다형성 사례**   
* 메소드 오버로딩 : 한 클래스 내에서 같은 이름이지만 다르게 작동하는 여러 메소드  
* 메소드 오버라이딩 : 슈퍼 클래스의 메소드를 동일한 이름으로 서브 클래스마다 다르게 구현  

**객체 지향 언어의 목적**  
1. 소프트웨어의 생산성 향상  
    * 컴퓨터 산업 발전에 따라 소프트웨어의 생명 주기 단축  
        * 소프트웨어를 빠른 속도로 생산할 필요성 증대  
    * 객체 지향 언어  
2. 실세계에 대한 쉬운 모델링  

**클래스와 객체**  
* 클래스  
    * 객체의 속성과 행위 선언  
    * 객체의 설계도 혹은 틀  
* 객체  
    * 클래스의 틀로 찍어낸 실체  
        * 프로그램 실행 중에 생성되는 실체  
        * 메모리 공간을 갖는 구체적인 실체  
        * 인스턴스 라고도 부름  

**자바 클래스 구성**  
* 클래스  
    * class 키워드로 선언  
    * 멤버 : 클래스 구성 요소  
    * 클래스에 대한 public 접근 지정 : 다른 모든 클래스에서 클래스 사용 허락  
    * 멤버에 대한 public 접근 지정 : 다른 모든 클래스에게 멤버 접근 허용  

**클래스를 이용한 사각형 넓이 구하기**
~~~ java 
import java.util.Scanner;

public class Ex4_02 {
    public static void main(String[] args) {
    Rectangle rect = new Rectangle(); 
    Scanner scanner= new Scanner(System.in);
    System.out.print(">> 사각형의 가로 길이");
    rect.width = scanner.nextInt();
    System.out.print(">> 사각형의 세로 길이");
    rect.height = scanner.nextInt(); 
    System.out.println("사각형의 면적은 " + rect.getArea());
    scanner.close();
    }
}

class Rectangle {
    int width;
    int height;
    public int getArea() {
        return width*height;
    }
}
~~~

**메소드**  
* 메소드는 C/C++의 함수와 동일  
* 자바의 모든 메소드는 반드시 클래스 안에 있어야 함 (캡슐화 원칙)  

**메소드 오버로딩**  
* 오버로딩  
    * 한 클래스 내에서 두 개 이상의 이름이 같은 메소드 작성

**객체 소멸**  
* 객체 소멸  
    * new에 의해 할당 받은 객체와 배열 메모리를 자바 가상 기계로 되돌려 주는 행위  
    * 소멸된 객체 공간은 가용 메모리에 포함  
* 자바에서 사용자 임의로 객체 소멸이 안된다.  
    * 자바는 객체 소멸 연산자 없음  
        * 객체 생성 연산자 : new  
    * 객체 소멸은 자바 가상 기계의 고유한 역할  

**가비지**  
* 가비지  
    * 가리키는 레퍼런스가 하나도 없는 객체  
        * 더 이상 접근할 수 없어 사용할 수 없게 된 메모리  
    * 가비지 컬렉션  
        * 자바 가상 기계의 가비지 컬렉터가 자동으로 가비지 수집, 반환  

**가비지 컬렉션** 
* 가비지 컬렉션  
    * 자바 가상 기계가 가비지 자동 회수  
        * 가용 메모리 공간이 일정 이하로 부족해질 때  
        * 가비지를 수거하여 가용 메모리 공간으로 확보  
    * 가비지 컬렉터에 의해 자동 수행  
* 강제 가비지 컬렉션 강제 수행  
    * System 또는 Runtime 객체의 gc() 메소드 호출  
        System.gc();  

**자바의 패키지 개념**  
* 패키지  
    * 상호 관련 있는 클래스 파일(컴파일 된 .class)을 저장하여 관리하는 디렉터리  
    * 자바 응용프로그램은 하나 이상의 패키지로 구성  

## 3월 29일   
**실수 리터럴**  
* 소수점 형태나 지수 형태로 표현한 실수  
  
**문자 리터럴**  
* 단일 인용부호('')로 문자 표현  
* 특수문자 리터럴은 백슬래시(\\)로 표현  

**기본 타입 이외 리터럴**  
* null 리터럴  
    * 레퍼런스에 대입 사용  
  
* 문자열 리터럴(스트링 리터럴)  
    * 이중 인용부호로 묶어 표현("")  

**상수**  
* 상수 선언  
    * final 키워드 사용  
    * 선언 시 초기값 지정  
    * 실행 중 값 변경 불가  

**var 키워드**  
* java 10부터 도입  
* 기존의 변수 선언 방식 : 변수의 타입 반드시 지정  
    int price = 200;  
    String name = "kitae";  
* var 키워드  
    * 타입을 생략하고 변수 선언 가능  
    * 컴파일러가 추론하여 변수 타입 결정    
    * 변수 선언 시 초기값이 주어지지 않으면 컴파일 오류  
    * var는 지역 변수 선언에만 한정  

**타입 변환**  
* 타입 변환  
    * 한 타입의 값을 다른 타입의 값으로 변환  
* 자동 타입 변환  
    * 컴파일러에 의해 원래의 타입보다 큰 타입으로 자동 변환  

**강제 타입 변환**
* 개발자의 의도적 타입 변환  
* ()안에 개발자가 명시적으로 타입 변환 지정
    int n = 300;  
    byte b = n; // int 타입이 byte로 자동 변환 안 됨  
    (byte b = (byte)n)으로 수정
* 강제 변환 시 값 손실 우려가 있음  

**Scanner 클래스**  
* 읽은 바이트를 문자, 정수, 실수, 불린, 문자열 등 다양한 타입으로 변환하여 리턴  
    * java.util.Scanner  
* 객체 생성  
    import java.util.Scanner; // 임포트 문 필요  
    Scanner a = new Scanner(System.in) Scanner 객체 생성  

**증감 연산**  
* 1 증가 혹은 감소 시키는 연산  
    * ++, --  

**비교 연산, 논리 연산**  
* 비교 연산자: 두 개의 값을 비교하여 true/false/ 결과  (a>b, a<b, a=b 등) 
* 논리 연산자: 두 개의 논리 값에 논리 연산, 논리 결과(!a, a^b 등)  

**조건 연산**  
* 3개의 피연산자로 구성된 삼항(ternary)연산자  
    * opr1?opr2:opr3  
    * opr1이 true이면 연산식의 결과는 opr2, false이면 opr3  

**조건문 - 단순 if 문, if else 문**   
* 단순 if문 - if의 괄호 안에 조건식(논리형 변수나 논리 연산)  
    if(n%2 == 0)  {  
        System.out.print(n);  
        System.out.println("은 짝수입니다.");
}  
* if-else 문 - 조건식이 true면 실행문장1, false이면 실행문장2 실행  
    if(score >= 90)  
        System.out.println("합격입니다.");  
    else  
        System.out.println("합격입니다."); 

**if, else if와 Scanner로 월 입력에 따른 계절 출력**   
~~~java  
import java.util.Scanner;
public class Season {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("월(1 ~ 12)을 입력하시오");
        int month = scanner.nextInt();

        if(month < 6 && month > 2){
            System.out.println("봄입니다.");
        }
        else if(month < 9 && month > 5){
            System.out.println("여름입니다.");
        }
        else if(month < 12 && month > 8){
            System.out.println("가을입니다.");
        }
        else if(month > 12){
            System.out.println("잘못된 입력입니다.");
        }
        else{
            System.out.println("겨울입니다.");
        }
        scanner.close();
    }
}  
~~~  
**for 문을 활용해 구구단 출력**
~~~ java  
public class Ex3_4 {

    public static void main(String[] args) {
        
        for(int i = 1; i < 10; i++){
            for(int j = 1; j < 10; j++){
                System.out.print(i + "x" + j + "=" + i*j);
                System.out.print('\t');
            }
            System.out.println();
        }
    }
}
~~~
**자바 배열**  
* 배열
    * 인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료 구조  
        * 배열을 이용하면 한 번에 많은 메모리 공간 선언 가능  
    * 배열은 같은 타입의 데이터들이 순차적으로 저장되는 공간 이다.    

**배열 선언 및 생성 디테일**  
* 배열 선언과 배열 생성의 두 단계 필요  
* 배열 선언  
    * 배열의 이름 선언(배열 레퍼런스 변수 선언)  
    int intArray []; 또는 int[] intArray;  
* 배열 인덱스  
    * 배열의 인덱스는 0 ~ (배열 크기는 1)  
    int intArray = new int[5]; //인덱스는 0~4까지 가능  
    intArray[0] = 5;  // 원소 0에 5 저장  
    intArray[3] = 6; // 원소 3에 6 저장  
    int n = intArray[3];  // n의 값을 원소 3의 값으로 저장  

## 3월 22일
**자바의 플랫폼 독립성, WORA**    
* 한번 작성된 코드는 모든 플랫폼에서 바로 실행됨  
* C/C++ 등 기존 언어가 가진 플랫폼 종속성 극복  
    * OS,H/W에 상관없이 자바 프로그램이 동일 실행  
* 네트워크에 연결된 어느 클라이언트에서나 실행

**실행 환경**  
자바 가상 기계 + 자바 플랫폼의 다양한 클래스 라이브러리(자바API)

**JDK**  
    자바 응용 개발 환경, 개발에 필요한 도구 포함

**JRE**  
   자바 실행 환경

**JDK의 bin 디렉터리에 포함된 주요 개발 도구**  
* javac - 자바 소스를 바이트 코드로 변환하는 컴파일러  
* java  
* jar  
* jdb
    
**자바 통합 개발 환경** 이클립스

**자바 응용의 종류 :** 서블릿    
서블릿  
* 웹 서버에서 실행되는 자바 프로그램

자바의 특성  
* 플랫폼 독립성  
    * 하드웨어, 운영체제에 종속되지 않는 바이트 코드로 플랫폼 독립성
* 객체지향  
    * 캡슐화, 상속, 다형성 지원
* 클래스로 캡슐화  
    * 자바의 모든 변수나 함수는 클래스내에 선언  
    * 클래스 안에서 클래스(내부 클래스) 작성 가능
* 소스(.java)와 클래스(.class) 파일

**자바의 특징** 
* 실행 코드 배포   
    * 구성  
        * 한 개의 class 파일 또는 다수의 class 파일로 구성  
        * 여러 폴더에 걸쳐 다수의 클래스 파일로 구성된 경우 : jar 압축 파일로 배포  
    * 자바 응용프로그램의 실행은 main() 메소드에서 시작  
        * 하나의 클래스 파일에 두 개 이상의 main() 메소드가 있을 수 없음
* 패키지  
    * 서로 관련 있는 여러 클래스를 패키지로 묶어 관리  
    * 패키지는 폴더 개념
* 멀티스레드  
* 가비지 컬렉션
* 실시간 응용프로그램에 부적합  
    * 실행 도중 예측할 수 없는 시점에 가비지 컬렉션 실행 때문  
        * 응용프로그램의 일시적 중단 발생  
* 자바 프로그램은 안전  
    * 타입 체크 엄격  
    * 물리적 주소를 사용하는 포인터 개념 없음
* 프로그램 작성 쉬움  
    * 포인터 개념이 없음
    * 동적 메모리 반환 하지 않음  
    * 다양한 라이브러리 지원
* 실행 속도 개선을 위한 JIT 컴파일러 사용  
    * 자바는 바이트 코드를 인터프리터 방식으로 실행  
    * JIT 컴파일 기법으로 실행 속도 개선  

**식별자**  
* 클래스, 변수, 상수, 메소드 등에 붙히는 이름  
    * 식별자의 원칙  
        * @ # ! 와 같은 특수 문자, 공백, 또는 탭은 식별자로 사용할 수 없으나 _ $ 는 사용 가능  
        * 유니코드 나 한글 사용 가능  

**문자열**  
* 문자열은 기본 타입이 아님  
* String 클래스로 문자열 표현   
  
**리터럴과 정수 리터럴**  
* 리터럴
    * 프로그램에서 직접 표현한 값  
* 정수 리터럴  
    * 10진수, 8진수, 16진수, 2진수 리터럴


## 3월 15일
수정된 내용이 있다면 커밋을 할 수 있다.커밋을 할 때에는 간단한 내용을 적는다.