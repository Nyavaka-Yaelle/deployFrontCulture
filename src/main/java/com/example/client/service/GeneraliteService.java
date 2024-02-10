package com.example.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.client.service.connection.EConnection;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneraliteService {
    public String condition(String response, Object attr, String name)
    {
        if(attr != null) 
        {
            if(attr instanceof java.sql.Date)
            {
                if(response.length() == 0) response = response + " set ";
                else if(response.length() > 0) response = response + ", ";
                attr = "'"+(String) attr.toString()+"'";
                response = response + name+" = "+attr;
            }
            else if(attr instanceof String ) 
            {
                String tmp = (String)attr;
                System.out.println("condition: "+name+" "+tmp);
                if(!tmp.equals("") && !tmp.isEmpty())
                {
                    if(response.length() == 0) response = response + " set ";
                    else if(response.length() > 0) response = response + ", ";
                    attr = "'"+tmp+"'";
                    response = response + name+" = "+attr;

                }
            }
            else{
                if(response.length() == 0) response = response + " set ";
                else if(response.length() > 0) response = response + ", ";
                response = response + name+" = "+attr;
            }
        }
        return response;
    }
    public String filtrer(String response, Object attr, String name, boolean strict)
    {
        if(attr != null) 
        {
            String pre = (!strict)? "%":"";
            if(response.length() == 0) response = response + " where ";
            else if(response.length() > 0) response = response + " and ";
            if(attr instanceof String) attr = " ilike '"+pre+(String) attr.toString()+pre+"'";
            else if(attr instanceof java.sql.Date) attr = " = '"+(String) attr.toString()+"'";
            else if(attr instanceof Integer || attr instanceof Double) attr = " = "+(String) attr.toString();
            response = response + name+attr;
        }
        return response;
    }
    public String filtre(String response, Object attr, String name)
    {
        return filtrer(response, attr, name, false);
    }
    public String strictFiltre(String response, Object attr, String name)
    {
        return filtrer(response, attr, name, true); 
    }
}
