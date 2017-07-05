package com.saresource.aws.beans;

public class InformationSchemaTables {
    public String getTable_catalog() {
        return table_catalog;
    }

    public void setTable_catalog(String table_catalog) {
        this.table_catalog = table_catalog;
    }

    public String getTable_schema() {
        return table_schema;
    }

    public void setTable_schema(String table_schema) {
        this.table_schema = table_schema;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_type() {
        return table_type;
    }

    public void setTable_type(String table_type) {
        this.table_type = table_type;
    }

    public String getSelf_referencing_column_name() {
        return self_referencing_column_name;
    }

    public void setSelf_referencing_column_name(String self_referencing_column_name) {
        this.self_referencing_column_name = self_referencing_column_name;
    }

    public String getReference_generation() {
        return reference_generation;
    }

    public void setReference_generation(String reference_generation) {
        this.reference_generation = reference_generation;
    }

    public String getUser_defined_type_catalog() {
        return user_defined_type_catalog;
    }

    public void setUser_defined_type_catalog(String user_defined_type_catalog) {
        this.user_defined_type_catalog = user_defined_type_catalog;
    }

    public String getUser_defined_type_schema() {
        return user_defined_type_schema;
    }

    public void setUser_defined_type_schema(String user_defined_type_schema) {
        this.user_defined_type_schema = user_defined_type_schema;
    }

    public String getUser_defined_name() {
        return user_defined_name;
    }

    public void setUser_defined_name(String user_defined_name) {
        this.user_defined_name = user_defined_name;
    }

    private String table_catalog;
    private String table_schema;
    private String table_name;
    private String table_type;
    private String self_referencing_column_name;
    private String reference_generation;
    private String user_defined_type_catalog;
    private String user_defined_type_schema;
    private String user_defined_name;
}
