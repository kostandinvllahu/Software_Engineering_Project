package ui;

import query.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * A UI panel for entering new queries.
 */
public class NewQueryPanel extends JPanel {
    private final JTextField newQuery = new JTextField(10);
    private final JLabel queryLabel = new JLabel("Enter Search: ");
    private final JPanel colorSetter;
    private final Application app;
    private Random random;

    public NewQueryPanel(Application application) {
        this.app = application;
        this.colorSetter = new JPanel();

        random = new Random();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        queryLabel.setLabelFor(newQuery);
        GridBagConstraints grideBagContstaints = new GridBagConstraints();
        grideBagContstaints.gridwidth = GridBagConstraints.RELATIVE;
        grideBagContstaints.fill = GridBagConstraints.NONE;
        grideBagContstaints.gridy = 0;
        grideBagContstaints.gridx = 0;
        add(queryLabel, grideBagContstaints);
        grideBagContstaints.gridwidth = GridBagConstraints.REMAINDER;
        grideBagContstaints.fill = GridBagConstraints.HORIZONTAL;
        newQuery.setMaximumSize(new Dimension(200, 20));
        grideBagContstaints.gridx = 1;
        add(newQuery, grideBagContstaints);

        add(Box.createRigidArea(new Dimension(5, 5)));

        JLabel colorLabel = new JLabel("Select Color: ");
        colorSetter.setBackground(getRandomColor());

        grideBagContstaints.gridwidth = GridBagConstraints.RELATIVE;
        grideBagContstaints.fill = GridBagConstraints.NONE;
        grideBagContstaints.gridy = 1;
        grideBagContstaints.gridx = 0;
        add(colorLabel, grideBagContstaints);
        grideBagContstaints.gridwidth = GridBagConstraints.REMAINDER;
        grideBagContstaints.fill = GridBagConstraints.BOTH;
        grideBagContstaints.gridx = 1;
        colorSetter.setMaximumSize(new Dimension(200, 20));
        add(colorSetter, grideBagContstaints);

        add(Box.createRigidArea(new Dimension(5, 5)));

        JButton addQueryButton = new JButton("Add New Search");
        grideBagContstaints.gridx = GridBagConstraints.RELATIVE;       //aligned with button 2
        grideBagContstaints.gridwidth = 2;   //2 columns wide
        grideBagContstaints.gridy = GridBagConstraints.RELATIVE;       //third row
        add(addQueryButton, grideBagContstaints);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("New Search"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));

        addQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!newQuery.getText().equals("")) {
                    addQuery(newQuery.getText().toLowerCase());
                    newQuery.setText("");
                }
            }
        });

        // This makes the "Enter" key submit the query.
        application.getRootPane().setDefaultButton(addQueryButton);

        colorSetter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    Color newColor = JColorChooser.showDialog(
                            application,
                            "Choose Background Color",
                            colorSetter.getBackground());
                    // newColor is "null" if user clicks "Cancel" button on JColorChooser popup.
                    if (newColor != null) {
                        colorSetter.setBackground(newColor);
                    }
                }
            }
        });
    }

    private void addQuery(String newQuery) {
        Query query = new Query(newQuery, colorSetter.getBackground(), app.map());
        app.addQuery(query);
        colorSetter.setBackground(getRandomColor());
    }

    public Color getRandomColor() {
        // Pleasant colors: https://stackoverflow.com/questions/4246351/creating-random-colour-in-java#4246418
        final float hue = random.nextFloat();
        final float saturation = (random.nextInt(2000) + 1000) / 10000f;
        final float luminance = 0.9f;
        return Color.getHSBColor(hue, saturation, luminance);
    }
}
