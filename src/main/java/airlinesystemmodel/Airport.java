/****************************************************************************
 * FILE: Airport.java
 * DSCRPT:
 ****************************************************************************/

package airlinesystemmodel;

public class Airport {
    private String name;





    /**
     * @param src_ 
     *
     */
    public Airport(String name_) {
    	name = name_;
    }





    /**
     * 
     */
    public final String getName() {
        return name;
    }





    /**
     * 
     */
    public final void setName(String name_) {
        name = name_;
    }





    /** 
     * (non-Javadoc)
     */
    @Override
    public String toString() {
        return "Airport [name=" + name + "]";
    }





}
