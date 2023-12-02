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


        JPanel buttons = new JPanel();


        ChocolateChip = new JButton("Chocolate Chip \nFlavour: Cookie Dough \nPrice: $10");
        ChocolateChip.setPreferredSize(new Dimension(300, 150));
        buttons.add(ChocolateChip);

        Vanilla = new JButton("Vanilla \nFlavour: Classic Vanilla \nPrice: $9");
        Vanilla.setPreferredSize(new Dimension(300, 150));
        buttons.add(Vanilla);

        Strawberry = new JButton("Strawberry \nFlavour: Creamy Strawberry \nPrice: $$11");
        Strawberry.setPreferredSize(new Dimension(300, 150));
        buttons.add(Strawberry);

        MintChocolateChip = new JButton("MintChocolateChip \nFlavour: Mint Chocolate Chip \nPrice: $12");
        MintChocolateChip.setPreferredSize(new Dimension(300, 150));
        buttons.add(MintChocolateChip);

        Next = new JButton("Next");
        Next.setPreferredSize(new Dimension(100, 50));
        buttons.add(Next);


        buttons.setBackground(new Color(255, 218, 185));

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
