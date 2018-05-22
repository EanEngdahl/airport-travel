/****************************************************************************
 * FILE: Airport.java
 * DSCRPT:
 ****************************************************************************/

package airlinesystemmodel;

public class Airport {
    private String _name;





    /**
     * @param src_ 
     *
     */
    public Airport(String src_) {}





    /**
     * 
     */
    public final String getName() {
        return _name;
    }





    /**
     * 
     */
    public final void setName(String name_) {
        _name = name_;
    }





    /** 
     * (non-Javadoc)
     */
    @Override
    public String toString() {
        return "Airport [_name=" + _name + "]";
    }





}
