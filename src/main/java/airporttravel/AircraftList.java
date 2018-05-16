package airporttravel;

import java.util.ArrayList;
import java.util.Iterator;

public class AircraftList extends ArrayList<Aircraft>{

	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(Aircraft a_) 
	{
		if (isIn(a_.getName()))
			return false;
		return super.add(a_);
	}
	
	public Aircraft remove(String name_)
	{
		int _index = indexOf(name_);
		Aircraft _tmp = super.get(_index);
		
		if (_index == -1)
			return null;
		else
		{
			super.remove(_index);
			return _tmp;
		}
	}
	
	public int indexOf(String name_)
	{
		Iterator<Aircraft> _itr = this.iterator();
		Aircraft _tmp;
		int _count = 0;
		
		while (_itr.hasNext())
		{
			_tmp = _itr.next();
			if (_tmp.getName() == name_)
			{
				return _count;
			}
			_count++;
		}
		return -1;
	}
		
	public boolean isIn(String name_)
	{
		if(indexOf(name_) == -1) {
			return false;
		}
	
		return true;
	}
}
