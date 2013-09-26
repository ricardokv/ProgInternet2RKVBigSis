package util;

import java.util.Date;

public final class Parametro {

    private Object valor;

    public Parametro()
    {
        valor = null;
    }
    
    public Parametro(Object valor)
    {
        this.valor = valor;
        
    }

    public Parametro setString(String valor)
    {
        this.valor = valor;
        return this;
    }

    public Parametro setInteger(Integer valor)
    {
        this.valor = valor;
        return this;
    }
    
    public Parametro setDate(Date valor)
    {
        this.valor = valor;
        return this;
    }
    
    public Parametro setFloat(Float valor)
    {
        this.valor = valor;
        return this;
    }

    public Parametro setBoolean(Boolean valor)
    {
        this.valor = valor;
        return this;
    }
    
    public String getString()
    {
        return (String) valor;
    }

    public Integer getInteger()
    {
        return (Integer) valor;
    }
    
    public Date getDate()
    {
        return (Date) valor;
    }
    
    public Float getFloat()
    {
        return (Float) valor;
    }
    
    public Boolean getBoolean()
    {
        return (Boolean) valor;
    }

    public boolean isString()
    {
        return valor instanceof String;
    }

    public boolean isInteger()
    {
        return valor instanceof Integer;
    }
    
    public boolean isDate()
    {
        return valor instanceof Date;
    }
    
    public boolean isFloat()
    {
        return valor instanceof Float;
    }
    
    public boolean isBoolean()
    {
        return valor instanceof Boolean;
    }
}
