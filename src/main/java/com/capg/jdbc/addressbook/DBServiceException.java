package com.capg.jdbc.addressbook;

public class DBServiceException extends Exception{
	
	DBServiceExceptionType exceptionType;
	public DBServiceException(String message, DBServiceExceptionType exceptionType) {
		super(message);
		this.exceptionType=exceptionType;
	}
}

enum DBServiceExceptionType{
	SQL_EXCEPTION , CLASS_NOTFOUND_EXCEPTION
}
