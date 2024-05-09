package studentmanage;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import studentmanage.Connectionbd;

public class DataTransferApp extends JFrame {
    private JButton exportButton;
    private JButton importButton;

    public DataTransferApp() {
        setTitle("Data Transfer Tool");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        exportButton = new JButton("Export to XML");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportToXML();
            }
        });
        panel.add(exportButton);

        importButton = new JButton("Import from XML");
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                importFromXML();
            }
        });
        panel.add(importButton);

        add(panel);
    }

    private void exportToXML() {
        try {
            // Connexion à la base de données
            Connection con = Connectionbd.getConnection();

            // Récupération des données de la base de données
            PreparedStatement st = con.prepareStatement("SELECT * FROM student");
            ResultSet resultSet = st.executeQuery();

            // Création du document XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("data");
            document.appendChild(rootElement);

            // Parcours des résultats et création des éléments XML
            while (resultSet.next()) {
                Element entry = document.createElement("entry");
                rootElement.appendChild(entry);

                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(resultSet.getString("id")));
                entry.appendChild(id);
                
                
                 Element first_name = document.createElement("first_name");
                id.appendChild(document.createTextNode(resultSet.getString("first_name")));
                entry.appendChild(first_name);
                
                Element last_name = document.createElement("last_name");
                id.appendChild(document.createTextNode(resultSet.getString("last_name")));
                entry.appendChild(last_name);
                Element sex = document.createElement("sex");
                id.appendChild(document.createTextNode(resultSet.getString("sex")));
                entry.appendChild(sex);
                 Element adress = document.createElement("adress");
                id.appendChild(document.createTextNode(resultSet.getString("adress")));
                entry.appendChild(sex);
                 
                
                Element phone = document.createElement("phone");
                id.appendChild(document.createTextNode(resultSet.getString("phone")));
                entry.appendChild(phone);

                // Ajouter d'autres colonnes si nécessaire...
            }

            // Écriture du document XML dans un fichier
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("exported_data.xml"));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(this, "Data exported to XML successfully!");

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting data to XML: " + ex.getMessage());
        }
    }

    public void importFromXML() {
        try {
            File xmlFile = new File("C:\\Users\\Lenovo T460s\\OneDrive\\Documents\\NetBeansProjects\\projetjeee\\import.xml");

            // Lecture du fichier XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Récupération des données depuis le document XML
            NodeList entries = document.getElementsByTagName("entry");

            // Connexion à la base de données
            Connection con = Connectionbd.getConnection();

            // Insertion des données dans la base de données
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `student`( `first_name`, `last_name`, `sex`, `adress`, `phone`) VALUES (?,?,?,?,?)");

            // Traitement de chaque entrée dans le fichier XML
            for (int i = 0; i < entries.getLength(); i++) {
                Element entry = (Element) entries.item(i);

                // Extraction des valeurs des éléments enfants
                
                String firstName = getElementText(entry, "first_name");
                String lastName = getElementText(entry, "last_name");
                String sex = getElementText(entry, "sex");
                String address = getElementText(entry, "address");
                String phone = getElementText(entry, "phone");

                // Paramétrage des valeurs dans la requête SQL
               
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, sex);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, phone);

                // Exécution de la requête SQL d'insertion
                preparedStatement.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Data imported from XML successfully!");

            con.close();
        } catch (ParserConfigurationException | SAXException | IOException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error importing data from XML: " + ex.getMessage());
        }
    }

    private String getElementText(Element parentElement, String childTagName) {
        Node childNode = parentElement.getElementsByTagName(childTagName).item(0);
        if (childNode != null) {
            return childNode.getTextContent().trim();  // Retourne le contenu du nœud s'il existe
        }
        return "";  // Retourne une chaîne vide si le nœud n'existe pas
    }

    public static void main(String[] args) {
        // Exécution de l'importation à travers l'interface graphique Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DataTransferApp app = new DataTransferApp();
                app.setVisible(true); // Appel de la méthode d'importation depuis le XML
            }
        });
    }
}