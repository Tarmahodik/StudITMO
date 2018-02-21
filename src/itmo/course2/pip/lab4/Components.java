package itmo.course2.pip.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.BoxLayout.LINE_AXIS;


public class Components extends JPanel {
    public Components(final Drawing drawElements) {

        setLayout(new BorderLayout());
        add(drawElements, BorderLayout.CENTER);
        setBackground(Main.BACKGROUND_COLOR);


        String[] items = {
                "1",
                "2",
                "3"
        };
        Container content = new Container();

        //ÂÅÐÕÍßß ÑÒÎÐÎÍÀ
        //region Description
        content.setLayout(new BoxLayout(content, LINE_AXIS));


        JList<String> lis = new JList<String>(items);
        lis.setAlignmentY(RIGHT_ALIGNMENT);
        content.add(lis);


        final int FPS_MIN = 1;
        final int FPS_MAX = 6;
        final int FPS_INIT = 2;

        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);

        framesPerSecond.setMajorTickSpacing(10);
        framesPerSecond.setMinorTickSpacing(1);
        framesPerSecond.setPaintTicks(true);
        framesPerSecond.setPaintLabels(true);
        framesPerSecond.setSize(100, 20);
        framesPerSecond.addChangeListener(e -> drawElements.setRadius(framesPerSecond.getValue()));


        content.add(framesPerSecond);
        content.setSize(700, 100);
        add(content, BorderLayout.PAGE_START);

        setVisible(true);
        //endregion

        //ËÅÂÀß ÑÒÎÐÎÍÀ
        //region Description
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Main.BACKGROUND_COLOR);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();
        final JRadioButton rb1 = new JRadioButton("1");
        rb1.setBackground(Main.BACKGROUND_COLOR);
        group.add(rb1);
        JPanel jp = new JPanel();
        jp.setBackground(Main.BACKGROUND_COLOR);
        jp.add(rb1);
        mainPanel.add(jp);

        final JRadioButton rb2 = new JRadioButton("2");
        group.add(rb2);
        jp = new JPanel();
        jp.setBackground(Main.BACKGROUND_COLOR);
        jp.add(rb2);
        mainPanel.add(jp);

        final JRadioButton rb3 = new JRadioButton("3");
        group.add(rb3);
        jp = new JPanel();
        jp.setBackground(Main.BACKGROUND_COLOR);
        jp.add(rb3);
        mainPanel.add(jp);

        content.add(mainPanel);
        add(content, BorderLayout.WEST);
        //endregion

        //ÏÐÀÂÀß ÑÒÎÐÎÍÀ
        //region Description

        //endregion

        //ÖÅÍÒÐ

        //ÍÈÇ
        //region Description
        JButton button = new JButton("Make DOT");
        button.setSize(70, 30);
        add(button, BorderLayout.SOUTH);
        button.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String x = lis.getSelectedValue();
                int y;
                if (rb1.isSelected()) y = 1;
                else if (rb2.isSelected()) y = 2;
                else y = 3;
                drawElements.addPoint(new Double(x), y);
            }

            //endregion

        });
    }


}