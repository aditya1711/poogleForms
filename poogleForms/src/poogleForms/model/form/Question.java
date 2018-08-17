package poogleForms.model.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public interface Question {
	public static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	public static  ObjectMapper mapper = new ObjectMapper();
	public long getID();
	public long getFormID();
	public String getPrompt();
	public TYPES_OF_QUESTION getType();
	public String getHandler();
	public String toJSONString() throws JsonProcessingException;
}
