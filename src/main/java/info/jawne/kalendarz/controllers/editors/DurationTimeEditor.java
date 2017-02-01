package info.jawne.kalendarz.controllers.editors;

import java.beans.PropertyEditorSupport;

import info.jawne.kalendarz.dao.DurationTimeDao;
import info.jawne.kalendarz.models.DurationTime;

public class DurationTimeEditor extends PropertyEditorSupport {

	DurationTimeDao durationtime_dao;

	public DurationTimeEditor(DurationTimeDao durationtime_dao) {
		this.durationtime_dao = durationtime_dao;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(durationtime_dao.findById(Integer.parseInt(text)));
	}

	@Override
	public String getAsText() { // Zamiana obiektu na ³añcuch znaków
		Object value = getValue();
		if (value == null)
			return null;
		else
			return new Integer(((DurationTime) value).getId()).toString();
	}
}