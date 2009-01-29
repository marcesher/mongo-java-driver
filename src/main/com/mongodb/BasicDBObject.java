// BasicDBObject.java

package com.mongodb;

import java.util.*;

import com.mongodb.util.*;

public class BasicDBObject extends HashMap<String,Object> implements DBObject {
    
    public BasicDBObject(){
    }

    public Object removeField( String key ){
        return remove( key );
    }

    public boolean isPartialObject(){
        return _isPartialObject;
    }

    public boolean containsKey( String key ){
        return super.containsKey( (Object)key );
    }

    public Object get( String key ){
        return super.get( (Object)key );
    }

    public int getInt( String key ){
        return ((Number)get( key )).intValue();
    }

    public int getInt( String key , int def ){
        Object foo = get( key );
        if ( foo == null )
            return def;
        return ((Number)foo).intValue();
    }
    
    public String getString( String key ){
        Object foo = get( key );
        if ( foo == null )
            return null;
        return foo.toString();
    }

    public Object put( String key , Object val ){
        _keys.add( key );
        return super.put( key , val );
    }

    public Set<String> keySet(){
        assert( _keys.size() == size() );
        return _keys;
    }
    
    public String toString(){
        return JSON.serialize( this );
    }

    public void markAsPartialObject(){
        _isPartialObject = true;
    }

    private final Set<String> _keys = new OrderedSet<String>();
    private boolean _isPartialObject = false;
}
