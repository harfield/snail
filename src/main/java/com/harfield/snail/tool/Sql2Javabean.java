package com.harfield.snail.tool;


import java.sql.*;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Sql2Javabean {

    private String driver = "com.mysql.jdbc.Driver"; //驱动
    private String url = "jdbc:mysql://192.168.199.101:3306/pgc?useUnicode=true&characterEncoding=UTF-8"; //数据库访问串
    private String userName = "dw"; //数据库用户名
    private String password = "123456"; //数据库密码
    private String tableName = "vanee_pgc_play_data"; //要生成jopo对象的表名,使用;进行分割
    private String tableMatchPattern = "%"; //数据库表名匹配模式
    private String matchPattern = "true"; //是否启用数据库表名匹配模式功能,启用后tableName属性不被使用


    public Sql2Javabean(){}

    public Sql2Javabean(boolean init){
        if(init){
            ResourceBundle rb = ResourceBundle.getBundle("db.properties");
            this.driver = rb.getString("driver");
            this.url = rb.getString("url");
            this.userName = rb.getString("userName");
            this.password = rb.getString("password");
            this.tableName = rb.getString("tableName");
            this.matchPattern = rb.getString("matchPattern");
            this.tableMatchPattern = rb.getString("tableMatchPattern");
        }

    }

    public Sql2Javabean(String baseName){
        ResourceBundle rb = ResourceBundle.getBundle(baseName);
        this.driver = rb.getString("driver");
        this.url = rb.getString("url");
        this.userName = rb.getString("userName");
        this.password = rb.getString("password");
        this.tableName = rb.getString("tableName");
        this.tableMatchPattern = rb.getString("tableMatchPattern");
        this.matchPattern = rb.getString("matchPattern");

    }

    public Sql2Javabean(String driver,String url,String userName,String password,String tableName,String tableMatchPattern,String matchPattern){
        this.driver = driver;
        this.password = password;
        this.userName = userName;
        this.url = url;
        this.tableName = tableName;
        this.tableMatchPattern = tableMatchPattern;
        this.matchPattern = matchPattern;
    }

    public void setDriver(String driver){
        this.driver = driver;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setTableName(String tableName){
        this.tableName = tableName;
    }

    public void setTableMatchPattern(String tableMatchPattern){
        this.tableMatchPattern=tableMatchPattern;
    }

    public void setMatchPattern(String matchPattern){
        this.matchPattern = matchPattern;
    }

    public String getDriver(){
        return this.driver;
    }

    public String getUrl(){
        return this.url;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }

    public String getTableName(){
        return this.tableName;
    }

    public String getTableMatchPattern(){
        return this.tableMatchPattern;
    }

    public String getMatchPattern(){
        return this.matchPattern;
    }
    public void init(int ObjectTypeOrCommonlyType){
        try{
            Class.forName(this.driver).newInstance();
            Connection conn = DriverManager.getConnection(this.url,this.userName, this.password);
            String [] tables = null;
            ArrayList tableal = new ArrayList(20);
            if("true".equals(this.matchPattern)){
                DatabaseMetaData dbmd = conn.getMetaData();
                ResultSet dbmdrs = dbmd.getTables(null, this.userName.toUpperCase(), this.tableMatchPattern, new String[]{"TABLE"});
                while(dbmdrs.next()){
                    tableal.add(dbmdrs.getString(3));
                }
                dbmdrs.close();
                if(tableal.size()==0){
                    dbmdrs = dbmd.getTables(null, this.userName.toLowerCase(), this.tableMatchPattern, new String[]{"TABLE"});
                    while(dbmdrs.next()){
                        tableal.add(dbmdrs.getString(3));
                    }
                    dbmdrs.close();
                }
                if(tableal.size()==0){
                    dbmdrs = dbmd.getTables(null, this.userName, this.tableMatchPattern, new String[]{"TABLE"});
                    while(dbmdrs.next()){
                        tableal.add(dbmdrs.getString(3));
                    }
                    dbmdrs.close();
                }
                tables = new String[tableal.size()];
                for(int ti = 0;ti<tableal.size() ;ti++)
                    tables[ti] = tableal.get(ti)+"";
            } else{
            tables = this.tableName.split(";");
            }
            String strType;
            String strName;
            String className;
            String[] nameSect;
            StringBuilder classNameBuilder = new StringBuilder();
            StringBuilder tstr1 = new StringBuilder();
            StringBuilder tstr2 = new StringBuilder();
            //
            StringBuilder stat = new StringBuilder();
            StringBuilder field = new StringBuilder();
            StringBuilder clean = new StringBuilder();
            //
            File file = new File("JavaBean");
           if(!file.exists())file.mkdir();
           if(!file.isDirectory())file.mkdir();
           for(int i=0;i<tables.length;i++){
               nameSect = tables[i].split("_");
               for(String ns : nameSect){
                   classNameBuilder.append(ns.substring(0,1).toUpperCase()+ns.substring(1).toLowerCase());
               }
               className = classNameBuilder.toString();
               classNameBuilder.delete(0,classNameBuilder.length());
               tstr1.append("import java.sql.*; ").append("\n");
               tstr1.append("import javax.sql.*; ").append("\n");
               tstr1.append("import java.io.*; ").append("\n");
               tstr1.append("public class "+className+" implements Serializable{ ").append("\n");
                try{
                    System.out.println(tables[i]);
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery("select * from " + tables[i] + " limit 1 ");
                    ResultSetMetaData rsd = rs.getMetaData();
                    int cc = rsd.getColumnCount();
                    for(int j=1;j<=cc;j++){
                        if(ObjectTypeOrCommonlyType == StaticVar.OBJECTTYPE){
                            strType = this.getObjectType(rsd.getColumnType(j));
                        }
                        else{
                            strType = this.getCommonlyType(j);
                        }
                        if(strType == null)continue;
                        String columnName = rsd.getColumnName(j);
                        field.append("\"").append(columnName).append("\"").append(",\n");
                        strName = toCamel(columnName);

                        stat.append("statement.setLong(i++,").append(strName).append(");").append("\n");
                        clean.append(strName).append(" = 0;").append("\n");

                        tstr1.append(" private "+strType+" "+strName+";").append("\n");
                        tstr2.append(" public void set"+strName.substring(0,1).toUpperCase()+strName.substring(1)+"("+strType+" "+strName+"){").append("\n");
                        tstr2.append("    this."+strName+" = "+strName+";").append("\n");
                        tstr2.append(" }").append("\n");
                        tstr2.append(" public "+strType+" get"+strName.substring(0,1).toUpperCase()+strName.substring(1)+"(){").append("\n");
                        tstr2.append("    return this."+strName+";").append("\n");
                        tstr2.append(" }").append("\n");
                    }
                    rs.close();
                    statement.close();
                }catch(Exception tableE) {
                    tableE.printStackTrace();
                }
                tstr2.append("} ").append("\n");
                tstr1.append(tstr2.toString()).append("\n");
                file = new File("JavaBean/"+className+".java");
                File file1 = new File("JavaBean/"+className+".txt");
                FileWriter fw = new FileWriter(file);
                fw.write(tstr1.toString());
                fw.flush();
                fw.close();
                tstr1.delete(0, tstr1.length());
                tstr2.delete(0, tstr2.length());

               FileWriter fw1 = new FileWriter(file1);
               fw1.write(stat.toString());
               fw1.write("\n");
               fw1.write(field.toString());
               fw1.write("\n");
               fw1.write(clean.toString());
               fw1.flush();
               fw1.close();
               stat.delete(0, stat.length());
               field.delete(0, field.length());
               clean.delete(0, clean.length());
            }
           conn.close();
        }catch(Exception driverE){
                driverE.printStackTrace();
        }
    }

    private String toCamel(String columnName) {
        char[] chars = columnName.toCharArray();
        boolean leading = false;
        for(int i=0;i<chars.length;i++){
            if(chars[i] == '_'){
                leading = true;
            }else{
                if(leading && chars[i]>='a' && chars[i]<='z'){
                    chars[i] = (char) (chars[i] - 32);
                  leading = false;
                }
            }
        }
        return String.valueOf(chars).replace("_","");
    }

    public String getObjectType(int type){
        switch(type){
        case Types.ARRAY:return null;
        case Types.BIGINT:return "Long";
        case Types.BINARY:return null;
        case Types.BIT:return "Byte";
        case Types.BLOB:return "Blob";
        case Types.BOOLEAN:return "Boolean";
        case Types.CHAR:return "String";
        case Types.CLOB:return "Clob";
        case Types.DATALINK:return null;
        case Types.DATE:return "Date";
        case Types.DECIMAL:return "Double";
        case Types.DISTINCT:return null;
        case Types.DOUBLE:return "Double";
        case Types.FLOAT:return "Float";
        case Types.INTEGER:return "Integer";
        case Types.NUMERIC:return "Integer";
        case Types.JAVA_OBJECT:return null;
        case Types.LONGVARBINARY:return null;
        case Types.LONGVARCHAR:return null;
        case Types.NULL:return null;
        case Types.OTHER:return null;
        case Types.REAL:return null;
        case Types.REF:return null;
        case Types.SMALLINT:return "Short";
        case Types.STRUCT:return null;
        case Types.TIME:return "Date";
        case Types.TIMESTAMP:return "Date";
        case Types.TINYINT:return "Short";
        case Types.VARBINARY:return null;
        case Types.VARCHAR:return "String";
        default :return null;
        }
        }

    public String getCommonlyType(int type){
        switch(type){
        case Types.ARRAY:return null;
        case Types.BIGINT:return "long";
        case Types.BINARY:return null;
        case Types.BIT:return "byte";
        case Types.BLOB:return "String";
        case Types.BOOLEAN:return "boolean";
        case Types.CHAR:return "String";
        case Types.CLOB:return "String";
        case Types.DATALINK:return null;
        case Types.DATE:return "Date";
        case Types.DECIMAL:return "double";
        case Types.DISTINCT:return null;
        case Types.DOUBLE:return "double";
        case Types.FLOAT:return "float";
        case Types.INTEGER:return "int";
        case Types.NUMERIC:return "int";
        case Types.JAVA_OBJECT:return null;
        case Types.LONGVARBINARY:return null;
        case Types.LONGVARCHAR:return null;
        case Types.NULL:return null;
        case Types.OTHER:return null;
        case Types.REAL:return null;
        case Types.REF:return null;
        case Types.SMALLINT:return "short";
        case Types.STRUCT:return null;
        case Types.TIME:return "Date";
        case Types.TIMESTAMP:return "Date";
        case Types.TINYINT:return "short";
        case Types.VARBINARY:return null;
        case Types.VARCHAR:return "String";
        default :return null;
        }
    }

    public static void main(String[] args) {
        Sql2Javabean d2j = new Sql2Javabean(false);
        System.out.println(d2j.getDriver());
        System.out.println(d2j.getUrl());
        System.out.println(d2j.getUserName());
        System.out.println(d2j.getPassword());
        System.out.println(d2j.getTableName());
        System.out.println(d2j.getTableMatchPattern());
        System.out.println(d2j.getMatchPattern());
        d2j.init(StaticVar.OBJECTTYPE);
        //d2j.init(StaticVar.COMMONLYTYPE);
        System.out.println("OK");
    }

    static class StaticVar {
        public static final int OBJECTTYPE = 0;
        public static final int COMMONLYTYPE = 1;
    }
}
