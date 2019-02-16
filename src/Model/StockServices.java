package Model;

import Model.ProduitStock;
import Model.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Saidi Khaled
 */

public class StockServices {

    private Connection cnx;

    public StockServices() {
        cnx=ConnectionDB.getInstance().getConnection();
    }


    public void ajouterProduit(ProduitStock p) {
        String req="INSERT INTO Product (name, price, description, category, quantity) values( ?, ?, ?, ?, ?)";


        PreparedStatement ps = null;
        try {
            ps = cnx.prepareStatement(req);

            ps.setString(1,p.getName());
            ps.setDouble(2,p.getPrice());
            ps.setString(3,p.getDescription());
            ps.setString(4,p.getCategory());
            ps.setDouble(5,p.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    public void supprimerProduit(int Id) throws SQLException {
        String req="Delete from Product where (id=?);";
        PreparedStatement ps=cnx.prepareStatement(req);
        ps.setInt(1,Id);
        ps.executeUpdate();
        String req1="set @autoid :=0";
        String req2="update product set id = @autoid :=(@autoid+1)";
        String req3= "alter table product auto_increment = 1";
        Statement s=cnx.createStatement();
        s.executeUpdate(req1);
        s.executeUpdate(req2);
        s.executeUpdate(req3);
    }
    public boolean updateProduit(ProduitStock p, int Id) {
        try {
            String req = "UPDATE Product SET name=?,Price=?,description=?,Category=?,quantity=? WHERE id="+Id;

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1,p.getName());
            ps.setDouble(2,p.getPrice());
            ps.setString(3,p.getDescription());
            ps.setString(4,p.getCategory());
            ps.setDouble(5,p.getQuantity());

            ps.executeUpdate();

            System.out.println("Votre Produit a ete modifie avec succees");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }


    public List<ProduitStock> listerProduit() throws SQLException {
        List <ProduitStock> products= new ArrayList<>();
        String req="SELECT * FROM Product";
        Statement stm=cnx.createStatement();
        ResultSet rst=stm.executeQuery(req);

        while (rst.next()){
            ProduitStock p=new ProduitStock(rst.getInt("id"),rst.getString("name"),rst.getDouble("Price"),rst.getString("description"),rst.getString("category"),rst.getInt("quantity"));
            products.add(p);
        }
        return products;
    }
    public List<ProduitStock> filtrerProduit(String s) throws SQLException {
        List<ProduitStock> products = new ArrayList<>();
        String rq = "select * from product where id like'%"+s+"%' or name like'%"+s+"%' or price like'%"+s+"%'  or quantity like'%"+s+"%' or category like'%"+s+"%' or description like'%"+s+"%'";

        Statement st = cnx.createStatement();
        ResultSet rst = st.executeQuery(rq);

        while (rst.next()) {
            ProduitStock p=new ProduitStock(rst.getInt("id"),rst.getString("name"),rst.getDouble("price"),rst.getString("description"),rst.getString("category"),rst.getInt("quantity"));
            products.add(p);
        }

        return products;
    }
}
