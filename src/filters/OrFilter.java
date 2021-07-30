package filters;

import twitter4j.Status;
import java.util.ArrayList;
import java.util.List;

public class OrFilter implements Filter {
	private final Filter childOne;
	private final Filter childTwo;
	
	public OrFilter(Filter childOne, Filter childTwo) {
		this.childOne = childOne;
		this.childTwo = childTwo;
	}

	@Override
	public boolean matches(Status status) {
		// TODO Auto-generated method stub
		if(childOne.matches(status) || childTwo.matches(status)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<String> terms() {
		// TODO Auto-generated method stub
		List<String> terms = new ArrayList<>();
		terms.add(childOne.toString());
		terms.add(childTwo.toString());
		return terms;
	}
	
	public String toString() {
		return "(" + childOne.toString() + " or " + childTwo.toString() + ")";
	}
	
}
