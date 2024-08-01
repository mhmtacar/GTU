import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class LibrarianGUI implements ActionListener {
    JFrame librarianFrame;
    JPanel panel1;

    /*
        Features of all librarian
     */
    JButton lendBook;
    JButton relendBook;
    JButton searchBook;
    JButton demandBook;
    /*
        Icon for title
     */
    URL book = new URL("https://i.pinimg.com/originals/2c/fc/93/2cfc93d7665f5d7728782700e50596e3.png");
    final ArrayList<Image> icons = new ArrayList<Image>();

    public LibrarianGUI() throws Exception {
        super();
        librarianFrame = new JFrame("Librarian System");
        librarianFrame.setTitle("Librarian System");
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0,1));
        librarianFrame.setLocationRelativeTo( null );
        icons.add(ImageIO.read(book));
        librarianFrame.setIconImages(icons);
        lendBook = new JButton("Lend Book");
        lendBook.setPreferredSize(new Dimension(150, 35));
        relendBook = new JButton(("Relend Book"));
        relendBook.setPreferredSize(new Dimension(150,35));
        searchBook = new JButton(("Search Book"));
        searchBook.setPreferredSize(new Dimension(150,35));
        demandBook = new JButton(("Demand Book"));
        demandBook.setPreferredSize(new Dimension(150,35));

        panel1.add(lendBook);
        panel1.add(relendBook);
        panel1.add(searchBook);
        panel1.add(demandBook);

        librarianFrame.add(panel1,BorderLayout.CENTER);

        librarianFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        librarianFrame.setSize(350,400);
        librarianFrame.setLocationByPlatform(true);
        librarianFrame.setResizable(false);
        librarianFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(e.getSource() == ){
            Admin admin = new Admin();
        }*/
    }
}
