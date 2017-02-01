package info.jawne.kalendarz.controllers.editors;

import java.beans.PropertyEditorSupport;
import java.time.Duration;

import info.jawne.kalendarz.parsers.DurationParser;

public class DurationEditor extends PropertyEditorSupport {

	public DurationEditor() {
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(DurationParser.parse(text));
	}

	@Override
	public String getAsText() { // Zamiana obiektu na ³añcuch znaków
		Duration value = (Duration) getValue();
		return value == null ? null
				: String.format("%s:%s", value.toMinutes(), value.getSeconds() - 60 * value.toMinutes());
	}
}