package ui.components.frames;

import entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JDialog {
    private Entity user;

    /**
     * frame used by the user to login
     * @param parentFrame AppFrame
     */
    public LoginFrame(AppFrame parentFrame){
        this.parentFrame = parentFrame;
        init();
    }

    private void init(){
        this.setSize(new Dimension(350,250));
        this.setTitle("Login");
        this.initComponents();
        this.setResizable(false);
        this.setModal(true);
    }

    private void initComponents(){
        JLabel instructions = new JLabel("Please Log In to continue");
        instructions.setFont(new Font("Arial",Font.BOLD,25));

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension((int) (this.getWidth()*0.7), 20));
        usernameField.setFont(fieldFont);

        JPasswordField pwdField = new JPasswordField();
        pwdField.setPreferredSize(new Dimension((int) (this.getWidth()*0.7), 20));
        pwdField.setFont(fieldFont);

        JLabel usernameLabel = new JLabel("Login");
        usernameLabel.setFont(new Font("Arial", Font.BOLD ,15));

        JLabel pwdLabel = new JLabel("Password");
        pwdLabel.setFont(new Font("Arial", Font.BOLD ,15));

        JButton frgtpwdButton = new JButton("<html><u>Forgot password ?</u></html>");
        frgtpwdButton.setPreferredSize(new Dimension(120, 20));
        frgtpwdButton.setFont(new Font("Arial", Font.PLAIN,10));
        frgtpwdButton.addActionListener(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Please contact an administrator to retrieve your password");
            }
        });

        JButton createAccountButton = new JButton("<html><u>Create Account</u></html>");
        createAccountButton.setPreferredSize(new Dimension(120, 20));
        createAccountButton.setFont(new Font("Arial", Font.PLAIN,10));
        createAccountButton.addActionListener(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Please contact an administrator to create a new account");
            }
        });

        JButton connect = new JButton("Connect");
        connect.setPreferredSize(new Dimension((int) (this.getWidth()*0.7), 20));
        connect.setFont(new Font("Arial", Font.BOLD ,12));

        this.errorLabel = new JLabel("Unknown credentials");
        this.errorLabel.setFont(new Font("Arial",Font.BOLD,15));
        this.errorLabel.setForeground(Color.red);
        this.errorLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        connect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = connect();
                if(userName == null){
                    LoginFrame.this.remove(LoginFrame.this.errorLabel);
                    LoginFrame.this.add(LoginFrame.this.errorLabel);
                    LoginFrame.this.repaint();
                }else{
                    //LoginFrame.this.parentFrame.getContentPanel().getTopPanel().getAccountButton().setUserName(userName);
                    LoginFrame.this.dispose();
                }
            }
        });

        JPanel allPanel = new JPanel();
        allPanel.setPreferredSize(this.getPreferredSize());
        allPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        allPanel.setBackground(parentFrame.getBackground().darker());

        JPanel titlepanel = new JPanel();
        titlepanel.setPreferredSize(new Dimension((int) (this.getWidth()*0.95), 40));
        titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlepanel.setBackground(this.getBackground());

        JPanel connectpanel = new JPanel();
        connectpanel.setPreferredSize(new Dimension((int) (this.getWidth()*0.95), (int) (this.getHeight()*0.75)));
        connectpanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8,10));
        connectpanel.setBackground(this.getBackground());

        titlepanel.add(instructions);
        connectpanel.add(usernameLabel);
        connectpanel.add(usernameField);
        connectpanel.add(pwdLabel);
        connectpanel.add(pwdField);
        connectpanel.add(frgtpwdButton);
        connectpanel.add(connect);
        connectpanel.add(createAccountButton);
        allPanel.add(titlepanel);
        allPanel.add(connectpanel);
        this.add(allPanel);
    }

    /**
     * Connects to the database
     * @return user_name:string
     */
    private String connect(){
        return null;
    }

    private final AppFrame parentFrame;
    private JLabel errorLabel;
    private final Font fieldFont = new Font("Arial", Font.PLAIN,15);
}
