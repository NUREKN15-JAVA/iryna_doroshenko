package ua.nure.kn156.doroshenko.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import ua.nure.kn156.doroshenko.User;
import ua.nure.kn156.doroshenko.db.DatabaseException;
import ua.nure.kn156.doroshenko.gui.MainFrame;

public class EditPanel extends AddPanel {
	private User user;

    public EditPanel(MainFrame parent) {
        super(parent);
        setName("editPanel");
    }

    protected void doAction(ActionEvent e) throws ParseException {
        System.out.println(user);
        if ("ok".equalsIgnoreCase(e.getActionCommand())) {
            user.setFirstName(getFirstNameField().getText());
            user.setLasttName(getLastNameField().getText());
            DateFormat format = DateFormat.getDateInstance();
            try {
                Date date = format.parse(getDateOfBirthField().getText());
                user.setDateOfBirthd(date);
            } catch (ParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                throw e1;
            }
            try {
                parent.getDao().update(user);
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void setUser(User user) {
        DateFormat format = DateFormat.getDateInstance();
        this.user = user;
        getFirstNameField().setText(user.getFirstName());
        getLastNameField().setText(user.getLasttName());
        getDateOfBirthField().setText(format.format(user.getDateOfBirthd()));
    }

}
