# 안상하 202130311

## 5월 24일

**이벤트 기반 프로그래밍**  
* 이벤트의 발생에 의해 프로그램 흐름이 결정되는 방식  
    * 이벤트가 발생하면 이벤트를 처리하는 루틴(이벤트 리스너) 실행  
    * 실행될 코드는 이벤트의 발생에 의해 전적으로 결정  
* 반대되는 개볌 : 배치 실행  
    * 프로그램의 개발자가 프로그램의 흐름을 결정하는 방식  
* 이벤트 종류  
    * 사용자의 입력 : 마우스 드래그, 마우스 클릭, 키보드 누름 등  
    * 센서로부터의 입력, 네트워크로부터 데이터 송수신  
    * 다른 응용프로그램이나 다른 스레드로부터의 메시지  
* 이벤트 기반 응용 프로그램의 구조  
    * 각 이벤트마다 처리하는 리스너 코드 보유  
* GUI 응용프로그램은 이벤트 기반 프로그래밍으로 작성됨  
    * GUI 라이브러리 종류  
        * C++의 MFC, C# GUI, Visual Basic, X Window, Android등  
        * 자바의 AWT와 Swing  

**자바 스윙 프로그램에서 이벤트 처리 과정**
* 이벤트가 처리되는 과정  
    * 이벤트 발생
        * 예 : 마우스의 움직임 혹은 키보드입력  
    * 이벤트 객체 생성  
        * 현재 발생한 이벤트에 대한 정보를 가진 객체
    * 응용프로그램에 작성된 이벤트 리스너 찾기  
    * 이벤트 리스너 실행  
        * 리스너에 이벤트 객체 전달  
        * 리스너 코드 실행

**이벤트 객체**  
* 발생한 이벤트에 관한 정보를 가진 객체  
* 이벤트 리스너에 전달됨  
    * 이벤트 리스너 코드가 발생한 이벤트에 대한 상황을 파악할 수 있게 함  
* 이벤트 객체가 포함하는 정보  
    * 이벤트 종류와 이벤트 소스  
    * 이벤트 발생한 화면 좌표 및 컴포넌트 내 좌표  
    * 이벤트가 발생한 버튼이나 메뉴 아이템의 문자열  
    * 클릭된 마우스 버튼 번호 및 마우스의 클릭 횟수  
    * 키의 코드 값과 문자 값  
    * 체크박스, 라디오버튼 등과 같은 컴포넌트에 이벤트가 발생하였다면 체크 상태  
* 이벤트 소스를 알아 내는 메소드  
    * Object getSource()
        * 발생한 이벤트 소스 컴포넌트 리턴  
        * Object 타입으로 리턴하므로 캐스팅하여 사용  
        * 모든 이벤트 객체에 대해 적용  

**리스너 인터페이스**  
* 이벤트 리스너  
    * 이벤트를 처리하는 자바 프로그램 코드, 클래스로 작성  
* 자바는 다양한 리스너 인터페이스 제공 
* 사용자의 이벤트 리스너 작성  
    * 자바의 리스너 인터페이스(interface)를 상속받아 구현  
    * 리스너 인터페이스의 모든 추상 메소드 구현  

**이벤트 리스너 작성 과정 사례**
1. 이벤트와 이벤트 리스너 선택
    * 버튼 클릭을 처리하고자 하는 경우
    * 이벤트 : Action 이벤트, 이벤트 리스너 : ActionListener
2. 이벤트 리스너 클래스 작성 : ActionListener 인터페이스 구현  
3. 이벤트를 받아 처리하고자 하는 컴포넌트에 이벤트 리스너 등록
    * component.addXXXListener(listener)
        * xxx : 이벤트 명, listener : 이벤트 리스너 객체  

**이벤트 리스너 작성 방법**  
* 3가지 방법
    * 독립 클래스로 작성  
        * 이벤트 리스너를 완전한 클래스로 작성  
        * 이벤트 리스너를 여러 곳에서 사용할 때 적합  
    * 내부 클래스(inner class)로 작성
        * 클래스 안에 멤버처럼 클래스 작성  
        * 이벤트 리스너를 특정 클래스에서만 사용할 때 적합  
    * 익명 클래스(anoymous class)로 작성
        * 클래스의 이름 없이 간단히 리스너 작성  
        * 클래스 조차 만들 필요 없이 리스너 코드가 간단한 경우에 적합

**독립 클래스로 Action 이벤트 리스너 만들기**
~~~java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class IndepClassListener extends JFrame {
        public IndepClassListener() {
        setTitle("Action 이벤트 리스너 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn = new JButton("Action");
        
        btn.addActionListener(new MyActionListener());
        c.add(btn);
        
        setSize(250, 120);
        setVisible(true);
    }
    public static void main(String [] args) {
        new IndepClassListener();
       }
    }

    class MyActionListener implements ActionListener {
     public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Action"))
             b.setText("액션");
        else
            b.setText("Action");
        }
    }
~~~
![Alt text](image-2.png)![Alt text](image-3.png)

**내부 클래스로 Action 이벤트 리스너 만들기**
~~~java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class IndepClassListener extends JFrame {
        public IndepClassListener() {
        setTitle("Action 이벤트 리스너 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn = new JButton("Action");
        btn.addActionListener(new MyActionListener());
        c.add(btn);
        
        setSize(250, 120);
        setVisible(true);
    }

   private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           JButton b = (JButton)e.getSource();
           if(b.getText().equals("Action"))
                b.setText("액션");
           else
               b.setText("Action");
        
               IndepClassListener.this.setTitle(b.getText());
            }
       }
    public static void main(String [] args) {
        new IndepClassListener();
       }
    }
~~~
![Alt text](image-4.png)![Alt text](image-5.png)
* 버튼의 문자열이 타이틀에 출력됨

**어댑터 클래스**  
* 이벤트 리스너에 구현에 따른 부담
    * 리스너의 추상 메소드를 모두 구현해야 하는 부담  
    * 예 : 마우스 리스너에서 마우스가 눌러지는 경우(mousePressed())만 처리하고자 하는 경우에
도 나머지 4개의 메소드를 모두 구현해야 하는 부담  
* 어댑터 클래스  
    * 리스너의 모든 메소드를 단순 리턴하도록 만든 클래스  
* 추상 메소드가 하나뿐인 리스너는 어댑터 없음  
    * ActionAdapter, ItemAdapter 클래스는 존재하지 않음  

**key 이벤트와 포커스**  
* 키 입력 시, 다음 세 경우 각각 key 이벤트 발생  
    * 키를 누르는 순간  
    * 누른 키를 때는 순간  
    * 누른 키를 때는 순간(Unicode 키의 경우에만)  
* 키 이벤트를 받을 수 있는 조건  
    * 모든 컴포넌트  
    * 현재 포커스(focus)를 가진 컴포넌트가 키 이벤트 독점  
* 포커스(focus)  
    * 컴포넌트나 응용프로그램이 키 이벤트를 독점하는 권한  
    * 컴포넌트에 포커스 설정 방법 : 다음 2 라인 코드 필요  
~~~java
component.setFocusable(true); // component가 포커스를 받을 수 있도록 설정
component.requestFocus(); // component에 포커스 강제 지정
~~~  
* 자바플랫폼마다 실행 환경의 초기화가 서로 다를 수 있기 때문에 다음 코드가 필요함  
**component.setFocusable(true);**

**KeyListener**
* 응용프로그램에서 KeyListener를 상속받아 키 리스너 구현
* KeyListener의 3 개 메소드
    * keyPressed
    * keyReleased
    * keyTyped  
* 컴포넌트에 키 이벤트 리스너 달기  

**유니코드(Unicode)키**  
* 유니코드 키의 특징  
    * 국제 산업 표준  
    * 전 세계의 문자를 컴퓨터에서 일관되게 표현하기 위한 코드 체계  
    * 문자들에 대해서만 키 코드 값 정의  
        * A~Z, a~z, 0~9, !, @, & 등  
    * 문자가 아닌 키 경우에는 표준화된 키 코드 값 없음  
        * Function 키, Home 키, Up 키,Delete 키, Control 키, Shift
키, Alt 등은 플랫폼에 따라 키 코드 값이 다를 수 있음  
* 유니코드 키가 입력되는 경우  
    * keyPressed(), keyTyped(), keyReleased() 가 순서대로 호출  
* 유니코드 키가 아닌 경우  
    * keyPressed(), keyReleased() 만 호출됨  

**가상 키와 입력된 키 판별**
* KeyEvent 객체  
    * 입력된 키 정보를 가진 이벤트 객체  
    * KeyEvent 객체의 메소드로 입력된 키 판별  
* KeyEvent 객체의 메소드로 입력된 키 판별  
    * char KeyEvent.getKeyChar()  
        * 키의 유니코드 문자 값 리턴  
        * Unicode 문자 키인 경우에만 의미 있음  
        * 입력된 키를 판별하기 위해 문자 값과 비교하면 됨  
    * int KeyEvent.getKeyCode()  
        * 유니코드 키 포함  
        * 모든 키에 대한 정수형 키 코드 리턴  
        * 입력된 키를 판별하기 위해 가상키(Virtual Key) 값과 비교하여야 함  
        * 가상 키 값은 KeyEvent 클래스에 상수로 선언  

**KeyListener 활용 예제**
~~~java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyCharEx extends JFrame {
    private JLabel la =
    new JLabel("<Enter>키로 배경색이 바뀝니다");

    public KeyCharEx() {
        super("KeyListener의 문자 키 입력 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
    
        c.setLayout(new FlowLayout());
        c.add(la);
        c.addKeyListener(new MyKeyListener());
    
        setSize(250, 150);
        setVisible(true);
    
        c.setFocusable(true);
        c.requestFocus(); 
    }
class MyKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
       
        switch(e.getKeyChar()) { 
        case '\n':
            la.setText("r=" + r + ", g=" + g + ", b=" + b);
            getContentPane().setBackground(
            new Color(r, g, b));
            break;
            
            case 'q':
            System.exit(0);
        }
    }
}
public static void main(String[] args) {
    new KeyCharEx();
    }
}
~~~

**마우스 리스너 달기와 MouseEvent 객체 활용**  
* 마우스 리스너 달기
    * 마우스 리스너는 컴포넌트에 다음과 같이 등록  
    **component.addMouseListener(myMouseListener);**  
    * 컴포넌트가 마우스 무브(mouseMoved())나 마우스 드래깅(mouseDraggecd())을 함께 처리하고자 하면, MouseMotion 리스너 따로 등록  
    **component.addMouseMotionListener(myMouseMotionListener);**  
* MouseEvent 객체 활용
    * 마우스 포인터의 위치, 컴포넌트 내 상대 위치
        * int getX(), int getY()
    * 마우스 클릭 횟수
        * int getClickCount()

## 5월 17일
**중간고사 채점 확인**

**컨테이너와 배치, 배치관리자 개념**  
* 컨테이너마다 하나의 배치관리자 존재
* 컨테이너에 부착되는 컴포넌트의 위치와 크기 결정
* 컨테이너의 크기가 변경되면, 컴포넌트의 위치와 크기 재결정

**배치 관리자 대표 유형 4가지**  
* FlowLayout 배치관리자
    * 컴포넌트가 삽입되는 순서대로 왼쪽에서 오른쪽으로 배치  
    * 배치할 공간이 벗으면 아래로 내려와서 반복한다.
* BorderLayout 배치 관리자
    * 컨테이너의 공간을 동(EAST), 서(WEST), 남(SOUTH), 북(NORTH), 중앙(CENTER)의 5개 영역으로 나눔
* GridLayout 배치 관리자
    * 컨테이너를 프로그램에서 설정한 동일한 크기의 2차원 격자로 나눔  
    * 컴포넌트는 삽입 순서대로 좌에서 우로, 다시 위에서 아래로 배치
* CardLayout 배치관리자  
    * 컨테이너의 공간에 카드를 쌓아 놓은 듯이 컴포넌트를 포개어 배치  
* java.awt 패키지에 구현되어 있음  

**컨테이너와 디폴트 배치관리자**  
* 컨테이너 생성시 자동으로 생성되는 배치관리자  

**컨테이너에 새로운 배치관리자 설정**
* setLayoutt(LayoutManager lm) 메소드 호출 
    * lm을 새로운 배치관리자로 설정  

**FlowLayout 배치관리자**  
* 배치 방법
    * 컴포넌트를 컨테이너 내에 왼쪽에서 오른쪽으로 배치
        * 다시 위에서 아래로 순서대로 배치  

**FlowLayout의 생성자**  
* 생성자
    * FlowLayout()
    * FlowLayout(int align, int hGap, int yGap)
        * align : 컴포넌트를 정렬하는 방법 지정, 왼쪽 정렬(FlowLayoutLEFT), 오른쪽 정렬(FlowLayoutRIGHT), 중앙 정렬(FlowLayoutCENTER(디폴트))
        * hGap : 좌우 두 컴포넌트 사이의 수평 간격, 픽셀 단위, 디폴트는 5
        * vGap : 상하 두 컴포넌트 사이의 수직 간격, 픽셀 단위, 디폴트는 5

**FlowLayout 배치관리자 활용**
~~~java
import javax.swing.*;
import java.awt.*;
    
    public class FlowLayoutEx extends JFrame {
        public FlowLayoutEx() {
            setTitle("FlowLayout 예제");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container contentPane = getContentPane();
        
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));
        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        contentPane.add(new JButton("Calculate"));
        
        setSize(300, 200);
        setVisible(true);
    }
        public static void main(String[] args) {
        new FlowLayoutEx();
    }
}
~~~

**BorderLayout 배치관리자**  
* 배치방법
    * 컨테이너 공간을 5 구역으로 분할, 배치
        * 동, 서, 남, 북, 중앙

**BorderLayout 생성자와 add()메소드**
* 생성자
    * BorderLayout()
    * BorderLayout(int hGap, int vGap)
        * hGap : 좌우 두 컴포넌트 사이의 수평 간격, 픽셀 단위(디폴트 : 0)
        * vGap : 상하 두 컴포넌트 사이의 수직 간격, 픽셀 단위(디폴트 : 0)
* add() 메소드
    * void add(Component comp, int index)
        * comp 컴포넌트를 index 위치에 삽입한다.
        * index : 컴포넌트의 위치  
            * 동 : BorderLayout.EAST 
            * 서 : BorderLayout.WEST 
            * 남 : BorderLayout.SOUTH 
            * 북 : BorderLayout.NORTH  
            * 중앙 : BorderLayout.CENTER

**BorderLayout 배치관리자 활용**

~~~java
import javax.swing.*;
import java.awt.*;
    
    public class BorderLayoutEx extends JFrame {
        public BorderLayoutEx() {
            setTitle("BorderLayout 예제");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout(30, 20));
        contentPane.add(new JButton("Calculate"), BorderLayout.CENTER);
        contentPane.add(new JButton("add"), BorderLayout.NORTH);
        contentPane.add(new JButton("sub"), BorderLayout.SOUTH);
        contentPane.add(new JButton("mul"), BorderLayout.EAST);
        contentPane.add(new JButton("div"), BorderLayout.WEST);
        
        setSize(300, 200); 
        setVisible(true); 
    }
        public static void main(String[] args) {
        new BorderLayoutEx();
    }
}

~~~

**GrigLayout 배치관리자**
* 배치방법  
    * 컨테이너 공간을 동일한 사각형 격자(그리드)로 분할하고 각 셀에 컴포넌트 하나씩 배치
        * 생성자에 행수와 열수 지정  
        * 셀에 왼쪽에서 오른쪽으로, 다시 위에서 아래로 순서대로 배치

**GridLayout 생성자**
* 생성자
    * GridLayout(int rows, int cols)
    * GridLayout(int rows, int cols, int hGap, int vGap)
        * rows : 격자의 행수 (디폴트 : 1)
        * cols : 격자의 열수 (디폴트 : 1)
        * hGap : 좌우 두 컴포넌트 사이의 수평 간격, 픽셀 단위(디폴트 : 0)
        * vGap : 상하 두 컴포넌트 사이의 수직 간격, 픽셀 단위(디폴트 : 0)
        * rows x cols 만큼의 셀을 가진 격자로 컨테이너 공간을 분할, 배치

**GridLayout 배치관리자 활용**

~~~ java
import java.awt.*;
import javax.swing.*;
    
    public class GridLayoutEx extends JFrame {
        public GridLayoutEx() {
        super("GridLayout 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(new GridLayout(1, 10));
            for(int i=0; i<10; i++) {
                String text = Integer.toString(i);
                JButton button = new JButton(text);
                contentPane.add(button);
            }               
            setSize(500, 200);
            setVisible(true);
        }
    public static void main(String[] args) {
        new GridLayoutEx();
    }
}
~~~

**배치관리자 없는 컨테이너**
* 배치관리자 없는 컨테이너가 필요한 경우
    * 응용프로그램에서 직접 컴포넌트의 크기와 위치를 결정하고자 하는 경우
        1. 컴포넌트의 크기나 위치를 개발자 임의로 결정하고자 하는 경우
        2. 게임 프로그램과 같이 시간이나 마우스/키보드의 입력에 따라 컴포넌트들의 위치와 크기가 수시로 변하는 경우
        3. 여러 컴포넌트들이 서로 겹쳐 출력하고자 하는 경우

**컴포넌트의 절대 위치와 크기 설정**
* 배치관리자가 없는 컨테이너에 컴포넌트를 삽입할 때
    * 프로그램에서 컴포넌트의 절대 크기와 위치 설정
    * 컴포넌트들이 서로 겹치게 할 수 있음


## 5월 10일
**휴강**

## 5월 3일  
**컬렉션의 개념**  
* 컬렉션  
    * 요소 라고 불리는 가변 개수의 객체들의 저장소  
    * 고정 크기의 배열을 다루는 어려움 해소  
    * 다양한 객체들의 삽입, 삭제, 검색 등의 관리 용이  

**컬렉션의 특징**  
1. 컬렉션은 제네릭(generics)기법으로 구현  
    * 제네릭  
        * 특정 타입만 다루지 않고, 여러 종류의 타입으로 변신할 수 있도록 클래스나 메소드를 일반화시키는 기법  
        * 클래스나 인터페이스 이름에 E, k, v등 타입매개변수 포함  
    * 제네릭 컬렉션 사례: 벡터 Vector E 
        * E에서 E에 구체적인 타입을 주어 구체적인 타입만 다루는 벡터로 활용  
        * 정수만 다루는 컬렉션 벡터 Vector<Integer>  
        * 문자열만 다루는 컬렉션 벡터 Vector<String>  
2. 컬렉션의 요소는 객체만 가능  
    * int,char,double 등의 기본 타입으로 구체화 불가  

**제네릭**  
* 모든 종류의 데이터 타입을 다룰 수 있도록 일반화된 타입 매개 변수로 클래스(인터페이스)나 메소드를 작성하는 기법
* C++의 템플릿(template)과 동일  

**Vector E**  
* 벡터 Vector E의 특성  
    * E에 사용할 요소의 특정 타입으로 구체화  
    * 배열을 가변 크기로 다룰 수 있게 하는 컨테이너  
        * 배열의 길이 제한 극복  
        * 요소의 개수가 넘치면 자동으로 길이 조절  
    * 요소 객체들을 삽입, 삭제, 검색하는 컨테이너  
        * 삽입, 삭제에 따라 자동으로 요소의 위치 조정  
    * Vector에 삽입 가능한 것  
        * 객체.null  
        * 기본 타입의 값은 Wrapper 객체로 만들어 저장  
    * Vector에 객체 삽입  
        * 벡터의 맨 뒤, 중간에 객체 삽입 가능  
    * Vector에서 객체 삭제  
        * 임의의 위치에 있는 객체 삭제 가능  

**벡터 사용 예시**
~~~ java
import java.util.Vector;

public class VectorEx {
    public static void main(String[] args) {
        Vector<Integer> v = new Vector<Integer>();
        v.add(5);
        v.add(4);
        v.add(-1);

        v.add(2,100);
        System.out.println("벡터 내의 요소 객체 수" + v.size());
        System.out.println("벡터의 현재 용량" + v.capacity());

        for(int i=0; i<v.size(); i++) {
            int n = v.get(i);
            System.out.println(n);
        }

        int sum =  0;

        for(int i=0; i<v.size(); i++) {
            int n = v.elementAt(i);
            sum += n;   
        }
        System.out.println("벡터에 있는 정수 합" + sum);
    }
}
~~~

**ArrayList[E]**  
* 가변 크기 배열을 구현한 클래스  
    * E에 요소로 사용할 특정 타입으로 구체화
* 벡터와 거의 동일  
    * 요소 삽입, 삭제, 검색 등 벡터 기능과 거의 동일
    * 벡터와 달리 스레드 동기화 기능 없음  

**컬렉션의 순차 검색을 위한 Iterator**  
* Iterator E 인터페이스  
    * 리스트 구조의 컬렉션에서 요소의 순차 검색을 위한 인터페이스  
        * Vector E, ArrayList E, LinkedList E가 상속받는 인터페이스  

* Iterator 객체 얻어내기  
    * 컬렉션의 iterator() 메소드 호출  
        * 해당 컬렉션을 순차 검색할 수 있는 Iterator 객체 리턴  

**Iterator 사용 예시**
~~~ java
import java.util.*;
public class IteratorEx {
public static void main(String[] args) {

    Vector<Integer> v = new Vector<Integer>();
   
    v.add(5); 
    v.add(4); 
    v.add(-1); 
    v.add(2, 100); 
    
    Iterator<Integer> it = v.iterator(); 
    
    while(it.hasNext()) {
        int n = it.next();
        System.out.println(n);
}

    int sum = 0;
    it = v.iterator(); 
       
    while(it.hasNext()) {
        int n = it.next();
        sum += n;
        }
        System.out.println("벡터에 있는 정수 합 : " + sum);
    }
}
~~~

**HashMap<K,V>**  
* 키(key)와 값(value)의 쌍으로 구성되는 요소를 다루는 컬렉션  
    * K : 키로 사용할 요소의 타입  
    * V : 값으로 사용할 요소의 타입  
    * 키와 값이 한 쌍으로 삽입  
    * '값'을 검색하기 위해서는 반드시 '키' 이용  
* 삽입 및 검색이 빠른 특징  
    * 요소 삽입 : put() 메소드  
    * 요소 검색 : get() 메소드  

**HashMap 사용 예시**

~~~java
import java.util.*;
public class HashMapDicEx {

    public static void main(String[] args) {

    HashMap<String, String> dic = new HashMap<String, String>();

    dic.put("baby", "아기"); 
    dic.put("love", "사랑");
    dic.put("apple", "사과");

    Set<String> keys = dic.keySet(); 
    Iterator<String> it = keys.iterator();
        
        while(it.hasNext()) {
            String key = it.next();
            String value = dic.get(key);
            System.out.print("(" + key + "," + value + ")");
    }
        System.out.println();

        Scanner scanner = new Scanner(System.in);
            for(int i=0; i<3; i++) {
                System.out.print("찾고 싶은 단어는?");
        String eng = scanner.next();
        String kor = dic.get(eng);
            if(kor == null)
                System.out.println(eng + "는 없는 단어 입니다.");
            else
            System.out.println(kor);
        }
    }
}
~~~
**자바의 GUI(Graphical User Interface)**  
* GUI 응용프로그램  
    * GUI  
        * 사용자가 편리하게 입출력 할 수 있도록 그래픽으로 화면을 구성하고, 마우스나 키보드로 입력받을 수 있도록 지원하는 사용자 인터페이스
* 자바 언어에서 GUI 응용프로그램 작성
    * AWT와 Swing 패키지에 강력한 GUI 컴포넌트 제공
        * 쉬운 GUI 프로그래밍  
* AWT와 Swing 패키지  
    * AWT(Abstract Windowing Toolkit) 패키지
        * 자바가 처음 나았을 때부터 배포된 GUI 패키지, 최근에는 거의 사용하지 않음
        * AWT 컴포넌트는 중량 컴포넌트(heavy weight component)
* Swing 패키지
    * AWT 기술을 기반으로 작성된 자바 라이브러리
    * 모든 AWT 기능 + 추가된 풍부하고 화려한 고급 컴포넌트
    * AWT 컴포넌트를 모두 스윙으로 재작성. AWT 컴포넌트 이름 앞에 J자를 덧붙임
    * 순수 자바 언어로 구현
    * 스윙 컴포넌트는 경량 컴포넌트(light weight component)
    * 현재 자바의 GUI로 사용됨  

**컨테이너와 컴포넌트**  
* 컨테이너
    * 다른 컴포넌트를 포함할 수 있는 GUI 컴포넌트
        * java.awt.Container를 상속받음
    * 다른 컨테이너에 포함될 수 있음
        * AWT 컨테이너 : Panel, Frame, Applet, Dialog, Window
        * Swing 컨테이너 : JPanel JFrame, JApplet, JDialog, JWindow
* 컴포넌트
    * 컨테이너에 포함되어야 화면에 출력될 수 있는 GUI 객체
    * 다른 컴포넌트를 포함할 수 없는 순수 컴포넌트
    * 모든 GUI 컴포넌트가 상속받는 클래스 : java.awt.Component
    * 스윙 컴포넌트가 상속받는 클래스 : javax.swing.JComponent
* 최상위 컨테이너
    * 다른 컨테이너에 포함되지 않고도 화면에 출력되며 독립적으로 존재 가능한 컨테이너
        * 스스로 화면에 자신을 출력하는 컨테이너 : JFrame, JDialog, JApplet

**스윙 GUI 프로그램 만들기**  
* 스윙 GUI 프로그램을 만드는 과정
1. 스윙 프레임 만들기
2. main() 메소드 작성
3. 스윙 프레임에 스윙 컴포넌트 붙이기

* 스윙 프로그램 작성에 필요한 import문
    * import java.awt.*; // 그래픽 처리를 위한 클래스들의 경로명
    * import java.awt.event.*; // AWT 이벤트 사용을 위한 경로명
    * import javax.swing.*; // 스윙 컴포넌트 클래스들의 경로명
    * import javax.swing.event.*; // 스윙 이벤트를 위한 경로명

**스윙 프레임**  
* 스윙 프레임 : 모든 스윙 컴포넌트를 담는 최상위 컨테이너
    * JFrame을 상속받아 구현
    * 컴포넌트들은 화면에 보이려면 스윙 프레임에 부착되어야 함
        * 프레임을 닫으면 프레임에 부착된 모든 컴포넌트가 보이지 않게 됨
* 스윙 프레임(JFrame) 기본 구성
    * 프레임 – 스윙 프로그램의 기본 틀
    * 메뉴바 – 메뉴들이 부착되는 공간
    * 컨텐트팬 – GUI 컴포넌트들이 부착되는 공간  

**프레임 만들기, JFrame 클래스 상속**  
* 스윙 프레임
    * JFrame 클래스를 상속받은 클래스 작성
    * 프레임의 크기 반드시 지정 : setSize() 호출
    * 프레임을 화면에 출력하는 코드 반드시 필요 : setVisible(true) 호출 

**300x300 프레임 만들기**  

~~~java
import javax.swing.*;

public class MyFrame extends JFrame{
    public MyFrame() {
        setTitle("300x300 Frame");
        setSize(300,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
    }
}

~~~

**프레임에 컴포넌트 붙이기**  
* 타이틀 달기  
    * super()나 setTitle()이용  
* 컨텐트팬에 컴포넌트 달기  
    * 컨텐트팬이란?  
        * 스윙 컴포넌트들이 부착되는 공간  


**컨텐트팬 예시**  
~~~java
import javax.swing.*;
import java.awt.*;

public class ContentPaneEx extends JFrame {
    public ContentPaneEx() {
        setTitle("ContentPane과 JFrame 예제"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container contentPane = getContentPane(); 
        contentPane.setBackground(Color.ORANGE); 
        contentPane.setLayout(new FlowLayout()); 
            
        contentPane.add(new JButton("OK")); 
        contentPane.add(new JButton("Cancel")); 
        contentPane.add(new JButton("Ignore")); 
           
        setSize(300, 150); 
        setVisible(true); 
        }
    public static void main(String[] args) {
        new ContentPaneEx();
    }
}

~~~
## 4월 19일  
**메소드 오버라이딩**  
* 메소드 오버라이딩  
    * 서브 클래스에서 슈퍼 클래스의 메소드 중복 작성  
    * 슈퍼 클래스의 메소드 무력화, 항상 서브 클래스에 오버라이딩한 메소드가 실행되도록 보장됨  

**오버라이딩의 목적, 다형성 실현**  
* 오버라이딩으로 다형성 실현  
    * 하나의 인터페이스(같은 이름)에 서로 다른 구현  

**추상 클래스**  
* 추상 메소드  
    * abstract로 선언된 메소드, 메소드의 코드는 없고 원형만 선언  
* 추상 클래스  
    * 추상 메소드를 가지며, abstract로 선언된 클래스  
    * 추상 메소드 없이, abstract로 선언한 클래스  

**추상 클래스의 상속과 구현**  
* 추상 클래스 상속  
    * 추상 클래스를 상속받으면 추상 클래스가 됨  
    * 서브 클래스도 abstract로 선언해야 함  
* 추상 클래스 구현  
    * 서브 클래스에서 슈퍼 클래스의 추상 메소드 구현(오버라이딩)  
    * 추상 클래스를 구현한 서브 클래스는 추상 클래스 아님  

**추상 클래스의 목적**  
* 추상 클래스의 목적  
    * 상속을 위한 슈퍼 클래스를 활용하는 것  
    * 서브 클래스에서 추상 메소드 구현  
    * 다형성 실현  

**자바의 인터페이스**  
* 자바의 인터페이스  
    * 클래스가 구현해야 할 메소드들이 선언되는 추상형  
    * 인터페이스 선언  
        * interface 키워드로 선언  

**인터페이스의 상속**  
* 인터페이스 간에 상속 가능  
    * 인터페이스를 상속하여 확장된 인터페이스 작성 가능  
    * 여러 개의 인터페이스 동시 구현 가능  

**자바의 패키지와 모듈**  
* 패키지  
    * 서로 관련돤 클래스와 인터페이스를 컴파일한 클래스 파일들을 묶어 놓은 디렉터리  
    * 하나의 응용프로그램은 한 개 이상의 패키지로 작성  
    * 패키지는 jar 파일로 압축할 수 있음  

* 모듈  
    * 여러 패키지와 이미지 등의 저원을 모아 놓은 컨테이너  
    * 하나의 모듈을 하나의 .jmod 파일에 저장  

* java 9부터 모듈화 도입  
    * 플랫폼의 모듈화  
    * 응용프로그램의 모듈화  

**자바의 모듈화의 목적**  
* 모듈화의 목적  
    * java 9부터 자바 API를 여러 모듈(99개)로 분할  
    * 응용프로그램이 실행할 때 꼭 필요한 모듈들로만 실행 환경 구축  
        * 메모리 자원이 열악한 작은 소형 기기에 꼭 필요한 모듈로 구성된 작은 크기의 실행 이미지를 만들기 위함  

**패키지 사용하기, import문**  
* 필요한 클래스만 import  
    * 소스 시작 부분에 클래스의 경로명 import  
    * import 패키지.클래스  
    * 소스에는 클래스 명만 명시하면 됨  

**패키지 만들기**  
* 클래스 파일(.class)이 저장되는 위치는?  
    * 클래스나 인터페이스가 컴파일되면 클래스 파일(.class)생성  
    * 클래스 파일은 패키지로 선언된 디렉터리에 저장  
* 패키지 선언  
    * 소스 파일의 맨 앞에 컴파일 후 저장될 패키지 지정  

**자바 모듈화의 목적**  
* 가장 큰 목적  
    * 자바 컴포넌트들을 필요에 따라 조립하여 사용하기 위함  
    * 컴퓨터 시스템의 불필요한 부담 감소  

**Object 클래스**  
* 특징  
    * 모든 자바 클래스는 반드시 Object를 상속받도록 자동 컴파일  
        * 모든 클래스의 슈퍼 클래스  
        * 모든 클래스가 상속받는 공통 메소드 포함  

**Wrapper 클래스**  
* Wrapper 클래스  
    * 자바의 기본 타입을 클래스화한 8개 클래스를 통칭  
        * Byte, Short, Integer, Long, Character, Float, Double, Boolean
* 용도  
    * 객체만 사용할 수 있는 컬렉션 등에 기본 타입의 값을 사용하기 위해 Wrapper 객체로 만들어 사용  

**박싱과 언박싱**  
* 박싱(boxing)
    * 기본 타입과 값을 Wrapper 객체로 변환하는 것  
* 언박싱(unboxing)
    * Wrapper 객체에 들어 있는 기본 타입의 값을 빼내는 것  
    * 박싱의 반대  


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