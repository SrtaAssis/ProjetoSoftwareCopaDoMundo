package com.companyJAF.sistemaclassificacao.exception;

public class ExceptionEncontrado extends RuntimeException
{
	private static final long serialVersionUID = 85323452217206856L;

	public ExceptionEncontrado()
	{
		super();
	}

	public ExceptionEncontrado(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionEncontrado(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ExceptionEncontrado(String message)
	{
		super(message);
	}

	public ExceptionEncontrado(Throwable cause)
	{
		super(cause);
	}
}