import javax.swing.*;
import java.awt.*;
    
    public class ButtonImageEx extends JFrame {
        public ButtonImageEx() {
        setTitle("이미지 버튼 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        ImageIcon normalIcon = new ImageIcon("C:/fridayJava/java2-2/lib/Yee.jpg");
        ImageIcon rolloverIcon = new ImageIcon("C:/fridayJava/java2-2/lib/Yee2.jpg");
        ImageIcon pressedIcon = new ImageIcon("C:/fridayJava/java2-2/lib/Yee3.jpg");
        JButton btn = new JButton("call~~", normalIcon);
        btn.setPressedIcon(pressedIcon);
        btn.setRolloverIcon(rolloverIcon);
        
            c.add(btn);
            setSize(250,150);
            setVisible(true);
        }
    public static void main(String [] args) {
        new ButtonImageEx();
    }
}