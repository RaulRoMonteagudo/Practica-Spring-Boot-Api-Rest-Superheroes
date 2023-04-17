package com.rrm.superhero.exception;

public class SuperheroNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -3490925740895788709L;

	  public SuperheroNotFoundException(String text) {
		    super(text);
		  }
}
