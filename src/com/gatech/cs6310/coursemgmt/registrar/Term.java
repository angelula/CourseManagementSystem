package com.gatech.cs6310.coursemgmt.registrar;

public enum Term {
		FALL("Fall"),
		WINTER("Winter"),
		SPRING("Spring"),
		SUMMER("Summer");
		
	    private String value;

		Term(String value){
	        this.value = value;
	    }
		
		public String toString() {
			return this.value;
		}
		
		public Term getNextTerm() {
			Term ret = null;
			if (value.equals("Fall")){
				ret = Term.WINTER;
			} else if (value.equals("Winter")) {
				ret = Term.SPRING;
			} else if (value.equals("Spring")) {
				ret = Term.SUMMER;
			} else if (value.equals("Summer")) {
				ret = Term.FALL;
			}
			return ret;
		}
}
