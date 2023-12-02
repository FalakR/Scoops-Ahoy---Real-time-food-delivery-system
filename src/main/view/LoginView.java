package view;

import interface_adapters.login.LoginController;
import interface_adapters.login.LoginState;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JFrame implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final LoginController loginController;

    /**
     * The username chosen by the user
     */
    final JTextField emailInputField = new JTextField(15);
    private final JLabel emailErrorField = new JLabel();
    /**
     * The password
     */
    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;

    /**
     * A window with a title and a JButton.
     */
    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Scoops Ahoy");
        title.setFont(new Font("Engravers Gothic BT", Font.BOLD, 24)); // Set font to Engravers Gothic BT

        emailInputField.setPreferredSize(new Dimension(300, 50));
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel("Email"), emailInputField);
        passwordInputField.setPreferredSize(new Dimension(300, 50));
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        logIn.setPreferredSize(new Dimension(100, 50));
        buttons.add(logIn);

        logIn.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            loginController.execute(loginViewModel.getState().getEmail(),
                                    loginViewModel.getState().getPassword());
                        }
                    }
                }
        );

        this.setBackground(new Color(253, 227, 245)); // Pastel Pink
        emailInfo.setBackground(new Color(255, 250, 205)); // Lemon Chiffon
        passwordInfo.setBackground(new Color(240, 255, 240)); // Honeydew
        buttons.setBackground(new Color(230, 230, 250)); // Mint Green

        // Set text color
        title.setForeground(new Color(77, 77, 77)); // Dark Gray

//        // Set button color
//        logIn.setBackground(new Color(255, 182, 193)); // Baby Pink
//        cancel.setBackground(new Color(255, 182, 193)); // Baby Pink

        emailInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateEmail();
            }

            private void updateEmail() {
                LoginState currentState = loginViewModel.getState();
                currentState.setEmail(emailInputField.getText());
                loginViewModel.setState(currentState);
            }
        });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(String.valueOf(passwordInputField.getPassword()) + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(emailInfo);
        formPanel.add(passwordInfo);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);

        // Pack the components to determine the frame size
        pack();

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Set the operation to close the application when the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();

        if (state.getEmailError() != null) {
            JButton okButton1 = new JButton("OK");

            okButton1.addActionListener(e -> {
                Component source = (Component) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(source);

                if (window != null) {
                    window.dispose();  // Close the top-level parent window
                }
            });

            SwingUtilities.invokeLater(() -> {
                JOptionPane.showOptionDialog(
                        this,
                        "User with this email does not exist. Please try signing up or logging in with a different email.",
                        state.getEmailError(),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new Object[]{okButton1},
                        okButton1);
            });
            resetState();
        }

        if (state.getPasswordError() != null) {
            JButton okButton = new JButton("OK");

            okButton.addActionListener(e -> {
                Component source = (Component) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(source);

                if (window != null) {
                    window.dispose();  // Close the top-level parent window
                }
            });

            SwingUtilities.invokeLater(() -> {
                JOptionPane.showOptionDialog(
                        this,
                        "The password entered is incorrect. Please try again.",
                        state.getPasswordError(),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new Object[]{okButton},
                        okButton);
            });
            resetState();
        }


//        if (state.getEmailError() != null) {
//            JButton okButton1 = new JButton("OK");
//
////            okButton1.addActionListener(e -> {
////                Container container = (Container) e.getSource();
////                Frame frame = JOptionPane.getFrameForComponent(container);
////                frame.dispose();  // Close the frame associated with the JOptionPane
////            });
//
//            okButton1.addActionListener(e -> {
//                Component source = (Component) e.getSource();
//                Window window = SwingUtilities.getWindowAncestor(source);
//
//                if (window != null) {
//                    window.dispose();  // Close the top-level parent window
//                }
//            });
//
//
//            JOptionPane.showOptionDialog(
//                    this,
//                    "User with this email does not exists. Please try signing up, or logging in with a different email.",
//                    state.getEmailError(),
//                    JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.ERROR_MESSAGE,
//                    null,
//                    new Object[]{okButton1},
//                    okButton1);
//        }
//        if (state.getPasswordError() != null) {
//            JButton okButton = new JButton("OK");
//
//            okButton.addActionListener(e -> {
//                Component source = (Component) e.getSource();
//                Window window = SwingUtilities.getWindowAncestor(source);
//
//                if (window != null) {
//                    window.dispose();  // Close the top-level parent window
//                }
//            });
//
//
//            JOptionPane.showOptionDialog(
//                    this,
//                    "The password entered is incorrect. Please try again.",
//                    state.getPasswordError(),
//                    JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.ERROR_MESSAGE,
//                    null,
//                    new Object[]{okButton},
//                    okButton);
//        }

        setFields(state);
    }

    private void setFields(LoginState state) {
        emailInputField.setText(state.getEmail());
        passwordInputField.setText(state.getPassword());
    }

    private void resetState() {
        // Reset the state to its initial or default values
        LoginState newState = new LoginState();
        loginViewModel.setState(newState);
    }
}

