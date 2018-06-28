package com.example.myproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Value
{

    private Long   id;
    private String quote;

    public Value()
    {
    }

    public String getQuote()
    {
        return this.quote;
    }

    public void setQuote( String quote )
    {
        this.quote = quote;
    }

    @Override
    public String toString()
    {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }
}
