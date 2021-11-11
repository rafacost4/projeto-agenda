package ui;

import business.ContactBusiness;
import com.sun.org.apache.xpath.internal.objects.XObject;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainForm extends JFrame {
     private JPanel  rootpanel ;
     private JButton buttonNewcontact;
     private JButton buttonRemove;
     private JTable tablecontacts;
    private JLabel labelContactCount;

    private ContactBusiness mContactbusiness;
    private String mName= "";
    private String mPhone= "";

     public MainForm() {

         setContentPane(rootpanel);
         setSize (500,250);
         setVisible( true);

         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         setLocation(dim.width/2 - getSize().width/ 2 , dim.height/2 - getSize().height/2);
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

         mContactbusiness =  new ContactBusiness();

         setListeners();
         loadContacts();
buttonNewcontact.setBackground(Color.yellow);

     }
     private void loadContacts() {
        List<ContactEntity>contactList= mContactbusiness.getLIst();
        String[] ColumNames = {"Nome", "Telefone"};

         DefaultTableModel model = new DefaultTableModel(new Object[0] [0], ColumNames);

         for (ContactEntity i : contactList) {
             Object[] o = new Object[2];

             o [0] = i.getName();
             o [1] = i.getPhone();
         model .addRow(o);
         }
         tablecontacts.clearSelection();
         tablecontacts.setModel(model);

         labelContactCount.setText(mContactbusiness.getContactCoutDescription());
     }
    private void setListeners() {
    buttonNewcontact. addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
                new ContactForm();
                dispose();
        }
    });

    tablecontacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if (listSelectionEvent.getValueIsAdjusting()) {

                if (tablecontacts.getSelectedRow() != -1) {
                    mName = tablecontacts.getValueAt(tablecontacts.getSelectedRow(), 0).toString();
                    mPhone = tablecontacts.getValueAt(tablecontacts.getSelectedRow(), 1).toString();
                }
            }
        }
    });


    buttonRemove.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
         try{mContactbusiness.delete (mName,mPhone);
             loadContacts();

             mName= "";
             mPhone= "";
         } catch (Exception excp) {
             JOptionPane. showMessageDialog(new JFrame(), excp.getMessage());
         }
        }
    });
    }

}

