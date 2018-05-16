package airporttravel;

import java.util.ArrayList;
import java.util.Iterator;

public class EmployeeList extends ArrayList<Employee>{

	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean add(Employee e_)  {
		if (isIn(e_.getId()))
			return false;
		return super.add(e_);
	}
	
	public Employee remove(int id_) {
		int _index = indexOf(id_);
		Employee _tmp = super.get(_index);
		
		if (_index == -1) {
			return null;
		}
		else {
			super.remove(_index);
			return _tmp;
		}
	}
	
	public int indexOf(int id_) {
		Iterator<Employee> _itr = this.iterator();
		Employee _tmp;
		int _count = 0;
		
		while (_itr.hasNext()) {
			_tmp = _itr.next();
			if (_tmp.getId() == id_) {
				return _count;
			}
			_count++;
		}
		return -1;
	}
		
	public boolean isIn(int id_) {
		if(indexOf(id_) == -1) {
			return false;
		}
		return true;
	}
}
