package poogleForms.model.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import poogleForms.model.misc.IDAble;
import poogleForms.model.misc.JSONConvertible;

public interface Question extends IDAble<Long>, JSONConvertible{
	
	public static  ObjectMapper mapper = new ObjectMapper();
	public long getFormID();
	public String getPrompt();
	public TYPES_OF_QUESTION getType();
	public String getHandler();
	
}
