package itmo.course2.pip.lab5.client.src;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

public class InputPanel extends JPanel {
    private static final int ENG = 0;
    private static final int EL = 1;

    private ResourceBundle resBundle;
    private Vector<Locale> locales;

    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel rLabel;
    private JList xCoordList;
    private ButtonGroup yCoordGroup;
    private JSlider rSlider;
    private JButton addPointBtn;
    private Double radius;
    private JComboBox<String> languagesBox;

    InputPanel(GraphicPanel graphicPanel) {
        resBundle = ResourceBundle.getBundle("client");

        locales = new Vector<Locale>();
        locales.add(new Locale("en", "US"));
        locales.add(new Locale("fr", "FR"));

        JFrame mainWindow = (JFrame) graphicPanel.getTopLevelAncestor();
        mainWindow.setTitle(resBundle.getString("title"));

        FlowLayout layout = new FlowLayout();
        layout.setHgap(15);
        this.setLayout(layout);

        languagesBox = new JComboBox<String>();
        languagesBox.addItem("English");
        languagesBox.addItem("Français");

        languagesBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLanguage(languagesBox.getSelectedIndex());
            }
        });


        xLabel = new JLabel(resBundle.getString("valX"));
        Float[] xCoordArray = {-5.0f, -3.0f, -2.0f, 0.0f, 1.0f, 4.0f};
        xCoordList = new JList(xCoordArray);
        xCoordList.setVisibleRowCount(xCoordArray.length);
        xCoordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        xCoordList.setSelectedIndex(0);

        yLabel = new JLabel(resBundle.getString("valY"));

        Box yBox = Box.createVerticalBox();

        yCoordGroup = new ButtonGroup();
        Float[] yCoordsArray = {-5.0f, -2.0f, 0.0f, 1.0f, 3.0f, 4.0f};
        for (int i = 0; i < yCoordsArray.length; i++) {
            JRadioButton rButton = new JRadioButton(yCoordsArray[i].toString());
            if (i == 0)
                rButton.setSelected(true);
            yCoordGroup.add(rButton);
            yBox.add(rButton);
        }

        addPointBtn = new JButton(resBundle.getString("addPoint"));
        addPointBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float xCoord = new Float((float) xCoordList.getSelectedValue());
                Float yCoord = 0.0f;
                for (Enumeration<AbstractButton> yElems = yCoordGroup.getElements(); yElems.hasMoreElements(); ) {
                    AbstractButton btn = yElems.nextElement();
                    if (btn.isSelected()) {
                        yCoord = Float.parseFloat(btn.getText());
                        break;
                    }
                }
                graphicPanel.addPoint(xCoord, yCoord);
            }
        });

        rLabel = new JLabel("R:");

        rSlider = new JSlider(JSlider.HORIZONTAL, 1, 30, 5);
        rSlider.setMajorTickSpacing(5);
        rSlider.setMinorTickSpacing(1);
        rSlider.setPaintTicks(true);
        rSlider.setSnapToTicks(true);
        rSlider.setPaintLabels(true);

        rSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    radius = (double) source.getValue();
                    graphicPanel.setRadius(radius);
                }
            }
        });


        this.add(languagesBox);
        this.add(xLabel);
        this.add(xCoordList);
        this.add(yLabel);
        this.add(yBox);
        this.add(addPointBtn);
        this.add(rLabel);
        this.add(rSlider);
    }

    private void setLanguage(int lang) {
        switch (lang) {
            case ENG:
            case EL:
                resBundle = ResourceBundle.getBundle("client", locales.elementAt(lang));
                break;
            default:
                resBundle = ResourceBundle.getBundle("client", locales.elementAt(ENG));
        }
        xLabel.setText(resBundle.getString("valX"));
        yLabel.setText(resBundle.getString("valY"));
        addPointBtn.setText(resBundle.getString("addPoint"));
        JFrame mainWindow = (JFrame) this.getTopLevelAncestor();
        mainWindow.setTitle(resBundle.getString("title"));
    }


}
