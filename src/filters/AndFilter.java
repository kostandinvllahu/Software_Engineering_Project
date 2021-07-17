package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter {
	
	private final Filter childOne;
	private final Filter childTwo;
	
	public AndFilter(Filter childOne, Filter childTwo) {
		this.childOne = childOne;
		this.childTwo = childTwo;
	}

	@Override
	public boolean matches(Status s) {
		// TODO Auto-generated method stub
		if(childOne.matches(s) && childTwo.matches(s)) {
			return true;
		}else {
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
		return "(" + childOne.toString() + " and " + childTwo.toString() + ")";
	}

}
