package dao;

import dataAccessLayer.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WarehouseDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(WarehouseDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public WarehouseDAO(){
        this.type = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * The function represents the SQL code for printing an un-deleted record
     * @param field
     * @return
     */
    private String createSelectQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=? AND ok = 0");
        return sb.toString();
    }

    /**
     * The update function represents the SQL code for updating the quantity of a product
     * @return
     */
    private String createUpdateQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET quantityProduct =? WHERE idProduct =? AND ok=0");

        return sb.toString();
    }

    /**
     * The delete function symbolize the SQL code for deleting a record. In fact, the delete function updates the flag ok of a record, setting it to '1'
     * which symbolize that the record was deleted.
     * @param field
     * @return
     */
    private String createDeleteQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ok=1 WHERE " + field + "=? AND ok=0");

        return sb.toString();
    }

    /**
     * The function represents the SQL code for printing un-deleted records
     * @return
     */
    private String createSelectAllQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName()+" WHERE ok = 0");

        return sb.toString();
    }

    /**
     * The function represents the SQL code for inserting an un-deleted record with its values.
     * @param object
     * @return
     */
    private String createInsertQuery(Object object){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName()+" (");
        for(Field field : type.getDeclaredFields()){
            field.setAccessible(true);
            sb.append(field.getName()+",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES (");
        for(Field field : object.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object value;
            try{
                value = field.get(object);
                sb.append("'" + value + "',");
            } catch (IllegalArgumentException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }

    /**
     * The function search in the database a certain given type of record by its ID.
     * @param id
     * @return
     */

    public T findById(int id){
        Connection conncection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getDeclaredFields()[0].getName());
        try{
            conncection = ConnectionFactory.getConnection();
            statement = conncection.prepareStatement(query);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }   catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName()+ "DAO:findById " + e.getMessage());
        }   finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(conncection);
        }
        return null;
    }

    public void update(int id, int quantity){
        Connection conncection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();
        try{
            conncection = ConnectionFactory.getConnection();
            statement = conncection.prepareStatement(query);
            statement.setInt(1,quantity);
            statement.setInt(2,id);
            statement.executeUpdate();

        }   catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName()+ "DAO:Update " + e.getMessage());
        }   finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(conncection);
        }
    }

    public void delete(int id){
        Connection conncection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(type.getDeclaredFields()[0].getName());
        try{
            conncection = ConnectionFactory.getConnection();
            statement = conncection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();

        }   catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName()+ "DAO:Delete " + e.getMessage());
        }   finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(conncection);
        }
    }

    public void insert(Object object){
        Connection conncection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(object);
        try{
            conncection = ConnectionFactory.getConnection();
            statement = conncection.prepareStatement(query);
            statement.executeUpdate();

        }   catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName()+ "DAO:Insert " + e.getMessage());
        }   finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(conncection);
        }
    }

    public ArrayList<T> findAll(){
        Connection conncection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try{
            conncection = ConnectionFactory.getConnection();
            statement = conncection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }   catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName()+ "DAO:findAll " + e.getMessage());
        }   finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(conncection);
        }
        return null;

    }


    private ArrayList<T> createObjects(ResultSet resultSet){
        ArrayList<T> list = new ArrayList<>();
        try{
            while(resultSet.next()){
                T instance = type.newInstance();
                for(Field field: type.getDeclaredFields()){
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance,value);
                }
                list.add(instance);
            }
        } catch (IllegalAccessException | InstantiationException | SQLException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }
}
