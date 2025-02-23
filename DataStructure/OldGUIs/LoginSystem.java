/**
 * Registration of the library
 * @author Mustafa Gurler
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LoginSystem extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel surname;
    private JTextField tsurname;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private String years[]
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019" };

    public LoginSystem(){
        setTitle("Registration Form");
        setBounds(300,90,900,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300,30);
        title.setLocation(300,30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial",Font.PLAIN,20));
        name.setSize(100,20);
        name.setLocation(100,100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial",Font.PLAIN,15));
        tname.setSize(190,20);
        tname.setLocation(200,100);
        c.add(tname);

        surname = new JLabel("Surname");
        surname.setFont(new Font("Arial",Font.PLAIN,20));
        surname.setSize(100,20);
        surname.setLocation(100,150);
        c.add(surname);

        tsurname = new JTextField();
        tsurname.setFont(new Font("Arial",Font.PLAIN,15));
        tsurname.setSize(190,20);
        tsurname.setLocation(200,150);
        c.add(tsurname);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100,20);
        gender.setLocation(100,200);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN,15));
        male.setSelected(false);
        male.setSize(75,20);
        male.setLocation(200,200);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN,15));
        female.setSelected(false);
        female.setSize(80,20);
        female.setLocation(275,200);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial",Font.PLAIN,20));
        dob.setSize(100,20);
        dob.setLocation(100,250);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial",Font.PLAIN,15));
        date.setSize(50,20);
        date.setLocation(200,250);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial",Font.PLAIN,15));
        month.setSize(60,20);
        month.setLocation(250,250);
        c.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial",Font.PLAIN,15));
        year.setSize(60,20);
        year.setLocation(320,250);
        c.add(year);

        term = new JCheckBox("Rıza Metni");
        term.setFont(new Font("Arial",Font.PLAIN,15));
        term.setSize(250,50);
        term.setLocation(150,400);
        c.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial",Font.PLAIN, 15));
        sub.setSize(100,20);
        sub.setLocation(150,450);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial",Font.PLAIN,15));
        reset.setSize(100,20);
        reset.setLocation(270,450);
        reset.addActionListener(this);
        c.add(reset);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300,400);
        tout.setLocation(500,100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);
        res = new JLabel("");
        res.setFont(new Font("Arial",Font.PLAIN, 20));
        res.setSize(500,25);
        res.setLocation(100,500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial",Font.PLAIN, 15));
        resadd.setSize(200,75);
        resadd.setLocation(580,175);
        resadd.setLineWrap(true);
        c.add(resadd);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sub){
            if(term.isSelected()){
                JOptionPane.showMessageDialog(null,"Message","mustafa",JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
