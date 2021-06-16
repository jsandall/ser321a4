package taskone;

import java.util.List;
import java.util.ArrayList;

class StringList {
    
    List<String> strings;
    
    public StringList() {
    	this.strings = new ArrayList<String>();
    }

    public void add(String str) {
        int pos = strings.indexOf(str);
        if (pos < 0) {
            strings.add(str);
        }
    }
    
    public String pop() {
    	String str;
    	if (size() == 0)
    		str = "null";
    	else
    		str = strings.remove(strings.size()-1);
    	System.out.println(str);
    	return str;
    }
    
    public List<String> switching(int i1, int i2) {
    	int indexes = strings.size() - 1;
    	if (i1 > indexes || i2 > indexes || i1 < 0 || i2 < 0)
    		return null;
    	else {
    		String temp = strings.get(i1);
    		strings.set(i1, strings.get(i2));
    		strings.set(i2, temp);
    	}
    	return strings;
    }

    public boolean contains(String str) {
        return strings.indexOf(str) >= 0;
    }

    public int size() {
        return strings.size();
    }

    public String toString() {
        return strings.toString();
    }
    
    public String get(int index) {
    	return strings.get(index);
    }
}