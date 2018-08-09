package poogleForms.model.form;

public enum TYPES_OF_QUESTION {
	MULTIPLE_CHOICES, TEXT_QUESTIONS;
	public TYPES_OF_QUESTION getType(Question q){
		if(q instanceof MultipleChoiceTypeQuestion){
			return MULTIPLE_CHOICES;
		}
		else if(q instanceof TextTypeQuestion){
			return TEXT_QUESTIONS;
		}
		else{
			return null;
		}
	}
}
