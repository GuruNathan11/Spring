package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fields")
public class field {

	@Id
	private String id;
	private String formName;
	private String fieldId;
	private String fieldLabel;
	private String fieldKeyLabel;
	private String organization;
	private String fieldPlaceholder;
	private List tableHeader;
	private String fieldSelect;
	private String fieldType;
	private boolean isMandatory;
	private boolean encryption;
	private int fieldWidth;
	private String fieldValue;
	private String[] fieldValue1;
	private boolean fieldValue2;
	private String[] fieldOptions1;
	private Options[] fieldOptions;
	private String createdAt;
	private String fieldBorderTop;
	private String fieldBorderBottom;
	private String fieldBorderLeft;
	private String fieldBorderRight;
	private String fieldBorderTopLeftRadius;
	private String fieldBorderBottomLeftRadius;
	private String fieldBorderTopRightRadius;
	private String fieldBorderBottomRightRadius;
	private String inputBorderTop;
	private String inputBorderBottom;
	private String inputBorderLeft;
	private String inputBorderRight;
	private String inputBorderTopLeftRadius;
	private String inputBorderBottomLeftRadius;
	private String inputBorderTopRightRadius;
	private String inputBorderBottomRightRadius;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getFormName() {
		return formName;
	}


	public void setFormName(String formName) {
		this.formName = formName;
	}


	public String getFieldId() {
		return fieldId;
	}


	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}


	public String getFieldLabel() {
		return fieldLabel;
	}


	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}


	public String getFieldKeyLabel() {
		return fieldKeyLabel;
	}


	public void setFieldKeyLabel(String fieldKeyLabel) {
		this.fieldKeyLabel = fieldKeyLabel;
	}


	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public String getFieldPlaceholder() {
		return fieldPlaceholder;
	}


	public void setFieldPlaceholder(String fieldPlaceholder) {
		this.fieldPlaceholder = fieldPlaceholder;
	}


	public List getTableHeader() {
		return tableHeader;
	}


	public void setTableHeader(List tableHeader) {
		this.tableHeader = tableHeader;
	}


	public String getFieldSelect() {
		return fieldSelect;
	}


	public void setFieldSelect(String fieldSelect) {
		this.fieldSelect = fieldSelect;
	}


	public String getFieldType() {
		return fieldType;
	}


	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}


	public boolean isMandatory() {
		return isMandatory;
	}


	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}


	public boolean isEncryption() {
		return encryption;
	}


	public void setEncryption(boolean encryption) {
		this.encryption = encryption;
	}


	public int getFieldWidth() {
		return fieldWidth;
	}


	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}


	public String getFieldValue() {
		return fieldValue;
	}


	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}


	public String[] getFieldValue1() {
		return fieldValue1;
	}


	public void setFieldValue1(String[] fieldValue1) {
		this.fieldValue1 = fieldValue1;
	}


	public boolean isFieldValue2() {
		return fieldValue2;
	}


	public void setFieldValue2(boolean fieldValue2) {
		this.fieldValue2 = fieldValue2;
	}


	public String[] getFieldOptions1() {
		return fieldOptions1;
	}


	public void setFieldOptions1(String[] fieldOptions1) {
		this.fieldOptions1 = fieldOptions1;
	}


	public Options[] getFieldOptions() {
		return fieldOptions;
	}


	public void setFieldOptions(Options[] fieldOptions) {
		this.fieldOptions = fieldOptions;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public String getFieldBorderTop() {
		return fieldBorderTop;
	}


	public void setFieldBorderTop(String fieldBorderTop) {
		this.fieldBorderTop = fieldBorderTop;
	}


	public String getFieldBorderBottom() {
		return fieldBorderBottom;
	}


	public void setFieldBorderBottom(String fieldBorderBottom) {
		this.fieldBorderBottom = fieldBorderBottom;
	}


	public String getFieldBorderLeft() {
		return fieldBorderLeft;
	}


	public void setFieldBorderLeft(String fieldBorderLeft) {
		this.fieldBorderLeft = fieldBorderLeft;
	}


	public String getFieldBorderRight() {
		return fieldBorderRight;
	}


	public void setFieldBorderRight(String fieldBorderRight) {
		this.fieldBorderRight = fieldBorderRight;
	}


	public String getFieldBorderTopLeftRadius() {
		return fieldBorderTopLeftRadius;
	}


	public void setFieldBorderTopLeftRadius(String fieldBorderTopLeftRadius) {
		this.fieldBorderTopLeftRadius = fieldBorderTopLeftRadius;
	}


	public String getFieldBorderBottomLeftRadius() {
		return fieldBorderBottomLeftRadius;
	}


	public void setFieldBorderBottomLeftRadius(String fieldBorderBottomLeftRadius) {
		this.fieldBorderBottomLeftRadius = fieldBorderBottomLeftRadius;
	}


	public String getFieldBorderTopRightRadius() {
		return fieldBorderTopRightRadius;
	}


	public void setFieldBorderTopRightRadius(String fieldBorderTopRightRadius) {
		this.fieldBorderTopRightRadius = fieldBorderTopRightRadius;
	}


	public String getFieldBorderBottomRightRadius() {
		return fieldBorderBottomRightRadius;
	}


	public void setFieldBorderBottomRightRadius(String fieldBorderBottomRightRadius) {
		this.fieldBorderBottomRightRadius = fieldBorderBottomRightRadius;
	}

	public String getInputBorderTop() {
		return inputBorderTop;
	}

	public void setInputBorderTop(String inputBorderTop) {
		this.inputBorderTop = inputBorderTop;
	}

	public String getInputBorderBottom() {
		return inputBorderBottom;
	}

	public void setInputBorderBottom(String inputBorderBottom) {
		this.inputBorderBottom = inputBorderBottom;
	}

	public String getInputBorderLeft() {
		return inputBorderLeft;
	}

	public void setInputBorderLeft(String inputBorderLeft) {
		this.inputBorderLeft = inputBorderLeft;
	}

	public String getInputBorderRight() {
		return inputBorderRight;
	}

	public void setInputBorderRight(String inputBorderRight) {
		this.inputBorderRight = inputBorderRight;
	}

	public String getInputBorderTopLeftRadius() {
		return inputBorderTopLeftRadius;
	}

	public void setInputBorderTopLeftRadius(String inputBorderTopLeftRadius) {
		this.inputBorderTopLeftRadius = inputBorderTopLeftRadius;
	}

	public String getInputBorderBottomLeftRadius() {
		return inputBorderBottomLeftRadius;
	}

	public void setInputBorderBottomLeftRadius(String inputBorderBottomLeftRadius) {
		this.inputBorderBottomLeftRadius = inputBorderBottomLeftRadius;
	}

	public String getInputBorderTopRightRadius() {
		return inputBorderTopRightRadius;
	}

	public void setInputBorderTopRightRadius(String inputBorderTopRightRadius) {
		this.inputBorderTopRightRadius = inputBorderTopRightRadius;
	}

	public String getInputBorderBottomRightRadius() {
		return inputBorderBottomRightRadius;
	}

	public void setInputBorderBottomRightRadius(String inputBorderBottomRightRadius) {
		this.inputBorderBottomRightRadius = inputBorderBottomRightRadius;
	}


	public static class Options {

		private String optionLabel;
		private boolean optionValue;
//		private String optionValue1;
		private String optionSize;

		public String getOptionLabel() {
			return optionLabel;
		}

		public void setOptionLabel(String optionLabel) {
			this.optionLabel = optionLabel;
		}

		public boolean isOptionValue() {
			return optionValue;
		}

		public void setOptionValue(boolean optionValue) {
			this.optionValue = optionValue;
		}

		public String getOptionSize() {
			return optionSize;
		}

		public void setOptionSize(String optionSize) {
			this.optionSize = optionSize;
		}

	}
	

	public void update(field field) {

		this.setFormName(field.getFormName());
		this.setFieldId(field.getFieldId());
		this.setFieldLabel(field.getFieldLabel());
		this.setFieldKeyLabel(field.getFieldKeyLabel());
		this.setOrganization(field.getOrganization());
		this.setFieldPlaceholder(field.getFieldPlaceholder());
		this.setTableHeader(field.getTableHeader());
		this.setFieldSelect(field.getFieldSelect());
		this.setMandatory(field.isMandatory());
		this.setEncryption(field.isEncryption());
		this.setFieldWidth(field.getFieldWidth());
		this.setFieldValue(field.getFieldValue());
		this.setFieldValue1(field.getFieldValue1());
		this.setFieldValue2(field.isFieldValue2());
		this.setFieldOptions1(field.getFieldOptions1());
		this.setFieldOptions(field.getFieldOptions());
		this.setFieldBorderTop(field.getFieldBorderTop());
		this.setFieldBorderBottom(field.getFieldBorderBottom());
		this.setFieldBorderLeft(field.getFieldBorderLeft());
		this.setFieldBorderRight(field.getFieldBorderRight());
		this.setFieldBorderTopLeftRadius(field.getFieldBorderTopLeftRadius());
		this.setFieldBorderBottomLeftRadius(field.getFieldBorderBottomLeftRadius());
		this.setFieldBorderTopRightRadius(field.getFieldBorderTopRightRadius());
		this.setFieldBorderBottomRightRadius(field.getFieldBorderBottomRightRadius());
		this.setInputBorderTop(field.getInputBorderTop());
		this.setInputBorderBottom(field.getInputBorderBottom());
		this.setInputBorderLeft(field.getInputBorderLeft());
		this.setInputBorderRight(field.getInputBorderRight());
		this.setInputBorderTopLeftRadius(field.getInputBorderTopLeftRadius());
		this.setInputBorderBottomLeftRadius(field.getInputBorderBottomLeftRadius());
		this.setInputBorderTopRightRadius(field.getInputBorderTopRightRadius());
		this.setInputBorderBottomRightRadius(field.getInputBorderBottomRightRadius());

	}
}