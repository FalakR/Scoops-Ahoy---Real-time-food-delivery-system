package view;

import data_access.FileIceCreamDataAccessObject;
import entities.Cart;
import entities.CartFactory;
import entities.CommonCartFactory;
import entities.IceCream;
import interface_adapters.add_to_cart.AddToCartController;
import interface_adapters.add_to_cart.AddToCartPresenter;
import interface_adapters.add_to_cart.AddToCartViewModel;
import use_cases.add_to_cart.AddToCartOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class BrowseView extends JFrame implements ActionListener, PropertyChangeListener {

    public final String viewName = "BrowseView";

    private final AddToCartViewModel addToCartViewModel;
    private final AddToCartController addToCartController;
    private final AddToCartPresenter addToCartPresenter;
    private final FileIceCreamDataAccessObject fileIceCreamDataAccessObject;

    private final JButton ChocolateChip;

    private final JButton Vanilla;

    private final JButton Strawberry;

    private final JButton MintChocolateChip;

    private final JButton Next;

    private ArrayList<IceCream> list;


    public BrowseView(AddToCartController addToCartController, AddToCartPresenter addToCartPresenter, AddToCartViewModel addToCartViewModel, FileIceCreamDataAccessObject fileIceCreamDataAccessObject) {

        this.addToCartController = addToCartController;
        this.addToCartPresenter = addToCartPresenter;
        this.addToCartViewModel = addToCartViewModel;
        this.fileIceCreamDataAccessObject = fileIceCreamDataAccessObject;
//        this.cartFactory = cartFactory;
        addToCartViewModel.addPropertyChangeListener(this);
        list = new ArrayList<>();

        JLabel title = new JLabel("Scoops Ahoy");
        title.setFont(new Font("Engravers Gothic BT", Font.BOLD, 24));


        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;  // Center-align components
        gbc.insets = new Insets(10, 10, 10, 10); // Adjust insets as needed

        ChocolateChip = new JButton("<html>Chocolate Chip<br>Flavour: Cookie Dough<br>Price: $10</html>");
        ChocolateChip.setPreferredSize(new Dimension(400, 150));
        buttons.add(ChocolateChip, gbc);

        gbc.gridy++;
        Vanilla = new JButton("<html>Vanilla<br>Flavour: Classic Vanilla<br>Price: $9</html>");
        Vanilla.setPreferredSize(new Dimension(400, 150));
        buttons.add(Vanilla, gbc);

        gbc.gridy++;
        Strawberry = new JButton("<html>Strawberry<br>Flavour: Creamy Strawberry<br>Price: $11</html>");
        Strawberry.setPreferredSize(new Dimension(400, 150));
        buttons.add(Strawberry, gbc);

        gbc.gridy++;
        MintChocolateChip = new JButton("<html>Mint Chocolate Chip<br>Flavour: Mint Chocolate Chip<br>Price: $12</html>");
        MintChocolateChip.setPreferredSize(new Dimension(400, 150));
        buttons.add(MintChocolateChip, gbc);

        gbc.gridy++;
        Next = new JButton("Next");
        Next.setPreferredSize(new Dimension(100, 50));
        buttons.add(Next, gbc);

        buttons.setBackground(new Color(255, 182, 193));


        // Set text color
        title.setForeground(new Color(77, 77, 77)); // Dark Gray

        ChocolateChip.setBackground(new Color(255, 182, 193)); // Baby Pink
        Vanilla.setBackground(new Color(255, 182, 193)); // Baby Pink
        Strawberry.setBackground(new Color(255, 182, 193)); // Baby Pink
        MintChocolateChip.setBackground(new Color(255, 182, 193)); // Baby Pink

        ChocolateChip.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(ChocolateChip)) {
                            IceCream myicecream = fileIceCreamDataAccessObject.getIceCream("ChocolateChip");
                            list.add(myicecream);
                            addToCartController.execute(list);
                        }
                    }
                }
        );

        Vanilla.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Vanilla)) {
                            IceCream myicecream = fileIceCreamDataAccessObject.getIceCream("Vanilla");
                            list.add(myicecream);
                            addToCartController.execute(list);
                        }
                    }
                }
        );

        Strawberry.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Strawberry)) {
                            IceCream myicecream = fileIceCreamDataAccessObject.getIceCream("Strawberry");
                            list.add(myicecream);
                            addToCartController.execute(list);
                        }
                    }
                }
        );

        MintChocolateChip.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(MintChocolateChip)) {
                            IceCream myicecream = fileIceCreamDataAccessObject.getIceCream("MintChocolateChip");
                            list.add(myicecream);
                            addToCartController.execute(list);
                        }
                    }
                }
        );

        Next.addActionListener((
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Next)) {
                            System.out.println("1");
                            System.out.println("Cart " + list);
                            CartFactory cartFactory = new CommonCartFactory();
                            Cart finalcart = cartFactory.create(list);
                            AddToCartOutputData outputData = new AddToCartOutputData(finalcart);
                            addToCartPresenter.prepareSuccessView(outputData);
                        }
                    }
                }
        ));


        this.setLayout(new BorderLayout());

        // Create a panel for the title with FlowLayout to center align horizontally
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

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
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Cancel not implemented yet.");
    }

    public void propertyChange(PropertyChangeEvent evt) {
//        I don't think this needs to be implemented.
    }

}
